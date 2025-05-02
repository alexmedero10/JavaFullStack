import models.Goblin;
import models.Human;
import models.World;

public class Main {
    public static void main(String[] args) {

        World world = new World();
        Human human = new Human(0,0, "Alex", 100,25);

        world.addCharacter(human);

        Goblin goblin1 = new Goblin(4,4, "PEPE", 75,10);
        Goblin goblin2 = new Goblin(1, 8, "LOLO", 90, 5);
        Goblin goblin3 = new Goblin(5,1, "RARA", 125, 15);

        world.addCharacter(goblin1);
        world.addCharacter(goblin2);
        world.addCharacter(goblin3);

        while (human.getAlive() && !world.goblins.isEmpty()) {
            System.out.println(world);
            human.move(world);

            for (int i=0; i<world.goblins.size(); i++) {
                System.out.println(world);
                if (world.goblins.get(i).move(world)) {
                    world.goblins.remove(i);
                    i--;
                }
            }
        }

        if (world.goblins.isEmpty()) {
            System.out.println("YOU WIN");
        } else {
            System.out.println("YOU LOSE");
        }
    }
}