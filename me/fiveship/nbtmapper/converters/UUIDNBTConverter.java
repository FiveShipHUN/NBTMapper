package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.UUID;

public class UUIDNBTConverter implements NBTConverter<UUID> {
    @Override
    public Class<UUID> type() {
        return UUID.class;
    }

    @Override
    public UUID deserialize(UUID value, ServerWorld context, INBT nbt) {
        return UUID.fromString(((StringNBT) nbt).getString());
    }

    @Override
    public INBT serialize(UUID value, ServerWorld context) {
        return StringNBT.valueOf(value.toString());
    }
}
