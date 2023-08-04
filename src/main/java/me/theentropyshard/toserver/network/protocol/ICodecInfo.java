package me.theentropyshard.toserver.network.protocol;

public interface ICodecInfo {
    boolean isOptional();

    ICodecInfo notOptionalCopy();

    ICodecInfo optionalCopy();
}
