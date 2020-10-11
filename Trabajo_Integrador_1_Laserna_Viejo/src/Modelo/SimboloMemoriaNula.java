package Modelo;

public class SimboloMemoriaNula {
	private char valor;
	private double probabilidad;

	// comentario de lau 1

	public SimboloMemoriaNula(char valor, double probabilidad) {

		this.valor = valor;
		this.probabilidad = probabilidad;
	}

	public char getValor() {
		return valor;
	}

	public void setValor(char valor) {
		this.valor = valor;
	}

	public double getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(float probabilidad) {
		this.probabilidad = probabilidad;
	}

	public double getInformacionBin() {
		return (double) (Math.log10(1 / probabilidad) / Math.log10(2));
	}

	public double getInformacionNat() {
		return (double) Math.log(1 / probabilidad);
	}

	public double getInformacionHart() {
		return (double) Math.log10(1 / probabilidad);
	}

}
