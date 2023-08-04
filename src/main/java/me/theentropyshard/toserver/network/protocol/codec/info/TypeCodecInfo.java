package me.theentropyshard.toserver.network.protocol.codec.info;

import java.util.Objects;

public class TypeCodecInfo extends CodecInfo {
    public final Class type;

    public TypeCodecInfo(Class type, boolean optional) {
        super(optional);
        this.type = type;
    }

    @Override
    public CodecInfo copy(boolean var1) {
        return new TypeCodecInfo(this.type, var1);
    }

    @Override
    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else if (!(var1 instanceof TypeCodecInfo)) {
            return false;
        } else {
            Class var2 = this.type;
            TypeCodecInfo var3 = (TypeCodecInfo)var1;
            if (!Objects.equals(var2, var3.type)) {
                return false;
            } else {
                return this.isOptional() == var3.isOptional();
            }
        }
    }

    @Override
    public int hashCode() {
        return this.type.hashCode() * 31 + Boolean.hashCode(this.isOptional());
    }

    public final Class getType() {
        return this.type;
    }
}
