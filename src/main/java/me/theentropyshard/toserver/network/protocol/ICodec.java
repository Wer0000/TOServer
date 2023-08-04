package me.theentropyshard.toserver.network.protocol;

public interface ICodec {
    void init(IProtocol protocol);

    Object decode(ProtocolBuffer buffer);

    void encode(ProtocolBuffer buffer, Object o);
}
