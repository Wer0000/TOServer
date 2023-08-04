package me.theentropyshard.toserver.network.protocol.codec.complex;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.LengthCodecHelper;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;

import java.util.HashMap;
import java.util.Map;

public class MapCodec implements ICodec {
    private final ICodec keyCodec;
    private final ICodec valueCodec;

    public MapCodec(ICodec keyCodec, ICodec valueCodec) {
        this.keyCodec = keyCodec;
        this.valueCodec = valueCodec;
    }

    @Override
    public void init(IProtocol protocol) {

    }

    @Override
    public Object decode(ProtocolBuffer buffer) {
        Map map = new HashMap();
        int length = LengthCodecHelper.decodeLength(buffer);
        for(int i = 0; i < length; i++) {
            map.put(this.keyCodec.decode(buffer), this.valueCodec.decode(buffer));
        }
        return map;
    }

    @Override
    public void encode(ProtocolBuffer buffer, Object o) {
        Map map = (Map) o;
        LengthCodecHelper.encodeLength(buffer, map.size());
        map.entrySet().forEach(obj -> {
            Object key = ((Map.Entry) obj).getKey();
            Object value = ((Map.Entry) obj).getValue();
            this.keyCodec.encode(buffer, key);
            this.valueCodec.encode(buffer, value);
        });
    }

    public ICodec getKeyCodec() {
        return this.keyCodec;
    }

    public ICodec getValueCodec() {
        return this.valueCodec;
    }
}
