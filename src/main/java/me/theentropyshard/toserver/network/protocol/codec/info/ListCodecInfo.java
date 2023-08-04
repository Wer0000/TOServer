package me.theentropyshard.toserver.network.protocol.codec.info;

import me.theentropyshard.toserver.network.protocol.ICodecInfo;

import java.util.Objects;

public class ListCodecInfo extends CollectionCodecInfo {
    public ListCodecInfo(ICodecInfo codecInfo, boolean optional) {
        super(codecInfo, optional);
    }

    public ListCodecInfo copy(boolean optional) {
        return new ListCodecInfo(this.getElementCodecInfo(), optional);
    }

    @Override
    public boolean equals(Object var1) {
        if(this == var1) {
            return true;
        } else if(!(var1 instanceof ListCodecInfo)) {
            return false;
        } else {
            ICodecInfo var2 = this.getElementCodecInfo();
            ListCodecInfo var3 = (ListCodecInfo) var1;
            if(!Objects.equals(var2, var3.getElementCodecInfo())) {
                return false;
            } else {
                return this.isOptional() == var3.isOptional();
            }
        }
    }

    @Override
    public int hashCode() {
        return this.getElementCodecInfo().hashCode() * 31 + Boolean.hashCode(this.isOptional());
    }
}
