package me.theentropyshard.toserver.network.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.theentropyshard.toserver.network.ControlChannelContext;
import me.theentropyshard.toserver.network.command.SpaceCommand;
import me.theentropyshard.toserver.network.command.SpaceOpenedCommand;
import me.theentropyshard.toserver.network.command.control.client.HashRequestCommand;
import me.theentropyshard.toserver.network.command.control.server.HashResponseCommand;
import me.theentropyshard.toserver.network.command.control.server.OpenSpaceCommand;
import me.theentropyshard.toserver.network.protocol.codec.SpaceRootCodec;
import me.theentropyshard.toserver.network.protocol.protection.XorBasedProtectionContext;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Random;

public class CommandHandler extends ChannelInboundHandlerAdapter {
    private final CommandDecoder commandDecoder;
    private final CommandEncoder commandEncoder;

    public CommandHandler(CommandDecoder commandDecoder, CommandEncoder commandEncoder) {
        this.commandDecoder = commandDecoder;
        this.commandEncoder = commandEncoder;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object o) {
        if(o instanceof HashRequestCommand) {
            List<String> parameterNames = ((HashRequestCommand) o).getParameterNames();
            List<String> parameterValues = ((HashRequestCommand) o).getParameterValues();

            System.out.println(parameterNames);
            System.out.println(parameterValues);

            byte[] hash = new byte[32];
            new Random().nextBytes(hash);

            ctx.writeAndFlush(new HashResponseCommand(ByteBuffer.wrap(hash), true));
            ctx.writeAndFlush(new OpenSpaceCommand(0x5F691CCL));
        } else if(o instanceof SpaceOpenedCommand) {
            SpaceOpenedCommand command = (SpaceOpenedCommand) o;
            XorBasedProtectionContext context = new XorBasedProtectionContext(command.getHash(), command.getSpaceId());
            this.commandDecoder.setProtectionContext(context);
            this.commandEncoder.setProtectionContext(context);
            SpaceRootCodec codec = new SpaceRootCodec();
            this.commandDecoder.setCommandCodec(codec);
            this.commandEncoder.setCommandCodec(codec);
        } else if(o instanceof SpaceCommand) {
            System.out.println("Got space command: " + o);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
