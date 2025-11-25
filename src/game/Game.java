package game;

import java.util.Scanner;
import character.Player;
import utils.Utils;

public class Game {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Adventurer's Path ===");
        System.out.print("Your name: ");
        String name = sc.nextLine().trim();
        if(name.isEmpty()) name = "Wanderer";

        System.out.println("\nChoose your burden:\n1) Knight\n2) Archer\n3) Mage");
        System.out.print("> ");
        int clsChoice = Utils.safeNextInt(sc);
        Player.ClassType cls = Player.ClassType.KNIGHT;
        if(clsChoice == 2) cls = Player.ClassType.ARCHER;
        else if(clsChoice == 3) cls = Player.ClassType.MAGE;

        Player player = new Player(name, cls);
        System.out.println("\nWelcome, " + player.name + " the " + player.classType + "...");
        System.out.println("May your courage last longer than your life.\n");

        Story.intro(player);

        while(player.isAlive()){
            System.out.println("\n-- What will you do? --");
            System.out.println("1) Explore the next floor");
            System.out.println("2) Check your stats");
            System.out.println("3) Open inventory");
            System.out.println("4) Rest to recover HP/Mana (and advance floor)");
            System.out.println("5) Flee from the dungeon");
            System.out.println("0) Help - show this menu");
            System.out.print("> ");

            int choice = Utils.safeNextInt(sc);

            if(choice == 0){
                System.out.println("\n-- Menu Options --");
                System.out.println("1) Explore: Face the horrors below");
                System.out.println("2) Stats: Check your fragile life and worn strength");
                System.out.println("3) Inventory: Items to keep you alive, if you dare");
                System.out.println("4) Rest: Recover your body... but the dungeon never sleeps");
                System.out.println("5) Flee: Escape the abyss, leaving others to their fate\n");
                continue;
            }

            if(choice == 1){
                Story.floorEvent(player.floor);
                Dungeon.battle(player, sc);    
            }
            else if(choice == 2){
                System.out.println("\n" + player.name + " Lv" + player.level + 
                                   " HP:" + player.hp + "/" + player.maxHp +
                                   " ATK:" + player.attack + 
                                   " DEF:" + player.defense + 
                                   (player.classType == Player.ClassType.MAGE ? 
                                    " MANA:" + player.mana + "/" + player.maxMana : ""));
                System.out.println("Next floor: " + player.floor);
            }
            else if(choice == 3){
                player.showInventory();
                if(!player.inventory.isEmpty()){
                    System.out.println("Item index to use or -1 to cancel:");
                    System.out.print("> ");
                    int idx = Utils.safeNextInt(sc);
                    if(idx >= 0) player.useItem(idx);
                }
            }
            else if(choice == 4){
               
                if(Math.random() < 0.2){ // 20% chance
                    System.out.println("\nAs you try to rest, shadows stir...");
                    System.out.println("A hidden enemy ambushes you while you're vulnerable!");
                    Dungeon.battle(player, sc);
                    if(!player.isAlive()) break;
                }
                player.hp = player.maxHp;
                if(player.classType == Player.ClassType.MAGE) player.mana = player.maxMana;
                player.floor++;
                System.out.println("You cling to life and descend further. Floor now: " + player.floor);
            }
            else if(choice == 5){
                System.out.println("\nYou flee from the dungeon, leaving the horrors behind...");
                break;
            }
            else{
                System.out.println("Invalid choice! Type 0 to see available commands.");
            }
        }

        Story.ending(player.isAlive(), player.floor);
        sc.close();
    }
}
