package control;

import modelo.Coordenada;
import modelo.Tablero;
import modelo.Tipo;

public class TresEnRaya {
	private Tablero tablero;
	private Juego juego;
	private Coordenada origen = null;
	private Coordenada destino = null;
	private boolean mover = false;

	public TresEnRaya() {
		super();
		tablero = new Tablero();
		juego = new Juego();
	}

	private boolean colocarFichaInicial() {
		if (tablero.isLibre(origen)) {
			colocarFicha(origen);
			// Importante: mantenemos mover = true para que al llegar a la jugada 7
			// el sistema sepa que debe empezar pidiendo el origen.
			this.mover = true;
			return true;
		}
		return false;
	}

	/**
	 * Este metodo debe mover una ficha ya puesta en el tablero, en concreto se
	 * encarga de decidir si la ficha puede moverse, si es as� la borra Para poder
	 * moverse se deben cumplir los siguientes requisitos: que la ficha sea del
	 * jugador al que pertenece el turno y si est� no est� bloqueada si la ficha ha
	 * sido movida el campo mover se pone a true.
	 * 
	 * @return
	 */
	private boolean moverFicha(Coordenada origen, Coordenada destino) {
		if (tablero.isLibre(destino) && origen.isContigua(destino)) {
			tablero.colocarFicha(origen, Tipo.blanco); // Borra origen
			tablero.colocarFicha(destino, getTipoActual()); // Pone en destino
			return true;
		}
		return false;
	}

	/**
	 * Va a pedir posiciones de la ficha que queremos colocar hasta que hayamos
	 * elegido una posicion libre
	 */
	public boolean realizarJugada(Coordenada coordenada) {
		// FASE 1: Colocación inicial (Jugadas 1 a 6)
		if (juego.getNumeroJugada() < 6) {
			this.origen = coordenada; // Usamos origen como la casilla donde se coloca
			return colocarFichaInicial();
		}

		// El usuario elige qué ficha quiere mover
		if (mover) {
			boolean comprobarPropiedad = tablero.isPropiedad(coordenada, getTipoActual());
			boolean comprobarBloqueada = tablero.isBloqueada(coordenada);
			if (comprobarPropiedad && !comprobarBloqueada) {
				this.origen = coordenada;
				tablero.borrarCasilla(coordenada, Tipo.blanco);
				this.mover = false; // Cambiamos el estado: el siguiente clic será el destino
				return true;
			}
			return false;
		}

		// El usuario ya eligió origen, ahora elige el destino
		else {
			this.destino = coordenada;
			boolean exito = moverFicha(origen, destino);
			if (exito) {
				juego.incrementaJugada();
				this.mover = true; // Reset para el siguiente turno
				return true;
			}

			return false;
		}
	}

	private boolean colocarFicha(Coordenada coordenada, Coordenada antigua) {
		if (coordenada.isContigua(antigua)) {
			return colocarFicha(coordenada);
		}
		return false;
	}

	private boolean colocarFicha(Coordenada coordenada) {
		boolean colocada = this.tablero.colocarFicha(coordenada, this.juego.getTurnoActual());
		if (colocada) {
			this.juego.incrementaJugada();
			return true;
		}
		return false;
	}

	public String getTipoActualName() {
		return this.juego.getTurnoActualName();
	}

	public Tipo getTipoActual() {
		return this.juego.getTurnoActual();
	}

	public String getTipoAnteriorName() {
		return this.juego.getTurnoAnteriorName();
	}

	public boolean comprobarTresEnRaya() {
		return tablero.isTresEnRaya();
	}

	public int getNumerojugada() {
		return juego.getNumeroJugada();
	}

}
