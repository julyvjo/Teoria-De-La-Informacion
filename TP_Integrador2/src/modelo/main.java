package modelo;

import java.io.FileNotFoundException;
import java.util.Iterator;

public class main {

	public static void main(String[] args) {
		
		//comentario de july
		//leer un txt y a partir de eso obtener los simbolos con sus probabilidades
		Fuente fuente1 = new Fuente();
		
		try {
			fuente1 = fuente1.leerFuenteTXT("fuente.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		fuente1.mostrarFuente();
		
		
		
		//una vez obtenida la estructura simbolo-probabilidad codificar para huffman, shanon-fano y RLC (para ambas fuentes)
		
		//mostrar resultados
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
