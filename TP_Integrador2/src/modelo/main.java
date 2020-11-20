package modelo;

import java.io.FileNotFoundException;
import java.util.Iterator;

public class main {

	public static void main(String[] args) {

		// leer un txt y a partir de eso obtener los simbolos con sus probabilidades
		Fuente fuente1 = new Fuente();
		Fuente fuente2 = new Fuente();

		try {
			fuente1.leerFuenteTXT("fuente.txt");
			fuente2.leerFuenteTXT("fuente.txt");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		fuente1.codificarHuffman();
		fuente1.mostrarFuente();
		fuente1.codificarShannonFano();
		
		fuente1.mostrarFuente();

		// una vez obtenida la estructura simbolo-probabilidad codificar para huffman,
		// shanon-fano y RLC (para ambas fuentes)

		// calculos

		// mostrar resultados

	}

}
