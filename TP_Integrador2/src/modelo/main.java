package modelo;

import java.io.FileNotFoundException;
import java.util.Iterator;

public class main {
	
	public static void main(String[] args) {
		
		//-----------------------------------------PARTE 1-----------------------------------------
		Fuente fuenteEspa�ol = new Fuente();
		Fuente fuenteSueco = new Fuente(); 
		
		try {
			fuenteEspa�ol.leerFuenteTXT("mdp-espa�ol.txt");
			fuenteSueco.leerFuenteTXT("mdp-sueco.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		fuenteEspa�ol.codificarHuffman();
		fuenteEspa�ol.codificarShannonFano();
		//fuenteEspa�ol.mostrarFuente("mdp-espa�ol.txt");
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
