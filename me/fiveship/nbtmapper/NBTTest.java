package me.fiveship.nbtmapper;

import net.minecraft.nbt.CompoundNBT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Deprecated
public class NBTTest {

    public static class Person {
        @NBTProperty("Name")
        public String name = "";
        @NBTProperty("Age")
        public int age = 0;
        @NBTProperty("IsMale")
        public boolean male = false;
        @NBTProperty("Address")
        public Address address = new Address();

        // Make sure that Collection fields (except if they can be ArrayList) will not be null when you pass the object to the mapper
        // Use Collections because arrays are not supported yet :c
        @NBTProperty("Items")
        public HashSet<String> items = new HashSet<>();
    }

    public static class Address {
        @NBTProperty("City")
        public String city = "";
        @NBTProperty("Street")
        public String street = "";
    }

    public static void main(String[] args) {
        NBTMapper map = new NBTMapper();
        Person person = new Person();
        Address address = new Address();
        person.name = "Peter";
        person.age = 20;
        person.male = true;
        address.city = "NYC";
        address.street = "Asd";
        person.address = address;
        person.items = new HashSet<>(Arrays.asList("apple", "stick"));
        CompoundNBT nbt = (CompoundNBT) map.serialize(person, null);
        System.out.println("Result: " + nbt.toString());
        Person copy = map.deserialize(nbt, Person.class, null);
        System.out.println("Age: " + copy.age);
        System.out.println("First item: " + new ArrayList<>(copy.items).get(0));
    }

}
