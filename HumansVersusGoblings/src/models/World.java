package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    private final int size = 10;
    private Land[][] grid = new Land[size][size];
    public final List<Goblin> goblins;

    public World() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Land(i, j);
            }
        }
        goblins = new ArrayList<>();
    }

    public void addCharacter(Character character) {
        if (character instanceof Goblin)
            goblins.add((Goblin) character);

        grid[character.getX()][character.getY()].setCharacterInLand(character);
    }

    public boolean isValidPosition(int newX, int newY) {
        return newX >= 0 && newY >= 0 && newX < size && newY < size;
    }

    public boolean moveCharacter(Character character, int newX, int newY) {
        Character characterInLand = grid[newX][newY].getCharacterInLand();

        if(character instanceof Human) {
            if (characterInLand instanceof Goblin) {
                if (fight(character, characterInLand)) {
                    grid[character.getX()][character.getY()].setCharacterInLand(null);
                    character.setPosition(newX, newY);
                    grid[newX][newY].setCharacterInLand(character);
                    goblins.remove(characterInLand);
                    return true;
                } else {
                    System.out.println("The goblins conquer the world. YOU LOSE");
                }
            } else if (characterInLand instanceof Human) {
                System.out.println("Can't move, there is another human there.");
            } else {
                grid[character.getX()][character.getY()].setCharacterInLand(null);
                character.setPosition(newX, newY);
                grid[newX][newY].setCharacterInLand(character);
            }
        } else if(character instanceof Goblin) {
            if (characterInLand instanceof Human) {
                if (fight(characterInLand, character)) {
                    grid[character.getX()][character.getY()].setCharacterInLand(null);
                    return true;
                } else {
                    System.out.println("The goblins conquer the world. YOU LOSE");
                }
            } else if (characterInLand instanceof Goblin) {
                System.out.println("Can't move, there is another goblin there.");
            } else {
                grid[character.getX()][character.getY()].setCharacterInLand(null);
                character.setPosition(newX, newY);
                grid[newX][newY].setCharacterInLand(character);
            }
        }

        return false;
    }

    public boolean fight(Character human, Character goblin) {
        Random random = new Random();
        int attack;

        while (human.getAlive() && goblin.getAlive()) {
            attack = random.nextInt(human.getAttack());
            goblin.receiveAttack(attack);
            if (!goblin.getAlive()) break;
            attack = random.nextInt(goblin.getAttack());
            human.receiveAttack(attack);
        }

        if (!goblin.getAlive()) {
            grid[goblin.getX()][goblin.getY()].setCharacterInLand(null);
            return true;
        }

        if (!human.getAlive()) {
            grid[human.getX()][human.getY()].setCharacterInLand(null);
            human.setAlive(false);
            return false;
        }

        return true;
    }

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                if (grid[j][i].getCharacterInLand() != null)
                    stringBuilder.append(grid[j][i].getCharacterInLand());
                else stringBuilder.append(grid[j][i]);
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

}
