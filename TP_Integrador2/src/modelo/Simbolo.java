package modelo;

public class Simbolo {

	Character c;
	double probabilidad;
	String codHuffman;
	String codShannonFano;

	public Simbolo(char c, double probabilidad) {

		this.c = c;
		this.probabilidad = probabilidad;
		this.codHuffman = "";
		this.codShannonFano = "";
	}

	public char getCaracter() {
		return c;
	}

	public void setCaracter(char c) {
		this.c = c;
	}

	public double getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}

	public String getCodHuffman() {
		return codHuffman;
	}

	public void setCodHuffman(String codHuffman) {
		this.codHuffman = codHuffman;
	}

	public String getCodShannonFano() {
		return codShannonFano;
	}

	public void setCodShannonFano(String codShannonFano) {
		this.codShannonFano = codShannonFano;
	}
	
	public double getCantidadDeInformacion() {
		return -(Math.log10(this.getProbabilidad()) / Math.log10(2) );
	}

}
