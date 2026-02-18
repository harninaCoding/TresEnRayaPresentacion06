package modelo;

public class Coordenada {
	private int x, y;

	public Coordenada(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @return true si son contiguas
	 */
	public boolean isContigua(Coordenada antigua) {
		int x = this.x - antigua.getX(), y = this.y - antigua.getY();
		if (x > -2 && x < 2 && y > -2 && y < 2)
			return true;
		return false;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
