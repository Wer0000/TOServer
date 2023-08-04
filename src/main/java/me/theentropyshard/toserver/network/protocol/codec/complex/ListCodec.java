package me.theentropyshard.toserver.network.protocol.codec.complex;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.LengthCodecHelper;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

import java.util.ArrayList;
import java.util.List;

public class ListCodec implements ICodec {
    private final ICodec elementCodec;

    public ListCodec(ICodec elementCodec) {
        this.elementCodec = elementCodec;
    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        List list = new ArrayList();
        int length = LengthCodecHelper.decodeLength(buffer);
        for(int i = 0; i < length; i++) {
            list.add(this.elementCodec.decode(buffer));
        }
        return list;
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        List list = (List) o;
        LengthCodecHelper.encodeLength(buffer, list.size());
        for(Object value : list) {
            this.elementCodec.encode(buffer, value);
        }
    }

    public ICodec getElementCodec() {
        return this.elementCodec;
    }
}
