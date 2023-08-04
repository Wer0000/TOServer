package me.theentropyshard.toserver.network.protocol.codec.primitive;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

public class ByteCodec implements ICodec {
    public ByteCodec() {

    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        return buffer.getBuffer().get();
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        buffer.getBuffer().put((byte) o);
    }
}
