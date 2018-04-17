package application;

class Creature extends Model {
	private int hp;
	private int maxHp;
	private int damage;
	private int defense; //if def skill -> dmg = hp - atack
	private boolean defenseIsActive;
	
	public Creature(double xposition, double yposition, double xsize, double ysize,
			int hp, int maxHp, int dmg, int def){
		super (xposition, yposition, xsize, ysize);
		this.hp = hp;
		this.maxHp = maxHp;
		damage = dmg;
		defense = def;
		defenseIsActive = false;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		if (hp > maxHp || hp < 0)
			return;
		this.hp = hp;
	}
		
	public int getMaxHp() {
		return maxHp;
	}
	
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
		
	public void changeHp (int howManyPoints) {
		int tmpHp = hp + howManyPoints;
		if (tmpHp > maxHp || tmpHp < 0)
			return;
		hp = tmpHp;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int dmg) {
		damage = dmg;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public void setDefense(int def) {
		defense = def;
	}
	
	public boolean getDefenseIsActive() {
		return defenseIsActive;
	}
	
	public void setDefenseIsActive(boolean isActive) {
		defenseIsActive = isActive;
	}
	
}

class Character extends Creature {
	private int money;
	
	public Character(double xposition, double yposition, double xsize, double ysize,
			int hp, int maxHp, int dmg, int def, int money){
		super (xposition, yposition, xsize, ysize, hp, maxHp, dmg, def);
		this.money = money;
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		if (money >= 0)
			this.money = money;
		else
			this.money = 0;
	}
	
	public void changeMoney (int money) {
		int tmpMoney = this.money + money;
		if (tmpMoney >= 0)
			this.money = tmpMoney;
		else
			this.money = 0;
	}
}

class Bear extends Creature{
	
	public Bear(double xposition, double yposition, double xsize, double ysize,
			int hp, int maxHp, int dmg, int def){
		super (xposition, yposition, xsize, ysize, hp, maxHp, dmg, def);
	}
}