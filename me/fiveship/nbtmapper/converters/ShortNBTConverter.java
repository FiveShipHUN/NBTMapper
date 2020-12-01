package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ShortNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ShortNBTConverter implements NBTConverter<Short> {
    @Override
    public Class<Short> type() {
        return Short.class;
    }

    @Override
    public boolean canItUse(Class<?> value) {
        return value.equals(Short.class) || value.equals(Short.TYPE);
    }

    @Override
    public INBT serialize(Short value, ServerWorld context) {
        return ShortNBT.valueOf(value);
    }

    @Override
    public Short deserialize(Short value, ServerWorld context, INBT nbt) {
        return ((ShortNBT) nbt).getShort();
    }
}
