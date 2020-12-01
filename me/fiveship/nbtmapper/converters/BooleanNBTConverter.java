package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BooleanNBTConverter implements NBTConverter<Boolean> {
    @Override
    public Class<Boolean> type() {
        return Boolean.class;
    }

    @Override
    public boolean canItUse(Class<?> value) {
        return value.equals(Boolean.class) || value.equals(Boolean.TYPE);
    }

    @Override
    public INBT serialize(Boolean value, ServerWorld context) {
        return ByteNBT.valueOf((Boolean) value ? (byte) 1 : (byte) 0);
    }

    @Override
    public Boolean deserialize(Boolean value, ServerWorld context, INBT nbt) {
        return ((ByteNBT) nbt).getByte() == (byte) 1;
    }
}
