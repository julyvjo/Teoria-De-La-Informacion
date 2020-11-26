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
		//fuenteEspañol.mostrarFuente("mdp-español.txt");
		fuenteSueco.codificarHuffman();
		fuenteSueco.codificarShannonFano();
		//fuenteSueco.mostrarFuente("mdp-sueco.txt");
		
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
