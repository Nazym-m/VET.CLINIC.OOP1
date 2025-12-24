public class Pet {
    private String name;
    private String species;
    private int age;
    private String ownerName;

    public Pet(String name, String species, int age, String ownerName) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.ownerName = ownerName;
    }

    public Pet() {
        this.name = "Unknown";
        this.species = "Unknown";
        this.age = 0;
        this.ownerName = "None";
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public boolean isYoung() {
        return this.age < 2;
    }

    public String getLifeStage() {
        if (age < 2) return "Puppy/Kitten";
        if (age < 8) return "Adult";
        return "Senior";
    }

    @Override
    public String toString() {
        return "Pet{name='" + name + "', species='" + species + "', age=" + age + ", owner='" + ownerName + "'}";
    }
}