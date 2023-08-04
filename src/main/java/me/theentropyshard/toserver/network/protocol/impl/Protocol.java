package me.theentropyshard.toserver.network.protocol.impl;

import me.theentropyshard.toserver.network.protocol.*;
import me.theentropyshard.toserver.network.protocol.codec.OptionalCodecDecorator;
import me.theentropyshard.toserver.network.protocol.codec.complex.*;
import me.theentropyshard.toserver.network.protocol.codec.info.*;
import me.theentropyshard.toserver.network.protocol.codec.primitive.*;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Protocol implements IProtocol {
    public static final Protocol INSTANCE = new Protocol();

    public Map info2codec = new ConcurrentHashMap();
    public Set listInitCodec;

    public Protocol() {
        this.listInitCodec = new HashSet();
        this.registerCodecForType(ByteBuffer.class, new ByteBufferCodec());
        this.registerCodecForType(Byte.TYPE, new ByteCodec());
        this.registerCodecForType(Short.TYPE, new ShortCodec());
        this.registerCodecForType(Integer.TYPE, new IntCodec());
        this.registerCodecForType(Float.TYPE, new FloatCodec());
        this.registerCodecForType(Double.TYPE, new DoubleCodec());
        this.registerCodecForType(Boolean.TYPE, new BooleanCodec());
        this.registerCodecForType(String.class, new StringCodec());
        this.registerCodecForType(Long.TYPE, new LongCodec());
    }

    private TypeCodecInfo convert(TypeNameCodecInfo var1) {
        switch(var1.getTypeName()) {
            case "String":
                return new TypeCodecInfo(String.class, var1.isOptional());
            case "Int":
                return new TypeCodecInfo(Integer.TYPE, var1.isOptional());
            case "Byte":
                return new TypeCodecInfo(Byte.TYPE, var1.isOptional());
            case "Long":
                return new TypeCodecInfo(Long.TYPE, var1.isOptional());
            case "Float":
                return new TypeCodecInfo(Float.TYPE, var1.isOptional());
            case "Short":
                return new TypeCodecInfo(Short.TYPE, var1.isOptional());
            case "Boolean":
                return new TypeCodecInfo(Boolean.TYPE, var1.isOptional());
            case "Double":
                return new TypeCodecInfo(Double.TYPE, var1.isOptional());
        }
        return null;
    }

    private ICodec tryCreateAndRegisterCodec(ICodecInfo var1) {
        ICodec var2;
        if(var1 instanceof EnumCodecInfo) {
            var2 = new EnumCodec(((EnumCodecInfo) var1).getType());
        } else if(var1 instanceof SetCodecInfo) {
            var2 = new SetCodec(this.getCodec(((SetCodecInfo) var1).getElementCodecInfo()));
        } else if(var1 instanceof MapCodecInfo) {
            MapCodecInfo var4 = (MapCodecInfo) var1;
            var2 = new MapCodec(this.getCodec(var4.getKeyCodecInfo()), this.getCodec(var4.getValueCodecInfo()));
        } else if(var1 instanceof ListCodecInfo) {
            var2 = new ListCodec(this.getCodec(((ListCodecInfo) var1).getElementCodecInfo()));
        } else if(var1 instanceof ArrayCodecInfo) {
            var2 = new ArrayCodec(this.getCodec(((ArrayCodecInfo) var1).getElementCodecInfo()));
        } else {
            var2 = null;
        }

        if(var2 != null) {
            OptionalCodecDecorator var3 = new OptionalCodecDecorator(var2);
            this.info2codec.put(var1.notOptionalCopy(), var2);
            this.info2codec.put(var1.optionalCopy(), var3);
            if(var1.isOptional()) {
                var2 = var3;
            }

            return var2;
        } else if(var1.isOptional()) {
            OptionalCodecDecorator var5 = new OptionalCodecDecorator(this.getCodec(var1.notOptionalCopy()));
            this.info2codec.put(var1.optionalCopy(), var5);
            return var5;
        } else {
            return null;
        }
    }


    public ICodec getCodec(ICodecInfo var1) {
        if(var1 instanceof TypeNameCodecInfo) {
            var1 = this.convert((TypeNameCodecInfo) var1);
        }

        ICodec var3 = (ICodec) this.info2codec.get(var1);
        if(var3 == null) {
            var3 = this.tryCreateAndRegisterCodec(var1);
            if(var3 == null) {
                throw new RuntimeException("Unknown codec");
            }
        }

        if(!this.listInitCodec.contains(var3)) {
            var3.init(this);
            this.listInitCodec.add(var3);
        }

        return var3;
    }

    public ICodec getCodec(TypeNameCodecInfo var1) {
        return this.getCodec(this.convert(var1));
    }

    public ICodec getCodec(Class var1, boolean var2) {
        return this.getCodec(new TypeCodecInfo(var1, var2));
    }

    public void registerCodec(ICodecInfo var1, ICodec var2) {
        this.info2codec.put(var1, var2);
    }

    public void registerCodecForType(Class var1, ICodec var2) {
        this.info2codec.put(new TypeCodecInfo(var1, false), var2);
        this.info2codec.put(new TypeCodecInfo(var1, true), new OptionalCodecDecorator(var2));
    }

    public boolean unwrapPacket(ByteBuffer var1, ProtocolBuffer var2) {
        return PacketHelper.unwrapPacket(var1, var2);
    }

    public void wrapPacket(ByteBuffer var1, ProtocolBuffer var2) {
        PacketHelper.wrapPacket(var1, var2);
    }
}
