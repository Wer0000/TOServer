package me.theentropyshard.toserver.network.command.control.server;

import me.theentropyshard.toserver.network.protocol.ICodec;
import me.theentropyshard.toserver.network.protocol.IProtocol;
import me.theentropyshard.toserver.network.protocol.ProtocolBuffer;
import me.theentropyshard.toserver.network.protocol.codec.info.TypeCodecInfo;

import java.nio.ByteBuffer;

public class HashResponseCommandCodec implements ICodec {
    private static final int HASH_BYTE_LENGTH = 32;

    private ICodec booleanCodec;
    private ICodec byteCodec;
    private ICodec longCodec;

    public void init(IProtocol protocol) {
        this.byteCodec = protocol.getCodec(Byte.TYPE, false);
        this.longCodec = protocol.getCodec(Long.TYPE, false);
        this.booleanCodec = protocol.getCodec(new TypeCodecInfo(Boolean.TYPE, false));
    }

    public Object decode(ProtocolBuffer buffer) {
        throw new UnsupportedOperationException();
    }

    public void encode(ProtocolBuffer buffer, Object o) {
        HashResponseCommand command = (HashResponseCommand) o;
        ByteBuffer hash = command.getHash();
        for(int i = 0; i < HashResponseCommandCodec.HASH_BYTE_LENGTH; i++) {
            this.byteCodec.encode(buffer, hash.get());
        }
        this.booleanCodec.encode(buffer, command.isChannelProtectionEnabled());
//        this.byteCodec.encode(buffer, (byte) 0x20);
//        this.longCodec.encode(buffer, 0x5F691CCL);
    }
}
