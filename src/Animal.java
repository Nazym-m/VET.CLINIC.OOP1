public class Animal {

    protected int id;
    protected String name;
    protected int age;
    protected String ownerName;

    public Animal(int id, String name, int age, String ownerName) {
        this.id = id;
        setName(name);
        setAge(age);
        this.ownerName = ownerName;
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
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name. Setting to 'Unknown'.");
            this.name = "Unknown";
        }
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            System.out.println("Age cannot be negative. Setting to 0.");
            this.age = 0;
        }
    }

    public void makeSound() {
        System.out.println("The animal makes a sound.");
    }

    public String getType() {
        return "Animal";
    }

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