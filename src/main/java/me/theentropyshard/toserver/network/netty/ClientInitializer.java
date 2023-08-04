package me.theentropyshard.toserver.network.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    public ClientInitializer() {

    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new ClientHandler());
        CommandDecoder commandDecoder = new CommandDecoder();
        pipeline.addLast(commandDecoder);
        CommandEncoder commandEncoder = new CommandEncoder();
        pipeline.addLast(commandEncoder);
        pipeline.addLast(new CommandHandler(commandDecoder, commandEncoder));
    }
}
