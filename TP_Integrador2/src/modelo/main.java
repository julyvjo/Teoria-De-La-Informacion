package modelo;

import java.io.FileNotFoundException;
import java.util.Iterator;

public class main {
	
	public static void main(String[] args) {
		
		//-----------------------------------------PARTE 1-----------------------------------------
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
		
		//-----------------------------------------PARTE 2-----------------------------------------
		
		Canal canal = new Canal();
		try {
			canal.leerCanalTXT("canal.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		canal.mostrarCanal();
	}
}
