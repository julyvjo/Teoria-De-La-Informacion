package Main;

import Modelo.FuenteMemoriaNula;
import Modelo.SimboloMemoriaNula;

public class Main {

	public static void main(String[] args) {

		FuenteMemoriaNula f = new FuenteMemoriaNula();
		f.agregarSimbolo(new SimboloMemoriaNula('A',0.25));
		f.agregarSimbolo(new SimboloMemoriaNula('B',0.25));
		f.agregarSimbolo(new SimboloMemoriaNula('C',0.25));
		f.agregarSimbolo(new SimboloMemoriaNula('D',0.25));
		System.out.println(f.getEntropia());
	}

}
