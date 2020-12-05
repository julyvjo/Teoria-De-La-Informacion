package modelo;

import java.io.FileNotFoundException;
import java.util.Iterator;

public class main {
	
	public static void main(String[] args) {
		
		//-----------------------------------------PARTE 1-----------------------------------------
		Fuente fuenteEspañol = new Fuente();
		Fuente fuenteSueco = new Fuente(); 
		
		try {
			fuenteEspañol.leerFuenteTXT("mdp-español.txt");
			fuenteSueco.leerFuenteTXT("mdp-sueco.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		fuenteEspañol.codificarHuffman();
		fuenteEspañol.codificarShannonFano();
		fuenteEspañol.mostrarFuente("mdp-español.txt");
		fuenteSueco.codificarHuffman();
		fuenteSueco.codificarShannonFano();
		//fuenteSueco.mostrarFuente("mdp-sueco.txt");
		
		//-----------------------------------------PARTE 2-----------------------------------------
		
		Canal canal1 = new Canal();
		Canal canal2 = new Canal();
		Canal canal3 = new Canal();
		try {
			canal1.leerCanalTXT("canal1.txt");
			canal2.leerCanalTXT("canal2.txt");
			canal3.leerCanalTXT("canal3.txt");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		canal1.calculos();
		canal2.calculos();
		canal3.calculos();
		
		canal1.mostrarCanal();
		canal2.mostrarCanal();
		canal3.mostrarCanal();
	}
}
