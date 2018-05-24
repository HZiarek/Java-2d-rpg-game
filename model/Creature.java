package model;

/**
 * It extends Model; describes base logic of the creatures (enemy and character).
 * It adds parameters required to define entities as an alive creature, which is able to take part in combat.
 */

public class Creature extends Model {
	private boolean alive;
	private boolean inFight;
	private int hp;
	private int maxHp;
	private int damage;
	
	public Creature(double xposition, double yposition, double xsize, double ysize,
			int hp, int maxHp, int dmg){
		super (xposition, yposition, xsize, ysize);
		this.hp = hp;
		if (maxHp < hp)
			this.maxHp = hp;
		this.maxHp = maxHp;
		damage = dmg;
		alive = true;
		inFight = false;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
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
	
	//return true if the creature is still alive
	public boolean changeHpAndCheckIsDead (int howManyPoints) {
		int tmpHp = hp + howManyPoints;
		if (tmpHp <= 0) {
			alive = false;
			hp = 0;
			return false;
		}
		if (tmpHp > maxHp)
			hp = maxHp;
		else
			hp = tmpHp;
		
		return true;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int dmg) {
		damage = dmg;
	}
	
	public void setInFight(boolean yesOrNot) {
		inFight = yesOrNot;
	}
	
	public boolean getInFight() {
		return inFight;
	}
	
}
