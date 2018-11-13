package model;


public class Player extends Entity {

    // REQUIRES: all int values > 0
    // EFFECTS: Constructs a player character with given HP, damage, speed, and name
    public Player(String name, int hitPoint, int damage, int speed) {
        super(name,hitPoint,damage,speed);
    }



    // MODIFIES: m
    // EFFECTS: monsters hp lowers by value of player's attack
    //          player attack is their base damage + 1d6
    public void attack(Monster m) {
        //generate random damage for attack
        int damage = this.getDamage() + 1 + rm.nextInt(6);
        m.setHitPoint(m.getHitPoint()-damage);
        System.out.println(this.getName()+" attacked the monster! Dealt " + damage +" damage!");
    }

    // MODIFIES: healed
    // EFFECTS: healer player increases hp of healed player by heal value if healed player's HP does not go over maxHP
    //          otherwise, throws HPOutOfBoundsException
    //          heal value is healer's base damage + 1d4
    public void playerHeal(model.Player healed) {
        int heal = this.getDamage() + 1 + rm.nextInt(4);

        if (healed.getHitPoint() + heal > healed.getMaxHP()){
            heal = healed.getMaxHP() - healed.getHitPoint();
            healed.setHitPoint(healed.getMaxHP());
        }
        else
            healed.setHitPoint(healed.getHitPoint() + heal);

        System.out.println(healed.getName() +" was healed for " + heal + " points! " +
                healed.getName() +" now has " + healed.getHitPoint() +" HP.");


    }

    // EFFECTS: returns monster's hp and name in dialogue
    public void examine(Monster m) {
        System.out.println(this.getName() + " examined the monster.");
        System.out.println("It's a " + m.getName() + "! It appears to have " + m.getHitPoint() + " HP left.");
    }


}
