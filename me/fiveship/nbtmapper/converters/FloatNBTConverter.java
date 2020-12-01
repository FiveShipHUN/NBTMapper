package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.FloatNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;

public class FloatNBTConverter implements NBTConverter<Float> {
    @Override
    public Class<Float> type() {
        return Float.class;
    }
    @Override
    public boolean canItUse(Object value) {
        return value.getClass().equals(Float.class) || value.getClass().equals(Float.TYPE);
    }

    @Override
    public INBT serialize(Float value, World context) {
        return FloatNBT.valueOf(value);
    }

    @Override
    public Float deserialize(Float value, World context, INBT nbt) {
        return ((FloatNBT) nbt).getFloat();
    }
}
