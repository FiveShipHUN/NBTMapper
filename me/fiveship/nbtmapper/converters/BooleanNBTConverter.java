package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;

public class BooleanNBTConverter implements NBTConverter<Boolean> {
    @Override
    public Class<Boolean> type() {
        return Boolean.class;
    }

    @Override
    public boolean canItUse(Object value) {
        return value.getClass().equals(Boolean.class) || value.getClass().equals(Boolean.TYPE);
    }

    @Override
    public INBT serialize(Boolean value, World context) {
        return ByteNBT.valueOf((Boolean) value ? (byte) 1 : (byte) 0);
    }

    @Override
    public Boolean deserialize(Boolean value, World context, INBT nbt) {
        return ((ByteNBT) nbt).getByte() == (byte) 1;
    }
}
