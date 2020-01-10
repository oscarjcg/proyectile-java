package tiroParabolico;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class ActionsPanel extends JPanel {
	private JButton buttonLanzar = new JButton("Lanzar");
	private JButton buttonPausa = new JButton("Pausa");
	private JButton buttonBorrar = new JButton("Borrar");
	private JLabel velocityLabel = new JLabel();
	private JLabel angleLabel = new JLabel();
	private JLabel heightLabel = new JLabel();
	private JTextField velocityInput = new JTextField(5);
	private JTextField angleInput = new JTextField(5);
	private JTextField heightInput = new JTextField(5);
	private JScrollBar velocityBar = new JScrollBar(Scrollbar.HORIZONTAL, 0, 30, 0, 130);
	private JScrollBar angleBar = new JScrollBar(Scrollbar.HORIZONTAL, 0, 30, 0, 120);
	private JScrollBar heightBar = new JScrollBar(Scrollbar.HORIZONTAL, 0, 30, 0, 230);
	private JCheckBox velocityBox = new JCheckBox("Mostrar velocidades");
	private JCheckBox traceBox = new JCheckBox("Mostrar rastro");
	private JCheckBox positionBox = new JCheckBox("Mostrar vector de posición");
	private boolean trace = false;
	private boolean velocity = false;
	private boolean position = false;
	
	public ActionsPanel() {
		//Buttons
		JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 0, 10));			
		buttonsPanel.add(buttonLanzar);
		buttonsPanel.add(buttonPausa);
		buttonsPanel.add(buttonBorrar);
		
		//Parameters: Velocity, angle and height
		JPanel parametersPanel = new JPanel(new GridLayout(3, 3, 5, 5));
		velocityBar.setValue(50);
		angleBar.setValue(45);
		heightBar.setValue(0);
		
		velocityLabel.setText("Velocidad inicial " + velocityBar.getValue() + " m/s");
		angleLabel.setText("Angulo inicial " + angleBar.getValue() + " grados");
		heightLabel.setText("Altura inicial " + heightBar.getValue() + " metros");
		
		parametersPanel.add(velocityLabel);
		parametersPanel.add(velocityInput);
		parametersPanel.add(velocityBar);
		parametersPanel.add(angleLabel);
		parametersPanel.add(angleInput);
		parametersPanel.add(angleBar);
		parametersPanel.add(heightLabel);
		parametersPanel.add(heightInput);
		parametersPanel.add(heightBar);		
		
		//CheckBoxes
		JPanel boxesPanel = new JPanel(new GridLayout(3, 1, 0, 10));
		boxesPanel.add(velocityBox);
		boxesPanel.add(traceBox);
		boxesPanel.add(positionBox);
		
		//Components listeners
		velocityInput.addActionListener(new InputListener());
		angleInput.addActionListener(new InputListener());
		heightInput.addActionListener(new InputListener());
		
		velocityBar.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				velocityLabel.setText("Velocidad inicial " + velocityBar.getValue() + " m/s");				
			}
		});
		angleBar.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				angleLabel.setText("Angulo inicial " + angleBar.getValue() + " grados");			
			}
		});
		heightBar.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				heightLabel.setText("Altura inicial " + heightBar.getValue() + " metros");			
			}
		});
		
		velocityBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					velocity = true;
				}
				else {
					velocity = false;
				}				
			}
		});
		traceBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {					
					trace = true;
				}
				else {
					trace = false;
				}				
			}
		});
		positionBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					position = true;
				}
				else {
					position = false;
				}				
			}
		});
		//General frame
		add(buttonsPanel, BorderLayout.EAST);
		add(parametersPanel, BorderLayout.CENTER);
		add(boxesPanel, BorderLayout.WEST);
	}
		
	public void addController(ActionListener controller) {
		velocityInput.addActionListener(controller);
		angleInput.addActionListener(controller);
		heightInput.addActionListener(controller);
		buttonLanzar.addActionListener(controller);
		buttonPausa.addActionListener(controller);
		buttonBorrar.addActionListener(controller);		
		
		velocityBar.addAdjustmentListener((AdjustmentListener)controller);
		angleBar.addAdjustmentListener((AdjustmentListener)controller);
		heightBar.addAdjustmentListener((AdjustmentListener)controller);
	}
	
	public ArrayList<Integer> getTextFieldValues() {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		try {
			aux.add(Integer.parseInt(velocityInput.getText()));			
		}
		catch(Exception e) {
			aux.add(-1);
		}
		
		try {
			aux.add(Integer.parseInt(angleInput.getText()));			
		}
		catch(Exception e) {
			aux.add(-1);
		}
		
		try {
			aux.add(Integer.parseInt(heightInput.getText()));			
		}
		catch(Exception e) {
			aux.add(-1);
		}
			
		return aux;		
	}
	
	public ArrayList<Integer> getBarValues() {
		ArrayList<Integer> aux = new ArrayList<Integer>();		
		aux.add(velocityBar.getValue());	
		aux.add(angleBar.getValue());
		aux.add(heightBar.getValue());			
		return aux;		
	}
	
	public boolean getTrace() {
		return trace;
	}
	
	//Textfield events	
	class InputListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == velocityInput) {
				int velocity = Integer.parseInt(velocityInput.getText());
				velocityLabel.setText("Velocidad inicial " + velocity + " m/s");
			}
			else {			
				if (e.getSource() == angleInput) {
					int angle = Integer.parseInt(angleInput.getText());
					if (angle >= 90) {
						angle = 90;	
						angleInput.setText("90");
					}
					angleLabel.setText("Angulo inicial " + angle + " grados");
				}
				else {
					if (e.getSource() == heightInput) {
						int height = Integer.parseInt(heightInput.getText());
						heightLabel.setText("Altura inicial " + height + " metros");
					}					
				}
			}
		}
	}
	
	
	
}
