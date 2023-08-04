package me.theentropyshard.toserver.network.command.control.client;

import me.theentropyshard.toserver.network.command.ControlCommand;

public class LogCommand extends ControlCommand {
    private String channel;
    private int level;
    private String message;

    public LogCommand(int level, String channel, String message) {
        super(ControlCommand.CL_LOG, "Log");
        this.level = level;
        this.channel = channel;
        this.message = message;
    }

    public final String getChannel() {
        return this.channel;
    }

    public final int getLevel() {
        return this.level;
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setChannel(String var1) {
        this.channel = var1;
    }

    public final void setLevel(int var1) {
        this.level = var1;
    }

    public final void setMessage(String var1) {
        this.message = var1;
    }
}
