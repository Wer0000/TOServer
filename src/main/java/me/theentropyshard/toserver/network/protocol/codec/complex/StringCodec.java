package me.theentropyshard.toserver.network.protocol.codec.complex;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.LengthCodecHelper;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

import java.nio.charset.StandardCharsets;

public class StringCodec implements ICodec {
    private static final int CACHE_SIZE = 10240;
    private final byte[] cachedBytes;

    public StringCodec() {
        this.cachedBytes = new byte[StringCodec.CACHE_SIZE];
    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        int length = LengthCodecHelper.decodeLength(buffer);
        byte[] buf = new byte[length];
        buffer.getBuffer().get(buf, 0, length);
        return new String(buf, 0, length, StandardCharsets.UTF_8);
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        String s = (String) o;
        byte[] data = s.getBytes(StandardCharsets.UTF_8);
        LengthCodecHelper.encodeLength(buffer, s.length());
        buffer.getBuffer().put(data);
    }
}
