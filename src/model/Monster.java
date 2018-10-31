package model;


public class Monster extends Entity{
    private Item monsterDrop;

    public Monster(String name, int hitPoint, int damage, int speed) {
        super(name, hitPoint, damage, speed);
        monsterDrop = new Item("Green Jello", "Looks tasty!", "Monster Drops");
    }

    // MODIFIES: p
    // EFFECTS: player hp lowers by value of monster's attack
    //          monster attack is their base damage + 1d4
    @Override
    public void attack(Player p, Monster m) {
        this.damage = m.damage + 1 + rm.nextInt(4);
        p.setHitPoint(p.getHitPoint() - this.damage);
        System.out.println("Monster attacked " + p.getName() + " for " + this.damage + " damage!");
    }

    public Item getMonsterDrop(){
        return monsterDrop;
    }
}
