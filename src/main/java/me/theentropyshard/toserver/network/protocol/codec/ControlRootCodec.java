package me.theentropyshard.toserver.network.protocol.codec;

import me.theentropyshard.toserver.network.command.ControlCommand;
import me.theentropyshard.toserver.network.command.SpaceCommand;
import me.theentropyshard.toserver.network.command.SpaceOpenedCommand;
import me.theentropyshard.toserver.network.command.SpaceOpenedCommandCodec;
import me.theentropyshard.toserver.network.command.control.client.HashRequestCommandCodec;
import me.theentropyshard.toserver.network.command.control.client.LogCommandCodec;
import me.theentropyshard.toserver.network.command.control.server.HashResponseCommandCodec;
import me.theentropyshard.toserver.network.command.control.server.OpenSpaceCommandCodec;
import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;
import me.theentropyshard.toserver.network.protocol.codec.info.TypeCodecInfo;

import java.util.Arrays;
import java.util.HashMap;

public class ControlRootCodec implements ICodec {
    public ICodec byteCodec;
    public HashMap clientCommandCodecs = new HashMap();
    public HashMap serverCommandCodecs = new HashMap();

    public Object decode(ProtocolBuffer var1) {
        ICodec var2 = this.byteCodec;
        Byte var4 = (Byte) var2.decode(var1);
        System.out.println("Command id: " + var4);
        System.out.println("Command data: " + Arrays.toString(var1.getBuffer().array()));
        var2 = (ICodec) this.clientCommandCodecs.get(var4);
        if(var2 == null) {
            return null;
        } else {
            return var2.decode(var1);
        }
    }

    public void encode(ProtocolBuffer var1, Object var2) {

        if(var2 != null) {
            ControlCommand var5 = (ControlCommand) var2;
            ICodec var3 = this.byteCodec;

            var3.encode(var1, var5.getId());
            if(this.serverCommandCodecs.containsKey(var5.getId())) {
                var3 = (ICodec) this.serverCommandCodecs.get(var5.getId());
                if(var3 != null) {
                    var3.encode(var1, var2);
                }

            } else {
                String var6 = "Command with id " +
                        var5.getId() +
                        " unknown";
                throw new RuntimeException(var6);
            }
        } else {
            throw new NullPointerException("null cannot be cast to non-null type alternativa.client.network.command.ControlCommand");
        }
    }

    public void init(IProtocol protocol) {
        this.byteCodec = protocol.getCodec(Byte.TYPE, false);
        protocol.registerCodec(new TypeCodecInfo(SpaceOpenedCommand.class, false), new SpaceOpenedCommandCodec());
        HashResponseCommandCodec var2 = new HashResponseCommandCodec();
        var2.init(protocol);
        this.serverCommandCodecs.put(ControlCommand.SV_HASH_RESPONSE, var2);
        OpenSpaceCommandCodec var3 = new OpenSpaceCommandCodec();
        var3.init(protocol);
        this.serverCommandCodecs.put(ControlCommand.SV_OPEN_SPACE, var3);
        HashRequestCommandCodec var4 = new HashRequestCommandCodec();
        var4.init(protocol);
        this.clientCommandCodecs.put(ControlCommand.CL_HASH_REQUEST, var4);
        LogCommandCodec var5 = new LogCommandCodec();
        var5.init(protocol);
        this.clientCommandCodecs.put(ControlCommand.CL_LOG, var5);
        SpaceOpenedCommandCodec var6 = new SpaceOpenedCommandCodec();
        var6.init(protocol);
        this.clientCommandCodecs.put(var6, SpaceCommand.PRODUCE_HASH);
    }
}
