package me.theentropyshard.toserver.network.protocol;

import me.theentropyshard.toserver.network.protocol.codec.info.TypeNameCodecInfo;

import java.nio.ByteBuffer;

public interface IProtocol {
    ICodec getCodec(ICodecInfo codecInfo);

    ICodec getCodec(TypeNameCodecInfo typeNameCodecInfo);

    ICodec getCodec(Class clazz, boolean optional);

    void registerCodec(ICodecInfo codecInfo, ICodec codec);

    void registerCodecForType(Class clazz, ICodec codec);

    boolean unwrapPacket(ByteBuffer data, ProtocolBuffer buffer);

    void wrapPacket(ByteBuffer data, ProtocolBuffer buffer);
}
