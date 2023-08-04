package me.theentropyshard.toserver.network.command.control.server;

import me.theentropyshard.toserver.network.command.ControlCommand;

import java.nio.ByteBuffer;

public class HashResponseCommand extends ControlCommand {
    private boolean channelProtectionEnabled;
    private ByteBuffer hash;

    public HashResponseCommand(ByteBuffer hash, boolean channelProtectionEnabled) {
        super(ControlCommand.SV_HASH_RESPONSE, "Hash response");
        this.hash = hash;
        this.channelProtectionEnabled = channelProtectionEnabled;
    }

    public boolean isChannelProtectionEnabled() {
        return this.channelProtectionEnabled;
    }

    public void setChannelProtectionEnabled(boolean channelProtectionEnabled) {
        this.channelProtectionEnabled = channelProtectionEnabled;
    }

    public ByteBuffer getHash() {
        return this.hash;
    }

    public void setHash(ByteBuffer hash) {
        this.hash = hash;
    }
}
