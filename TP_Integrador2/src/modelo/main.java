package modelo;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int c = 1;
		//men�
		while( c != 0 ) { //0 para salir
			System.out.println("\n\n**********************");
			System.out.println("PARTE 1");
			System.out.println("  1) Fuente espa�ol");
			System.out.println("  2) Fuente sueco\n");
			System.out.println("PARTE 2");
			System.out.println("  3) Canal 1");
			System.out.println("  4) Canal 2");
			System.out.println("  5) Canal 3");
			System.out.println("**********************");
			
			c = sc.nextInt();
			
			switch(c) {
			case 1:
				invocarFuente("mdp-espa�ol.txt");
				break;
			case 2:
				invocarFuente("mdp-sueco.txt");
				break;
			case 3:
				invocarCanal("canal1.txt");
				break;
			case 4:
				invocarCanal("canal2.txt");
				break;
			case 5:
				invocarCanal("canal3.txt");
				break;
			default:
			break;
			}
			
		}
		
		
	}
	
	
	public static void invocarFuente(String f) {
		
		Fuente fuenteEspa�ol = new Fuente();
		
		try {
			fuenteEspa�ol.leerFuenteTXT(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		fuenteEspa�ol.codificarHuffman();
		fuenteEspa�ol.codificarShannonFano();
		fuenteEspa�ol.mostrarFuente(f);
	}
	
	public static void invocarCanal(String c) {
		
		Canal canal = new Canal();
		
		try {
			canal.leerCanalTXT(c);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		canal.calculos();
		canal.mostrarCanal();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
