package me.theentropyshard.toserver.network.protocol.codec.complex;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

public class EnumCodec implements ICodec {
    private final Class type;

    public EnumCodec(Class type) {
        this.type = type;
    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        return null;
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {

    }

    public Class getType() {
        return this.type;
    }
}
