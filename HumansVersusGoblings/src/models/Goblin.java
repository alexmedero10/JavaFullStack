package models;

import java.util.Random;
import java.util.Scanner;

public class Goblin extends Character {

    private Random random;

    public Goblin(int x, int y, String name, int health, int attack) {
        super(x, y, name, health, attack);
        random = new Random();
    }

    public void generateDrop() {

        int drop = random.nextInt(5);

        switch (drop) {
            case 1:
                System.out.println("HEALTH +20");
                setHealth(getHealth()+20);
                break;
            case 2:
                System.out.println("HEALTH +5 - ATTACK +5");
                setHealth(getHealth()+5);
                setAttack(getAttack()+5);
                break;
            case 3:
                System.out.println("HEALTH +10 - ATTACK +5");
                setHealth(getHealth()+10);
                setAttack(getAttack()+5);
                break;
            case 4:
                System.out.println("HEALTH +5 - ATTACK +10");
                setHealth(getHealth()+5);
                setAttack(getAttack()+10);
                break;
            case 5:
                System.out.println("ATTACK +20");
                setAttack(getAttack()+20);
                break;
            default:
                System.out.println("ERROR IN DROP");
        }

    }

    @Override
    public boolean move(World world){
        int newX, newY;
        do {
            newX = random.nextInt(3) - 1; // genera -1, 0, o 1
            newY = random.nextInt(3) - 1;
        } while(newX == 0 && newY == 0);

        newX += x;
        newY += y;

        if (world.isValidPosition(newX, newY)) {
            return world.moveCharacter(this, newX, newY);
        } else {
            System.out.println("Can't move to the grid");
        }

        return false;
    }

    public String toString() {
        return "G";
    }

}
