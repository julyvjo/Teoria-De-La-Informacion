package Modelo;

public class Simbolo {
	private char valor;
	private float probabilidad;

	// comentario de lau

	public Simbolo(char valor, float probabilidad) {

		this.valor = valor;
		this.probabilidad = probabilidad;
	}

	public char getValor() {
		return valor;
	}

	public void setValor(char valor) {
		this.valor = valor;
	}

	public float getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(float probabilidad) {
		this.probabilidad = probabilidad;
	}

	public float getInformacionBin() {
		return (float) (Math.log10(1 / probabilidad) / Math.log10(2));
	}

	/*
	 * 
	 */
	public float getInformacionNat() {
		return (float) Math.log(1 / probabilidad);
	}

	/*
	 * 
	 */
	public float getInformacionHart() {
		return (float) Math.log10(1 / probabilidad);
	}

}
