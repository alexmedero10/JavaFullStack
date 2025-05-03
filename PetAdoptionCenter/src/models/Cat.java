package models;

public class Cat extends Pet {
    private boolean indoorOnly;

    public Cat(int petId, String name, int age, String breed, boolean indoorOnly) {
        super(petId, name, "Cat", age, breed);
        this.indoorOnly = indoorOnly;
    }

    public boolean isIndoorOnly() { return indoorOnly; }
    public void setIndoorOnly(boolean indoorOnly) { this.indoorOnly = indoorOnly; }

    @Override
    public String makeSound() {
        return getName() + " purrs contentedly!";
    }

    @Override
    public String toString() {
        return super.toString() + ", Indoor Only: " + indoorOnly;
    }
}
