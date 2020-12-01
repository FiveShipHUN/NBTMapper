package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.FloatNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class FloatNBTConverter implements NBTConverter<Float> {
    @Override
    public Class<Float> type() {
        return Float.class;
    }
    @Override
    public boolean canItUse(Class<?> value) {
        return value.equals(Float.class) || value.equals(Float.TYPE);
    }

    @Override
    public INBT serialize(Float value, ServerWorld context) {
        return FloatNBT.valueOf(value);
    }

    @Override
    public Float deserialize(Float value, ServerWorld context, INBT nbt) {
        return ((FloatNBT) nbt).getFloat();
    }
}
