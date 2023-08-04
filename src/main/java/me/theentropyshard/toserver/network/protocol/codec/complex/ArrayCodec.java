package me.theentropyshard.toserver.network.protocol.codec.complex;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.LengthCodecHelper;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

public class ArrayCodec implements ICodec {
    private final ICodec elementCodec;

    public ArrayCodec(ICodec elementCodec) {
        this.elementCodec = elementCodec;
    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        int length = LengthCodecHelper.decodeLength(buffer);
        Object[] data = new Object[length];
        for(int i = 0; i < length; i++) {
            data[i] = this.elementCodec.decode(buffer);
        }

        return data;
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        Object[] data = (Object[]) o;
        LengthCodecHelper.encodeLength(buffer, data.length);
        for(Object obj : data) {
            this.elementCodec.encode(buffer, obj);
        }
    }

    public ICodec getElementCodec() {
        return this.elementCodec;
    }
}
