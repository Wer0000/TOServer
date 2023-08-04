package me.theentropyshard.toserver.network.protocol.codec.complex;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.LengthCodecHelper;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

import java.util.HashSet;
import java.util.Set;

public class SetCodec implements ICodec {
    private final ICodec elementCodec;

    public SetCodec(ICodec elementCodec) {
        this.elementCodec = elementCodec;
    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        Set set = new HashSet();
        int length = LengthCodecHelper.decodeLength(buffer);
        for(int i = 0; i < length; i++) {
            set.add(this.elementCodec.decode(buffer));
        }
        return set;
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        Set set = (Set) o;
        LengthCodecHelper.encodeLength(buffer, set.size());
        for(Object obj : set) {
            this.elementCodec.encode(buffer, obj);
        }
    }

    public ICodec getElementCodec() {
        return this.elementCodec;
    }
}
