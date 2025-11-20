package game;

import java.util.Random;

public class Item {
    public enum ItemType { POTION, WEAPON, ARMOR }
    public String name;
    public ItemType type;
    public int value;

    public Item(String name,ItemType type,int value){ this.name=name; this.type=type; this.value=value; }

    public static Item randomDrop(int floor){
        Random r=new Random();
        if(r.nextInt(100)<50) return null;
        int pick=r.nextInt(3);
        switch(pick){
            case 0: return new Item("Health Potion (L"+floor+")",ItemType.POTION,8+floor*2);
            case 1: return new Item("Sword +"+floor,ItemType.WEAPON,1+floor);
            default: return new Item("Shield +"+floor,ItemType.ARMOR,1+floor/2);
        }
    }
}
