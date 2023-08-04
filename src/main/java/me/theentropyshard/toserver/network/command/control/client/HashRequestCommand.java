package me.theentropyshard.toserver.network.command.control.client;

import me.theentropyshard.toserver.network.ControlChannelContext;
import me.theentropyshard.toserver.network.command.ControlCommand;
import me.theentropyshard.toserver.network.command.IClientControlCommand;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HashRequestCommand extends ControlCommand implements IClientControlCommand {
    private final List<String> parameterNames;
    private final List<String> parameterValues;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public HashRequestCommand(ArrayList parameterNames, ArrayList parameterValues) {
        super(ControlCommand.CL_HASH_REQUEST, "Hash request");
        this.parameterNames = parameterNames;
        this.parameterValues = parameterValues;
    }

    @Override
    public void execute(ControlChannelContext context) {
        byte[] hash = new byte[32];
        new Random().nextBytes(hash);

        context.setHash(ByteBuffer.wrap(hash));
        context.setChannelProtectionEnabled(true);

        //PlayerSocket.encoder.sendCommand(new HashResponseCommand(ByteBuffer.wrap(hash), true));
        //PlayerSocket.encoder.sendCommand(new OpenSpaceCommand(0x5F691CCL));
    }

    public final List<String> getParameterNames() {
        return this.parameterNames;
    }

    public final List<String> getParameterValues() {
        return this.parameterValues;
    }
}
