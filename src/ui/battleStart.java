package ui;

import model.Monster;
import model.Player;
import java.util.Scanner;


public class battleStart {
    Scanner scanner = new Scanner(System.in);

    public battleStart(){
        Monster monster = new Monster();
        Player player = new Player();
        int action = 0;
        System.out.println("Enemy monster appeared! What will you do?");
        while(player.hitPoint > 0 && monster.hitPoint > 0 && action != 3){
            System.out.println("You have "+player.hitPoint +" HP. You may: [1]attack, [2]examine, or [3]flee");
            action = scanner.nextInt();

            if(action == 1){
                playerAttack(player, monster);
                if(monster.hitPoint > 0){
                    monsterAttack(player, monster);
                }
                else System.out.println("The monster was defeated.");

            }

            else if(action == 2){
                int monsterHP = examine(monster);
                System.out.println("It appears to have " +monsterHP+ " HP.");
            }

            else System.out.println("You ran away! Maybe another day you'll defeat it...");
        }
        if(player.hitPoint <= 0){
            System.out.println("Hogh...you draw your last breath at the monster's final blow...");
            System.out.println("Should've stayed in school...");
        }

    }

    private void playerAttack(Player p, Monster m) {
        m.hitPoint -= p.damage;
        System.out.println("You attacked the monster! You dealt "+p.damage +" damage!");
    }

    private void monsterAttack(Player p, Monster m) {
        p.hitPoint -= m.damage;
        System.out.println("Monster attacked for "+m.damage + " damage!");
    }

    private int examine(Monster m){
        System.out.println("You examine the monster.");
        return m.hitPoint;
    }
    public static void main(String[] args) {
        new battleStart();
    }
}
