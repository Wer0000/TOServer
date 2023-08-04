package me.theentropyshard.toserver.network.command.control.client;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;
import me.theentropyshard.toserver.network.protocol.codec.info.ListCodecInfo;
import me.theentropyshard.toserver.network.protocol.codec.info.TypeCodecInfo;

import java.util.ArrayList;

public class HashRequestCommandCodec implements ICodec {
    private ICodec arrayCodec;

    public HashRequestCommandCodec() {

    }

    public void init(IProtocol var1) {
        this.arrayCodec = var1.getCodec(new ListCodecInfo(new TypeCodecInfo(String.class, false), false));
    }

    public Object decode(ProtocolBuffer buffer) {
        ArrayList parameterNames = (ArrayList) this.arrayCodec.decode(buffer);
        ArrayList parameterValues = (ArrayList) this.arrayCodec.decode(buffer);
        return new HashRequestCommand(parameterNames, parameterValues);
    }

    public void encode(ProtocolBuffer buffer, Object o) {
        throw new UnsupportedOperationException();
    }
}
