package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DoubleNBTConverter implements NBTConverter<Double> {
    @Override
    public Class<Double> type() {
        return Double.class;
    }
    @Override
    public boolean canItUse(Class<?> value) {
        return value.equals(Double.class) || value.equals(Double.TYPE);
    }
    @Override
    public INBT serialize(Double value, ServerWorld context) {
        return DoubleNBT.valueOf(value);
    }

    @Override
    public Double deserialize(Double value, ServerWorld context, INBT nbt) {
        return ((DoubleNBT) nbt).getDouble();
    }
}
