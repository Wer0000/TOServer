package me.theentropyshard.toserver.network.protocol.protection;

import java.nio.ByteBuffer;

public class XorBasedProtectionContext implements IProtectionContext {
    private final int[] clientSequence;
    private int clientSelector;

    private final int[] serverSequence;
    private int serverSelector;

    private int initialSeed;

    public XorBasedProtectionContext(ByteBuffer hash, long spaceId) {
        this.clientSequence = new int[8];
        this.serverSequence = new int[8];

        int position = hash.position();
        int limit = hash.limit();

        int n2 = (int) spaceId ^ (int) (spaceId >> 32);
        for(int i = 0; i < 8; i++) {
            n2 ^= hash.getInt();
        }

        this.initialSeed = n2 ^ (this.initialSeed
                ^ (n2 >> 24)
                ^ (n2 >> 16)
                ^ (n2 >> 8)
                & 0xFF
        ) & 0xFF;

        this.reset();
        hash.position(position);
        hash.limit(limit);
    }

    @Override
    public void encrypt(ByteBuffer in, ByteBuffer out) {
        while(in.hasRemaining()) {
            int b = in.get();
            out.put((byte) (this.clientSequence[this.clientSelector] ^ b));
            this.clientSequence[this.clientSelector] = b;
            this.clientSelector = b & 0x07 ^ this.clientSelector;
        }
    }

    @Override
    public void decrypt(ByteBuffer in, ByteBuffer out) {
        while(in.hasRemaining()) {
            int b = in.get();
            this.serverSequence[this.serverSelector] = b ^ this.serverSequence[this.serverSelector];
            out.put((byte) this.serverSequence[this.serverSelector]);
            this.serverSelector = this.serverSelector ^ (this.serverSequence[this.serverSelector] & 0x07);
        }
    }

    @Override
    public void reset() {
        for(int i = 0; i < 8; i++) {
            int n = i << 3;

            this.serverSequence[i] = this.initialSeed ^ n;
            this.clientSequence[i] = this.initialSeed ^ n;

            // Emulate client (C2S): clientSequence[i] = clientSequence[i] ^ 0x57
            this.serverSequence[i] = this.serverSequence[i] ^ 0x57;
        }

        this.serverSelector = 0;
        this.clientSelector = 0;
    }
}
