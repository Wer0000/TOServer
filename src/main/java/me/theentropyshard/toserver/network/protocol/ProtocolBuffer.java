package me.theentropyshard.toserver.network.protocol;

import java.nio.ByteBuffer;

public final class ProtocolBuffer {
    private final ByteBuffer buffer;
    private final ByteBuffer writer;
    private final ByteBuffer reader;
    private final OptionalMap optionalMap;

    public ProtocolBuffer(ByteBuffer buffer, OptionalMap map) {
        this.buffer = buffer;
        this.optionalMap = map;
        this.reader = buffer;
        this.writer = buffer;
    }

    public void clear() {
        this.buffer.clear();
        this.optionalMap.clear();
    }

    public void flip() {
        this.buffer.flip();
        this.optionalMap.flip();
    }

    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    public OptionalMap getOptionalMap() {
        return this.optionalMap;
    }

    public ByteBuffer getReader() {
        return this.reader;
    }

    public ByteBuffer getWriter() {
        return this.writer;
    }
}
