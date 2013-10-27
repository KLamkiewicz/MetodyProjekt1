package main;

import static polynomialOperators.Polynomial.generatePolynomials;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import customExceptions.InputException;
import polynomialOperators.DataManipulation;
import polynomialOperators.Polynomial;
import static matrixOperators.Matrix.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, InputException {
		
		DataManipulation data = new DataManipulation();
		
		data.readDataFromFile(); // wczytuje dane wejœciowe do arry List obiektu Data

		
		// konwersja Array List do tablicy ( wyci¹gam wartoœci z Array List z obiektu data )
		int n = data.getInputx().size() - 1;
		
		double[] z = new double[n + 1];
		double[] y = new double[n + 1];
		double[] x = new double[n + 1];
		
		for (int i = 0; i <= n; i++) {
			z[i] = data.getInputz().get(i);
		}
		
		for (int i = 0; i <= n; i++) {
			y[i] = data.getInputy().get(i);
		}
		
		for (int i = 0; i <= n; i++) {
			x[i] = data.getInputx().get(i);
		}
		//KONIEC konwersji
		
		double[][] m =new double[2*n+2][2*n+2];  //glowna macierz
		double[] b =new double[2*n+2];  //wektor kolumnowy B ( sklada sie z y'ow i z'ow )
		double detm; // wyznacznik glowny
		
		List<Polynomial> polynomiallist = new ArrayList<Polynomial>();  // lista wielomianów 
		polynomiallist = generatePolynomials(x, 2 * n + 1); // generowanie wielomianów na podstawie danych wejsciowych x

		System.out.println("n wynosi: " + n);

		System.out.println("======================================================="); 
		for (int counter = 0; counter < polynomiallist.size(); counter++) {
			polynomiallist.get(counter).printPolynomial();  //drukowanie wielomianów ( wspolczynnikow stoj¹cych przy a)
		}
		
		
		//przepisanie wartosci do glownej macierzy
		for(int p=0;p<=2*n+1;p++){
			for(int i=0;i<=2*n+1;i++){
				double[] temp = new double[2*n+1];
				temp =polynomiallist.get(p).getRatios();
				m[p][i]=temp[i];
				}
			
		}
		//koniec przepisania
		detm=det(m);
		
		
		//przepisanie wartosci do wektora kolumnowego.
		int counter=0;
		for(int p=0;p<n+1;p++){
			b[p]=y[p];
			counter++;
		}
		for(int p=0;p<n+1;p++){
			b[counter]=z[p];
			counter++;
		}
		// koniec przepisania
	
		
		
	System.out.println("======================================================="); 
	System.out.println("Macierz glowna: ");
	printMatrix(m);
	System.out.println("");
	System.out.println("ma wyznacznik=" + detm +"\n");
	System.out.println("======================================================="); 
		
	
	System.out.println("Wektor kolumnowy b");
	for(int p=0;p<2*n+2;p++){
		System.out.println("["+b[p]+"]");
	}
	
	
	System.out.println("======================================================="); 
	System.out.println("Macierz a0:");
	double[][] matrixA0=swapMatrixcolumn(m,0,b);
	printMatrix(matrixA0);
	System.out.println();
	System.out.println("ma wyznacznik=" + det(matrixA0) +"\n");
	

	System.out.println("======================================================="); 
	System.out.println("Macierz a1:");
	double[][] matrixA1=swapMatrixcolumn(m,1,b);
	printMatrix(matrixA1);
	System.out.println();
	System.out.println("ma wyznacznik=" + det(matrixA1) +"\n");
	}

}
