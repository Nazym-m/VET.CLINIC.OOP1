Animal cat = new Cat(id, name, age, owner, indoor); // POLYMORPHISM
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
        a.makeSound(); // SAME METHOD, DIFFERENT OUTPUT
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