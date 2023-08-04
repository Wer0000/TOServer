package me.theentropyshard.toserver.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.theentropyshard.toserver.network.command.IConnectionInitCommand;
import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.OptionalMap;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;
import me.theentropyshard.toserver.network.protocol.codec.ControlRootCodec;
import me.theentropyshard.toserver.network.protocol.impl.Protocol;
import me.theentropyshard.toserver.network.protocol.protection.IProtectionContext;
import me.theentropyshard.toserver.network.protocol.protection.PrimitiveProtectionContext;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class CommandEncoder extends MessageToByteEncoder<Object> {
    private IProtectionContext protectionContext;
    private ICodec commandCodec;

    public CommandEncoder() {
        this.protectionContext = PrimitiveProtectionContext.INSTANCE;
        this.commandCodec = new ControlRootCodec();
        this.commandCodec.init(Protocol.INSTANCE);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object command, ByteBuf buf) throws Exception {
        // Encode command
        ProtocolBuffer protocolBuffer = new ProtocolBuffer(ByteBuffer.allocate(4096), new OptionalMap());
        this.commandCodec.encode(protocolBuffer, command);
        protocolBuffer.flip();

        // Wrap packet
        ByteBuffer wrappedPacket = ByteBuffer.allocate(4096);
        Protocol.INSTANCE.wrapPacket(wrappedPacket, protocolBuffer);
        protocolBuffer.clear();

        // Encrypt data
        IProtectionContext protectionContext = command instanceof IConnectionInitCommand ?
                PrimitiveProtectionContext.INSTANCE : this.protectionContext;
        ByteBuffer encryptedCommand = ByteBuffer.allocate(4096);
        protectionContext.encrypt(wrappedPacket, encryptedCommand);

        wrappedPacket.clear();
        encryptedCommand.flip();

        byte[] cmd = encryptedCommand.array();
        cmd = Arrays.copyOfRange(cmd, 0, cmd[1] + 2);

        buf.writeBytes(cmd);
    }

    public IProtectionContext getProtectionContext() {
        return this.protectionContext;
    }

    public void setProtectionContext(IProtectionContext protectionContext) {
        this.protectionContext = protectionContext;
    }

    public ICodec getCommandCodec() {
        return this.commandCodec;
    }

    public void setCommandCodec(ICodec commandCodec) {
        this.commandCodec = commandCodec;
    }
}
