package me.theentropyshard.toserver.network.command.control.server;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

public class OpenSpaceCommandCodec implements ICodec {
    private ICodec idCodec;

    @Override
    public void init(IProtocol protocol) {
        this.idCodec = protocol.getCodec(Long.TYPE, false);
    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        OpenSpaceCommand command = (OpenSpaceCommand) o;
        this.idCodec.encode(buffer, command.getSpaceId());
    }
}
