package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.world.World;

public class LongNBTConverter implements NBTConverter<Long> {
    @Override
    public Class<Long> type() {
        return Long.class;
    }

    @Override
    public boolean canItUse(Object value) {
        return value.getClass().equals(Long.class) || value.getClass().equals(Long.TYPE);
    }

    @Override
    public INBT serialize(Long value, World context) {
        return LongNBT.valueOf(value);
    }

    @Override
    public Long deserialize(Long value, World context, INBT nbt) {
        return ((LongNBT) nbt).getLong();
    }
}
