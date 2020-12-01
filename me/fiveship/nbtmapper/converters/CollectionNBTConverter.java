package me.fiveship.nbtmapper.converters;

import me.fiveship.nbtmapper.NBTMapper;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionNBTConverter implements NBTConverter<Collection> {

    private NBTMapper parent;

    public CollectionNBTConverter(NBTMapper mapper) {
        parent = mapper;
    }

    @Override
    public Class<Collection> type() {
        return Collection.class;
    }

    @Override
    public INBT serialize(Collection value, ServerWorld context) {
        ListNBT nbt = new ListNBT();
        if (value.size() > 0) {
            List<?> list = new ArrayList<>(value);
            nbt.add(StringNBT.valueOf(list.get(0).getClass().getName()));
            for (Object o : value) {
                nbt.add(parent.serialize(o, context));
            }
        }
        return nbt;
    }

    @Override
    public Collection deserialize(Collection value, ServerWorld context, INBT nbt) {
        value.clear();
        ListNBT list = (ListNBT) nbt;
        if (list.size() == 0) {
            return value;
        }
        try {
            String typeName = ((StringNBT) list.get(0)).getString();
            Class<?> type = Class.forName(typeName, true, ClassLoader.getSystemClassLoader());
            for (int i = 1; i < list.size(); i++) {
                Object o = parent.deserialize(list.get(i), type, context);
                value.add(o);
            }
            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
