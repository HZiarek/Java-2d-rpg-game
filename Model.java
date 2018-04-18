package application;

import javafx.geometry.Rectangle2D;

class Model {

	private double Xposition;
	private double Yposition;
	private double Xsize;
	private	double Ysize;

	public Model(){
		Xposition = 0;
		Yposition = 0;
		Xsize = 0;
		Ysize = 0;
	}
		
	public Model(double xposition, double yposition, double xsize, double ysize) {
		Xposition = xposition;
		Yposition = yposition;
		Xsize = xsize;
		Ysize = ysize;
	}

	public double getXposition() {
		return Xposition;
	}

	public void setXposition(double xposition) {
		Xposition = xposition;
	}

	public double getYposition() {
		return Yposition;
	}

	public void setYposition(double yposition) {
		Yposition = yposition;
	}

	public double getXsize() {
		return Xsize;
	}

	public void setXsize(double xsize) {
		Xsize = xsize;
	}

	public double getYsize() {
		return Ysize;
	}

	public void setYsize(double ysize) {
		Ysize = ysize;
	}
		
	public Rectangle2D getBoundary(){
	       return new Rectangle2D(Xposition,Yposition,Xsize,Ysize);
	}
		
	public boolean intersects (Model example){
		return example.getBoundary().intersects( this.getBoundary() );
	}
	
	public void relocate(double x, double y) {
		Xposition += x;
		Yposition += y;
	}
}


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

class Chest extends Item{
	private int value;
	
	public Chest(double xposition, double yposition, double xsize, double ysize,
			boolean active, int value){
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

class Ladder extends Item{
	private double height;
	
	public Ladder(double xposition, double yposition, double xsize, double ysize,
			boolean active, double height){
		super (xposition, yposition, xsize, ysize, active);
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double newHeight) {
		height = newHeight;
	}
}