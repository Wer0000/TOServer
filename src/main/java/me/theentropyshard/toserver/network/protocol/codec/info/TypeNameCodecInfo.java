package me.theentropyshard.toserver.network.protocol.codec.info;

public class TypeNameCodecInfo extends CodecInfo {
    private final String typeName;

    public TypeNameCodecInfo(String typeName, boolean optional) {
        super(optional);
        this.typeName = typeName;
    }

    @Override
    public CodecInfo copy(boolean optional) {
        return new TypeNameCodecInfo(this.typeName, optional);
    }

    public String getTypeName() {
        return this.typeName;
    }
}
