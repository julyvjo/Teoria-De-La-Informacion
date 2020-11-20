package modelo;

public class NodoHuffman implements Comparable {

	double prob;
	Character caracter;
	NodoHuffman izq = null;
	NodoHuffman der = null;

	public NodoHuffman(double prob, char caracter) {

		this.prob = prob;
		this.caracter = caracter;
	}

	public NodoHuffman(double prob, NodoHuffman izq, NodoHuffman der) {

		this.prob = prob;
		this.caracter = null;
		this.izq = izq;
		this.der = der;
	}

	public double getProb() {
		return prob;
	}

	public Character getCaracter() {
		return caracter;
	}

	public NodoHuffman getIzq() {
		return izq;
	}

	public NodoHuffman getDer() {
		return der;
	}

	@Override
	public int compareTo(Object o) {
		int respuesta = 1;
		NodoHuffman otro = (NodoHuffman) o;

		if (this.prob <= otro.prob)
			respuesta = -1;

		return respuesta;
	}
}
