package control;

import java.awt.EventQueue;

import vista.GUI;

public class Principal {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//el interfaz grafico
					GUI frame=new GUI();
					//el control que conecta el gui con la logica del program , o sea TresNRaya
					EventGUI eventGUI=new EventGUI(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
