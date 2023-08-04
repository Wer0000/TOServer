package me.theentropyshard.toserver.network.protocol.codec.complex;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

public class LongCodec implements ICodec {
    public LongCodec() {

    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        return buffer.getBuffer().getLong();
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        buffer.getBuffer().putLong((long) o);
    }
}
