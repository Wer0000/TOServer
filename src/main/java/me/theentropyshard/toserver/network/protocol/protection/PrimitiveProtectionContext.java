package me.theentropyshard.toserver.network.protocol.protection;

import java.nio.ByteBuffer;

public class PrimitiveProtectionContext implements IProtectionContext {
    public static final PrimitiveProtectionContext INSTANCE = new PrimitiveProtectionContext();

    public PrimitiveProtectionContext() {

    }

    @Override
    public void encrypt(ByteBuffer in, ByteBuffer out) {
        out.put(in).flip();
    }

    @Override
    public void decrypt(ByteBuffer in, ByteBuffer out) {
        out.put(in).flip();
    }

    @Override
    public void reset() {

    }
}
