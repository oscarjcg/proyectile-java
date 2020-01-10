package tiroParabolico;

import java.util.ArrayList;

public class ProyectileModel extends java.util.Observable {
	private int x;
	private int y;
	private double initialVelocity;
	private int initialAngle;
	private int initialHeight;		
	private double msTime; //Miliseconds
	private double gravity = 9.8;
	
	public ProyectileModel() {
		initialHeight = 0;
		initialAngle = 45;
		initialVelocity = 50;
		msTime = 0.0;
		
		ArrayList<Object> aux = new ArrayList<Object>();
		aux.add(x);
		aux.add(y);
		aux.add(getTime());
		aux.add(getVelocityX());
		aux.add(getVelocityY());
		aux.add(getVelocity());
		aux.add(getMaxY());
		setChanged();
		notifyObservers(aux);
	}
	
	public void inicialState(int velocity, int angle, int height) {
		initialVelocity = velocity;		
		initialAngle = angle;
		initialHeight = height;
		y = height;
		msTime = 0.0;
		x = 0;
		
		ArrayList<Object> aux = new ArrayList<Object>();
		aux.add(x);
		aux.add(y);
		aux.add(getTime());
		aux.add(getVelocityX());
		aux.add(getVelocityY());
		aux.add(getVelocity());
		aux.add(getMaxY());
		setChanged();
		notifyObservers(aux);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double getInitialVelocity() {
		return initialVelocity;
	}
	public int getInitialAngle() {
		return initialAngle;
	}
	public int getInitialHeight() {
		return initialHeight;
	}
	
	public double getTime() {
		return msTime;
	}
	
	public void setTime(double msTime) {
		this.msTime = msTime;
		
		calculatePosition();
		
		ArrayList<Object> aux = new ArrayList<Object>();
		aux.add(x);
		aux.add(y);
		aux.add(getTime());
		aux.add(getVelocityX());
		aux.add(getVelocityY());
		aux.add(getVelocity());
		aux.add(getMaxY());
		setChanged();
		notifyObservers(aux);
	}
	
	public void calculatePosition() {
		//Rounded to floor. Implement to floor(<0.5) and roof(>0.5) ??
		x = (int)(initialVelocity * Math.cos(Math.toRadians(initialAngle)) * msTime / 1000.0);		
		y = initialHeight + (int)(initialVelocity * Math.sin(Math.toRadians(initialAngle)) * msTime / 1000.0
			- (1.0 / 2.0 * gravity * Math.pow(msTime / 1000.0, 2)));	
	}
	
	public double getVelocityX() {
		return initialVelocity * Math.cos(Math.toRadians(initialAngle));
	}
	
	public double getVelocityY() {
		return initialVelocity * Math.sin(Math.toRadians(initialAngle)) - (gravity * msTime / 1000.0);
	}
	
	public double getVelocity() {
		return Math.sqrt(Math.pow(getVelocityX(), 2) + Math.pow(getVelocityY(), 2));
	}
	
	public double getMaxY() {
		return (Math.pow(initialVelocity, 2) * Math.pow(Math.sin(Math.toRadians(initialAngle)), 2) 
				/ (2.0 * gravity)) + initialHeight;
	}
}
