package character;

import java.util.Random;


public class Enemy {
public String name;
public int hp;
public int attack;
public int defense;
public int expReward;


public Enemy(String name, int hp, int attack, int defense, int expReward) {
this.name = name;
this.hp = hp;
this.attack = attack;
this.defense = defense;
this.expReward = expReward;
}


public static Enemy randomEnemy(int floor) {
Random r = new Random();
int pick = r.nextInt(4); // 0..3
int baseHp, baseAtk, baseDef, baseExp;
String name;
switch (pick) {
case 0:
name = "Goblin";
baseHp = 15; baseAtk = 4; baseDef = 1; baseExp = 5;
break;
case 1:
name = "Skeleton";
baseHp = 20; baseAtk = 5; baseDef = 2; baseExp = 8;
break;
case 2:
name = "Orc";
baseHp = 25; baseAtk = 6; baseDef = 3; baseExp = 12;
break;
default:
name = "Wraith";
baseHp = 30; baseAtk = 8; baseDef = 2; baseExp = 18;
break;
}
double scale = 1 + (floor - 1) * 0.12; 
return new Enemy(name, (int)(baseHp * scale), (int)(baseAtk * scale), (int)(baseDef * scale), (int)(baseExp * scale));
}


public boolean isAlive() {
return hp > 0;
}
}

