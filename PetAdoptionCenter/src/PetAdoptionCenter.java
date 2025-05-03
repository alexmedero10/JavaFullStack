import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetAdoptionCenter {

    private String centerName;
    private Map<Integer, Pet> pets;
    private Map<Integer, Adopter> adopters;
    private int nextPetId;
    private int nextAdopterId;

    public PetAdoptionCenter(String centerName) {
        this.centerName = centerName;
        this.pets = new HashMap<>();
        this.adopters = new HashMap<>();
        this.nextPetId = 1;
        this.nextAdopterId = 1;
    }

    public Pet addPet(Pet pet) {
        pets.put(pet.getPetId(), pet);
        return pet;
    }

    public Dog addDog(String name, int age, String breed, boolean housetrained) {
        Dog dog = new Dog(nextPetId++, name, age, breed, housetrained);
        pets.put(dog.getPetId(), dog);
        return dog;
    }

    public Cat addCat(String name, int age, String breed, boolean indoorOnly) {
        Cat cat = new Cat(nextPetId++, name, age, breed, indoorOnly);
        pets.put(cat.getPetId(), cat);
        return cat;
    }

    public Bird addBird(String name, int age, String breed, boolean canTalk) {
        Bird bird = new Bird(nextPetId++, name, age, breed, canTalk);
        pets.put(bird.getPetId(), bird);
        return bird;
    }

    public void updatePet(Pet pet) {
        if (!pets.containsKey(pet.getPetId())) {
            throw new IllegalArgumentException("Pet not found in the system");
        }
        pets.put(pet.getPetId(), pet);
    }

    public void removePet(int petId) {
        if (!pets.containsKey(petId)) {
            throw new IllegalArgumentException("Pet not found in the system");
        }
        pets.remove(petId);
    }

    public Pet getPet(int petId) {
        if (!pets.containsKey(petId)) {
            throw new IllegalArgumentException("Pet not found in the system");
        }
        return pets.get(petId);
    }

    public List<Pet> getAllPets() {
        return new ArrayList<>(pets.values());
    }

    public List<Pet> getAvailablePets() {
        return pets.values().stream()
                .filter(Pet::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Pet> findPetsBySpecies(String species) {
        return pets.values().stream()
                .filter(pet -> pet.getSpecies().equalsIgnoreCase(species))
                .collect(Collectors.toList());
    }

    public List<Pet> findPetsByBreed(String breed) {
        return pets.values().stream()
                .filter(pet -> pet.getBreed().equalsIgnoreCase(breed))
                .collect(Collectors.toList());
    }

    public List<Pet> findPetsByAge(int maxAge) {
        return pets.values().stream()
                .filter(pet -> pet.getAge() <= maxAge)
                .collect(Collectors.toList());
    }

    public Adopter addAdopter(String name, String contactInfo) {
        Adopter adopter = new Adopter(nextAdopterId++, name, contactInfo);
        adopters.put(adopter.getAdopterId(), adopter);
        return adopter;
    }

    public void updateAdopter(Adopter adopter) {
        if (!adopters.containsKey(adopter.getAdopterId())) {
            throw new IllegalArgumentException("Adopter not found in the system");
        }
        adopters.put(adopter.getAdopterId(), adopter);
    }

    public Adopter getAdopter(int adopterId) {
        if (!adopters.containsKey(adopterId)) {
            throw new IllegalArgumentException("Adopter not found in the system");
        }
        return adopters.get(adopterId);
    }

    public List<Adopter> getAllAdopters() {
        return new ArrayList<>(adopters.values());
    }

    public void processPetAdoption(int petId, int adopterId) {
        Pet pet = getPet(petId);
        Adopter adopter = getAdopter(adopterId);

        if (!pet.isAvailable()) {
            throw new IllegalStateException("Pet is not available for adoption");
        }

        adopter.adoptPet(pet);
        System.out.println("Adoption successful! " + adopter.getName() + " has adopted " + pet.getName());
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Data saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public static PetAdoptionCenter loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            PetAdoptionCenter center = (PetAdoptionCenter) in.readObject();
            System.out.println("Data loaded successfully from " + filename);
            return center;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
            return new PetAdoptionCenter("New Pet Adoption Center");
        }
    }
}
