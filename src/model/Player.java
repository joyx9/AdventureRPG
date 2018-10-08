package model;

public class Player extends Entity{

    // REQUIRES: all int values > 0
    // EFFECTS: Constructs a player character with given HP, damage, speed, and name
    public Player(String name, int hitPoint, int damage, int speed) {
        super(name,hitPoint,damage,speed);
    }

    //TODO: HP should not go above original set HP
    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    // MODIFIES: m
    // EFFECTS: monsters hp lowers by value of player's attack
    //          player attack is their base damage + 1d6
    @Override
    public void attack(Player p, Monster m) {
        //generate random damage for attack
        this.damage = p.getDamage() + 1 + rm.nextInt(6);
        m.hitPoint -= this.damage;
        System.out.println(p.getName()+" attacked the monster! Dealt " + this.damage +" damage!");
    }

    // MODIFIES: healed
    // EFFECTS: healer player increases hp of healed player by heal value
    //          heal value is healer's base damage + 1d4
    public void playerHeal(Player healer, Player healed) {
        int heal = healer.getDamage() + 1 + rm.nextInt(4);
        healed.setHitPoint(healed.getHitPoint() + heal);

        System.out.println(healed.getName() +" was healed for " + heal + " points!");
    }

    // EFFECTS: returns monster's hp and name in dialogue
    public void examine(Monster m) {
        System.out.println("You examine the monster.");
        System.out.println("It's a " + m.getName() + "! It appears to have " + m.getHitPoint() + " HP left.");
    }

    // EFFECTS: returns true if player's hitPoint is above 0 and is therefore alive


    //TODO: level up system?
}
