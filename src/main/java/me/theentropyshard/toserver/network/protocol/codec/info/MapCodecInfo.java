package me.theentropyshard.toserver.network.protocol.codec.info;

import me.theentropyshard.toserver.network.protocol.ICodecInfo;

import java.util.Objects;

public class MapCodecInfo extends CodecInfo {
    private ICodecInfo keyCodecInfo;
    private ICodecInfo valueCodecInfo;

    public MapCodecInfo(ICodecInfo var1, ICodecInfo var2, boolean var3) {
        super(var3);
        this.keyCodecInfo = var1;
        this.valueCodecInfo = var2;
    }

    @Override
    public CodecInfo copy(boolean var1) {
        return new MapCodecInfo(this.keyCodecInfo, this.valueCodecInfo, var1);
    }

    @Override
    public boolean equals(Object var1) {
        if(this == var1) {
            return true;
        } else if(!(var1 instanceof MapCodecInfo)) {
            return false;
        } else {
            ICodecInfo var2 = this.keyCodecInfo;
            MapCodecInfo var3 = (MapCodecInfo) var1;
            if(!Objects.equals(var2, var3.keyCodecInfo)) {
                return false;
            } else if(!Objects.equals(this.valueCodecInfo, var3.valueCodecInfo)) {
                return false;
            } else {
                return this.isOptional() == var3.isOptional();
            }
        }
    }

    @Override
    public int hashCode() {
        return (this.keyCodecInfo.hashCode() * 31 + this.valueCodecInfo.hashCode()) * 31 + Boolean.hashCode(this.isOptional());
    }

    public final ICodecInfo getKeyCodecInfo() {
        return this.keyCodecInfo;
    }

    public final ICodecInfo getValueCodecInfo() {
        return this.valueCodecInfo;
    }

    public final void setKeyCodecInfo(ICodecInfo var1) {
        this.keyCodecInfo = var1;
    }

    public final void setValueCodecInfo(ICodecInfo var1) {
        this.valueCodecInfo = var1;
    }
}
