package me.theentropyshard.toserver.network.command;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

import java.nio.ByteBuffer;

public class SpaceOpenedCommandCodec implements ICodec {

    private ICodec idCodec;

    public void init(IProtocol protocol) {
        this.idCodec = protocol.getCodec(Long.TYPE, false);
    }

    public Object decode(ProtocolBuffer buffer) {
        buffer.getBuffer().get();
        byte[] hash = new byte[32];
        buffer.getBuffer().get(hash);
        long spaceId = (long) this.idCodec.decode(buffer);
        return new SpaceOpenedCommand(spaceId, ByteBuffer.wrap(hash));
    }

    public void encode(ProtocolBuffer buffer, Object o) {
        throw new UnsupportedOperationException();
    }
}
