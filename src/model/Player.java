package model;

public class Player {
    private int hitPoint;
    private int damage;
    private int speed;
    private String name;

    // REQUIRES: all int values > 0
    // EFFECTS: Constructs a player character with given HP, damage, speed, and name
    public Player(String name, int hitPoint, int damage, int speed) {
        this.hitPoint = hitPoint;
        this.damage = damage;
        this.speed = speed;
        this.name = name;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    //TODO: HP should not go above original set HP
    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    // EFFECTS: returns true if player's hitPoint is above 0 and is therefore alive
    public boolean isAlive() {
        if(this.hitPoint > 0) {
            return true;
        }
        return false;
    }


    //TODO: level up system?
}
