package me.fiveship.nbtmapper.converters;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.UUID;

public class EntityNBTConverter implements NBTConverter<Entity> {

    @Override
    public Class<Entity> type() {
        return Entity.class;
    }

    @Override
    public INBT serialize(Entity value, ServerWorld context) {
        return StringNBT.valueOf(value.getUniqueID().toString());
    }

    @Override
    public Entity deserialize(Entity value, ServerWorld context, INBT nbt) {
        return context.getEntityByUuid(UUID.fromString(((StringNBT) nbt).getString()));
    }


}
