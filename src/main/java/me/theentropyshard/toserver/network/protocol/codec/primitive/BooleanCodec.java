package me.theentropyshard.toserver.network.protocol.codec.primitive;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

public class BooleanCodec implements ICodec {
    public BooleanCodec() {

    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        return buffer.getBuffer().get() > 0;
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        buffer.getBuffer().put((byte) ((boolean) o ? 1 : 0));
    }
}
