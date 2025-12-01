-----

### 𐔌 .⋮ Adventurer's Path .ᐟ ֹ ₊ ꒱

*Your console-based Java dungeon crawler RPG.*

*[Your Name Here]*
[Your Section/Group]

‧₊˚ ┊ *Overview*

*Adventurer's Path* is a console-based Role-Playing Game (RPG) where players descend into an infinite dungeon. It focuses on tactical turn-based combat, resource management, and risk-taking.

It demonstrates the practical use of *Java fundamentals*, *Control Flow*, and *Object-Oriented Programming (OOP)* concepts to create an interactive game loop.

*Players can:*
⚔️ *Choose a Class* (Knight, Archer, Mage)
💀 *Fight Random Enemies* (Goblins, Wraiths, etc.)
🛡️ *Loot Equipment* (Weapons, Armor, Potions)
📈 *Level Up* Stats (HP, Attack, Mana)

‧₊˚ ┊ *Project Structure*

📂 *src/*
├── 📂 *character/*
│   ├── ☕ Enemy.java
│   └── ☕ Player.java
├── 📂 *game/*
│   ├── ☕ Dungeon.java
│   ├── ☕ Game.java
│   ├── ☕ Item.java
│   └── ☕ Story.java
└── 📂 *utils/*
└── ☕ Utils.java

  * *Game.java* - Entry point containing the main game loop and menu.
  * *Dungeon.java* - Handles combat mechanics, enemy generation, and turn logic.
  * *Player.java* - Manages user stats, inventory, and leveling.
  * *Story.java* - Contains narrative text and world-building descriptions.
  * *Utils.java* - Helper class for handling safe user input.

‧₊˚ ┊ *How to Run*

Open your terminal in the **src/** folder and run:

javac game/Game.java

Run the program using:

java game.Game

‧₊˚ ┊ *Features*

  * *Class System.* Select from 3 distinct classes with unique starting stats and skill names.
  * *Dynamic Combat.* Turn-based battles where you can Attack, Defend, or use Skills.
  * *Scaling Difficulty.* Enemies get stronger (higher HP/Atk) as you descend deeper floors.
  * *Inventory System.* Find and use potions to recover health during critical moments.
  * *Risk Mechanic.* "Resting" heals you but carries a 20% chance of an ambush.

‧₊˚ ┊ *Object-Oriented Principles*

💊 *Encapsulation*
Encapsulation is applied by bundling data and behavior into specific classes. For example, the **Player** class holds its own hp, mana, and inventory, and modifies them through methods like gainExp() and useItem(). This keeps the character logic separate from the game loop.

💡 *Abstraction*
The **Utils** class abstracts the complexity of error handling in user input. The safeNextInt() method hides the try-catch or hasNextInt logic, allowing the main code to simply ask for a number without worrying about crashes. Similarly, **Story** abstracts the flavor text, keeping Game.java clean.

🧬 *Inheritance*
While direct inheritance (e.g., extends) is not heavily used in this version, the structure is designed for it. The **Item** class currently handles Potions, Weapons, and Armor via an Enum, but this could easily be refactored so that Weapon extends Item in future updates.

🎭 *Polymorphism*
The **Enemy.randomEnemy()** method demonstrates a form of factory polymorphism. It returns an Enemy object, but that object behaves differently (different stats/names) depending on the random generation (Goblin vs Wraith). Additionally, the **Player** class uses switch cases on ClassType to simulate polymorphic skills (e.g., a Mage casting "Fireball" vs a Knight using "Shield Bash").

‧₊˚ ┊ *Example Output*

text
--- Floor 5 ---
A wild Wraith emerges from the shadows!

Wanderer HP:25/30 | Mana:18/20
Wraith HP:30

1) Attack
2) Defend
3) Skill
4) Flee
> 3
Used Ice Shard! Dealt 12 damage.
Wraith attacks you for 6 damage!

‧₊˚ ┊ *Dungeon.java Snippet*

// Enemy turn
if(e.isAlive()){
    int dmg = Math.max(1, e.attack - (defending ? p.defense*2 : p.defense));
    p.hp -= dmg;
    System.out.println(e.name + " attacks you for " + dmg + " damage!\n");
}

if(p.isAlive()){ 
    System.out.println("You defeated "+e.name+"!");
    p.gainExp(e.expReward);

    Item drop = Item.randomDrop(p.floor);
    if(drop != null){ 
        System.out.println("You found: "+drop.name); 
        p.addItem(drop); 
    }
}

‧₊˚ ┊ *Contributors*

| Name | Role |
| :--- | :--- |
| *[Your Name]* | Lead Developer |
| *[Teammate Name]* | System Architect |

‧₊˚ ┊ *Acknowledgment*

Special thanks to our instructor for providing the foundational concepts of Java Programming that made this dungeon crawler possible.

*DISCLAIMER*
This project is for educational purposes only.

-----

### *Code Explanation*

Here is a breakdown of how the code actually works, detailed by package.

#### **1. The Brain (game Package)**

  * **Game.java**: This is the "Main" class.
      * It creates the Scanner for input.
      * It asks for your name and class, creating the Player object.
      * *The Loop:* It runs while(player.isAlive()). Inside this loop, it shows the main menu. If you choose to "Explore", it calls Dungeon.battle(). If you choose "Rest", it heals you but rolls a random number (Math.random() < 0.2) to see if you get ambushed.
  * **Dungeon.java**: This handles the fighting.
      * **battle()**: This is a static method that creates a random Enemy scaled to the current floor.
      * It runs a mini-loop (the combat loop) that continues as long as both the Player and Enemy are alive.
      * It calculates damage using simple math: Attacker Damage - Defender Defense.
  * **Item.java**:
      * Defines what an item is (Name, Type, Value).
      * **randomDrop(int floor)**: This is a "Factory" method. It flips a coin (50% chance); if successful, it generates a Potion, Sword, or Shield with stats based on the floor number.

#### **2. The Entities (character Package)**

  * **Player.java**:
      * Stores the state of the user (Health, Mana, XP, Inventory List).
      * **gainExp(int exp)**: When this is called, it adds XP. If XP is high enough (simplified here to just always leveling up on kill), it increases Max HP, Attack, and Defense.
      * **useItem(int index)**: Checks if the item at the specific index in the list is a Potion. If yes, it heals; otherwise, it says you can't use it.
  * **Enemy.java**:
      * **randomEnemy(int floor)**: This picks a number 0-3 to decide if the enemy is a Goblin, Skeleton, Orc, or Wraith.
      * It then multiplies the base stats by a modifier based on the floor level (1 + (floor - 1) * 0.12), ensuring floor 10 enemies are much stronger than floor 1 enemies.

#### **3. The Helpers (utils Package)**

  * **Utils.java**:
      * **safeNextInt**: This prevents the "Crash on Typo" bug. It loops while(!sc.hasNextInt()), consuming any garbage text the user types (like "abc") until they actually type a number.
