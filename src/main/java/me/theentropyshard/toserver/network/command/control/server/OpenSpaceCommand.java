package me.theentropyshard.toserver.network.command.control.server;

import me.theentropyshard.toserver.network.command.ControlCommand;

public class OpenSpaceCommand extends ControlCommand {
    private final long spaceId;

    public OpenSpaceCommand(long spaceId) {
        super(ControlCommand.SV_OPEN_SPACE, "Open space");
        this.spaceId = spaceId;
    }

    /*public void execute(ControlChannelContext context) {
        Object var2;
        if(context.getChannelProtectionEnabled()) {
            var2 = new XorBasedProtectionContext(context.getHash(), this.spaceId);
        } else {
            var2 = PrimitiveProtectionContext.INSTANCE;
        }

        //TODO
        //Space var3 = new Space(this.spaceId, context.getSpaceProtocol(), (ProtectionContext)var2, context.getHash(), null.INSTANCE);
    }*/

    public long getSpaceId() {
        return this.spaceId;
    }
}
