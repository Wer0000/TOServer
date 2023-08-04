package me.theentropyshard.toserver.network.protocol.codec;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

public class OptionalCodecDecorator implements ICodec {
    private final ICodec codec;

    public OptionalCodecDecorator(ICodec codec) {
        this.codec = codec;
    }

    @Override
    public void init(IProtocol protocol) {
        this.codec.init(protocol);
    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        if (buffer.getOptionalMap().get()) {
            return null;
        } else {
            return this.codec.decode(buffer);
        }
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        if (o == null) {
            buffer.getOptionalMap().addBit(true);
        } else {
            buffer.getOptionalMap().addBit(false);
            this.codec.encode(buffer, o);
        }
    }
}
