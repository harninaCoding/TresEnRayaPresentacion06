package control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Coordenada;
import modelo.RespuestaColocacion;
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
	// Si necesitas usar un objeto, debe existir, si no tienes nullpointerException
	// cuando lo uses. Dentro de Eventgui NO se crea ningun objeto AccesoGUI.
	// es decir, no hay un new AccesoGUI().
	// Entonces, desde fuera, la clase que cree el objeto eventGUI debe crear un
//	objeto AccesoGUI y pasarselo al EventGUI cuando lo construya
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
				RespuestaColocacion respuestaJugada = tresNRaya.realizarJugada(coordenada);
				// Tell dont ask
				if (respuestaJugada.isRespuesta())
					boton.setText(respuestaJugada.getTipo().getNombre());
				else
					// De esta forma como el tresNRAya me dice lo que ha pasado
					// no tengo que preguntarselo aqui
					accesoGUI.getLblMensaje().setText(respuestaJugada.getMensaje());
				if (respuestaJugada.isFinJuego()) {
					pararJuego();
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
