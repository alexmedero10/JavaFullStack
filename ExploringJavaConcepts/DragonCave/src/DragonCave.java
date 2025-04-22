import java.util.Scanner;

public class DragonCave {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isAlive = true;

        System.out.println(
                "You are in a land full of dragons. In front of you,\n" +
                "You see two caves. In one cave, the dragon is friendly\n" +
                "and will share his treasure with you. The other dragon\n" +
                "is greedy and hungry and will eat you on sight.\n" +
                "Which cave will you go into? (1 or 2)"
        );

        switch (scanner.nextInt()) {
            case 1:
                System.out.println(
                        "You approach the cave…\n" +
                        "It is dark and spooky…\n" +
                        "A large dragon jumps out in front of you!\n" +
                        "He opens his jaws and…\n" +
                        "Gobbles you down in one bite!"
                );
                isAlive = false;
                break;
            case 2:
                System.out.println(
                        "You sneak into the cave...\n" +
                        "A golden light begins to give light to the cave...\n" +
                        "You see a treasure chest in the distance.\n" +
                        "What are you up to?\n" +
                        "You run to the treasure and take whatever you can get your hands on. (1)\n" +
                        "You say you come in peace and wait for them to answer you. (2)");
                break;
            default:
                System.out.println("Solo opción 1 o 2");
        }

        if (!isAlive) return;

        switch (scanner.nextInt()) {
            case 1:
                System.out.println("The dragon sees you running with its treasure...\n" +
                        "It begins to chase you...\n" +
                        "It throws a fireball at you that hits you.\n" +
                        "It's over.");
                break;
            case 2:
                System.out.println("You hear them respond to you with a rusty...\n" +
                        "You get paralyzed and you can't run...\n" +
                        "The dragon comes out and slowly approaches you - it seems to like you.\n" +
                        "He lets you go out with a bit of the treasure on the condition that you don't tell anyone his hiding place.");
                break;
            default:
                System.out.println("Solo opción 1 o 2");
        }
    }
}