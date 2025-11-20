package game;

import java.util.Scanner;

import character.Player;
import utils.Utils;

public class Game {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("=== Adventurer's Path ===");
        System.out.print("Hero name: ");
        String name=sc.nextLine().trim(); if(name.isEmpty()) name="Hero";

        System.out.println("Choose class:\n1) Knight \n2) Archer  \n3) Mage");
        int clsChoice=Utils.safeNextInt(sc);
        Player.ClassType cls=Player.ClassType.KNIGHT;
        if(clsChoice==2) cls=Player.ClassType.ARCHER;
        else if(clsChoice==3) cls=Player.ClassType.MAGE;

        Player player=new Player(name,cls);
        System.out.println("Welcome "+player.name+" the "+player.classType+"!");

        while(player.isAlive()){
            System.out.println("\n1) Explore \n2) Stats \n3) Inventory \n4) Rest \n5) Exit");
            int choice=Utils.safeNextInt(sc);

            if(choice==1) Dungeon.battle(player,sc);
            else if(choice==2){
                System.out.println(player.name+" Lv"+player.level+" HP:"+player.hp+"/"+player.maxHp+" ATK:"+player.attack+" DEF:"+player.defense+(player.classType==Player.ClassType.MAGE?" MANA:"+player.mana+"/"+player.maxMana:""));
                System.out.println("Next floor: "+player.floor);
            }
            else if(choice==3){ player.showInventory(); System.out.println("Item index or -1:"); int idx=Utils.safeNextInt(sc); if(idx>=0)player.useItem(idx); }
            else if(choice==4){ player.hp=player.maxHp; if(player.classType==Player.ClassType.MAGE) player.mana=player.maxMana; player.floor++; System.out.println("Rested. Floor increased to "+player.floor); }
            else if(choice==5){ System.out.println("Goodbye. Reached floor "+player.floor); break; }
            else System.out.println("Invalid choice");
        }
        sc.close();
    }
}
