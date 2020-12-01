package me.fiveship.nbtmapper.converters;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.world.World;

public class CharNBTConverter implements NBTConverter<Character> {

    @Override
    public Class<Character> type() {
        return Character.class;
    }
    @Override
    public boolean canItUse(Object value) {
        return value.getClass().equals(Character.class) || value.getClass().equals(Character.TYPE);
    }
    @Override
    public INBT serialize(Character value, World context) {
        return StringNBT.valueOf(value + "");
    }

    @Override
    public Character deserialize(Character value, World context, INBT nbt) {
        return ((StringNBT) nbt).getString().charAt(0);
    }
}
