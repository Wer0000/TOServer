package me.theentropyshard.toserver.network.protocol.protection;

import java.nio.ByteBuffer;

public interface IProtectionContext {
    void encrypt(ByteBuffer in, ByteBuffer out);

    void decrypt(ByteBuffer in, ByteBuffer out);

    void reset();
}
