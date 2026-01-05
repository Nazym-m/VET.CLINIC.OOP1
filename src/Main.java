import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Animal> animals = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        animals.add(new Dog(1, "Buddy", 3, "Aruzhan", "Labrador"));
        animals.add(new Cat(2, "Misty", 2, "Dias", true));
        animals.add(new Dog(3, "Rex", 5, "Aidar", "German Shepherd"));

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    addDog();
                    break;
                case 3:
                    addCat();
                    break;
                case 4:
                    viewAllAnimals();
                    break;
                case 5:
                    demonstratePolymorphism();
                    break;
                case 6:
                    viewDogsOnly();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n================================");
        System.out.println(" VET CLINIC MANAGEMENT SYSTEM");
        System.out.println("================================");
        System.out.println("1. Add Animal (General)");
        System.out.println("2. Add Dog");
        System.out.println("3. Add Cat");
        System.out.println("4. View All Animals");
        System.out.println("5. Demonstrate Polymorphism");
        System.out.println("6. View Dogs Only");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }


    private static void addAnimal() {
        System.out.println("\n--- ADD ANIMAL ---");

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Owner name: ");
        String owner = scanner.nextLine();

        Animal animal = new Animal(id, name, age, owner);
        animals.add(animal);

        System.out.println("Animal added successfully.");
    }

    private static void addDog() {
        System.out.println("\n--- ADD DOG ---");

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Owner name: ");
        String owner = scanner.nextLine();

        System.out.print("Breed: ");
        String breed = scanner.nextLine();

        Animal dog = new Dog(id, name, age, owner, breed); 
        animals.add(dog);

        System.out.println("Dog added successfully.");
    }

    private static void addCat() {
        System.out.println("\n--- ADD CAT ---");

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Owner name: ");
        String owner = scanner.nextLine();

        System.out.print("Is indoor? (true/false): ");
        boolean indoor = scanner.nextBoolean();
        scanner.nextLine();

        Animal cat = new Cat(id, name, age, owner, indoor); 
        animals.add(cat);

        System.out.println("Cat added successfully.");
    }

    private static void viewAllAnimals() {
        System.out.println("\n--- ALL ANIMALS ---");

        if (animals.isEmpty()) {
            System.out.println("No animals found.");
            return;
        }

        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);
            System.out.println((i + 1) + ". " + a);
        }
    }

    private static void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");

        for (Animal a : animals) {
            a.makeSound(); 
        }

        System.out.println("Same method, different behavior = Polymorphism.");
    }

    private static void viewDogsOnly() {
        System.out.println("\n--- DOGS ONLY ---");

        int count = 0;

        for (Animal a : animals) {
            if (a instanceof Dog) {
                Dog dog = (Dog) a;
                count++;
                System.out.println(count + ". " + dog.getName() +
                        " | Breed: " + dog.getBreed());
            }
        }

        if (count == 0) {
            System.out.println("No dogs found.");
        }
    }
}
