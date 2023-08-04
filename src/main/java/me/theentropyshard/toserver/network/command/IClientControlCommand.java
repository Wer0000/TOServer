package me.theentropyshard.toserver.network.command;

import me.theentropyshard.toserver.network.ControlChannelContext;

public interface IClientControlCommand {
    void execute(ControlChannelContext context);
}
