package modelo;

import java.io.FileNotFoundException;
import java.util.Iterator;

public class main {
	
	public static void main(String[] args) {

		//Leer un txt y a partir de eso obtener los simbolos con sus probabilidades
		Fuente fuente1 = new Fuente();
		Fuente fuente2 = new Fuente();

		try {
			fuente1.leerFuenteTXT("fuente.txt");
			fuente2.leerFuenteTXT("fuente.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		fuente1.codificarHuffman();
		fuente1.codificarShannonFano();
		fuente1.mostrarFuente();
		try {
			System.out.println("RLC: " + fuente1.generaRLC("fuente.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Calculos
		
		System.out.println("ENTROPIA LONG MEDIA HUFFMAN LONG MEDIA SHANNONFANO");
		System.out.println(fuente1.entropía());
		System.out.println(fuente1.longitudMedia("huffman"));
		System.out.println(fuente1.longitudMedia("shannonfano"));
		//Mostrar resultados
	}
}
