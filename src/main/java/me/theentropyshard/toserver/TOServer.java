package me.theentropyshard.toserver;

import me.theentropyshard.toserver.network.netty.NettyServer;

public final class TOServer {
    public TOServer() {
        new NettyServer();
    }
}
