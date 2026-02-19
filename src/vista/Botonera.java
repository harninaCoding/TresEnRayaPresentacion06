package vista;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

import modelo.Coordenada;


public class Botonera extends JPanel {
	private JPanel botonera;

	public Botonera(JPanel contentPane,int dimension) {
		super();
		botonera = new JPanel();
		botonera.setBounds(122, 58, 228, 181);
		botonera.setLayout(new GridLayout(3, 3, 0, 0));
		contentPane.add(botonera);

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				MyButton comp = new MyButton(new Coordenada(i, j));
				botonera.add(comp);
			}
		}
	}
	public Component[] getComponents() {
		Component[] components = botonera.getComponents();
		return components;
	}

	public Component getElemento(Coordenada coordenada) {
		for (Component boton : botonera.getComponents()) {
			if(((MyButton)boton).getCoordenada().equals(coordenada)) return boton;
		}
		return null;
	}
	

}
