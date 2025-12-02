# ⚔️ Adventurer's Path

**Adventurer's Path** is a console-based, infinite dungeon crawler RPG built in Java. It demonstrates robust software architecture using the four pillars of Object-Oriented Programming (OOP) to create a scalable and interactive game world.

Players choose a class, descend into the abyss, fight procedurally generated monsters, and manage resources to survive as long as possible.

## 🌟 Features

* **Class System**: Choose your path—**Knight** (Tank), **Archer** (Balanced), or **Mage** (High Damage/Mana).
* **Tactical Combat**: Turn-based battles where you must decide to Attack, Defend, or use unique Skills (like *Fireball* or *Shield Bash*).
* **Smart Enemy AI**: Enemies use **Polymorphism** to behave differently—Goblins stab, while Wraiths ignore armor with piercing screams.
* **Progression System**: Earn EXP to level up your HP and Attack.
* **Equipment System**: Find Weapons and Armor. "Using" them permanently upgrades your stats, simulating character growth.
* **Robust Input**: The game handles invalid inputs gracefully and keeps the interface clean with console flushing.

---

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
---

## 📂 Project Structure

```text
src/
├── 📂 character/       # Entities and Logic
│   ├── ☕ Player.java  # Handles stats, inventory, and skills
│   ├── ☕ Enemy.java   # Abstract Parent Class
│   ├── ☕ Goblin.java  # Child Class (Basic Enemy)
│   └── ☕ Wraith.java  # Child Class (Special Enemy)
│
├── 📂 game/            # Core Game Engine
│   ├── ☕ Game.java    # Main Entry Point & Game Loop
│   ├── ☕ Dungeon.java # Combat Logic & Battle Loop
│   ├── ☕ Story.java   # Narrative Text & Events
│   └── ☕ Item.java    # Loot Generation
│
└── 📂 utils/           # Helper Tools
    └── ☕ Utils.java   # Input Validation & Screen Clearing
