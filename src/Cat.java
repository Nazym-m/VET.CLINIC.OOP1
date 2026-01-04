public class Cat extends Animal {

    private boolean indoor;

    public Cat(int id, String name, int age, String ownerName, boolean indoor) {
        super(id, name, age, ownerName);
        this.indoor = indoor;
    }

    public boolean isIndoor() {
        return indoor;
    }

    @Override
    public void makeSound() {
        System.out.println("Cat " + name + " says: Meow!");
    }

    @Override
    public String getType() {
        return "Cat";
    }

    public boolean needsVaccination() {
        return !indoor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Indoor: " + (indoor ? "Yes" : "No");
    }
}