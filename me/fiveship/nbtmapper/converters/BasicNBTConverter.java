package me.fiveship.nbtmapper.converters;

import me.fiveship.nbtmapper.NBTMapper;
import me.fiveship.nbtmapper.NBTProperty;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.world.World;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BasicNBTConverter implements NBTConverter<Object> {

    private NBTMapper parent;

    public BasicNBTConverter(NBTMapper parent) {
        this.parent = parent;
    }

    @Override
    public Class<Object> type() {
        return Object.class;
    }

    @Override
    public INBT serialize(Object value, World context) {
        //  System.out.println("Serialization started...");
        CompoundNBT nbt = new CompoundNBT();
        // System.out.println(value.getClass().getName());
        for (Field field : value.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.isAnnotationPresent(NBTProperty.class)) {
                    NBTProperty property = field.getAnnotation(NBTProperty.class);
                    if (property != null) {
                        //  System.out.println("Property: " + property.value());
                        nbt.put(isPropertyNameValid(property.value()) ? property.value() : field.getName(), parent.serialize(field.get(value), context));
                    }
                }
            } catch (Exception e) {
            }
        }
        // System.out.println("Serialization over...");
        return nbt;
    }

    private static Field fieldOf(String name, Class<?> clazz) {
        for (Field field : clazz.getFields()) {
            try {
                if (field.isAnnotationPresent(NBTProperty.class)) {
                    if (field.getName().equals(name)) {
                        return field;
                    }
                    NBTProperty property = field.getAnnotation(NBTProperty.class);
                    if (property != null && property.value().equals(name)) {
                        return field;
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    private static boolean isPropertyNameValid(String s) {
        if (s == null) {
            return false;
        }
        if (s.isEmpty()) {
            return false;
        }
        if (s.equalsIgnoreCase("null")) {
            return false;
        }
        return true;
    }

    @Override
    public Object deserialize(Object value, World context, INBT inbt) {
        CompoundNBT nbt = (CompoundNBT) inbt;
        for (String key : nbt.keySet()) {
            try {
                Field field = fieldOf(key, value.getClass());
                if (field != null) {
                    INBT nbtvalue = nbt.get(key).copy();
                    if (field.get(value) == null) {
                        Object o = parent.deserialize(nbtvalue, field.getType(), context);
                        field.set(value, o);
                    } else {
                        Object o = parent.deserializeAndApply(nbtvalue, field.get(value), context);
                        field.set(value, o);
                    }
                }
            } catch (Exception e) {
            }
        }
        return value;
    }


}
