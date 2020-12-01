package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;

public interface NBTConverter<T> {

    Class<T> type();

    default boolean canItUse(Object value) {
        // System.out.println(value.getClass().getName());
        return type().isInstance(value) || type().equals(value.getClass()) || value.getClass().isAssignableFrom(type());
    }

    default INBT serializeObject(Object value, World context) {
        return serialize((T) value, context);
    }

    INBT serialize(T value, World context);

    T deserialize(T value, World context, INBT nbt);

    default Object deserializeValue(Object value, World context, INBT nbt) {
        Object o = deserialize((T) value, context, nbt);
        return o;
    }

}
