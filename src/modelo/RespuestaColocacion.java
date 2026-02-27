package modelo;

public class RespuestaColocacion {
	private boolean respuesta=false;
	private String mensaje="";
	private Tipo tipo=Tipo.blanco;
	private boolean finJuego=false;
	
	public RespuestaColocacion(boolean respuesta, String mensaje, Tipo tipo, boolean finJuego) {
		super();
		this.respuesta = respuesta;
		this.mensaje = mensaje;
		this.tipo = tipo;
		this.finJuego = finJuego;
	}
	
	public RespuestaColocacion(String mensaje) {
		super();
		this.mensaje = mensaje;
		this.respuesta=false;
	}

	public RespuestaColocacion(Tipo tipo,boolean isFinJuego) {
		super();
		this.respuesta = true;
		this.tipo = tipo;
		this.finJuego=isFinJuego;
	}

	public RespuestaColocacion() {
		super();
		// TODO Auto-generated constructor stub
	}


	public boolean isRespuesta() {
		return respuesta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public boolean isFinJuego() {
		return finJuego;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public void setFinJuego(boolean finJuego) {
		this.finJuego = finJuego;
	}
	
	
}
