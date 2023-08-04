package me.theentropyshard.toserver.network.protocol.codec.info;

import me.theentropyshard.toserver.network.protocol.ICodecInfo;

public abstract class CollectionCodecInfo extends CodecInfo {
    private final ICodecInfo elementCodecInfo;

    public CollectionCodecInfo(ICodecInfo elementCodecInfo, boolean optional) {
        super(optional);
        this.elementCodecInfo = elementCodecInfo;
    }

    public final ICodecInfo getElementCodecInfo() {
        return this.elementCodecInfo;
    }
}
