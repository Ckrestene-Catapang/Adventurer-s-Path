𐔌 .⋮ Adventurer's Path .ᐟ ֹ ₊ ꒱

Your console-based infinite dungeon crawler RPG.

CS 2114

Ckrestene F. Catapang
Joshua Dimaculangan
French Hernandez

‧₊˚ ┊ Overview

Adventurer's Path is a console-based Java application that simulates a turn-based Role-Playing Game (RPG). Players descend into an infinite abyss, fighting procedurally generated monsters and managing resources to survive.

It demonstrates the practical use of Object-oriented Programming (OOP) concepts such as encapsulation, inheritance, polymorphism, and abstraction, alongside robust input handling and modular design.
Players can:

⚔️ Choose a Class (Knight, Archer, Mage)
💀 Fight Random Enemies (Goblins, Wraiths, etc.)
🛡️ Loot & Equip Items to upgrade stats
🧪 Use Potions to recover health
🏃 Flee or Rest to survive longer runs

‧₊˚ ┊ Project Structure

📂 src/
├── 📂 character/
│   ├── ☕ Player.java
│   ├── ☕ Enemy.java
│   ├── ☕ Goblin.java
│   └── ☕ Wraith.java
├── 📂 game/
│   ├── ☕ Game.java          
│   ├── ☕ Dungeon.java
│   ├── ☕ Story.java
│   └── ☕ Item.java
└── 📂 utils/
    └── ☕ Utils.java

Game.java - Entry point of the program; handles the main game loop and menu.
Dungeon.java - Handles combat mechanics and turn-based logic.
Player.java - Manages user stats, inventory, and skills.
Enemy.java - Abstract parent class for all monsters.
Utils.java - Handles input validation and console flushing.

How to Run the Program

Open your terminal in the src/ folder and run:
javac game/Game.java

Run the program using:
java game.Game

‧₊˚ ┊ Features

Class Selection. Choose from 3 distinct classes with unique skills and starting stats.
Dynamic Combat. Turn-based battles where you can Attack, Defend, or use Skills.
Scaling Difficulty. Enemies get stronger (higher HP/Atk) as you descend floors.
Inventory System. Find loot; "using" weapons/armor permanently upgrades your stats.
Robust Input. Prevents crashes by validating user input and flushing the screen.

‧₊˚ ┊ Object-oriented Principles

💊 Encapsulation

Encapsulation is the bundling of data and methods that operate on that data within a single unit (class), while restricting access to some of the object's components.
* **Implementation:** In `Player.java`, we moved away from modifying health directly (e.g., `hp -= 10`). Instead, we implemented a controlled method `takeDamage(int amount)`.
* **Benefit:** This acts as a protective shield for the data. The method contains logic to ensure health never drops below zero, preventing "negative HP" bugs. It safeguards the character's state from external interference and keeps the combat logic centralized.

💡 Abstraction

Abstraction involves hiding complex implementation details and showing only the essential features of the object.
* **Implementation:** We created an `abstract class Enemy` in `Enemy.java`. This class acts as a blueprint or contract. It defines that *every* monster must have health, a name, and an ability to attack, but it leaves the implementation of the `attackPlayer()` method abstract (empty).
* **Benefit:** The main game loop (`Dungeon.java`) can interact with the concept of an "Enemy" without needing to know the specific math behind a Goblin's stab or a Wraith's scream. It simplifies the game engine significantly.

🧬 Inheritance

Inheritance allows a new class to acquire the properties and behaviors of an existing class, promoting code reuse.
* **Implementation:** Specific monster classes like `Goblin` and `Wraith` extend the parent `Enemy` class.
* **Benefit:** We write the code for `hp`, `name`, `isAlive()`, and `takeDamage()` only once in the parent class. The child classes automatically inherit these features. This reduces code duplication and makes it incredibly easy to add new monsters—we simply create a class that extends `Enemy` and focuses solely on its unique stats.

🎭 Polymorphism

Polymorphism allows objects to be treated as instances of their parent class rather than their actual class.
* **Implementation:** In the `Dungeon.java` battle loop, the code executes `e.attackPlayer()`. The variable `e` is declared generally as an `Enemy`, but at runtime, it might hold a `Goblin` object or a `Wraith` object.
* **Benefit:** This single line of code behaves differently depending on what the object actually is. If it's a Goblin, it runs the standard attack logic. If it's a Wraith, it runs the armor-piercing logic. This allows the battle system to remain flexible and handle any number of new enemy types without changing the core loop.
‧₊˚ ┊ Example Output

--- Floor 5 ---
A wild Wraith emerges from the shadows!

Warrior HP:25/30 | Mana:18/20
Wraith HP:30

1) Attack
2) Defend
3) Skill
4) Flee
> 3
You used Shield Bash! Dealt 12 damage.
Wraith screeches, piercing your soul!
It hits you for 6 shadow damage!

‧₊˚ ┊ Dungeon.java Snippet

// Polymorphism in action
// 'e' could be a Goblin or a Wraith
if(e.isAlive()){
    e.attackPlayer(p, defending); 
    System.out.println(""); 
}

if(p.isAlive()){ 
    System.out.println("You defeated "+e.getName()+"!");
    p.gainExp(e.getExpReward());
    
    // Loot generation
    Item drop = Item.randomDrop(p.floor);
    if(drop != null){ 
        System.out.println("You found: "+drop.name); 
        p.addItem(drop); 
    }
}

‧₊˚ ┊ Contributors

Name                      Role
Ckrestene F. Catapang        Lead Developer / Encapsulation
Joshua Dimaculangan          System Architect / Inheritance
French Hernandez             Logic Specialist / Polymorphism

‧₊˚ ┊ Acknowledgment

We sincerely express our gratitude to our instructor for the guidance and support provided throughout the completion of this project. We also extend our appreciation to our classmates and peers for their cooperation and encouragement.
