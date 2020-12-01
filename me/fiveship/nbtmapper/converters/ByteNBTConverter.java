package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;

public class ByteNBTConverter implements NBTConverter<Byte> {
    @Override
    public Class<Byte> type() {
        return Byte.class;
    }

    @Override
    public boolean canItUse(Object value) {
        return value.getClass().equals(Byte.class) || value.getClass().equals(Byte.TYPE);
    }

    @Override
    public INBT serialize(Byte value, World context) {
        return ByteNBT.valueOf(value);
    }

    @Override
    public Byte deserialize(Byte value, World context, INBT nbt) {
        return ((ByteNBT) nbt).getByte();
    }
}
