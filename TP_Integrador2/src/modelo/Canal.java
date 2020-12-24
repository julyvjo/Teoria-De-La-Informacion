package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class Canal {
	double[] priori = new double[10];
	double[] probB = new double[10];
	double[][] posteriori = new double[10][10];
	double[] Hposteriori = new double[10];
	double[][] mat = new double[10][10];
	int i = 0, j = 0;

	// Metodo que lee el .TXT y carga el canal
	public void leerCanalTXT(String nombreArchivo) throws FileNotFoundException {
		File file = new File(nombreArchivo);
		Scanner sc = new Scanner(file).useLocale(Locale.US);

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
		System.out.println("Ai  P(Ai)");
		for (int k = 0; k < this.i; k++) {
			System.out.println("A" + (k + 1) + "  " + this.priori[k]);
		}
		System.out.println();
		System.out.println("Matriz del Canal:");
		for (int p = 0; p < this.i; p++) {
			System.out.print("| ");
			for (int q = 0; q < this.j; q++) {
				System.out.print(df.format(this.mat[p][q]) + " ");
			}
			System.out.println("|");
		}

		System.out.println("\nCALCULOS INTERMEDIOS\n");
		
		
		System.out.println("Probabilidades de salida:");
		for (int k = 0; k < this.j; k++) {
			System.out.print(df.format(this.probB[k]) + "  ");
		}

		System.out.println("\n\nProbabilidades a-posteriori:");
		for (int p = 0; p < this.i; p++) {
			System.out.print("| ");
			for (int q = 0; q < this.j; q++) {
				System.out.print(df.format(this.posteriori[p][q]) + " ");
			}
			System.out.println("|");
		}
		
		System.out.println("\nEntropia a-priori: " + df.format(this.Hpriori()));
		
		System.out.println("\nEntropias a-posteriori:");
		for (int k = 0; k < this.j; k++) {
			System.out.print(df.format(this.Hposteriori[k]) + "  ");
		}
		System.out.println("\n\nRESULTADOS\n");
		System.out.println("- H(A/B) = " + df.format(this.equivocacion()));
		System.out.println("- I(A,B) = " + df.format(this.infoMutua()));
		System.out.println("- I(B,A) = " + df.format(this.infoMutuaSimetrica()));
	}

	// --------------------------------------------------------------------------------------------CALCULOS

	public void calculos() {
		// Se invocrian todos los calculos a realizar del canal
		this.calculaProbB();
		this.calculaProbPosteriori();
		this.calculaHposteriori();
	}

	public double cantInfo(double p) {
		double res = 0;
		
		if (p != 0)
			res = Math.log10(1 / p) / Math.log10(2);

		return res;
	}

	public double Hpriori() {
		double hpriori = 0;

		for (int i = 0; i < this.i; i++) {

			hpriori += this.priori[i] * this.cantInfo(this.priori[i]);
		}

		return hpriori;
	}

	public void calculaHposteriori() {

		for (int j = 0; j < this.j; j++) {
			double h = 0;
			for (int i = 0; i < this.i; i++) {
				h += Math.abs(this.posteriori[i][j] * this.cantInfo(this.posteriori[i][j]));
			}
			this.Hposteriori[j] = h;
		}

	}

	public void calculaProbB() {

		for (int j = 0; j < this.j; j++) {
			double prob = 0;
			for (int i = 0; i < this.i; i++) {
				prob += this.mat[i][j] * this.priori[i];
			}
			this.probB[j] = (double) Math.round(prob * 1000) / 1000;
		}
	}

	public void calculaProbPosteriori() {
		// prob( ai / bi ) -> matriz

		for (int i = 0; i < this.i; i++) {
			for (int j = 0; j < this.j; j++) {
				posteriori[i][j] = (double) (this.mat[i][j] * this.priori[i] / this.probB[j]);
			}
		}
	}

	public double equivocacion() {
		double equiv = 0;

		for (int j = 0; j < this.j; j++) {
			equiv += (double) (this.Hposteriori[j] * this.probB[j]);
		}

		return equiv;
	}

	public double infoMutua() {
		return this.Hpriori() - this.equivocacion();
	}
	
	public double infoMutuaSimetrica() {
        double infosimetrica = 0;
        //I(B,A) = H(B) - H(B/A)
        double hb = 0; //H(B)
        double hba = 0; //H(B/A)

        //armam H(B)
        for(int j=0; j<this.j; j++) {
            hb += this.probB[j] * this.cantInfo(this.probB[j]);
        }

        //arma H(B/A)
        double[] hBa = new double[10]; //esto es H(B/a), lo necesitamos para calcular H(B/A)

        for(int i=0; i<this.i; i++) {
            hBa[i] = 0;
            for(int j=0; j<this.j; j++) {
                hBa[i] += this.mat[i][j] * this.cantInfo(this.mat[i][j]);
            }
        }
        for(int i=0; i<this.i; i++) {
            hba += this.priori[i] * hBa[i];
        }
        return hb - hba;
    }
}
