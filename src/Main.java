import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Pet> pets = new ArrayList<>();
    private static ArrayList<Owner> owners = new ArrayList<>();
    private static ArrayList<Visit> visits = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Test data
        pets.add(new Pet(1, "Buddy", "Dog", 3));
        pets.add(new Pet(2, "Mimi", "Cat", 2));

        owners.add(new Owner(1, "Aruzhan", "+77011234567"));
        owners.add(new Owner(2, "Dias", "+77019876543"));

        visits.add(new Visit(1, "Buddy", "Vaccination", "Completed"));
        visits.add(new Visit(2, "Mimi", "Check-up", "Pending"));

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addPet(); break;
                case 2: viewAllPets(); break;
                case 3: addOwner(); break;
                case 4: viewAllOwners(); break;
                case 5: addVisit(); break;
                case 6: viewAllVisits(); break;
                case 0:
                    System.out.println("Goodbye 👋");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice ❌");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== VET CLINIC SYSTEM ===");
        System.out.println("1. Add Pet");
        System.out.println("2. View All Pets");
        System.out.println("3. Add Owner");
        System.out.println("4. View All Owners");
        System.out.println("5. Add Visit");
        System.out.println("6. View All Visits");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addPet() {
        System.out.print("Pet ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Pet name: ");
        String name = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        pets.add(new Pet(id, name, type, age));
        System.out.println("Pet added successfully ✅");
    }

    private static void viewAllPets() {
        if (pets.isEmpty()) {
            System.out.println("No pets found.");
            return;
        }
        for (Pet p : pets) {
            System.out.println(p);
        }
    }

    private static void addOwner() {
        System.out.print("Owner ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Owner name: ");
        String name = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        owners.add(new Owner(id, name, phone));
        System.out.println("Owner added successfully ✅");
    }

    private static void viewAllOwners() {
        if (owners.isEmpty()) {
            System.out.println("No owners found.");
            return;
        }
        for (Owner o : owners) {
            System.out.println(o);
        }
    }

    private static void addVisit() {
        System.out.print("Visit ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Pet name: ");
        String petName = scanner.nextLine();

        System.out.print("Reason: ");
        String reason = scanner.nextLine();

        System.out.print("Status (Pending/Completed): ");
        String status = scanner.nextLine();

        visits.add(new Visit(id, petName, reason, status));
        System.out.println("Visit added successfully ✅");
    }

    private static void viewAllVisits() {
      if (visits.isEmpty()) {
            System.out.println("No visits found.");
            return;
        }
        for (Visit v : visits) {
            System.out.println(v);
        }
    }
}
