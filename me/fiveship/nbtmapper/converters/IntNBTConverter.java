package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class IntNBTConverter implements NBTConverter<Integer> {
    @Override
    public Class<Integer> type() {
        return Integer.class;
    }

    @Override
    public boolean canItUse(Class<?> value) {
        return value.equals(Integer.class) || value.equals(Integer.TYPE);
    }

    @Override
    public INBT serialize(Integer value, ServerWorld context) {

        return IntNBT.valueOf(value);
    }

    @Override
    public Integer deserialize(Integer value, ServerWorld context, INBT nbt) {
        IntNBT inbt = ((IntNBT) nbt);
        Integer i = new Integer(inbt.getInt());
        return i;
    }
}
