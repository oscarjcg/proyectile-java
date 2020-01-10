package tiroParabolico;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class TrayectoryPanel extends JPanel {	
	int positionX = 0;
	int positionY = 0;
	private boolean trace = false;
	private boolean hide = false;
	ArrayList<ArrayList<Integer>> traceArray;
	private int numberTrace = 0;
	
	ArrayList<Object> state;

	public TrayectoryPanel() {
		setBackground(Color.WHITE);
		state = new ArrayList<Object>();
		state.add(0);
		state.add(0);
		state.add(0.0);
		state.add(0.0);
		state.add(0.0);
		state.add(0.0);
		state.add(0.0);
		traceArray = new ArrayList<ArrayList<Integer>>();
		startTrace();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
						
		//Drawing axis Y
		g.drawLine((int)(getWidth() * 0.1), (int)(getHeight() * 0.9), (int)(getWidth() * 0.1), 0);
		
		int intervalSize = 20;
		double y = 0.0;		
		double smallMarkSize = 10;
		double bigMarkSize = 20;
		int markNumber = 50;
		int meter = 0;
		int scale = 100;
		
		for(int i = 0; i <= markNumber ; i++) {
			if((i == 0) || ((i % 10 ) == 0)) {					
				//Mark
				g.drawLine((int)(getWidth() * 0.1 - bigMarkSize), (int)(getHeight() * 0.9 - y), 
				(int)(getWidth() * 0.1), (int)(getHeight() * 0.9 - y));
				//Label
				g.drawString(Integer.toString(meter), (int)(getWidth() * 0.1 - bigMarkSize * 2), (int)(getHeight() * 0.9 - y - 1));
				meter = meter + scale;
				
			}
			else {
				//Mark
				g.drawLine((int)(getWidth() * 0.1 - smallMarkSize), (int)(getHeight() * 0.9 - y), 
				(int)(getWidth() * 0.1), (int)(getHeight() * 0.9 - y));
			}
			y = y + intervalSize;
		}
		
		//Drawing axis X
		g.drawLine((int)(getWidth() * 0.1), (int)(getHeight() * 0.9), getWidth(), (int)(getHeight() * 0.9));
		
		double x = 0.0;	
		meter = 0;
		
		for(int i = 0; i <= markNumber ; i++) {
			if((i == 0) || ((i % 10 ) == 0)) {
				//Mark
				g.drawLine((int)(getWidth() * 0.1 + x), (int)(getHeight() * 0.9), 
					(int)(getWidth() * 0.1 + x), (int)(getHeight() * 0.9 + bigMarkSize));
				//Label
				g.drawString(Integer.toString(meter), (int)(getWidth() * 0.1 + x - 10), (int)(getHeight() * 0.9 + bigMarkSize + 10));
				meter = meter + scale;
			}
			else {
				//Mark
				g.drawLine((int)(getWidth() * 0.1 + x), (int)(getHeight() * 0.9), 
				(int)(getWidth() * 0.1 + x), (int)(getHeight() * 0.9 + smallMarkSize));
			}
			x = x + intervalSize;
		}
		
		//		Drawing information
		//Time
		Double time =  (Double)state.get(2);
		time = time / 1000;
		g.drawString("t = " + String.format("%.1f",time) + " s", (int)(getWidth() *  0.9), (int)(getHeight() * 0.1));
		
		//X
		g.drawString("x = " + positionX + " m", (int)(getWidth() *  0.9), (int)(getHeight() * 0.15));
		//Y
		g.drawString("y = " + positionY + " m", (int)(getWidth() *  0.9), (int)(getHeight() * 0.2));
		//Vx
		Double vx = (Double)(state.get(3));
		g.drawString("Vx = " + vx.intValue() + " m/s", (int)(getWidth() *  0.9), (int)(getHeight() * 0.25));
		//Vy
		Double vy = (Double)(state.get(4));
		g.drawString("Vy = " + vy.intValue() + " m/s", (int)(getWidth() *  0.9), (int)(getHeight() * 0.3));
		//V
		g.drawString("V = " + String.format("%.1f", state.get(5)) + " m/s", (int)(getWidth() *  0.9), (int)(getHeight() * 0.35));
		//Max Y
		g.drawString("MaxY = " + String.format("%.1f", state.get(6)) + " m", (int)(getWidth() *  0.9), (int)(getHeight() * 0.4));
									
		//Drawing trace
		int convertedX;
		int convertedY;
		int radio = 4;
		
		g.setColor(Color.YELLOW);
		int spaceTrace = 4;
		
		if (positionX != 0) {
			traceArray.get(numberTrace-1).add(positionX);
			traceArray.get(numberTrace-1).add(positionY);
		}
		
		if(trace && (!hide)) {
			for(int nTrace = 0; nTrace < traceArray.size(); nTrace++) {
				switch(nTrace) {
					case 0: g.setColor(Color.YELLOW); break;
					case 1: g.setColor(Color.CYAN); break;
					case 2: g.setColor(Color.GRAY); break;
					case 3: g.setColor(Color.ORANGE); break;
					default: g.setColor(Color.PINK);
				}
				
				for(int i = 0; i < (traceArray.get(nTrace).size() - 2); i = i + 4) {
					convertedX = (int)(getWidth() * 0.1 + (2.0 * traceArray.get(nTrace).get(i)));
					convertedY = (int)(getHeight() * 0.9 - (2.0 * traceArray.get(nTrace).get(i+1)));
					g.fillOval(convertedX - radio / 2, convertedY - radio / 2, radio, radio);
				}
			}
			
		}
		hide = false;
		
		
		//Drawing proyectile
		radio = 6;
		g.setColor(Color.RED);
				
		convertedX = (int)(getWidth() * 0.1 + (2.0 * positionX));
		convertedY = (int)(getHeight() * 0.9 - (2.0 * positionY));
		g.fillOval(convertedX - radio / 2, convertedY - radio / 2, radio, radio);
	}
	
	public void update(ArrayList<Object> aux) {
		positionX = (int)aux.get(0); //x
		positionY =(int) aux.get(1); //y
		state = aux;
		repaint();
	}
	
	public void setTrace(boolean trace) {
		this.trace = trace;
	}
	
	public void startTrace() {
		traceArray.add(new ArrayList<Integer>());
		numberTrace++;
	}
		
	public void deleteTraces() {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		
		for(int i = 0; i < traceArray.get(numberTrace -1).size(); i++) {
			aux.add(traceArray.get(numberTrace -1).get(i));
		}
		traceArray.clear();
		traceArray.add(aux);
		numberTrace = 1;
	}
	
	public void setHideTrace(boolean hide) {
		this.hide = hide;
	}
}
