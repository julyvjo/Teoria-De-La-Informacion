package Modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class FuenteMemoriaNula {
	private ArrayList<SimboloMemoriaNula> simbolos = new ArrayList<SimboloMemoriaNula>();

	public void agregarSimbolo(SimboloMemoriaNula s) {
		simbolos.add(s);
	}

	public double getEntropia() {
		double rta = 0;
		Iterator<SimboloMemoriaNula> it = simbolos.iterator();
		while (it.hasNext()) {
			SimboloMemoriaNula s = it.next();
			rta += s.getProbabilidad() * s.getInformacionBin();
		}
		return rta;
	}
}
