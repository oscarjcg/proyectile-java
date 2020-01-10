package tiroParabolico;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.Timer;

public class ProyectileController implements ActionListener, AdjustmentListener {
	private ProyectileModel model;
	private ProyectileView view;
	private Timer timer;
	private int delay = 50;
	
	public ProyectileController() {
		timer = new Timer(delay, new TimerListener());
	}
	
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Control of event " + e.paramString());
		
		if (e.getActionCommand() == "Lanzar") {			
			view.startTrace();
			if(timer.isRunning()) {
				model.setTime(0.0);
				timer.restart();					
			}
			else {
				model.setTime(0.0);
				timer.start();
			}
		}
		else {			
			if (e.getActionCommand() == "Pausa") {				
				if(timer.isRunning()) {
					timer.stop();
				}
				else {
					timer.start();
				}
			}
			else {
				if (e.getActionCommand() == "Borrar") {
					System.out.println("B");
					view.deleteTraces();
					view.checkChanges();
				}
				else { //Values from textfield to model
					ArrayList<Integer> aux = view.getTextFieldValues();
					if(Integer.parseInt(e.getActionCommand()) == aux.get(0)) {
						model.inicialState(aux.get(0), model.getInitialAngle(), model.getInitialHeight());
					}
					
					if(Integer.parseInt(e.getActionCommand()) == aux.get(1)) {
						model.inicialState((int)model.getInitialVelocity(), aux.get(1), model.getInitialHeight());
					}
					if(Integer.parseInt(e.getActionCommand()) == aux.get(2)) {
						model.inicialState((int)model.getInitialVelocity(), model.getInitialAngle(), aux.get(2));
					}
					//System.out.println(view.getTextFieldValues());
				}
				
			}
		}
	}
	
	public void adjustmentValueChanged(AdjustmentEvent e) {
		System.out.println(view.getBarValues());
		ArrayList<Integer> aux = view.getBarValues();
		if(e.getValue() == aux.get(0)) {
			model.inicialState(aux.get(0), model.getInitialAngle(), model.getInitialHeight());
		}
		
		if(e.getValue() == aux.get(1)) {
			model.inicialState((int)model.getInitialVelocity(), aux.get(1), model.getInitialHeight());
		}
		
		if(e.getValue() == aux.get(2)) {
			model.inicialState((int)model.getInitialVelocity(), model.getInitialAngle(), aux.get(2));
		}
		System.out.println("Control of AD " + e.paramString());
		
	}
	
	public void addModel(ProyectileModel model) {	
		this.model = model;
	}

	public void addView(ProyectileView view) {		
		this.view = view;		
	}
	
	public void initModel() {
		model.inicialState(50, 45, 0);
	} 
	
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//System.out.println("T " + proyectileModel.getTime());
			if(model.getY() < 0) {
				view.startTrace();
				timer.stop();
			}
			else {
				model.setTime(model.getTime() + delay);
			}
			
		}
	}
	
}
