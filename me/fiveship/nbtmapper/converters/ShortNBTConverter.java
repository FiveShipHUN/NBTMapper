package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ShortNBT;
import net.minecraft.world.World;

public class ShortNBTConverter implements NBTConverter<Short> {
    @Override
    public Class<Short> type() {
        return Short.class;
    }

    @Override
    public boolean canItUse(Object value) {
        return value.getClass().equals(Short.class) || value.getClass().equals(Short.TYPE);
    }

    @Override
    public INBT serialize(Short value, World context) {
        return ShortNBT.valueOf(value);
    }

    @Override
    public Short deserialize(Short value, World context, INBT nbt) {
        return ((ShortNBT) nbt).getShort();
    }
}
