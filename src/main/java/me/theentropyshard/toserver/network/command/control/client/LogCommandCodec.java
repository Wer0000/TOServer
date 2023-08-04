package me.theentropyshard.toserver.network.command.control.client;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

public class LogCommandCodec implements ICodec {
    public ICodec intCodec;
    public ICodec stringCodec;

    @Override
    public void init(IProtocol var1) {
        this.intCodec = var1.getCodec(Integer.TYPE, false);
        this.stringCodec = var1.getCodec(Integer.TYPE, false);
    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        int level = (int) this.intCodec.decode(buffer);
        String channel = (String) this.stringCodec.decode(buffer);
        String name = (String) this.stringCodec.decode(buffer);

        return new LogCommand(level, channel, name);
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        throw new UnsupportedOperationException();
    }
}
