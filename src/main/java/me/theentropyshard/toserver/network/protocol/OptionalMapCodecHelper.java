package me.theentropyshard.toserver.network.protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public enum OptionalMapCodecHelper {
    ;

    public static final int INPLACE_MASK_1_BYTES = 32;
    public static final int INPLACE_MASK_2_BYTES = 64;
    public static final int INPLACE_MASK_3_BYTES = 96;
    public static final int INPLACE_MASK_FLAG = 128;

    public static final int MASK_LENGTH_1_BYTE = 128;
    public static final int MASK_LENGTH_2_BYTES_FLAG = 64;
    public static final int MASK_LENGTH_3_BYTE = 12582912;

    /*public static void decodeNullMap(ByteBuffer data, OptionalMap map) {
        int loc5 = 0;
        int loc6 = 0;
        int loc7 = 0;
        int loc8 = 0;
        int loc9 = 0;
        int loc3 = map.getSize();
        //var loc4:ByteArray = param1.getMap();
        byte[] loc4 = map.getMap();
        if(loc3 <= 5) {
            data.put((byte) ((loc4[0] & 255) >>> 3));
        } else if(loc3 <= 13) {
            data.put((byte) (((loc4[0] & 255) >>> 3) + INPLACE_MASK_1_BYTES));
            data.put((byte) (((loc4[1] & 255) >>> 3) + (loc4[0] << 5)));
        } else if(loc3 <= 21) {
            data.put((byte) (((loc4[0] & 255) >>> 3) + INPLACE_MASK_2_BYTES));
            data.put((byte) (((loc4[1] & 255) >>> 3) + (loc4[0] << 5)));
            data.put((byte) (((loc4[2] & 255) >>> 3) + (loc4[1] << 5)));
        } else if(loc3 <= 29) {
            data.put((byte) (((loc4[0] & 255) >>> 3) + INPLACE_MASK_3_BYTES));
            data.put((byte) (((loc4[1] & 255) >>> 3) + (loc4[0] << 5)));
            data.put((byte) (((loc4[2] & 255) >>> 3) + (loc4[1] << 5)));
            data.put((byte) (((loc4[3] & 255) >>> 3) + (loc4[2] << 5)));
        } else {
            int loc51 = (loc3 >>> 3) + ((loc3 & 7) == 0 ? 0 : 1);
            if(loc3 <= 504) {
                loc5 = loc51;
                loc6 = (loc5 & 255) + MASK_LENGTH_1_BYTE;
                data.put((byte) loc6);
                data.put(loc4, 0, loc5);
            } else {
                if(loc3 > 33554432) {
                    throw new Error("NullMap overflow");
                }
                loc5 = loc51;
                loc7 = loc5 + MASK_LENGTH_3_BYTE;
                loc6 = (loc7 & 16711680) >>> 16;
                loc8 = (loc7 & 65280) >>> 8;
                loc9 = loc7 & 255;
                data.put((byte) loc6);
                data.put((byte) loc8);
                data.put((byte) loc9);
                data.put(loc4, 0, loc5);
            }
        }
    }*/

    public static void decodeNullMap(ByteBuffer var1, OptionalMap var2) {
        byte[] var7 = var2.getMap();
        byte var6 = var1.get();
        int var3 = INPLACE_MASK_FLAG;
        boolean var4 = true;
        boolean var8;
        var8 = (var3 & var6) != 0;

        int var5;
        int var9;
        if(var8) {
            var5 = var6 & 63;
            if((var6 & MASK_LENGTH_2_BYTES_FLAG) != 0) {
                var8 = var4;
            } else {
                var8 = false;
            }

            var9 = var5;
            if(var8) {
                var9 = (var5 << 16) + ((var1.get() & 255) << 8) + (var1.get() & 255);
            }

            var1.get(var7, 0, var9);
            var2.init(var9 << 3, var7);
        } else {
            var3 = var6 << 3;
            var9 = (var6 & 96) >>> 5;
            if(var9 != 0) {
                byte var10;
                var10 = var1.get();
                if(var9 != 1) {
                    var5 = var1.get();
                    if(var9 != 2) {
                        var6 = var1.get();
                        var7[0] = (byte) (var3 + ((var10 & 255) >>> 5));
                        var7[1] = (byte) ((var10 << 3) + ((var5 & 255) >>> 5));
                        var7[2] = (byte) ((var5 << 3) + ((var6 & 255) >>> 5));
                        var7[3] = (byte) (var6 << 3);
                        var2.init(29, var7);
                    } else {
                        var7[0] = (byte) (var3 + ((var10 & 255) >>> 5));
                        var7[1] = (byte) ((var10 << 3) + ((var5 & 255) >>> 5));
                        var7[2] = (byte) (var5 << 3);
                        var2.init(21, var7);
                    }
                } else {
                    var7[0] = (byte) (var3 + ((var10 & 255) >>> 5));
                    var7[1] = (byte) (var10 << 3);
                    var2.init(13, var7);
                }
            } else {
                var7[0] = (byte) var3;
                var2.init(5, var7);
            }
        }
    }

    /*public static void encodeNullMap(OptionalMap map, ByteBuffer data) {
        int loc4 = 0;
        int loc7 = 0;
        boolean loc8 = false;
        int loc9 = 0;
        int loc10 = 0;
        int loc11 = 0;
        int loc12 = 0;
        //var loc3:ByteArray = new ByteArray();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int loc5 = data.get();
        boolean loc6 = (loc5 & INPLACE_MASK_FLAG) != 0;
        if(loc6) {
            loc7 = loc5 & 63;
            loc8 = (loc5 & MASK_LENGTH_2_BYTES_FLAG) != 0;
            if(loc8) {
                loc10 = data.get();
                loc11 = data.get();
                loc4 = (loc7 << 16) + ((loc10 & 255) << 8) + (loc11 & 255);
            } else {
                loc4 = loc7;
            }
            byte[] bufTmp = new byte[loc4];
            data.get(bufTmp, 0, loc4);

            try {
                baos.write(bufTmp);
            } catch (IOException e) {
                e.printStackTrace();
            }

            loc9 = loc4 << 3;
            map.init(loc9, bufTmp);

            return;
        }
        loc7 = loc5 << 3;
        loc4 = (loc5 & 96) >> 5;

        switch(loc4) {
            case 0:
                baos.write(loc7);
                map.init(5, baos.toByteArray());
                return;
            case 1:
                loc10 = data.get();
                baos.write(loc7 + ((loc10 & 255) >>> 5));
                baos.write(loc10 << 3);
                map.init(13, baos.toByteArray());
                return;
            case 2:
                loc10 = data.get();
                loc11 = data.get();
                baos.write(loc7 + ((loc10 & 255) >>> 5));
                baos.write((loc10 << 3) + ((loc11 & 255) >>> 5));
                baos.write(loc11 << 3);
                map.init(21, baos.toByteArray());
                return;
            case 3:
                loc10 = data.get();
                loc11 = data.get();
                loc12 = data.get();
                baos.write(loc7 + ((loc10 & 255) >>> 5));
                baos.write((loc10 << 3) + ((loc11 & 255) >>> 5));
                baos.write((loc11 << 3) + ((loc12 & 255) >>> 5));
                baos.write(loc12 << 3);
                map.init(29, baos.toByteArray());
                return;
            default:
                throw new Error("Invalid OptionalMap");
        }
    }*/

    public static void encodeNullMap(OptionalMap var1, ByteBuffer var2) {
        int var8 = var1.getSize();
        byte var7 = 0;
        if(var8 <= 5) {
            var2.put((byte) ((var1.getByte(0) & 255) >>> 3));
        } else {
            int var6 = 1;
            if(var8 <= 13) {
                var2.put((byte) (((var1.getByte(0) & 255) >>> 3) + INPLACE_MASK_1_BYTES));
                var2.put((byte) (((var1.getByte(1) & 255) >>> 3) + (var1.getByte(0) << 5)));
            } else if(var8 <= 21) {
                var2.put((byte) (((var1.getByte(0) & 255) >>> 3) + INPLACE_MASK_2_BYTES));
                var2.put((byte) (((var1.getByte(1) & 255) >>> 3) + (var1.getByte(0) << 5)));
                var2.put((byte) (((var1.getByte(2) & 255) >>> 3) + (var1.getByte(1) << 5)));
            } else if(var8 <= 29) {
                var2.put((byte) (((var1.getByte(0) & 255) >>> 3) + INPLACE_MASK_3_BYTES));
                var2.put((byte) (((var1.getByte(1) & 255) >>> 3) + (var1.getByte(0) << 5)));
                var2.put((byte) (((var1.getByte(2) & 255) >>> 3) + (var1.getByte(1) << 5)));
                var2.put((byte) (((var1.getByte(3) & 255) >>> 3) + (var1.getByte(2) << 5)));
            } else if(var8 <= 504) {
                if((var8 & 7) == 0) {
                    var6 = 0;
                }

                var6 += var8 >>> 3;
                var2.put((byte) ((var6 & 255) + MASK_LENGTH_1_BYTE));
                var2.put(var1.getMap(), 0, var6);
            } else {
                if(var8 > 33554432) {
                    throw new RuntimeException("NullMap overflow");
                }

                byte var9;
                if((var8 & 7) == 0) {
                    var9 = var7;
                } else {
                    var9 = 1;
                }

                var6 = (var8 >>> 3) + var9 + MASK_LENGTH_3_BYTE;
                byte var5 = (byte) ((16711680 & var6) >>> 16);
                byte var4 = (byte) (('\uff00' & var6) >>> 8);
                byte var3 = (byte) (var6 & 255);
                var2.put(var5);
                var2.put(var4);
                var2.put(var3);
                var2.put(var1.getMap());
            }
        }
    }
}
