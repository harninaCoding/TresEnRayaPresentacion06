package control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Coordenada;
import vista.AccesoGUI;
import vista.MyButton;

public class EventGUI {
	// La clase que se encrega de la logica del programa
	private TresEnRaya tresNRaya;
	// Acceso al GUI (en realidad a la interface que se encarga de proporcionar
//	el acceso a los elementos del gui que EventGUI necesita)
	private AccesoGUI accesoGUI;

	// creo el actionListener que sirve para todos los botones. uno solo para todos
	// porque todos los botones hacen lo mismo
	private ActionListener actionListener;

	// se inyecta el objeto que implemente el accesoGUI, en nuestro caso el GUI
	public EventGUI(AccesoGUI accesoGUI) {
		super();
		this.accesoGUI = accesoGUI;
		tresNRaya = new TresEnRaya();
		this.crearActionListenerParaBotones();
		this.asignarActionListenerABotones();
	}

	private void asignarActionListenerABotones() {
		Component[] buttonMatrix = accesoGUI.getButtonMatrix();
		for (Component component : buttonMatrix) {
			((MyButton) component).addActionListener(this.actionListener);
		}
	}

	private void crearActionListenerParaBotones() {
		this.actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// me entrega el objeto que ha disparado el evento (entre otras cosas)
				MyButton boton = (MyButton) e.getSource();
				Coordenada coordenada = boton.getCoordenada();
				if (tresNRaya.realizarJugada(coordenada)) {
					boton.setText(tresNRaya.getCasillaContenido(coordenada));
					accesoGUI.getLblMensaje().setText("");
					// fin de juego
					if(tresNRaya.isTresNRaya()) {
						accesoGUI.getLblMensaje().setText("Tres en raya!!!");
						pararJuego();
					}
				}
				// Algo ha fallado
				// ask instead tell
				else {
					// casilla ocupada
					if (tresNRaya.isCasillaOcupadaError(coordenada))
						accesoGUI.getLblMensaje().setText("casilla ocupada");
					// casilla bloqueada
					if (tresNRaya.isCasillaBloqueadaError(coordenada))
						accesoGUI.getLblMensaje().setText("casilla bloqueada");
					// no es una casilla de tu propiedad
					if (tresNRaya.isCasillaImpropiaError(coordenada))
						accesoGUI.getLblMensaje().setText("casilla no es tuya, no se puede mover");
				}
			}

			private void pararJuego() {
				Component[] buttonMatrix = accesoGUI.getButtonMatrix();
				for (int i = 0; i < buttonMatrix.length; i++) {
					buttonMatrix[i].setEnabled(false);
				}
				
			}
		};
	}

}
