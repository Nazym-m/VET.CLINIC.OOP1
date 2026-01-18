package menu;

import model.Animal;
import model.Dog;
import model.Cat;
import exception.AnimalAgeException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {
    private ArrayList<Animal> animals;
    private Scanner scanner;

    public MenuManager() {
        this.animals = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        try {
            animals.add(new Dog(1, "Buddy", 3, "Aruzhan", "Labrador"));
            animals.add(new Cat(2, "Misty", 2, "Dias", true));
            animals.add(new Dog(3, "Rex", 5, "Aidar", "German Shepherd"));
        } catch (AnimalAgeException e) {
            System.out.println("Error initializing test data: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error initializing test data: " + e.getMessage());
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("\n================================");
        System.out.println(" VET CLINIC MANAGEMENT SYSTEM");
        System.out.println("================================");
        System.out.println("1. Add Dog");
        System.out.println("2. Add Cat");
        System.out.println("3. View All Animals");
        System.out.println("4. Demonstrate Polymorphism");
        System.out.println("5. View Dogs Only");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addDog();
                        break;
                    case 2:
                        addCat();
                        break;
                    case 3:
                        viewAllAnimals();
                        break;
                    case 4:
                        demonstratePolymorphism();
                        break;
                    case 5:
                        viewDogsOnly();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

                if (running && choice != 0) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private void addDog() {
        System.out.println("\n--- ADD DOG ---");

        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Owner name: ");
            String owner = scanner.nextLine();

            System.out.print("Breed: ");
            String breed = scanner.nextLine();

            Dog dog = new Dog(id, name, age, owner, breed);
            animals.add(dog);
            System.out.println("Dog added successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format!");
        } catch (AnimalAgeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addCat() {
        System.out.println("\n--- ADD CAT ---");

        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Owner name: ");
            String owner = scanner.nextLine();

            System.out.print("Is indoor? (true/false): ");
            boolean indoor = Boolean.parseBoolean(scanner.nextLine());

            Cat cat = new Cat(id, name, age, owner, indoor);
            animals.add(cat);
            System.out.println("Cat added successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format!");
        } catch (AnimalAgeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllAnimals() {
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

    private void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");

        if (animals.isEmpty()) {
            System.out.println("No animals to demonstrate.");
            return;
        }

        for (Animal a : animals) {
            a.makeSound();
        }

        System.out.println("Same method, different behavior = Polymorphism.");
    }

    private void viewDogsOnly() {
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