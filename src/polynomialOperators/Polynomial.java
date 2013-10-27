static package polynomialOperators;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {

	private int level;
	double[] ratios; // wspolczynniki 1*a + 2*a + 4*a itd.
	private double x;
	private int y;
	private int[] a;

	public double[] getRatios() {
		return ratios;
	}

	public void setRatios(double[] ratios) {
		this.ratios = ratios;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int[] getA() {
		return a;
	}

	public void setA(int[] a) {
		this.a = a;
	}

	public Polynomial() {

	}

	public Polynomial(double x, int level) {  // Konstruktor standarowego wielomianu 

		this.level = level;
		this.x = x;
		this.ratios = new double[level + 1]; // stopieñ wielomianu okreœla jego
												// najwy¿sza potêga, wielomian
												// stopnia 7 ma 8 wspó³czynników
												// nie 7

		int i = 0;
		ratios[0] = 1; // 1*a0

		for (i = 1; i <= level; i++) {
			ratios[i] = pow(x, i);
		}

	}
	
	
	public Polynomial(double x, int level,int derivativeLevel){ // Konstruktor pochodnej wielomianu
		
		//derivativeLevel = stopieñ pochodnej

		this.level = level;
		this.x = x;
		this.ratios = new double[level + 1];
		
	
		int i = 0;
		for(i=1;i<=derivativeLevel;i++)
			ratios[i-1] = 0;  // aby wspolczynnik0 sie wyzerowa³ potrzeba pochodnej 1 stopnia, aby wspolczynnik1 sie wyzerowal potrzeba pochodnej stponia 2 itd.
		
		for (i = derivativeLevel; i <= level; i++) {
			ratios[i] = pow(x, i-1)*i;  // pozostale wspolczynniki niezerowe wg. wzoru na pochodn¹ potêgi
		}
		
	}

	public void printPolynomial() {

		int i = 0;
		System.out.println("Utworzylem Wielomian o wspolczynnikach: ");
		for (i = 0; i <= this.level; i++) {
			System.out.print(this.ratios[i] + " ");

		}
		System.out.println();
	}

	static public List<Polynomial> generatePolynomials(double[] x, int n) {

		List<Polynomial> temp = new ArrayList<Polynomial>();
		int i;

		for (i = 0; i < x.length; i++) {
			Polynomial poly = new Polynomial(x[i], n);
			temp.add(poly);
		}
		
		
		for (i = 0; i < x.length; i++) {
			Polynomial poly = new Polynomial(x[i], n, 1);  // nowa pochodna wielomianu stopnia 1
			temp.add(poly);
		}
		
		

		return temp;
	}

}
