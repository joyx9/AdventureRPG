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
                else {
                    System.out.println("The monster was defeated.");
                }

            }

            else if(action == 2){
                System.out.println("You examine the monster. It appears to have "+monster.hitPoint+ " HP.");
            }

            else {
                System.out.println("You ran away! Maybe another day you'll defeat it...");
            }
        }
        if(player.hitPoint <= 0){
            System.out.println("Hogh...you draw your last breath at the monster's final blow...");
            System.out.println("Should've stayed in school...");
        }

    }

    private void playerAttack(Player player, Monster monster) {
        monster.hitPoint -= player.damage;
        System.out.println("You attacked the monster! You dealt "+player.damage +" damage!");
    }

    private void monsterAttack(Player player, Monster monster) {
        player.hitPoint -= monster.damage;
        System.out.println("Monster attacked for "+monster.damage + " damage!");
    }


    public static void main(String[] args) {
        new battleStart();
    }
}
