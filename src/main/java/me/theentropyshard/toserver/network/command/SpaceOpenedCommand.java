package me.theentropyshard.toserver.network.command;

import me.theentropyshard.toserver.network.ControlChannelContext;

import java.nio.ByteBuffer;

public class SpaceOpenedCommand implements IConnectionInitCommand, IClientControlCommand {
    private final ByteBuffer hash;
    private final long spaceId;

    public SpaceOpenedCommand(long spaceId, ByteBuffer hash) {
        this.spaceId = spaceId;
        this.hash = hash;
    }

    @Override
    public void execute(ControlChannelContext context) {

    }

    public final ByteBuffer getHash() {
        return this.hash;
    }

    public final long getSpaceId() {
        return this.spaceId;
    }
}
