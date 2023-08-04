package me.theentropyshard.toserver.network.protocol.codec.info;

import java.util.Objects;

public class EnumCodecInfo extends TypeCodecInfo {
    public EnumCodecInfo(Class type, boolean optional) {
        super(type, optional);
    }

    public CodecInfo copy(boolean var1) {
        return new EnumCodecInfo(this.getType(), var1);
    }

    public boolean equals(Object var1) {
        if(this == var1) {
            return true;
        } else if(!(var1 instanceof EnumCodecInfo)) {
            return false;
        } else if(!super.equals(var1)) {
            return false;
        } else {
            Class var2 = this.getType();
            EnumCodecInfo var3 = (EnumCodecInfo) var1;
            if(!Objects.equals(var2, var3.getType())) {
                return false;
            } else {
                return this.isOptional() == var3.isOptional();
            }
        }
    }

    public int hashCode() {
        return (super.hashCode() * 31 + this.getType().hashCode()) * 31 + Boolean.hashCode(this.isOptional());
    }
}
