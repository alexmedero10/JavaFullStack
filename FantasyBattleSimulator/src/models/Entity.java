package models;

public abstract class Entity {

    protected String name;
    protected int health;
    protected int attack;
    protected int defend;
    protected String specialAbilityName;
    protected int specialAbility;
    protected boolean isActiveSpecialAbility;
    protected boolean isAlive;

    public Entity(String name, int health, int attack, int defend, String specialAbilityName, int specialAbility) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defend = defend;
        this.specialAbilityName = specialAbilityName;
        this.specialAbility = specialAbility;
        this.isActiveSpecialAbility = false;
        this.isAlive = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefend() {
        return defend;
    }

    public void setDefend(int defend) {
        this.defend = defend;
    }

    public String getSpecialAbilityName() {
        return specialAbilityName;
    }

    public void setSpecialAbilityName(String specialAbilityName) {
        this.specialAbilityName = specialAbilityName;
    }

    public int getSpecialAbility() {
        return specialAbility;
    }

    public void setSpecialAbility(int specialAbility) {
        this.specialAbility = specialAbility;
    }

    public boolean getActiveSpecialAbility() {
        return isActiveSpecialAbility;
    }

    public void setActiveSpecialAbility(boolean isActiveSpecialAbility) {
        this.isActiveSpecialAbility = isActiveSpecialAbility;
    }

    public boolean getAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }


    public abstract int attack();

    public abstract void activateSpecialAbility(int randomValue);

    public abstract void receiveDamage(int damage);


}
