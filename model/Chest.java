package model;

public class Chest extends Item{
	private int value;
	
	public Chest(double xposition, double yposition, double xsize, double ysize,
			boolean active, int type, int value){
		super (xposition, yposition, xsize, ysize, active);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int newValue) {
		value = newValue;
	}
}
