package game;

import character.Player;

public class Story {

    public static void intro(Player player) {
        System.out.println("The kingdom of Eldoria lies in shadow, corruption crawling through its veins...");
        System.out.println(player.name + ", a weary " + player.classType + ", descends into the abyssal dungeon.");
        System.out.println("Every step may be your last. Only the brave—or the foolish—venture further.\n");
    }

    public static void floorEvent(int floor) {
        switch(floor){
            case 1:
                System.out.println("The entrance reeks of decay. Stale air fills your lungs.");
                System.out.println("Faint whispers echo in the dark, as if the walls themselves are alive.\n"); break;
            case 2:
                System.out.println("The corridor narrows, dripping water forming puddles black as tar.");
                System.out.println("Something unseen moves in the shadows, watching, waiting.\n"); break;
            case 3:
                System.out.println("Broken statues litter the floor. Blood stains the walls.");
                System.out.println("A sense of dread gnaws at your sanity. Proceed, if you dare.\n"); break;
            case 5:
                System.out.println("A deafening roar reverberates through the halls. You feel powerless.");
                System.out.println("Your weapon feels fragile, your armor like paper.\n"); break;
            case 10:
                System.out.println("Ancient runes burn faintly on the walls. The air smells of sulfur and ash.");
                System.out.println("You know the evil here will crush all who oppose it.\n"); break;
            default:
                System.out.println("You descend into darkness, deeper than your nightmares.");
                System.out.println("Every step is uncertain. Every breath is borrowed.\n"); break;
        }
    }

    public static void ending(boolean victory, int floor) {
        if(victory){
            System.out.println("Against all odds, " + "you have survived the dungeon’s horrors.");
            System.out.println("Floor " + floor + " marks your escape, but Eldoria’s shadows will never forget you.\n");
        } else {
            System.out.println("You fought, you screamed, you bled, and still the dungeon took you.");
            System.out.println("Floor " + floor + " swallows your soul, joining the countless lost before you...\n");
        }
    }
}
