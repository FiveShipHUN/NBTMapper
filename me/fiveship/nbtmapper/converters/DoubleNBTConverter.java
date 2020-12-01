package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;

public class DoubleNBTConverter implements NBTConverter<Double> {
    @Override
    public Class<Double> type() {
        return Double.class;
    }
    @Override
    public boolean canItUse(Object value) {
        return value.getClass().equals(Double.class) || value.getClass().equals(Double.TYPE);
    }
    @Override
    public INBT serialize(Double value, World context) {
        return DoubleNBT.valueOf(value);
    }

    @Override
    public Double deserialize(Double value, World context, INBT nbt) {
        return ((DoubleNBT) nbt).getDouble();
    }
}
