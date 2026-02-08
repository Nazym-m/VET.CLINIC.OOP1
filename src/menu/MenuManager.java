package menu;

import model.Animal;
import model.Dog;
import model.Cat;
import exception.AnimalAgeException;
import database.AnimalDAO;

import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {
    private AnimalDAO animalDAO;
    private Scanner scanner;

    public MenuManager() {
        this.animalDAO = new AnimalDAO();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("   VET CLINIC MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Dog");
        System.out.println("2. Add Cat");
        System.out.println("3. View All Animals");
        System.out.println("4. Search Animal by Name");
        System.out.println("5. Search Animal by Owner");
        System.out.println("6. Update Animal");
        System.out.println("7. Delete Animal");
        System.out.println("8. Demonstrate Polymorphism");
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
                        searchByName();
                        break;
                    case 5:
                        searchByOwner();
                        break;
                    case 6:
                        updateAnimal();
                        break;
                    case 7:
                        deleteAnimal();
                        break;
                    case 8:
                        demonstratePolymorphism();
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
                System.out.println("Please enter a valid number!");
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

            if (animalDAO.insertAnimal(dog)) {
                System.out.println("Dog added successfully to database.");
            } else {
                System.out.println("Failed to add dog to database.");
            }

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

            if (animalDAO.insertAnimal(cat)) {
                System.out.println("Cat added successfully to database.");
            } else {
                System.out.println("Failed to add cat to database.");
            }

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

        List<Animal> animals = animalDAO.getAllAnimals();

        if (animals.isEmpty()) {
            System.out.println("No animals found in database.");
            return;
        }

        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);
            System.out.println((i + 1) + ". " + a);
        }

        System.out.println("\nTotal: " + animals.size() + " animals");
    }

    private void searchByName() {
        System.out.println("\n--- SEARCH BY NAME ---");
        System.out.print("Enter animal name (or part of name): ");
        String searchTerm = scanner.nextLine();

        List<Animal> animals = animalDAO.searchByName(searchTerm);

        if (animals.isEmpty()) {
            System.out.println("No animals found with name containing: " + searchTerm);
            return;
        }

        System.out.println("\nFound " + animals.size() + " animals:");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private void searchByOwner() {
        System.out.println("\n--- SEARCH BY OWNER ---");
        System.out.print("Enter owner name (or part of name): ");
        String ownerName = scanner.nextLine();

        List<Animal> animals = animalDAO.searchByOwner(ownerName);

        if (animals.isEmpty()) {
            System.out.println("No animals found for owner: " + ownerName);
            return;
        }

        System.out.println("\nFound " + animals.size() + " animals:");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private void updateAnimal() {
        System.out.println("\n--- UPDATE ANIMAL ---");
        System.out.print("Enter Animal ID to update: ");

        try {
            int animalId = Integer.parseInt(scanner.nextLine());

            Animal existingAnimal = animalDAO.getAnimalById(animalId);
            if (existingAnimal == null) {
                System.out.println("No animal found with ID: " + animalId);
                return;
            }

            System.out.println("Current information:");
            System.out.println(existingAnimal);

            System.out.println("\nEnter new information (press Enter to keep current value):");

            System.out.print("Name [" + existingAnimal.getName() + "]: ");
            String newName = scanner.nextLine();
            if (!newName.trim().isEmpty()) {
                existingAnimal.setName(newName);
            }

            System.out.print("Age [" + existingAnimal.getAge() + "]: ");
            String ageInput = scanner.nextLine();
            if (!ageInput.trim().isEmpty()) {
                try {
                    existingAnimal.setAge(Integer.parseInt(ageInput));
                } catch (AnimalAgeException e) {
                    System.out.println("Invalid age: " + e.getMessage());
                    return;
                }
            }

            System.out.print("Owner name [" + existingAnimal.getOwnerName() + "]: ");
            String newOwner = scanner.nextLine();
            if (!newOwner.trim().isEmpty()) {
                existingAnimal.setOwnerName(newOwner);
            }

            if (existingAnimal instanceof Dog) {
                Dog dog = (Dog) existingAnimal;
                System.out.print("Breed [" + dog.getBreed() + "]: ");
                String newBreed = scanner.nextLine();
                if (!newBreed.trim().isEmpty()) {
                    dog.setBreed(newBreed);
                }
            } else if (existingAnimal instanceof Cat) {
                Cat cat = (Cat) existingAnimal;
                System.out.print("Indoor [" + cat.isIndoor() + "] (true/false): ");
                String indoorInput = scanner.nextLine();
                if (!indoorInput.trim().isEmpty()) {
                    cat.setIndoor(Boolean.parseBoolean(indoorInput));
                }
            }

            if (animalDAO.updateAnimal(existingAnimal)) {
                System.out.println("Animal updated successfully.");
            } else {
                System.out.println("Failed to update animal.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private void deleteAnimal() {
        System.out.println("\n--- DELETE ANIMAL ---");
        System.out.print("Enter Animal ID to delete: ");

        try {
            int animalId = Integer.parseInt(scanner.nextLine());

            Animal animal = animalDAO.getAnimalById(animalId);
            if (animal == null) {
                System.out.println("No animal found with ID: " + animalId);
                return;
            }

            System.out.println("Animal to delete:");
            System.out.println(animal);
            System.out.print("\n Are you sure? Type 'yes' to confirm: ");

            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("yes")) {
                if (animalDAO.deleteAnimal(animalId)) {
                    System.out.println("Animal deleted successfully.");
                } else {
                    System.out.println("Failed to delete animal.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");

        List<Animal> animals = animalDAO.getAllAnimals();

        if (animals.isEmpty()) {
            System.out.println("No animals in database to demonstrate.");
            return;
        }

        System.out.println("Animal sounds:");
        for (Animal a : animals) {
            a.makeSound();
        }

        System.out.println("\nVaccination needs:");
        for (Animal a : animals) {
            if (a instanceof Cat) {
                Cat cat = (Cat) a;
                System.out.println(cat.getVaccineInfo());
            }
        }

        System.out.println("\nSame method, different behavior = Polymorphism.");
    }
}