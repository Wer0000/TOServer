package me.theentropyshard.toserver.network.command;

public class ControlCommand {
    public static final byte CL_HASH_REQUEST = 1;
    public static final byte SV_HASH_RESPONSE = 2;

    public static final byte CL_LOG = 32;
    public static final byte SV_OPEN_SPACE = 32;

    public byte id;
    public String name;

    public ControlCommand(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    public final byte getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final void setId(byte id) {
        this.id = id;
    }

    public final void setName(String name) {
        this.name = name;
    }
}
