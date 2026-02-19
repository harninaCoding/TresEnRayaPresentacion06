package vista;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Coordenada;

public class GUI extends JFrame  implements AccesoGUI {

	private JPanel contentPane;
	
	private JLabel lblMensaje;
	private Botonera botonera;

	/**
	 * Create the frame.
	 */
	public GUI() {
		int dimension = 3;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Mensaje");
		lblNewLabel.setBounds(10, 11, 75, 14);
		contentPane.add(lblNewLabel);
		
		lblMensaje = new JLabel(" ");
		lblMensaje.setBounds(104, 11, 320, 14);
		contentPane.add(lblMensaje);
		botonera=new Botonera(contentPane,dimension);
	}

	@Override
	public Component[] getButtonMatrix() {
		Component[] components = botonera.getComponents();
		return components;
	}

	@Override
	public JLabel getLblMensaje() {
		return lblMensaje;
	}

	@Override
	public MyButton getButton(Coordenada coordenada) {
		return (MyButton) botonera.getElemento(coordenada);
	}

	@Override
	public MyButton getBotonCentral() {
		return getButton(new Coordenada(1, 1));
	}
}
