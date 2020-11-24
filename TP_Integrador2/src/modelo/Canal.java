package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Canal {
	double[] priori = new double[10];
	double[][] mat = new double[10][10];
	int i = 0, j = 0;

	public void leerCanalTXT(String nombreArchivo) throws FileNotFoundException {
		File file = new File(nombreArchivo);
		Scanner sc = new Scanner(file);

		this.i = sc.nextInt();
		this.j = sc.nextInt();

		for (int k = 0; k < this.i; k++) {
			this.priori[k] = (double) sc.nextDouble();
		}

		for (int p = 0; p < this.i; p++) {
			for (int q = 0; q < this.j; q++) {
				mat[p][q] = sc.nextDouble();
			}
		}
	}

	public void mostrarCanal() {
		DecimalFormat df = new DecimalFormat("0.000");
		System.out.println("----------------- PARTE 2 -------------------");
		System.out.println("Ai  P(Ai)");
		for (int k = 0; k < this.i; k++) {
			System.out.println("A" + (k + 1) + "  " + this.priori[k]);
		}
		System.out.println();
		System.out.println("Matriz del Canal:");
		for (int p = 0; p < this.i; p++) {
			System.out.print("|");
			for (int q = 0; q < this.j; q++) {
				System.out.print(this.mat[p][q] + " ");
			}
			System.out.println("|");
		}
	}

}
