package character;

import java.util.ArrayList;
import java.util.List;
import game.Item;

public class Player {
    public enum ClassType { KNIGHT, ARCHER, MAGE }

    public String name;
    public ClassType classType;
    public int hp, maxHp;
    public int attack, defense;
    public int mana, maxMana;
    public int level;
    public int exp;
    public int floor;
    public List<Item> inventory = new ArrayList<>();

    public Player(String name, ClassType classType) {
        this.name = name;
        this.classType = classType;
        this.level = 1;
        this.exp = 0;
        this.floor = 1;
        switch (classType) {
            case KNIGHT: maxHp=40; hp=maxHp; attack=6; defense=4; mana=maxMana=0; break;
            case ARCHER: maxHp=30; hp=maxHp; attack=7; defense=2; mana=maxMana=0; break;
            case MAGE: maxHp=25; hp=maxHp; attack=4; defense=1; mana=maxMana=20; break;
        }
    }

    public boolean isAlive() { return hp>0; }

    public void gainExp(int amount) {
        exp += amount;
        int threshold = level*20;
        while(exp>=threshold) { exp-=threshold; levelUp(); threshold=level*20; }
    }

    public void levelUp() {
        level++;
        switch(classType){
            case KNIGHT: maxHp+=10; attack+=2; defense+=2; break;
            case ARCHER: maxHp+=8; attack+=3; defense+=1; break;
            case MAGE: maxHp+=6; attack+=1; defense+=1; maxMana+=5; mana=maxMana; break;
        }
        hp=maxHp;
        System.out.println("-- Level up! You are now level "+level+" --");
    }

    public void addItem(Item item){ if(item!=null) inventory.add(item); }

    public void showInventory(){
        if(inventory.isEmpty()){ System.out.println("Your inventory is empty."); return; }
        System.out.println("-- Inventory --");
        for(int i=0;i<inventory.size();i++){
            Item it=inventory.get(i);
            System.out.println(i+") "+it.name+" ["+it.type+"] (val: "+it.value+")");
        }
    }

    public void useItem(int index){
        if(index<0||index>=inventory.size()){ System.out.println("Invalid item index."); return; }
        Item item = inventory.get(index);
        switch(item.type){
            case POTION: System.out.println("You use "+item.name+" and heal "+item.value+" HP."); hp=Math.min(maxHp,hp+item.value); inventory.remove(index); break;
            case WEAPON: System.out.println("You equip "+item.name+". Attack +"+item.value); attack+=item.value; inventory.remove(index); break;
            case ARMOR: System.out.println("You equip "+item.name+". Defense +"+item.value); defense+=item.value; inventory.remove(index); break;
        }
    }
}
