package vista;

import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JLabel;

import modelo.Coordenada;

public interface AccesoGUI {

	public Component[] getButtonMatrix();
	public JLabel getLblMensaje();
	public MyButton getBotonCentral();
	public MyButton getButton(Coordenada coordenada);
}
