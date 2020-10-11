package Modelo;

public class FuenteMarkov {
	static final int ITERACIONES = 6;
	private int cantSimbolos;
	private double m[][];
	private double v[] = null;

	public FuenteMarkov(double m[][], int cantSimbolos) {
		this.cantSimbolos = cantSimbolos;
		this.m = m;
		this.v = calculaV(ITERACIONES);
	}

	public int getCantSimbolos() {
		return cantSimbolos;
	}

	public double[][] getM() {
		return m;
	}

	public double[] getV() {
		return v;
	}

	private double[][] matrizAlCuadrado(double mat[][]) {
		double acum;
		double[][] mAux = new double[cantSimbolos][cantSimbolos];

		for (int i = 0; i < cantSimbolos; i++) {
			for (int j = 0; j < cantSimbolos; j++) {
				acum = 0;
				for (int k = 0; k < cantSimbolos; k++) {
					acum += mat[i][k] * mat[k][j];
				}
				mAux[i][j] = acum;
			}
		}
		return mAux;
	}

	private double[] calculaV(int cantIter) {
		double[][] mAct = this.m;
		double[] v = new double[cantSimbolos];

		for (int i = 0; i < cantIter; i++) {
			mAct = matrizAlCuadrado(mAct);
		}

		for (int i = 0; i < cantSimbolos; i++) {
			v[i] = mAct[i][1];
		}
		return v;
	}

	private double cantInfo(double p) {
		if (p != 0)
			return (double) (Math.log10(1 / p) / Math.log10(2));
		else
			return 0;
	}

	public double getEntropia() {
		double rta = 0, h;

		for (int j = 0; j < cantSimbolos; j++) {
			h = 0;
			for (int i = 0; i < cantSimbolos; i++) {
				h += m[i][j] * cantInfo(m[i][j]);
			}
			rta += h * v[j];
		}

		return rta;
	}
}
