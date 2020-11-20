package modelo;

public class NodoSF implements Comparable {

	Character c;
	double probabilidad;
	String codigoSF;

	public NodoSF(Character c, double probabilidad) {
		this.c = c;
		this.probabilidad = probabilidad;
		this.codigoSF = "";
	}

	public Character getCaracter() {
		return c;
	}

	public double getProbabilidad() {
		return probabilidad;
	}

	public String getCodigoSF() {
		return codigoSF;
	}

	public void setCodigoSF(String codigoSF) {
		this.codigoSF = codigoSF;
	}

	@Override
	public int compareTo(Object o) {
		int respuesta = 1;
		NodoSF otro = (NodoSF) o;

		if (this.getProbabilidad() <= otro.getProbabilidad())
			respuesta = -1;

		return respuesta;
	}

}
