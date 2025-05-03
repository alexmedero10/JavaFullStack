import models.*;

import java.util.List;
import java.util.Scanner;

public class AdoptionApp {
    private static Scanner scanner = new Scanner(System.in);
    private static PetAdoptionCenter center;

    public static void main(String[] args) {
        System.out.println("Welcome to the Pet Adoption System!");

        try {
            center = PetAdoptionCenter.loadFromFile("petadoption.dat");
        } catch (Exception e) {
            center = new PetAdoptionCenter("Happy Paws Adoption Center");

            populateSampleData();
        }

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        displayAllPets();
                        break;
                    case 2:
                        displayAvailablePets();
                        break;
                    case 3:
                        addNewPet();
                        break;
                    case 4:
                        searchPets();
                        break;
                    case 5:
                        registerAdopter();
                        break;
                    case 6:
                        processAdoption();
                        break;
                    case 7:
                        displayAdopters();
                        break;
                    case 8:
                        center.saveToFile("petadoption.dat");
                        break;
                    case 9:
                        running = false;
                        System.out.println("Thank you for using the Pet Adoption System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== Pet Adoption System Menu =====");
        System.out.println("1. Display All Pets");
        System.out.println("2. Display Available Pets");
        System.out.println("3. Add New Pet");
        System.out.println("4. Search Pets");
        System.out.println("5. Register New Adopter");
        System.out.println("6. Process Adoption");
        System.out.println("7. Display All Adopters");
        System.out.println("8. Save Data");
        System.out.println("9. Exit");
        System.out.println("==================================");
    }

    private static void displayAllPets() {
        List<Pet> pets = center.getAllPets();
        if (pets.isEmpty()) {
            System.out.println("There are no pets in the system.");
            return;
        }

        System.out.println("\n===== All Pets =====");
        for (Pet pet : pets) {
            System.out.println(pet);
            System.out.println(pet.makeSound());
            System.out.println("-----------------");
        }
    }

    private static void displayAvailablePets() {
        List<Pet> availablePets = center.getAvailablePets();
        if (availablePets.isEmpty()) {
            System.out.println("There are no available pets for adoption.");
            return;
        }

        System.out.println("\n===== Available Pets =====");
        for (Pet pet : availablePets) {
            System.out.println(pet);
            System.out.println(pet.makeSound());
            System.out.println("-----------------");
        }
    }

    private static void addNewPet() {
        System.out.println("\n===== Add New Pet =====");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Bird");
        int type = getIntInput("Enter pet type: ");

        String name = getStringInput("Enter pet name: ");
        int age = getIntInput("Enter pet age: ");
        String breed = getStringInput("Enter pet breed: ");

        switch (type) {
            case 1:
                boolean housetrained = getBooleanInput("Is the dog housetrained? (yes/no): ");
                Dog dog = center.addDog(name, age, breed, housetrained);
                System.out.println("Added new dog: " + dog);
                break;
            case 2:
                boolean indoorOnly = getBooleanInput("Is the cat indoor only? (yes/no): ");
                Cat cat = center.addCat(name, age, breed, indoorOnly);
                System.out.println("Added new cat: " + cat);
                break;
            case 3:
                boolean canTalk = getBooleanInput("Can the bird talk? (yes/no): ");
                Bird bird = center.addBird(name, age, breed, canTalk);
                System.out.println("Added new bird: " + bird);
                break;
            default:
                System.out.println("Invalid pet type.");
        }
    }

    private static void searchPets() {
        System.out.println("\n===== Search Pets =====");
        System.out.println("1. Search by Species");
        System.out.println("2. Search by Breed");
        System.out.println("3. Search by Maximum Age");
        int searchType = getIntInput("Enter search type: ");

        List<Pet> results = null;

        switch (searchType) {
            case 1:
                String species = getStringInput("Enter species (Dog, Cat, Bird): ");
                results = center.findPetsBySpecies(species);
                break;
            case 2:
                String breed = getStringInput("Enter breed: ");
                results = center.findPetsByBreed(breed);
                break;
            case 3:
                int maxAge = getIntInput("Enter maximum age: ");
                results = center.findPetsByAge(maxAge);
                break;
            default:
                System.out.println("Invalid search type.");
                return;
        }

        if (results.isEmpty()) {
            System.out.println("No pets found matching your criteria.");
        } else {
            System.out.println("\n===== Search Results =====");
            for (Pet pet : results) {
                System.out.println(pet);
                System.out.println("-----------------");
            }
        }
    }

    private static void registerAdopter() {
        System.out.println("\n===== Register New Adopter =====");
        String name = getStringInput("Enter adopter name: ");
        String contactInfo = getStringInput("Enter contact information: ");

        Adopter adopter = center.addAdopter(name, contactInfo);
        System.out.println("Registered new adopter: " + adopter);
    }

    private static void processAdoption() {
        System.out.println("\n===== Process Adoption =====");

        displayAvailablePets();

        List<Adopter> adopters = center.getAllAdopters();
        if (adopters.isEmpty()) {
            System.out.println("There are no registered adopters.");
            return;
        }

        System.out.println("\n===== Registered Adopters =====");
        for (Adopter adopter : adopters) {
            System.out.println(adopter);
        }

        int petId = getIntInput("Enter pet ID to adopt: ");
        int adopterId = getIntInput("Enter adopter ID: ");

        try {
            center.processPetAdoption(petId, adopterId);
        } catch (Exception e) {
            System.err.println("Error processing adoption: " + e.getMessage());
        }
    }

    private static void displayAdopters() {
        List<Adopter> adopters = center.getAllAdopters();
        if (adopters.isEmpty()) {
            System.out.println("There are no registered adopters.");
            return;
        }

        System.out.println("\n===== All Adopters =====");
        for (Adopter adopter : adopters) {
            System.out.println(adopter);
            System.out.println("Adopted Pets:");

            List<Pet> adoptedPets = adopter.getAdoptedPets();
            if (adoptedPets.isEmpty()) {
                System.out.println("  None");
            } else {
                for (Pet pet : adoptedPets) {
                    System.out.println("  " + pet.getName() + " (" + pet.getSpecies() + ")");
                }
            }
            System.out.println("-----------------");
        }
    }

    private static void populateSampleData() {
        center.addDog("Buddy", 3, "Golden Retriever", true);
        center.addDog("Max", 2, "German Shepherd", true);
        center.addCat("Whiskers", 4, "Siamese", true);
        center.addCat("Mittens", 1, "Persian", true);
        center.addBird("Polly", 2, "Parrot", true);
        center.addBird("Tweety", 1, "Canary", false);

        center.addAdopter("John Smith", "john@example.com");
        center.addAdopter("Jane Doe", "jane@example.com");
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
            System.out.print(prompt);
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return input;
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static boolean getBooleanInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().toLowerCase();
        return input.startsWith("y");
    }
}