package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class StringNBTConverter implements NBTConverter<String> {

    @Override
    public Class<String> type() {
        return String.class;
    }

    @Override
    public INBT serialize(String value, ServerWorld context) {
        return StringNBT.valueOf(value);
    }

    @Override
    public String deserialize(String value, ServerWorld context, INBT nbt) {
        return ((StringNBT) nbt).getString();
    }
}
