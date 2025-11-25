package game;

import java.util.Scanner;

import character.Enemy;
import character.Player;
import utils.Utils;

public class Dungeon {
	public static void battle(Player p, Scanner sc){
	    Enemy e = Enemy.randomEnemy(p.floor);
	    System.out.println("\n--- Floor " + p.floor + " ---");
	    System.out.println("A wild " + e.name + " emerges from the shadows!\n");

	    boolean defending = false;

	    while(p.isAlive() && e.isAlive()){
	        System.out.println(p.name + " HP:" + p.hp + "/" + p.maxHp + 
	                           (p.classType == Player.ClassType.MAGE ? " | Mana:" + p.mana + "/" + p.maxMana : ""));
	        System.out.println(e.name + " HP:" + e.hp + "\n");

	        System.out.println("1) Attack");
	        System.out.println("2) Defend");
	        System.out.println("3) Skill");
	        System.out.println("4) Flee");
	        System.out.print("> ");

	        int choice = Utils.safeNextInt(sc);

	        switch(choice){
	            case 1: // Attack
	                int dmg = Math.max(1, p.attack - e.defense);
	                e.hp -= dmg;
	                System.out.println("You attack " + e.name + " for " + dmg + " damage!");
	                break;

	            case 2: // Defend
	                defending = true;
	                System.out.println("You brace yourself for the next attack!");
	                break;

	            case 3: // Skill
	                p.showSkills();
	                int skillChoice = Utils.safeNextInt(sc);
	                int skillDmg = 0;
	                if(skillChoice == 1){
	                    skillDmg = Math.max(1, p.attack - e.defense);
	                    System.out.println("Basic Attack hits " + e.name + " for " + skillDmg + " damage!");
	                } else if(skillChoice == 2 || skillChoice == 3){
	                    // Handle class skills
	                    switch(p.classType){
	                        case KNIGHT:
	                            skillDmg = (skillChoice == 2 ? 5 + p.level*2 : 8 + p.level*3); 
	                            System.out.println((skillChoice==2?"Shield Bash":"Power Strike")+" deals "+skillDmg+" damage!");
	                            break;
	                        case ARCHER:
	                            skillDmg = (skillChoice == 2 ? 6 + p.level*2 : 10 + p.level*2);
	                            System.out.println((skillChoice==2?"Quick Shot":"Rain of Arrows")+" deals "+skillDmg+" damage!");
	                            break;
	                        case MAGE:
	                            if(p.mana >= 5){
	                                skillDmg = (skillChoice == 2 ? 8 + p.level*2 : 12 + p.level*3);
	                                p.mana -= 5;
	                                System.out.println((skillChoice==2?"Ice Shard":"Fireball")+" deals "+skillDmg+" damage!");
	                            } else {
	                                System.out.println("Not enough mana!");
	                                skillDmg = 0;
	                            }
	                            break;
	                    }
	                }
	                e.hp -= skillDmg;
	                break;

	            case 4: // Flee
	                if(Math.random() < 0.5){
	                    System.out.println("You successfully fled from " + e.name + "!");
	                    return;
	                } else {
	                    System.out.println("Failed to escape!");
	                }
	                break;

	            default:
	                System.out.println("Invalid action! Choose 1-4.");
	                continue;
	        }

	        // Enemy turn
	        if(e.isAlive()){
	            int dmg = Math.max(1, e.attack - (defending ? p.defense*2 : p.defense));
	            p.hp -= dmg;
	            System.out.println(e.name + " attacks you for " + dmg + " damage!\n");
	        }

	        defending = false;
	    }

	    if(p.isAlive()){ 
	        System.out.println("You defeated "+e.name+"!");
	        p.gainExp(e.expReward);

	        Item drop = Item.randomDrop(p.floor);
	        if(drop != null){ 
	            System.out.println("You found: "+drop.name); 
	            p.addItem(drop); 
	        } else System.out.println("No loot this time.");

	        p.floor++;
	        System.out.println("You descend further into the abyss. Floor now: "+p.floor+"\n");
	    } else { 
	        System.out.println("You died on floor "+p.floor+"... the dungeon claims another soul."); 
	    }
	}
}
