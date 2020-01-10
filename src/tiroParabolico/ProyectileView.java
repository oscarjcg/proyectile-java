package tiroParabolico;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class ProyectileView extends JApplet implements Observer{
	private TrayectoryPanel trayectoryPanel;
	private ActionsPanel actionsPanel;
	
	public ProyectileView() {
		trayectoryPanel = new TrayectoryPanel();
		actionsPanel = new ActionsPanel();
		
		add(trayectoryPanel, BorderLayout.CENTER);
		add(actionsPanel, BorderLayout.SOUTH);
		
		//trayectoryPanel.start();
		/*
		setTitle("Proyectiles");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setVisible(true);*/
	}
	
	public void update(Observable obs, Object obj) {
		trayectoryPanel.update((ArrayList<Object>)(obj));
		trayectoryPanel.setTrace(actionsPanel.getTrace());
		//System.out.println("UPDATE" +  (ArrayList<Integer>)(obj));
	}
	
	public void addController(ActionListener controller) {
		
		actionsPanel.addController(controller);
	}
	
	public void startTrace() {
		trayectoryPanel.startTrace();
	}
	
	public void deleteTraces() {
		trayectoryPanel.deleteTraces();
	}
	
	public ArrayList<Integer> getTextFieldValues() {
		return actionsPanel.getTextFieldValues();
	}
	
	public ArrayList<Integer> getBarValues() {
		return actionsPanel.getBarValues();
	}
	
	public void checkChanges() {
		trayectoryPanel.setHideTrace(true);
		trayectoryPanel.repaint();
	}
}
