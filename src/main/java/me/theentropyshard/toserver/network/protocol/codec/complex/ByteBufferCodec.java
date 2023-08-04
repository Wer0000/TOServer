package me.theentropyshard.toserver.network.protocol.codec.complex;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.LengthCodecHelper;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

import java.nio.ByteBuffer;

public class ByteBufferCodec implements ICodec {
    public ByteBufferCodec() {

    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        byte[] data = new byte[LengthCodecHelper.decodeLength(buffer)];
        buffer.getBuffer().get(data);
        return data;
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        ByteBuffer buf = (ByteBuffer) o;
        LengthCodecHelper.encodeLength(buffer, buf.limit());
        buffer.getBuffer().put(buf);
    }
}
