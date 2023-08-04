package me.theentropyshard.toserver.network.protocol;

public enum LengthCodecHelper {
    ;

    public static void encodeLength(ProtocolBuffer protocolBuffer, int length) {
        int i;
        if(length < 0) {
            throw new IllegalArgumentException("Length is incorrect (" + length + ")");
        }
        if(length < 128) {
            protocolBuffer.getBuffer().put((byte) (length & 127));
        } else if(length < 16384) {
            i = (length & 16383) + 32768;
            protocolBuffer.getBuffer().put((byte) ((i & 65280) >> 8));
            protocolBuffer.getBuffer().put((byte) (i & 255));
        } else {
            if(length >= 4194304) {
                throw new IllegalArgumentException("Length is incorrect (" + length + ")");
            }
            i = (length & 4194303) + 12582912;
            protocolBuffer.getBuffer().put((byte) ((i & 16711680) >> 16));
            protocolBuffer.getBuffer().put((byte) ((i & 65280) >> 8));
            protocolBuffer.getBuffer().put((byte) (i & 255));
        }
    }

    public static int decodeLength(ProtocolBuffer protocolBuffer) {
        int i = protocolBuffer.getBuffer().get();
        if((i & 128) == 0) {
            return i;
        }
        int loc4 = protocolBuffer.getBuffer().get();
        if((i & 64) == 0) {
            return ((i & 63) << 8) + (loc4 & 255);
        }
        int loc6 = protocolBuffer.getBuffer().get();
        return ((i & 63) << 16) + ((loc4 & 255) << 8) + (loc6 & 255);
    }
}
