package model;

import exception.AnimalAgeException;

public abstract class Animal {
    protected int id;
    protected String name;
    protected int age;
    protected String ownerName;

    public Animal(int id, String name, int age, String ownerName) throws AnimalAgeException {
        this.id = id;
        setName(name);
        setAge(age);
        setOwnerName(ownerName);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
        this.name = name;
    }

    public void setAge(int age) throws AnimalAgeException {
        if (age < 0) {
            throw new AnimalAgeException("Age cannot be negative: " + age);
        }
        if (age > 30) {
            throw new AnimalAgeException("Age " + age + " is unrealistic for an animal");
        }
        this.age = age;
    }

    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty or null");
        }
        this.ownerName = ownerName;
    }

    public abstract void makeSound();
    public abstract String getType();

    public boolean isAdult() {
        return age >= 1;
    }

    @Override
    public String toString() {
        return "[" + getType() + "] ID: " + id +
                ", Name: " + name +
                ", Age: " + age +
                ", Owner: " + ownerName;
    }
}