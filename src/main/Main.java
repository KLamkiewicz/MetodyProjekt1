package main;

import static polynomialOperators.Polynomial.generatePolynomials;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import customExceptions.InputException;
import polynomialOperators.DataManipulation;
import polynomialOperators.Polynomial;
import static matrixOperators.Matrix.*;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, InputException {
		
		DataManipulation data = new DataManipulation();
		boolean picked = false;
		
			System.out.println("Wybierz sposob wprowadzania danych: ");
			System.out.println("1 - Wczytaj z pliku");
			System.out.println("2 - Wczytaj z konsoli");
			
			// wczytuje dane wejœciowe do arry List obiektu Data
			Scanner sc = new Scanner(System.in);
			do{
			switch (sc.next()) {
				case "1":
					data.readDataFromFile();
					picked = true;
					break;
				case "2":
					data.readDataFromInput();
					picked = true;
				default:
					if(!picked){
						System.out.println("Podales nieprawidlowe dane wejsciowe, sprobuj jeszcze raz.");
						System.out.println("Wybierz sposob wprowadzania danych: ");
						System.out.println("1 - Wczytaj z pliku");
						System.out.println("2 - Wczytaj z konsoli");
					}
					break;
				}
			}while(!picked);
			
		// konwersja Array List do tablicy ( wyci¹gam wartoœci z Array List z obiektu data )
		
		int n = data.getInputx().size() - 1;
		double[] z = new double[n + 1];
		double[] y = new double[n + 1];
		double[] x = new double[n + 1];
		
		for (int i = 0; i <= n; i++) {
			z[i] = data.getInputz().get(i);
			y[i] = data.getInputy().get(i);
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
		
if(detm!=0){
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
	

	/*
	 * Wypisanie wyznacznikow macierzy posrednich
	 */
	double[] detP = new double[2*n+2];
	for(int i=0; i<2*n+2; i++){
		System.out.println("======================================================="); 
		System.out.println("Macierz : A" +i );
		double[][] matrix = swapMatrixColumn(m, i, b);
		printMatrix(matrix);
		System.out.println();
		detP[i] = det(matrix);
		System.out.println("ma wyznacznik=" + detP[i] +"\n");
	}
	
	System.out.println("======================================================="); 
	/*
	 * Wypisanie wspolczynnikow
	 */
	double[] wsp = new double[2*n+2];
	for(int i=0; i<2*n+2; i++){
		wsp[i] = (detP[i]/detm);
		System.out.println("Wspolczynnik A"+i+" wynosi: "+wsp[i] );
	}
	
	System.out.println("======================================================="); 
	/*
	 * Wypisanie wielomianu P(x)
	 */
	System.out.println();
	System.out.print("P(x)= ");
	for(int i=0; i<2*n+2; i++){
		if(i==0)
			System.out.print(wsp[i]);
		else{
			if(wsp[i]<0)
				System.out.print(" " + wsp[i]+"x^"+i);
			else{
				System.out.print(" + " + wsp[i]+"x^"+i);
			}
		}
	}
	
	System.out.println();
	System.out.println("======================================================="); 
	/*
	 * Wypisanie pochodnej wielomianu P(x)
	 */
	System.out.println();
	System.out.print("P'(x)= ");
	for(int i=0; i<2*n+2; i++){
		if(i!=0){
			if(i==1){
				if(wsp[i]<0)
					System.out.print(" " + wsp[i]);
				else{
					System.out.print(wsp[i]);
				}
			}
			else{
				if(wsp[i]<0)
					System.out.print(" " + wsp[i]*i+"x^"+(i-1));
				else{
					System.out.print(" + " + wsp[i]*i+"x^"+(i-1));
				}
			}
		}
	}
	
	System.out.println();
	System.out.println("======================================================="); 
	/*
	 * Obliczenie calki z P(x)
	 */
	System.out.println();
	System.out.print("Calka P(x)= ");
	for(int i=0;i<2*n+2; i++){
		if(i==0){
			System.out.print(wsp[i]+"x ");
		}else{
			if(wsp[i]<0)
				System.out.print(" "+(wsp[i]/(i+1))+"x^"+(i+1) );
			else
				System.out.print(" + " + (wsp[i]/(i+1))+"x^"+(i+1));
		}
	}
	
	
	}
else
	System.out.println("Wyznacznik = 0 , BRAK ROZWI¥ZANIA !!!!");
}
	
}