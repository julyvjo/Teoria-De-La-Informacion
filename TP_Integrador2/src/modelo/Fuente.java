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

public class Fuente {

	ArrayList<Simbolo> simbolos = new ArrayList<Simbolo>();
	
	
	
	public Fuente leerFuenteTXT(String nombreArchivo) throws FileNotFoundException {
		
		Character c;
		Fuente fuente = new Fuente();
		HashMap<Character, Integer> acum = new HashMap<Character, Integer>();
		int total = 0; //total de caracteres en el texto (se usa para sacar las probabilidades haciendo acum(simbolo) / total
		
		//lectura de txt
		BufferedReader br = new BufferedReader( new FileReader(nombreArchivo) );
		int h;
		
		try {
			while( ( h = br.read() ) != -1 ) {
				
				c = (char) h;
				
				if(acum.containsKey(c)) { //contiene el caracter
					
					acum.put(c, acum.get(c)+1);
					
				}else { //no lo contiene
					
					acum.put(c, 1);
				}
				
				total +=1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		for (HashMap.Entry<Character, Integer> entry : acum.entrySet()) { //toma el hashmap, lo recorre y con sus datos crea los simbolos dentro de la fuente
		    Character car = entry.getKey();
		    Integer i = entry.getValue();
		    
		    Simbolo s = new Simbolo(car, (double) i/total);
		    
		    fuente.simbolos.add(s);
		    
		}
	
		return fuente;
	}
	
	
	public void mostrarFuente() {
		
		Iterator<Simbolo> it = this.simbolos.iterator();
		while( it.hasNext() ) {
			Simbolo s = it.next();
			System.out.println(s.c + " " + s.probabilidad);
		}
	}
	
	
	
	
	
}



class Simbolo{
	
	char c;
	double probabilidad;
	
	public Simbolo(char c, double probabilidad) {
		
		this.c = c;
		this.probabilidad = probabilidad;
	}
	
}