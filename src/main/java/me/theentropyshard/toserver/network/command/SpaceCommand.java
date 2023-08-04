package me.theentropyshard.toserver.network.command;

import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

public class SpaceCommand {
    public static final byte PRODUCE_HASH = 3; // Or is that CL_SPACE_OPENED ?
    public static final byte CL_SPACE_CONNECTED = 4;

    private final long methodId;
    private final long objectId;
    private final ProtocolBuffer protocolBuffer;

    public SpaceCommand(long objectId, long methodId, ProtocolBuffer var5) {
        this.objectId = objectId;
        this.methodId = methodId;
        this.protocolBuffer = var5;
    }

    public final long getMethodId() {
        return this.methodId;
    }

    public final long getObjectId() {
        return this.objectId;
    }

    public final ProtocolBuffer getProtocolBuffer() {
        return this.protocolBuffer;
    }
}
