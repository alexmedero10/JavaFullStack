package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Human extends Character {

    private Scanner scanner;
    private List<String> inventory;

    public Human(int x, int y, String name, int health, int attack) {
        super(x, y, name, health, attack);
        inventory = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void addToInventory(String item) {
        inventory.add(item);
    }

    public void removeFromInventory(String item) {
        for (int i=0; i<inventory.size(); i++) {
            if(inventory.get(i).equals(item)) {
                inventory.remove(i);
                break;
            }
        }
    }

    @Override
    public boolean move(World world) {
        System.out.print("""
                MOVE UP ( N )
                MOVE DOWN ( S )
                MOVE LEFT ( W )
                MOVE RIGHT ( E )
                YOUR MOVE: """);

        String move = scanner.nextLine();

        int newX = x, newY = y;
        switch (move) {
            case "N":
                newY--;
                break;
            case "S":
                newY++;
                break;
            case "W":
                newX--;
                break;
            case "E":
                newX++;
                break;
            default:
                System.out.println("Invalid input");
                return false;
        }

        if (world.isValidPosition(newX, newY)) {
            return world.moveCharacter(this, newX, newY);
        } else {
            System.out.println("Can't move to the grid");
        }

        return false;
    }

    public String toString() {
        return "H";
    }
}

