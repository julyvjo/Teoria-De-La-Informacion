package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Fuente {

	TreeMap<Character, Simbolo> simbolos = new TreeMap<Character, Simbolo>();

	public void leerFuenteTXT(String nombreArchivo) throws FileNotFoundException {
		Character c;
		HashMap<Character, Integer> acum = new HashMap<Character, Integer>();
		int total = 0; // total de caracteres en el texto

		// lectura de txt
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		int h;
		boolean isEnter = false;
		try {
			while ((h = br.read()) != -1) {
				if (!isEnter) {
					if (h == 13) {
						h = 187; // cambiamos el "new line" por "UTF BB"
						isEnter = true;
					}
					c = (char) h;
					if (acum.containsKey(c)) { // contiene el caracter
						acum.put(c, acum.get(c) + 1);
					} else { // no lo contiene
						acum.put(c, 1);
					}
					total += 1;
				} else {
					isEnter = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (HashMap.Entry<Character, Integer> entry : acum.entrySet()) { // recorre el hashmap
			Character car = entry.getKey();
			Integer i = entry.getValue();
			this.simbolos.put(car, new Simbolo(car, (double) i / total));
		}
	}

	public void mostrarFuente() {

		for (HashMap.Entry<Character, Simbolo> entry : this.simbolos.entrySet()) {
			Character car = entry.getKey();
			Simbolo s = entry.getValue();

			System.out.println("'" + car + "' " + s.probabilidad);
		}
	}

	public void codificarHuffman() {
		TreeSet<NodoHuffman> lista = new TreeSet<NodoHuffman>();
		//crea lista
		lista = this.creaListaHuffman();
		//crea arbol
		lista = this.creaArbolHuffman(lista);
		//crea codigos
		this.creaCodigoHuffman(lista);
	}

	private TreeSet<NodoHuffman> creaListaHuffman() {
		TreeSet<NodoHuffman> lista = new TreeSet<NodoHuffman>();

		for (HashMap.Entry<Character, Simbolo> entry : this.simbolos.entrySet()) {
			Character car = entry.getKey();
			Simbolo s = entry.getValue();
			lista.add(new NodoHuffman(s.getProbabilidad(), s.getCaracter()));
		}

		return lista;
	}

	private TreeSet<NodoHuffman> creaArbolHuffman(TreeSet<NodoHuffman> lista) {
		NodoHuffman primero, segundo, nuevo;

		while (lista.size() > 1) {
			primero = lista.pollFirst();
			segundo = lista.pollFirst();
			nuevo = new NodoHuffman(primero.getProb() + segundo.getProb(), primero, segundo);
			
			lista.add(nuevo);
		}
		return lista;
	}

	private void creaCodigoHuffman(TreeSet<NodoHuffman> lista) {
		
	}

}