package model;



public class Monster extends Entity {
    private Item monsterDrop;
    private String description;

    public Monster(String name, int hitPoint, int damage, int speed, String description) {
        super(name, hitPoint, damage, speed);
        this.description = description;
        monsterDrop = new Item("Green Jello", "Green and blobby jello...looks tasty!",
                                "Monster Drops");
    }

    // MODIFIES: p
    // EFFECTS: player hp lowers by value of monster's attack
    //          monster attack is their base damage + 1d4
    public String attack(Player p) {
        int damage = this.getDamage() + 1 + rm.nextInt(4);
        p.setHitPoint(p.getHitPoint() - damage);
        return ("\n " + this.getName() + " attacked " + p.getName() +
                " for " +  damage + " damage!");
    }

    public Item getMonsterDrop(){
        return monsterDrop;
    }

    public String getMonsterDescription(){
        return description;
    }
}
