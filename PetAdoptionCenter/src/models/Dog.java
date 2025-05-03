package models;

public class Dog extends Pet {
    private boolean housetrained;

    public Dog(int petId, String name, int age, String breed, boolean housetrained) {
        super(petId, name, "Dog", age, breed);
        this.housetrained = housetrained;
    }

    public boolean isHousetrained() { return housetrained; }
    public void setHousetrained(boolean housetrained) { this.housetrained = housetrained; }

    @Override
    public String makeSound() {
        return getName() + " barks enthusiastically!";
    }

    @Override
    public String toString() {
        return super.toString() + ", Housetrained: " + housetrained;
    }
}
