package game;

import java.util.Scanner;

import character.Enemy;
import character.Player;
import utils.Utils;

public class Dungeon {
    public static void battle(Player p, Scanner sc){
        Enemy e = Enemy.randomEnemy(p.floor);
        System.out.println("\n--- Floor "+p.floor+" ---");
        System.out.println("A wild "+e.name+" appears!\n");

        while(p.isAlive() && e.isAlive()){
            System.out.println(p.name+" HP:"+p.hp+"/"+p.maxHp+(p.classType==Player.ClassType.MAGE?" | Mana:"+p.mana+"/"+p.maxMana:""));
            System.out.println(e.name+" HP:"+e.hp+"\n");

            System.out.println("1) Attack  \n2) Heal  \n3) Fireball(if Mage)  \n4) Use Item");
            System.out.print("> ");
            int choice=Utils.safeNextInt(sc);

            if(choice==1){ int dmg=Math.max(1,p.attack-e.defense); e.hp-=dmg; System.out.println("You hit "+e.name+" for "+dmg); }
            else if(choice==2){ int heal=6+p.level; p.hp=Math.min(p.maxHp,p.hp+heal); System.out.println("You heal for "+heal); }
            else if(choice==3 && p.classType==Player.ClassType.MAGE){ 
                if(p.mana>=5){ int dmg=10+p.level*2; p.mana-=5; e.hp-=dmg; System.out.println("Fireball deals "+dmg+"!"); } 
                else { System.out.println("Not enough mana!"); } 
            }
            else if(choice==4){ p.showInventory(); System.out.println("Item index or -1:"); int idx=Utils.safeNextInt(sc); if(idx>=0)p.useItem(idx); }
            else System.out.println("Invalid action");

            if(e.isAlive()){ int dmg=Math.max(1,e.attack-p.defense); p.hp-=dmg; System.out.println(e.name+" hits you for "+dmg+"\n"); }
        }

        if(p.isAlive()){ 
            System.out.println("You defeated "+e.name+"!"); 
            p.gainExp(e.expReward);
            Item drop=Item.randomDrop(p.floor); 
            if(drop!=null){ System.out.println("You found: "+drop.name); p.addItem(drop); } 
            else System.out.println("No loot.");
            p.floor++; System.out.println("Descend to floor "+p.floor+"\n");
        } else { System.out.println("You died on floor "+p.floor); }
    }
}
