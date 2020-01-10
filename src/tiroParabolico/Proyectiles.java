package tiroParabolico;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class Proyectiles extends JApplet {	
	
	public Proyectiles() {
		ProyectileModel proyectileModel = new ProyectileModel();		
		ProyectileView proyectileView = new ProyectileView();
		
		proyectileModel.addObserver(proyectileView);
		ProyectileController controller = new ProyectileController();
		controller.addModel(proyectileModel);
		controller.addView(proyectileView);
		
		proyectileView.addController(controller);
				
		controller.initModel();		
		add(proyectileView);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Proyectil");
		
		Proyectiles program = new Proyectiles();
		frame.add(program);
		
		frame.setTitle("Proyectiles");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);
		frame.setVisible(true);
	}	
	
}
