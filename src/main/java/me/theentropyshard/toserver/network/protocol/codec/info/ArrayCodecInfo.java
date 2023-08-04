package me.theentropyshard.toserver.network.protocol.codec.info;

import me.theentropyshard.toserver.network.protocol.ICodecInfo;

import java.util.Objects;

public class ArrayCodecInfo extends CollectionCodecInfo {
    public ArrayCodecInfo(ICodecInfo elementCodecInfo, boolean optional) {
        super(elementCodecInfo, optional);
    }

    public ArrayCodecInfo copy(boolean optional) {
        return new ArrayCodecInfo(this.getElementCodecInfo(), optional);
    }

    public boolean equals(Object var1) {
        if(this == var1) {
            return true;
        } else if(!(var1 instanceof ArrayCodecInfo)) {
            return false;
        } else {
            ICodecInfo var2 = this.getElementCodecInfo();
            ArrayCodecInfo var3 = (ArrayCodecInfo) var1;
            if(!Objects.equals(var2, var3.getElementCodecInfo())) {
                return false;
            } else {
                return this.isOptional() == var3.isOptional();
            }
        }
    }

    public int hashCode() {
        return this.getElementCodecInfo().hashCode() * 31 + Boolean.hashCode(this.isOptional());
    }
}
