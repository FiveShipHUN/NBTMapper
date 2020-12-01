package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.world.World;

public class IntNBTConverter implements NBTConverter<Integer> {
    @Override
    public Class<Integer> type() {
        return Integer.class;
    }

    @Override
    public boolean canItUse(Object value) {
        return value.getClass().equals(Integer.class) || value.getClass().equals(Integer.TYPE);
    }

    @Override
    public INBT serialize(Integer value, World context) {

        return IntNBT.valueOf(value);
    }

    @Override
    public Integer deserialize(Integer value, World context, INBT nbt) {
        IntNBT inbt = ((IntNBT) nbt);
        Integer i = new Integer(inbt.getInt());
        return i;
    }
}
