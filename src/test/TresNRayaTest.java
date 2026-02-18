package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import control.TresEnRaya;
import modelo.Coordenada;

class TresNRayaTest {
	
	@Test
	void testRealizarJugadaMover() {
		int jugada=1;
		//1
		/*
		 * 0 0 0
		 * 0 O 0
		 * 0 0 0
		 */
		TresEnRaya tresNRaya=new TresEnRaya();
		//la X
		assertTrue(tresNRaya.realizarJugada(new Coordenada(0, 0)));
		jugada++;
		int actual=tresNRaya.getNumerojugada();
		assertEquals(actual, jugada);
		//2
		/*
		 * X 0 0
		 * 0 O 0
		 * 0 0 0
		 */
		assertFalse(tresNRaya.comprobarTresEnRaya());
		//la O
		assertTrue(tresNRaya.realizarJugada(new Coordenada(0, 1)));		
		jugada = checkTurno(jugada, tresNRaya);
		//3
		/*
		 * X O 0
		 * 0 O 0
		 * 0 0 0
		 */
		assertFalse(tresNRaya.comprobarTresEnRaya());
		//repetir no esta libre
		assertFalse(tresNRaya.realizarJugada(new Coordenada(0, 1)));		
		//3
		/*
		 * X O 0
		 * 0 O 0
		 * 0 0 0
		 */
		assertFalse(tresNRaya.comprobarTresEnRaya());
		
		assertTrue(tresNRaya.realizarJugada(new Coordenada(1, 0)));
		jugada = checkTurno(jugada, tresNRaya);
		//4
		/*
		 * X O 0
		 * X O 0
		 * 0 0 0
		 */
		assertFalse(tresNRaya.comprobarTresEnRaya());
		
		assertTrue(tresNRaya.realizarJugada(new Coordenada(2, 0)));
		jugada = checkTurno(jugada, tresNRaya);
		//5
		/*
		 * X O 0
		 * X O 0
		 * O 0 0
		 */
		assertFalse(tresNRaya.comprobarTresEnRaya());
		
		//la sexta jugada
		///////////////////////////////////////////////////
		assertTrue(tresNRaya.realizarJugada(new Coordenada(2, 1)));
		jugada++;
		actual=tresNRaya.getNumerojugada();
		assertEquals(actual, jugada);
		/*
		 * X O 0
		 * X O 0
		 * O X 0
		 */
		assertFalse(tresNRaya.comprobarTresEnRaya());
		
		///////////////////////////////////////////////////
		//jugada 7
		assertTrue("O".equals(tresNRaya.getTipoActualName()));
		//casilla de la X, no es tuya
		assertFalse(tresNRaya.realizarJugada(new Coordenada(2, 1)));
		//la casilla esta en blanco
		assertFalse(tresNRaya.realizarJugada(new Coordenada(1, 2)));
		assertFalse(tresNRaya.comprobarTresEnRaya());
		int posterior=tresNRaya.getNumerojugada();
		assertEquals(actual, posterior);
		actual=tresNRaya.getNumerojugada();
		assertEquals(actual, jugada);
		//ha fallado este caso porque no di valores correctos
		//sigo con la X
		assertTrue(tresNRaya.realizarJugada(new Coordenada(0, 1)));
		assertTrue(tresNRaya.realizarJugada(new Coordenada(0, 2)));
		jugada = checkTurno(jugada, tresNRaya);
		assertTrue(tresNRaya.comprobarTresEnRaya());
		/*
		 * X 0 O
		 * X O 0
		 * O X 0
		 */
		///////////////////////////////////////////////////
		//8
		//hacer jugada invalida de movimiento
		actual=tresNRaya.getNumerojugada();
		assertEquals(actual, jugada);
		assertFalse(tresNRaya.realizarJugada(new Coordenada(1, 1)));
		assertTrue(tresNRaya.realizarJugada(new Coordenada(2, 1)));
		assertTrue(tresNRaya.realizarJugada(new Coordenada(2, 2)));
		assertTrue(tresNRaya.comprobarTresEnRaya());
		posterior=tresNRaya.getNumerojugada();
		assertEquals(actual+1, posterior);
		/*
		 * X 0 O
		 * X O 0
		 * O 0 X
		 */
		///////////////////////////////////////////////////
		//hacer jugada intentando mover una pieza no tuya
		String turno = tresNRaya.getTipoActualName();
		actual=tresNRaya.getNumerojugada();
		//le toca a la O
		assertTrue(tresNRaya.realizarJugada(new Coordenada(1, 1)));
		assertTrue(tresNRaya.realizarJugada(new Coordenada(0, 1)));
		assertFalse(tresNRaya.comprobarTresEnRaya());
		posterior=tresNRaya.getNumerojugada();
		assertEquals(actual+1, posterior);
		/*
		 * X O O
		 * X 0 0
		 * O 0 X
		 */
		///////////////////////////////////////////////////
		//hacer jugada intentando mover una pieza no contigua
		turno = tresNRaya.getTipoActualName();
		assertTrue("X".equals(turno));
		actual=tresNRaya.getNumerojugada();
		//deben ser pares si le toca al 2
		assertTrue(tresNRaya.realizarJugada(new Coordenada(2, 2)));
		assertFalse(tresNRaya.realizarJugada(new Coordenada(0, 2)));
		assertTrue(tresNRaya.realizarJugada(new Coordenada(2, 1)));
		assertFalse(tresNRaya.comprobarTresEnRaya());
		posterior=tresNRaya.getNumerojugada();
		assertEquals(actual+1, posterior);
		/*
		 * X O O
		 * X 0 0
		 * O X 0
		 */
		///////////////////////////////////////////////////
		turno = tresNRaya.getTipoActualName();
		assertTrue("O".equals(turno));
		actual=tresNRaya.getNumerojugada();
		//deben ser pares si le toca al 2
		assertTrue(tresNRaya.realizarJugada(new Coordenada(0, 2)));
		assertTrue(tresNRaya.realizarJugada(new Coordenada(1, 1)));
		assertFalse(tresNRaya.comprobarTresEnRaya());
		posterior=tresNRaya.getNumerojugada();
		assertEquals(actual+1, posterior);
		/*
		 * X O O
		 * X O 0
		 * 0 X 0
		 */
		
		///////////////////////////////////////////////////
		
		
	}

	private int checkTurno(int jugada, TresEnRaya tresNRaya) {
		int actual;
		jugada++;
		actual=tresNRaya.getNumerojugada();
		assertEquals(actual, jugada);
		return jugada;
	}
}
