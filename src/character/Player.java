package character;

import java.util.ArrayList;
import java.util.List;

import game.Item;

public class Player {
    public String name;
    public ClassType classType;
    public int hp, maxHp, mana, maxMana, attack, defense, level, floor;
    public List<Item> inventory = new ArrayList<>();

    public enum ClassType { KNIGHT, ARCHER, MAGE }

    // Constructor
    public Player(String name, ClassType cls){
        this.name = name;
        this.classType = cls;
        this.level = 1;
        this.floor = 1;
        this.maxHp = 30;
        this.hp = maxHp;
        this.attack = 5;
        this.defense = 2;
        if(cls == ClassType.MAGE){
            this.maxMana = 20;
            this.mana = maxMana;
        }
    }

    public void addItem(Item item){
        inventory.add(item);
        System.out.println(item.name + " added to inventory.");
    }

    public void showInventory(){
        if(inventory.isEmpty()){
            System.out.println("Your inventory is empty.");
            return;
        }
        System.out.println("\n-- Inventory --");
        for(int i=0;i<inventory.size();i++){
            Item item = inventory.get(i);
            System.out.println(i + ") " + item.name + " [" + item.type + "] Value: " + item.value);
        }
    }

    public void useItem(int idx){
        if(idx < 0 || idx >= inventory.size()){
            System.out.println("Invalid item index.");
            return;
        }
        Item item = inventory.get(idx);
        if(item.type == Item.ItemType.POTION){
            hp = Math.min(maxHp, hp + item.value);
            System.out.println("Used " + item.name + " and recovered " + item.value + " HP.");
        } else {
            System.out.println("You can't use " + item.name + " right now!");
        }
        inventory.remove(idx);
    }

    public boolean isAlive(){ return hp > 0; }

    public void gainExp(int exp){
        System.out.println("Gained " + exp + " EXP.");
        level++;
        maxHp += 5;
        hp = maxHp;
        attack += 1;
        defense += 1;
        if(classType == ClassType.MAGE){
            maxMana += 3;
            mana = maxMana;
        }
        System.out.println("Leveled up! Now Lv" + level);
    }

    public void showSkills() {
        System.out.println("\n-- Skills --");
        System.out.println("1) Basic Attack");
        switch(this.classType) {
            case KNIGHT:
                System.out.println("2) Shield Bash");
                System.out.println("3) Power Strike");
                break;
            case ARCHER:
                System.out.println("2) Quick Shot");
                System.out.println("3) Rain of Arrows");
                break;
            case MAGE:
                System.out.println("2) Ice Shard");
                System.out.println("3) Fireball");
                break;
        }
        System.out.print("> ");
    }
}
