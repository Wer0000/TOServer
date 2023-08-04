package me.theentropyshard.toserver.network.protocol.codec.complex;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

import java.nio.charset.StandardCharsets;

public class SimpleStringCodec implements ICodec {
    private final ICodec byteCodec;

    public SimpleStringCodec(IProtocol protocol) {
        this.byteCodec = protocol.getCodec(Byte.class, false);
    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        byte b = (byte) this.byteCodec.decode(buffer);
        byte[] data = new byte[b];
        buffer.getBuffer().get(data);
        return new String(data, 0, b, StandardCharsets.UTF_8);
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {

    }
}
