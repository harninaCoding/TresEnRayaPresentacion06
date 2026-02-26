package control;

import javax.sql.RowSetReader;

import modelo.Coordenada;
import modelo.RespuestaColocacion;
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

	private RespuestaColocacion colocarFicha(Coordenada coordenada) {
		this.tablero.colocarFicha(coordenada, this.juego.getTurnoActual());
		this.juego.incrementaJugada();
		return new RespuestaColocacion(true, tablero.getPosicion(coordenada));
	}

	private RespuestaColocacion colocarFichaInicial() {
		RespuestaColocacion respuesta;
		if (tablero.isLibre(origen)) {
			respuesta = colocarFicha(origen);
			this.mover = true;
			respuesta.setFinJuego(isTresNRaya());
			return respuesta;
		}
		return new RespuestaColocacion("Casilla no libre");
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
	private RespuestaColocacion moverFicha(Coordenada origen, Coordenada destino) {
		RespuestaColocacion respuesta = new RespuestaColocacion();
		if (!tablero.isLibre(destino))
			respuesta.setMensaje("casilla ocupada");
		else if (!origen.isContigua(destino))
			respuesta.setMensaje("casilla no contigua");
		if (tablero.isLibre(destino) && origen.isContigua(destino)) {
			tablero.colocarFicha(origen, Tipo.blanco); // Borra origen
			tablero.colocarFicha(destino, getTipoActual()); // Pone en destino
			respuesta.setRespuesta(true);
			respuesta.setTipo(tablero.getPosicion(destino));
			respuesta.setFinJuego(isTresNRaya());
			return respuesta;
		}
		respuesta.setRespuesta(false);
		return respuesta;
	}

	/**
	 * Va a pedir posiciones de la ficha que queremos colocar hasta que hayamos
	 * elegido una posicion libre
	 */
	public RespuestaColocacion realizarJugada(Coordenada coordenada) {
		// FASE 1: Colocación inicial (Jugadas 1 a 6)
		if (juego.getNumeroJugada() < 6) {
			this.origen = coordenada; // Usamos origen como la casilla donde se coloca
			return colocarFichaInicial();
		}

		// El usuario elige qué ficha quiere mover
		if (mover) {
			RespuestaColocacion respuesta = new RespuestaColocacion();
			boolean comprobarPropiedad = tablero.isPropiedad(coordenada, getTipoActual());
			if (!comprobarPropiedad)
				respuesta.setMensaje("la casilla no es de tu propiedad");
			boolean comprobarBloqueada = tablero.isBloqueada(coordenada);
			if (comprobarBloqueada)
				respuesta.setMensaje("la casilla esta bloqueada");
			if (comprobarPropiedad && !comprobarBloqueada) {
				this.origen = coordenada;
				tablero.borrarCasilla(coordenada, Tipo.blanco);
				this.mover = false; // Cambiamos el estado: el siguiente clic será el destino
				respuesta.setRespuesta(true);
				respuesta.setTipo(tablero.getPosicion(origen));
				respuesta.setFinJuego(isTresNRaya());
			}
			return respuesta;
		}
		// El usuario ya eligió origen, ahora elige el destino
		else {
			this.destino = coordenada;
			RespuestaColocacion respuesta = moverFicha(origen, destino);
			if (respuesta.isRespuesta()) {
				juego.incrementaJugada();
				this.mover = true; // Reset para el siguiente turno
			}
			return respuesta;
		}
	}

	public String getTipoActualName() {
		return this.juego.getTurnoActualName();
	}

	public Tipo getTipoActual() {
		return this.juego.getTurnoActual();
	}

//	public String getTipoAnteriorName() {
//		return this.juego.getTurnoAnteriorName();
//	}

	public boolean comprobarTresEnRaya() {
		return tablero.isTresEnRaya();
	}

	public int getNumerojugada() {
		return juego.getNumeroJugada();
	}

	public String getCasillaContenido(Coordenada coordenada) {
		return tablero.getPosicion(coordenada).getNombre();
	}

	public boolean isCasillaOcupadaError(Coordenada coordenada) {
		return !tablero.isLibre(coordenada);
	}

	public boolean isCasillaBloqueadaError(Coordenada coordenada) {
		return tablero.isBloqueada(coordenada);
	}

	public boolean isCasillaImpropiaError(Coordenada coordenada) {
		return !tablero.isPropiedad(coordenada, getTipoActual());
	}

	public boolean isTresNRaya() {
		return tablero.isTresEnRaya();
	}

}
