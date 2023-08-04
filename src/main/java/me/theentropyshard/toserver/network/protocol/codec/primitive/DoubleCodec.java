package me.theentropyshard.toserver.network.protocol.codec.primitive;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

public class DoubleCodec implements ICodec {
    public DoubleCodec() {

    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        return buffer.getBuffer().getDouble();
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        buffer.getBuffer().putDouble((double) o);
    }
}
