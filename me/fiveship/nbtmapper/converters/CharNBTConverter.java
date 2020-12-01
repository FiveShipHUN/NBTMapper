package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class CharNBTConverter implements NBTConverter<Character> {

    @Override
    public Class<Character> type() {
        return Character.class;
    }
    @Override
    public boolean canItUse(Class<?> value) {
        return value.equals(Character.class) || value.equals(Character.TYPE);
    }
    @Override
    public INBT serialize(Character value, ServerWorld context) {
        return StringNBT.valueOf(value + "");
    }

    @Override
    public Character deserialize(Character value, ServerWorld context, INBT nbt) {
        return ((StringNBT) nbt).getString().charAt(0);
    }
}
