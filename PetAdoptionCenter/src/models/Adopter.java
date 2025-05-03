package models;

import java.util.ArrayList;
import java.util.List;

public class Adopter {

    private int adopterId;
    private String name;
    private String contactInfo;
    private List<Pet> adoptedPets;

    public Adopter(int adopterId, String name, String contactInfo) {
        this.adopterId = adopterId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.adoptedPets = new ArrayList<>();
    }

    public int getAdopterId() { return adopterId; }
    public String getName() { return name; }
    public String getContactInfo() { return contactInfo; }
    public List<Pet> getAdoptedPets() { return adoptedPets; }

    public void setName(String name) { this.name = name; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public void adoptPet(Pet pet) {
        if (!pet.isAvailable()) {
            throw new IllegalStateException("Pet is not available for adoption");
        }
        pet.adopt(this);
        adoptedPets.add(pet);
    }

    @Override
    public String toString() {
        return String.format("Adopter ID: %d, Name: %s, Contact: %s, Adopted Pets: %d",
                adopterId, name, contactInfo, adoptedPets.size());
    }
}

