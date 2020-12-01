package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ByteNBTConverter implements NBTConverter<Byte> {
    @Override
    public Class<Byte> type() {
        return Byte.class;
    }

    @Override
    public boolean canItUse(Class<?> value) {
        return value.equals(Byte.class) || value.equals(Byte.TYPE);
    }

    @Override
    public INBT serialize(Byte value, ServerWorld context) {
        return ByteNBT.valueOf(value);
    }

    @Override
    public Byte deserialize(Byte value, ServerWorld context, INBT nbt) {
        return ((ByteNBT) nbt).getByte();
    }
}
