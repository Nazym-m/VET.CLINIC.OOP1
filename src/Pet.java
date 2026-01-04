public class Pet {
    private int petId;
    private String name;
    private String type;
    private int age;

    public Pet(int petId, String name, String type, int age) {
        this.petId = petId;
        setName(name);
        setType(type);
        setAge(age);
    }

    public int getPetId() { return petId; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getAge() { return age; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown";
        }
    }

    public void setType(String type) {
        if (type != null && !type.trim().isEmpty()) {
            this.type = type;
        } else {
            this.type = "Unknown";
        }
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            this.age = 0;
        }
    }

    @Override
    public String toString() {
        return "Pet ID: " + petId +
               ", Name: " + name +
               ", Type: " + type +
               ", Age: " + age;
    }
}

