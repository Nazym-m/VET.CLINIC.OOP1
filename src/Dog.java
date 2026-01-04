public class Dog extends Animal {

    private String breed;

    public Dog(int id, String name, int age, String ownerName, String breed) {
        super(id, name, age, ownerName);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
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
        return breed.equalsIgnoreCase("German Shepherd")
                || breed.equalsIgnoreCase("Labrador");
    }

    @Override
    public String toString() {
        return super.toString() + ", Breed: " + breed;
    }
}