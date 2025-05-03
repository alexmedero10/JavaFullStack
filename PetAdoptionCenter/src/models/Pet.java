package models;

public abstract class Pet implements Adoptable {

    private int petId;
    private String name;
    private String species;
    private int age;
    private String breed;
    private String adoptionStatus; // "Available", "Adopted", "Under Medical Care"
    private Adopter adopter;

    public Pet(int petId, String name, String species, int age, String breed) {
        this.petId = petId;
        this.name = name;
        this.species = species;
        this.age = age;
        this.breed = breed;
        this.adoptionStatus = "Available";
    }

    // Getters and setters
    public int getPetId() { return petId; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public String getBreed() { return breed; }
    public String getAdoptionStatus() { return adoptionStatus; }
    public Adopter getAdopter() { return adopter; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setBreed(String breed) { this.breed = breed; }
    public void setAdoptionStatus(String adoptionStatus) { this.adoptionStatus = adoptionStatus; }

    // Adoptable interface methods
    @Override
    public boolean isAvailable() {
        return "Available".equals(adoptionStatus);
    }

    @Override
    public void adopt(Adopter adopter) {
        if (!isAvailable()) {
            throw new IllegalStateException("Pet is not available for adoption");
        }
        this.adopter = adopter;
        this.adoptionStatus = "Adopted";
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Species: %s, Age: %d, Breed: %s, Status: %s",
                petId, name, species, age, breed, adoptionStatus);
    }

    // Method to describe pet behavior - will be overridden by subclasses
    public String makeSound() {
        return "The pet makes a sound";
    }
}
