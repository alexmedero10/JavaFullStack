package models;

public class Bird extends Pet {
    private boolean canTalk;

    public Bird(int petId, String name, int age, String breed, boolean canTalk) {
        super(petId, name, "Bird", age, breed);
        this.canTalk = canTalk;
    }

    public boolean canTalk() { return canTalk; }
    public void setCanTalk(boolean canTalk) { this.canTalk = canTalk; }

    @Override
    public String makeSound() {
        return canTalk ? getName() + " says 'Hello there!'" : getName() + " chirps melodiously!";
    }

    @Override
    public String toString() {
        return super.toString() + ", Can Talk: " + canTalk;
    }
}
