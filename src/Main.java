public class Main {
    public static void main(String[] args) {
        System.out.println("=== Vet Clinic Management System ===");

        Pet pet1 = new Pet("Buddy", "Dog", 1, "Nazym");
        Pet pet2 = new Pet();
        Veterinarian vet1 = new Veterinarian("Dr. Smith", "Surgeon", 10, true);
        Appointment app1 = new Appointment("Buddy", "Dr. Smith", "Vaccination", 3000.0);

        System.out.println("\n--- Initial State ---");
        System.out.println(pet1);
        System.out.println(vet1);

        System.out.println("\n--- Testing Logic ---");
        System.out.println(pet1.getName() + " is young: " + pet1.isYoung());
        System.out.println(vet1.getName() + " is experienced: " + vet1.isExperienced());

        System.out.println("\n--- Updating Data (Setters) ---");
        pet2.setName("Misty");
        pet2.setAge(5);
        System.out.println("Updated Pet 2: " + pet2);

        System.out.println("\n=== Program Complete ===");
    }
}