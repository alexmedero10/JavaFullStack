package models;

public class Land extends Entity {

    private Character characterInLand;

    public Land(int x, int y) {
        super(x, y);
        characterInLand = null;
    }

    public Character getCharacterInLand() {
        if (characterInLand == null) return null;
        else return characterInLand;
    }

    public void setCharacterInLand(Character character) {
        characterInLand = character;
    }

    public String toString() {
        return ".";
    }

}
