package me.theentropyshard.toserver.network.protocol.codec.info;

import me.theentropyshard.toserver.network.protocol.ICodecInfo;

import java.util.Objects;

public class SetCodecInfo extends CodecInfo {
    public ICodecInfo elementCodecInfo;

    public SetCodecInfo(ICodecInfo var1, boolean var2) {
        super(var2);
        this.elementCodecInfo = var1;
    }

    @Override
    public CodecInfo copy(boolean var1) {
        return new SetCodecInfo(this.elementCodecInfo, var1);
    }

    @Override
    public boolean equals(Object var1) {
        if(this == var1) {
            return true;
        } else if(!(var1 instanceof SetCodecInfo)) {
            return false;
        } else {
            ICodecInfo var2 = this.elementCodecInfo;
            SetCodecInfo var3 = (SetCodecInfo) var1;
            if(!Objects.equals(var2, var3.elementCodecInfo)) {
                return false;
            } else {
                return this.isOptional() == var3.isOptional();
            }
        }
    }

    @Override
    public int hashCode() {
        return this.elementCodecInfo.hashCode() * 31 + Boolean.hashCode(this.isOptional());
    }

    public final ICodecInfo getElementCodecInfo() {
        return this.elementCodecInfo;
    }

    public final void setElementCodecInfo(ICodecInfo var1) {
        this.elementCodecInfo = var1;
    }
}
