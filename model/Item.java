package model;

public 
class Item extends Model {
	private boolean active;
	
	public Item(double xposition, double yposition, double xsize, double ysize, boolean active){
		super (xposition, yposition, xsize, ysize);
		this.active = active;
	}
	
	public boolean getActive() {
		return active;
	}

	public void setActive(boolean flag) {
		active = flag;
	}
}
