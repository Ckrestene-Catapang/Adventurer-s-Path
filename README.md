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

## 🏗️ Technical Highlights (OOP Principles)

This project was built to demonstrate mastery of Java Object-Oriented Programming:

### 1. 💊 Encapsulation
Data is protected within classes. For example, the `Player` class manages its own health via the `takeDamage()` method. This ensures that HP never drops below zero unexpectedly and keeps the combat logic safe.

### 2. 💡 Abstraction
We utilize an `abstract class Enemy` to define the blueprint for all monsters. The main game loop doesn't need to know *how* an enemy attacks; it simply calls the abstract `attackPlayer()` method, hiding the complex logic.

### 3. 🧬 Inheritance
To maximize code reusability, specific enemies like `Goblin` and `Wraith` inherit from the parent `Enemy` class. They automatically acquire health and name properties, allowing us to add new monsters easily without rewriting code.

### 4. 🎭 Polymorphism
The battle system uses polymorphism to handle dynamic behaviors. The code calls `enemy.attackPlayer()`, and the program automatically determines at runtime whether to execute the **Goblin's** physical attack or the **Wraith's** magic piercing attack.

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
