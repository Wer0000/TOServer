package me.theentropyshard.toserver.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.OptionalMap;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;
import me.theentropyshard.toserver.network.protocol.codec.ControlRootCodec;
import me.theentropyshard.toserver.network.protocol.impl.Protocol;
import me.theentropyshard.toserver.network.protocol.protection.IProtectionContext;
import me.theentropyshard.toserver.network.protocol.protection.PrimitiveProtectionContext;

import java.nio.ByteBuffer;
import java.util.List;

public class CommandDecoder extends ByteToMessageDecoder {
    private IProtectionContext protectionContext;
    private ICodec commandCodec;

    public CommandDecoder() {
        this.protectionContext = PrimitiveProtectionContext.INSTANCE;
        this.commandCodec = new ControlRootCodec();
        this.commandCodec.init(Protocol.INSTANCE);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        ByteBuffer data = ByteBuffer.wrap(bytes);

        // Decrypt data
        ByteBuffer decryptedData = ByteBuffer.allocate(16384);
        this.protectionContext.decrypt(data, decryptedData);

        // Unwrap packet
        ProtocolBuffer protocolBuffer = new ProtocolBuffer(ByteBuffer.allocate(16384), new OptionalMap());
        if(Protocol.INSTANCE.unwrapPacket(decryptedData, protocolBuffer)) {
            System.out.println("Unwrapped packet");
        } else {
            System.out.println("Could not unwrap packet");
        }

        // Decode command
        Object decodedCommand = this.commandCodec.decode(protocolBuffer);

        if(decodedCommand != null) {
            list.add(decodedCommand);
        }
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
