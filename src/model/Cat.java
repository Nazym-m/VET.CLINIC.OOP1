package model;

import exception.AnimalAgeException;

public class Cat extends Animal implements Vaccinatable {
    private boolean indoor;
    private boolean vaccinated;

    public Cat(int id, String name, int age, String ownerName, boolean indoor)
            throws AnimalAgeException {
        super(id, name, age, ownerName);
        this.indoor = indoor;
        this.vaccinated = false;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    @Override
    public void makeSound() {
        System.out.println("Cat " + name + " says: Meow!");
    }

    @Override
    public String getType() {
        return "Cat";
    }

    // Implement Vaccinatable interface methods
    @Override
    public boolean needsVaccination() {
        return !indoor && !vaccinated;
    }

    @Override
    public void administerVaccine() {
        if (needsVaccination()) {
            vaccinated = true;
            System.out.println(name + " has been vaccinated.");
        } else {
            System.out.println(name + " doesn't need vaccination.");
        }
    }

    @Override
    public String getVaccineInfo() {
        return "Cat: " + name +
                ", Indoor: " + indoor +
                ", Vaccinated: " + vaccinated +
                ", Needs vaccine: " + needsVaccination();
    }

    @Override
    public String toString() {
        return super.toString() + ", Indoor: " + (indoor ? "Yes" : "No");
    }
}