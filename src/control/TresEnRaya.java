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

	private RespuestaColocacion colocarFichaInicial() {
		RespuestaColocacion respuestaColocacion=new RespuestaColocacion();
		if (tablero.isLibre(origen)) {
			colocarFicha(origen);
			// Importante: mantenemos mover = true para que al llegar a la jugada 7
			// el sistema sepa que debe empezar pidiendo el origen.
			this.mover = true;
			respuestaColocacion.setRespuesta(true);
			respuestaColocacion.setTipo(getTipoActual());
			respuestaColocacion.setFinJuego(isTresNRaya());
		}
		respuestaColocacion.setRespuesta(false);
		respuestaColocacion.setMensaje("no esta libre");
		respuestaColocacion.setTipo(getTipoActual());
		respuestaColocacion.setFinJuego(isTresNRaya());
		return respuestaColocacion;
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
		RespuestaColocacion respuesta=new RespuestaColocacion();
		if (tablero.isLibre(destino) && origen.isContigua(destino)) {
			tablero.colocarFicha(origen, Tipo.blanco); // Borra origen
			tablero.colocarFicha(destino, getTipoActual()); // Pone en destino
			respuesta.setRespuesta(true);
			respuesta.setFinJuego(isTresNRaya());
			return respuesta;
		}
		respuesta.setRespuesta(false);
		if(!tablero.isLibre(destino)) respuesta.setMensaje("casilla ocupada");
		else if(!origen.isContigua(destino)) respuesta.setMensaje("casilla no contigua");
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
			boolean comprobarPropiedad = tablero.isPropiedad(coordenada, getTipoActual());
			boolean comprobarBloqueada = tablero.isBloqueada(coordenada);
			if (comprobarPropiedad && !comprobarBloqueada) {
				this.origen = coordenada;
				tablero.borrarCasilla(coordenada, Tipo.blanco);
				this.mover = false; // Cambiamos el estado: el siguiente clic será el destino
				RespuestaColocacion respuestaColocacion = new RespuestaColocacion();
				respuestaColocacion.setRespuesta(true);
				respuestaColocacion.setFinJuego(isTresNRaya());
				return respuestaColocacion;
			}
			RespuestaColocacion respuestaColocacion = new RespuestaColocacion();
			respuestaColocacion.setRespuesta(false);
			if(!comprobarPropiedad) respuestaColocacion.setMensaje("no es tuya");
			else if(!comprobarBloqueada) respuestaColocacion.setMensaje("esta bloqueada");
			respuestaColocacion.setFinJuego(isTresNRaya());
			return respuestaColocacion;
		}
		// El usuario ya eligió origen, ahora elige el destino
		else {
			this.destino = coordenada;
			RespuestaColocacion respuestaColocacion=moverFicha(origen, destino);
			boolean exito = respuestaColocacion.isRespuesta();
			if (exito) {
				juego.incrementaJugada();
				this.mover = true; // Reset para el siguiente turno
			}

			return respuestaColocacion;
		}
	}

//	private boolean colocarFicha(Coordenada coordenada, Coordenada antigua) {
//		if (coordenada.isContigua(antigua)) {
//			return colocarFicha(coordenada);
//		}
//		return false;
//	}

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
		return !tablero.isPropiedad(coordenada,getTipoActual());
	}

	public boolean isTresNRaya() {
		return tablero.isTresEnRaya();
	}

}
