package Main;

import Modelo.FuenteMarkov;
import Modelo.FuenteMemoriaNula;
import Modelo.SimboloMemoriaNula;

public class Main {

	public static void main(String[] args) {

		double m[][] = new double[3][3];
		m[0][0] = (double) 1 / 2;
		m[0][1] = (double) 1 / 3;
		m[0][2] = (double) 0;
		m[1][0] = (double) 1 / 2;
		m[1][1] = (double) 1 / 3;
		m[1][2] = (double) 1;
		m[2][0] = (double) 0;
		m[2][1] = (double) 1 / 3;
		m[2][2] = (double) 0;

		FuenteMemoriaNula f = new FuenteMemoriaNula();
		f.agregarSimbolo(new SimboloMemoriaNula('A', 0.10));
		f.agregarSimbolo(new SimboloMemoriaNula('B', 0.20));
		f.agregarSimbolo(new SimboloMemoriaNula('C', 0.30));
		f.agregarSimbolo(new SimboloMemoriaNula('D', 0.40));
		System.out.println("Entropia:" + f.getEntropia());

		FuenteMarkov fm = new FuenteMarkov(m, 3);
		System.out.println("Entropia:" + fm.getEntropia());
	}

}
