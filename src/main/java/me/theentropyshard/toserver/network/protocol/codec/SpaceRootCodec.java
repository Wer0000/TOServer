package me.theentropyshard.toserver.network.protocol.codec;

import me.theentropyshard.toserver.network.command.SpaceCommand;
import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.OptionalMap;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;
import me.theentropyshard.toserver.network.protocol.codec.info.TypeCodecInfo;

import java.nio.ByteBuffer;

public class SpaceRootCodec implements ICodec {
    private ICodec longCodec;

    public SpaceRootCodec() {

    }

    @Override
    public void init(IProtocol protocol) {
        this.longCodec = protocol.getCodec(new TypeCodecInfo(Long.TYPE, false));
    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        ProtocolBuffer protocolBuffer = new ProtocolBuffer(ByteBuffer.allocate(16384), new OptionalMap());

        for(int i = 0; i < buffer.getOptionalMap().getSize(); i++) {
            protocolBuffer.getOptionalMap().addBit(buffer.getOptionalMap().get());
        }

        while(buffer.getBuffer().hasRemaining()) {
            protocolBuffer.getBuffer().put(buffer.getBuffer().get());
        }

        long methodId = (long) this.longCodec.decode(buffer);
        long objectId = (long) this.longCodec.decode(buffer);

        return new SpaceCommand(objectId, methodId, protocolBuffer);
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        // TODO
    }
}
