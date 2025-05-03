import exceptions.CharacterNotFoundException;
import models.Archer;
import models.Entity;
import models.Warrior;
import models.Wizard;

import java.util.Random;
import java.util.Scanner;

public class FantasyBattle {

    private static final Random random = new Random();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Entity player, enemy;
        int option;

        do {
            System.out.print("""
                    SELECT CHARACTER
                    
                    1) ARCHER
                    2) WARRIOR
                    3) WIZARD
                    
                    YOUR OPTION: """);

            option = scanner.nextInt();
        } while(option < 1 || option > 3);

        try {
            player = choosePlayer(option);
            enemy = choosePlayer(random.nextInt(1, 4));
        } catch (CharacterNotFoundException e) {
            throw new RuntimeException(e);
        }


        startBattle(player, enemy);

        if (!player.getAlive()) {
            System.out.println(player.getName() + " has been defeated! " + enemy.getName() + " wins!");
        } else {
            System.out.println(enemy.getName() + " has been defeated! " + player.getName() + " wins!");
        }

    }

    public static Entity choosePlayer(int option) throws CharacterNotFoundException {
        return switch (option) {
            case 1 -> new Archer("JUDITH", 85, 10, 15, "GHOST ARROW", 15);
            case 2 -> new Warrior("MIROS", 150, 15, 30, "IRON FIST", 15);
            case 3 -> new Wizard("ACE", 100, 20, 35, "RECOVER HEALTH", 20);
            default -> throw new CharacterNotFoundException("CHOOSE A CHARACTER IN RANGE 1-3");
        };
    }

    public static void startBattle(Entity player, Entity enemy) {
        boolean turnPlayer = random.nextInt(2) == 1;

        while (player.getAlive() && enemy.getAlive()) {

            if (turnPlayer) {
                player.activateSpecialAbility(random.nextInt(20));
                enemy.receiveDamage(player.attack());
            } else {
                enemy.activateSpecialAbility(random.nextInt(20));
                player.receiveDamage(enemy.attack());
            }

            turnPlayer = !turnPlayer;
        }

    }

}