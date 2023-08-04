package me.theentropyshard.toserver.network.protocol;

import java.nio.ByteBuffer;

public enum PacketHelper {
    ;

    public static final int BIG_LENGTH_FLAG = 128;
    public static final int LONG_SIZE_DELIMITER = 16384;

    private static int determineSize(ByteBuffer var1, byte var2) {
        boolean var3;
        var3 = (BIG_LENGTH_FLAG & var2) != 0;

        int var7;
        if(var3) {
            byte var6 = var1.get();
            byte var5 = var1.get();
            var7 = var1.get();
            var7 &= 255;
            var2 = (byte) (((var2 ^ BIG_LENGTH_FLAG) << 24) + ((var6 & 255) << 16) + ((var5 & 255) << 8));
        } else {
            byte var8 = var1.get();
            var2 = (byte) ((var2 & 63) << 8);
            var7 = var8 & 255;
        }

        return var2 + var7;
    }

    private static int determineSizeLength(byte var1) {
        return (var1 & BIG_LENGTH_FLAG) != 0 ? 4 : 2;
    }

    private static boolean isDataEnough(ByteBuffer var1) {
        int var2 = var1.position();
        int var3 = var1.remaining();
        boolean var5 = false;
        if(var3 >= 2) {
            boolean var8 = false;

            label57:
            {
                int var4;
                try {
                    var8 = true;
                    var3 = PacketHelper.determineSizeLength(var1.get());
                    if(var1.remaining() < var3 - 1) {
                        var8 = false;
                        break label57;
                    }

                    var1.position(var2);
                    var4 = PacketHelper.determineSize(var1, var1.get());
                    var3 = var1.remaining();
                    var8 = false;
                } finally {
                    if(var8) {
                        var1.position(var2);
                    }
                }

                if(var3 >= var4) {
                    var5 = true;
                }

                var1.position(var2);
                return var5;
            }

            var1.position(var2);
        }
        return false;
    }

    private static boolean isLongSize(int var1) {
        boolean var2;
        var2 = var1 >= LONG_SIZE_DELIMITER;

        return var2;
    }

    private static void makeHeader(int var1, ByteBuffer var2, int var3) {
        if(PacketHelper.isLongSize(var1)) {
            var2.position(var3);
            var2.put((byte) (var1 >>> 24 | BIG_LENGTH_FLAG));
            var2.put((byte) ((16711680 & var1) >>> 16));
            var2.put((byte) ((var1 & '\uff00') >>> 8));
        } else {
            var3 += 2;
            var2.position(var3);
            var2.put((byte) ((var1 & '\uff00') >> 8));
        }
        var2.put((byte) (var1 & 255));
        var2.position(var3);

    }

    public static boolean unwrapPacket(ByteBuffer byteBuffer, ProtocolBuffer buffer) {
        if(!PacketHelper.isDataEnough(byteBuffer)) {
            return false;
        } else {
            int var4 = PacketHelper.determineSize(byteBuffer, byteBuffer.get());
            int var3 = byteBuffer.limit();
            var4 += byteBuffer.position();
            OptionalMapCodecHelper.decodeNullMap(byteBuffer, buffer.getOptionalMap());
            //byteBuffer.flip();
            if(var4 <= var3) {
                byteBuffer.limit(var4);
                buffer.getBuffer().put(byteBuffer);
                buffer.getBuffer().flip();
                byteBuffer.limit(var3);
                return true;
            } else {
                StringBuilder var5 = new StringBuilder();
                var5.append("Internal error [new limit] ");
                var5.append(var4);
                var5.append(" > ");
                var5.append(var3);
                throw new RuntimeException(var5.toString());
            }
        }
    }

    public static void wrapPacket(ByteBuffer var1, ProtocolBuffer var2) {
        int var4 = var1.position();
        var1.position(var4 + 4);
        OptionalMapCodecHelper.encodeNullMap(var2.getOptionalMap(), var1);
        var1.put(var2.getBuffer());
        int var3 = var1.position();
        PacketHelper.makeHeader(var1.position() - var4 - 4, var1, var4);
        var1.limit(var3);
    }
}
