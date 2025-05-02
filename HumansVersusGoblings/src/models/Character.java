package models;

public abstract class Character extends Entity {

    protected String name;
    protected int health;
    protected int attack;
    protected boolean isAlive;

    public Character(int x, int y, String name, int health, int attack) {
        super(x, y);
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.isAlive = true;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public boolean getAlive() {
        return  isAlive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void receiveAttack(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            isAlive = false;
        }
    }

    public abstract boolean move(World world);

}
