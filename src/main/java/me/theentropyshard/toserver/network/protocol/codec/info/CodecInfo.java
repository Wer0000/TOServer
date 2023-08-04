package me.theentropyshard.toserver.network.protocol.codec.info;

import me.theentropyshard.toserver.network.protocol.ICodecInfo;

public abstract class CodecInfo implements ICodecInfo {
    private final boolean optional;

    public CodecInfo(boolean optional) {
        this.optional = optional;
    }

    public abstract CodecInfo copy(boolean optional);

    @Override
    public boolean isOptional() {
        return this.optional;
    }

    @Override
    public ICodecInfo notOptionalCopy() {
        return this.copy(false);
    }

    @Override
    public ICodecInfo optionalCopy() {
        return this.copy(true);
    }
}
