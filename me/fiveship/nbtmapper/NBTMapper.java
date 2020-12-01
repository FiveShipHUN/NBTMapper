package me.fiveship.nbtmapper;

import me.fiveship.nbtmapper.converters.*;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.world.server.ServerWorld;

import java.util.*;
import java.util.function.Supplier;

public class NBTMapper {

    private List<NBTConverter<?>> converters = new ArrayList<>();
    private List<NBTConverter<?>> primitiveConverters = new ArrayList<>();
    private NBTConverter<?> basic;

    private HashMap<Class<?>, Supplier<?>> defaultConstructor = new HashMap<>();

    public NBTMapper() {
        registerDefaultConstructor(Character.class, () -> new Character('\0'));
        registerDefaultConstructor(String.class, () -> new String(""));
        registerDefaultConstructor(Byte.class, () -> new Byte((byte) 0));
        registerDefaultConstructor(Short.class, () -> new Short((short) 0));
        registerDefaultConstructor(Integer.class, () -> new Integer((int) 0));
        registerDefaultConstructor(Long.class, () -> new Long((long) 0));
        registerDefaultConstructor(Float.class, () -> new Float((float) 0));
        registerDefaultConstructor(Double.class, () -> new Double((double) 0));
        registerDefaultConstructor(Boolean.class, () -> new Boolean(false));
        registerDefaultConstructor(Boolean.TYPE, () -> new Boolean(false));
        registerDefaultConstructor(Double.TYPE, () -> new Double((double) 0));
        registerDefaultConstructor(Float.TYPE, () -> new Float((float) 0));
        registerDefaultConstructor(Long.TYPE, () -> new Long((long) 0));
        registerDefaultConstructor(Integer.TYPE, () -> new Integer((int) 0));
        registerDefaultConstructor(Short.TYPE, () -> new Short((short) 0));
        registerDefaultConstructor(Byte.TYPE, () -> new Byte((byte) 0));
        registerDefaultConstructor(Character.TYPE, () -> new Character('\0'));
        registerDefaultConstructor(Collection.class, () -> new ArrayList<>());
        basic = new BasicNBTConverter(this);
        primitiveConverters.add(new CollectionNBTConverter(this));
        primitiveConverters.add(new StringNBTConverter());
        primitiveConverters.add(new CharNBTConverter());
        primitiveConverters.add(new BooleanNBTConverter());
        primitiveConverters.add(new ByteNBTConverter());
        primitiveConverters.add(new ShortNBTConverter());
        primitiveConverters.add(new IntNBTConverter());
        primitiveConverters.add(new LongNBTConverter());
        primitiveConverters.add(new FloatNBTConverter());
        primitiveConverters.add(new DoubleNBTConverter());
        primitiveConverters.add(new UUIDNBTConverter());
        primitiveConverters.add(new EntityNBTConverter());
    }

    public void registerConverter(NBTConverter<?> c) {
        converters.add(c);
    }

    public void registerDefaultConstructor(Class<?> clazz, Supplier<?> s) {
        defaultConstructor.put(clazz, s);
    }

    public INBT serialize(Object value, ServerWorld context) {
        if (value == null) {
            return StringNBT.valueOf("#NULL#");
        }
        for (NBTConverter<?> c : converters) {
            try {
                if (c.canItUse(value.getClass())) {
                    return c.serializeObject(value, context);
                }
            } catch (Exception e) {
            }
        }
        for (NBTConverter<?> c : primitiveConverters) {
            try {
                if (c.canItUse(value.getClass())) {
                    return c.serializeObject(value, context);
                }
            } catch (Exception e) {
            }
        }
        return basic.serializeObject(value, context);
    }

    public <T> T deserializeAndApply(INBT nbt, T value, Class<?> type, ServerWorld context) {
        if (nbt instanceof StringNBT && nbt.getString().equals("#NULL#")) {
            return null;
        }
        // Class<?> clazz = value.getClass();
        for (NBTConverter<?> c : converters) {
            try {
                if (c.canItUse(type)) {
                    return (T) c.deserializeValue(value, context, nbt);
                }
            } catch (Exception e) {
            }
        }
        for (NBTConverter<?> c : primitiveConverters) {
            try {
                if (c.canItUse(type)) {
                    T t = (T) c.deserializeValue(value, context, nbt);
                    return t;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) basic.deserializeValue(value, context, nbt);
    }

    public <T> T deserialize(INBT nbt, Class<T> type, ServerWorld context) {
        for (Map.Entry<Class<?>, Supplier<?>> entry : defaultConstructor.entrySet()) {
            try {
                if (type.equals(entry.getKey())) {
                    Supplier<T> s = (Supplier<T>) entry.getValue();
                    return deserialize(nbt, s, type, context);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        for (Map.Entry<Class<?>, Supplier<?>> entry : defaultConstructor.entrySet()) {
            try {
                if (type.isAssignableFrom(entry.getKey()) || entry.getKey().isAssignableFrom(type)) {
                    Supplier<T> s = (Supplier<T>) entry.getValue();
                    return deserialize(nbt, s, type, context);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        Supplier<T> t = () -> {
            try {
                return type.getConstructor().newInstance();
            } catch (Exception e) {
                return null;
            }
        };
        return deserialize(nbt, t, type, context);
    }

    public <T> T deserialize(INBT nbt, Supplier<T> constructor, Class<T> type, ServerWorld context) {
        T t = constructor.get();
        t = deserializeAndApply(nbt, t, type, context);
        return t;
    }

}
