package me.theentropyshard.toserver.network.protocol;

public final class OptionalMap {
    public static final int DEFAULT_SIZE = 1000;
    public byte[] map;
    public int readPosition;
    public int size;

    public OptionalMap() {
        this(0, null, 3);
    }

    public OptionalMap(int var1, byte[] var2) {
        this.size = var1;
        this.map = var2;
    }

    public OptionalMap(int var1, byte[] var2, int var3) {
        this((var3 & 1) != 0 ? 0 : var1, (var3 & 2) != 0 ? new byte[OptionalMap.DEFAULT_SIZE] : var2);
    }

    private boolean getBit(int var1) {
        return (1 << (var1 & 7 ^ 7) & this.map[var1 >> 3]) != 0;
    }

    private void setBit(int pos, boolean bit) {
        int var3 = pos >> 3;
        int var4 = pos & 7 ^ 7;
        byte[] var5 = this.map;
        byte var6 = var5[var3];
        if(bit) {
            pos = 1 << var4 | var6;
        } else {
            pos = (1 << var4 ^ 255) & var6;
        }

        var5[var3] = (byte) pos;
    }

    public void addBit(boolean bit) {
        this.setBit(this.size, bit);
        ++this.size;
    }

    public void clear() {
        this.size = 0;
        this.readPosition = 0;
    }

    public void flip() {
        if(this.size == 0) {
            this.map[0] = 0;
        }

        this.readPosition = 0;
    }

    public boolean get() {
        int pos = this.readPosition;
        if(pos < this.size) {
            boolean bit = this.getBit(pos);
            ++this.readPosition;
            return bit;
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds: " + this.readPosition);
            //return false;
        }
    }

    public int getByte(int i) {
        return this.map[i];
    }

    public byte[] getMap() {
        return this.map;
    }

    public int getReadPosition() {
        return this.readPosition;
    }

    public int getSize() {
        return this.size;
    }

    public boolean hasNextBit() {
        return this.readPosition < this.size;
    }

    public void init(int size, byte[] map) {
        this.size = size;
        this.map = map;
        this.readPosition = 0;
    }

    public void reset() {
        this.readPosition = 0;
    }

    public void setMap(byte[] map) {
        this.map = map;
    }

    public void setReadPosition(int readPosition) {
        this.readPosition = readPosition;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
