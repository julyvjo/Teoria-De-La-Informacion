package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
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

		//Lectura de txt
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		int h;
		boolean isEnter = false;
		try {
			while ((h = br.read()) != -1) {
				if (!isEnter) {
					if (h == 13) {
						h = 126; // cambiamos el "new line" por "UTF 126"
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
		DecimalFormat df = new DecimalFormat("0.000");
		System.out.println("____________________________________________");
		System.out.println("Si  P(Si)       Huffman         Shannon-Fano");
		System.out.println("____________________________________________");
		for (HashMap.Entry<Character, Simbolo> entry : this.simbolos.entrySet()) {
			Character car = entry.getKey();
			Simbolo s = entry.getValue();
			System.out.println(car + "   " + df.format(s.probabilidad) + "\t" + s.getCodHuffman() + "\t\t" + s.getCodShannonFano());
		}
		System.out.println("____________________________________________");
		System.out.println();
	}

// ---------------------------------------------------------------------------------------------------------HUFFMAN
	public void codificarHuffman() {
		TreeSet<NodoHuffman> lista = new TreeSet<NodoHuffman>();
		// crea lista
		lista = this.creaListaHuffman();
		// crea arbol
		lista = this.creaArbolHuffman(lista);
		// crea codigos
		this.creaCodigoHuffman(lista.first(), "");
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

	private void creaCodigoHuffman(NodoHuffman nodo, String codigo) {

		if (nodo.getCaracter() == null) {
			String codIzq = codigo + "0";
			String codDer = codigo + "1";
			this.creaCodigoHuffman(nodo.getIzq(), codIzq);
			this.creaCodigoHuffman(nodo.getDer(), codDer);
		} else {
			Simbolo aux = this.simbolos.get(nodo.getCaracter());
			aux.setCodHuffman(codigo);
		}
	}

// ---------------------------------------------------------------------------------------------------------SHANNON-FANO

	public void codificarShannonFano() {
		TreeSet<NodoSF> lista = new TreeSet<NodoSF>();
		//Crear lista
		lista = this.creaListaShannonFano();
		//Crea codigo
		this.creaCodigoShannonFano(lista,1);
	}

	private TreeSet<NodoSF> creaListaShannonFano() {
		TreeSet<NodoSF> lista = new TreeSet<NodoSF>();

		for (HashMap.Entry<Character, Simbolo> entry : this.simbolos.entrySet()) {
			Simbolo s = entry.getValue();
			lista.add(new NodoSF(s.getCaracter(), s.getProbabilidad()));
		}

		return lista;
	}

	private void creaCodigoShannonFano(TreeSet<NodoSF> lista, double probTotal) {
		double probAcum = 0;
		TreeSet<NodoSF> listaAux = new TreeSet<NodoSF>();

		if (1 < lista.size()) {
			NodoSF nodo = lista.first();
			while(!this.esMasCercanoALaMitad(probTotal, probAcum, nodo.getProbabilidad()) && lista.size() > 1){
				probAcum += nodo.getProbabilidad();
				nodo = lista.pollFirst();
				nodo.setCodigoSF(nodo.getCodigoSF() + "1");
				listaAux.add(nodo);
				nodo = lista.first();
			}
	
			Iterator<NodoSF> it = lista.iterator();
			while (it.hasNext()) {
				NodoSF nodoAux = it.next();
				nodoAux.setCodigoSF(nodoAux.getCodigoSF() + "0");
			}
			this.creaCodigoShannonFano(lista,probTotal-probAcum);
			this.creaCodigoShannonFano(listaAux,probAcum);

		} else {
			Simbolo s = this.simbolos.get(lista.first().getCaracter());
			s.setCodShannonFano(lista.first().getCodigoSF());
		}
	}
	
	private boolean esMasCercanoALaMitad(double probTotal, double probAcum, double probAct) { // true mete el actual
		boolean respuesta = false;

		if (Math.abs((probAcum - probTotal / 2)) <= Math.abs((probAcum + probAct - probTotal / 2)))
			respuesta = true;

		return respuesta;
	}
//--------------------------------------------------------------------------------------------------------------------RLC
	public String generaRLC(String nombreArchivo) throws FileNotFoundException {
		int h, cont = 1; //Cuenta la cantidad de caracteres repetidos
		StringBuilder RLC = new StringBuilder();
		Character c, cAct = null;
		
		//Lectura de txt
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		boolean isEnter = false;
		try {
			while ((h = br.read()) != -1) {
				if (!isEnter) {
					if (h == 13) {
						h = 126; // cambiamos el "new line" por "UTF 126"
						isEnter = true;
					}
					c = (char) h;
					if(c == cAct) {
						cont++;
					}else {
						if(cAct!= null) {
							String s = Character.toString(cAct) + Integer.toString(cont);
							RLC.append(s);
						}
						cAct = c;
						cont = 1;
					}
				}else {
					isEnter = false;
				}
			}
			String s = Character.toString(cAct) + Integer.toString(cont);
			RLC.append(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return RLC.toString();
	}
}