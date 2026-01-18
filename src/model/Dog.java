package model;

import exception.AnimalAgeException;

public class Dog extends Animal {
    private String breed;

    public Dog(int id, String name, int age, String ownerName, String breed)
            throws AnimalAgeException {
        super(id, name, age, ownerName);
        setBreed(breed);
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        if (breed == null || breed.trim().isEmpty()) {
            throw new IllegalArgumentException("Breed cannot be empty or null");
        }
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Dog " + name + " says: Woof!");
    }

    @Override
    public String getType() {
        return "Dog";
    }

    public boolean isLargeBreed() {
        if (breed == null) return false;
        return breed.equalsIgnoreCase("German Shepherd")
                || breed.equalsIgnoreCase("Labrador")
                || breed.equalsIgnoreCase("Husky")
                || breed.equalsIgnoreCase("Rottweiler");
    }

    @Override
    public String toString() {
        return super.toString() + ", Breed: " + breed;
    }
}