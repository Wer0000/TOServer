package me.theentropyshard.toserver.network;

import me.theentropyshard.toserver.network.protocol.IProtocol;

import java.nio.ByteBuffer;

public class ControlChannelContext {
    private boolean channelProtectionEnabled;
    private ByteBuffer hash;
    private IProtocol spaceProtocol;

    public final boolean getChannelProtectionEnabled() {
        return this.channelProtectionEnabled;
    }

    public final ByteBuffer getHash() {
        return this.hash;
    }

    public final IProtocol getSpaceProtocol() {
        return this.spaceProtocol;
    }

    public final void setChannelProtectionEnabled(boolean enabled) {
        this.channelProtectionEnabled = enabled;
    }

    public final void setHash(ByteBuffer buffer) {
        this.hash = buffer;
    }

    public final void setSpaceProtocol(IProtocol protocol) {
        this.spaceProtocol = protocol;
    }
}
