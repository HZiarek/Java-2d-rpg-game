package model;

/**
 * It extends Creature; adds amount of money, which character has.
 */

public class Character extends Creature {
	private int money;
	
	public Character(double xposition, double yposition, double xsize, double ysize,
			int hp, int maxHp, int dmg, int def, int money){
		super (xposition, yposition, xsize, ysize, hp, maxHp, dmg);
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
