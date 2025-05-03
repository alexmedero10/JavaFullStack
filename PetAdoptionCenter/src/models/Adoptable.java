package models;

public interface Adoptable {
    boolean isAvailable();
    void adopt(Adopter adopter);
}
