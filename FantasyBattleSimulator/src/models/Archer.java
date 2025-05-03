package models;

public class Archer extends Entity {

    private final int valueActivateSpecialAbility = 15;

    public Archer(String name, int health, int attack, int defend, String specialAbilityName, int specialAbility) {
        super(name, health, attack, defend, specialAbilityName, specialAbility);
    }

    @Override
    public int attack() {

        if (isActiveSpecialAbility) {
            setActiveSpecialAbility(false);
            System.out.println("USING SPECIAL ABILITY (" + specialAbilityName + ")");
            return attack + specialAbility;
        }

        return attack;
    }

    @Override
    public void activateSpecialAbility(int randomValue) {
        if (valueActivateSpecialAbility == randomValue) {
            isActiveSpecialAbility = true;
        }
    }

    @Override
    public void receiveDamage(int damage) {
        health -= damage;

        if (health <= 0) {
            isAlive = false;
            health = 0;
        }

        System.out.println("DAMAGE RECEIVED " + name + "\nHEALTH - " + health);
    }

}
