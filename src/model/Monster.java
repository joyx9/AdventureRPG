package model;


public class Monster extends Entity{


    public Monster(String name, int hitPoint, int damage, int speed) {
        super(name, hitPoint, damage, speed);
    }

    // MODIFIES: p
    // EFFECTS: player hp lowers by value of monster's attack
    //          monster attack is their base damage + 1d4
    @Override
    public void attack(Player p, Monster m) {
        this.damage = m.damage + 1 + rm.nextInt(4);
        this.hitPoint = p.getHitPoint();
        this.hitPoint -= this.damage;
        p.setHitPoint(this.hitPoint);

        System.out.println("Monster attacked " + p.getName() + " for " + this.damage + " damage!");
    }
}
