package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LongNBTConverter implements NBTConverter<Long> {
    @Override
    public Class<Long> type() {
        return Long.class;
    }

    @Override
    public boolean canItUse(Class<?> value) {
        return value.equals(Long.class) || value.equals(Long.TYPE);
    }

    @Override
    public INBT serialize(Long value, ServerWorld context) {
        return LongNBT.valueOf(value);
    }

    @Override
    public Long deserialize(Long value, ServerWorld context, INBT nbt) {
        return ((LongNBT) nbt).getLong();
    }
}
