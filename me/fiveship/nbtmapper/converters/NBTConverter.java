package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public interface NBTConverter<T> {

    Class<T> type();

    default boolean canItUse(Class<?> value) {
        // System.out.println(value.getClass().getName());
        return type().isAssignableFrom(value);
    }

    default INBT serializeObject(Object value, ServerWorld context) {
        return serialize((T) value, context);
    }

    INBT serialize(T value, ServerWorld context);

    T deserialize(T value, ServerWorld context, INBT nbt);

    default Object deserializeValue(Object value, ServerWorld context, INBT nbt) {
        Object o = deserialize((T) value, context, nbt);
        return o;
    }

}
