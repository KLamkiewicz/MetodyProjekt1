package polynomialOperators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import customExceptions.*;

public class DataManipulation {

	private List<Double> inputx = new ArrayList<Double>(); // lista x'�w dane wejsciowe
	private List<Double> inputy = new ArrayList<Double>();
	private List<Double> inputz = new ArrayList<Double>();

	public List<Double> getInputx() {
		return inputx;
	}

	public void setInputx(List<Double> inputx) {
		this.inputx = inputx;
	}

	public List<Double> getInputy() {
		return inputy;
	}

	public void setInputy(List<Double> inputy) {
		this.inputy = inputy;
	}

	public List<Double> getInputz() {
		return inputz;
	}

	public void setInputz(List<Double> inputz) {
		this.inputz = inputz;
	}

	public DataManipulation() {

	}

	public DataManipulation(List<Double> inputx, List<Double> inputy,List<Double> inputz) {
		this.inputx = inputx;
		this.inputy = inputy;
		this.inputz = inputz;
	}

	public void readDataFromFile() throws FileNotFoundException, InputException {
		int[] pom = {0,0,0};
		Scanner sc = new Scanner(new File("myX.txt")); // docelowy scanner kt�ry czyta dane i podstawia
		Scanner check = new Scanner(new File("myX.txt")); // przelatuje po pliku by sprawdzi� czy podana ilo�c x i y jest zgodna

		check.findWithinHorizon("x:", 1000000); // szuka wzoru "x:" w tek�cie 
		while (check.hasNext() && !check.hasNext("y:")&& !check.hasNext("z:")) { // je�li jest nast�pny rekord i nie jest on wzorem "y:" 
			check.next();
			pom[0]++;
		}
		check.findWithinHorizon("y:", 1000000);
		while (check.hasNext() && !check.hasNext("x:") && !check.hasNext("z:")) {
			check.next();
			pom[1]++;
		}

		check.findWithinHorizon("z:", 1000000);
		while (check.hasNext() && !check.hasNext("x:")&& !check.hasNext("y:")) {
			check.next();
			pom[2]++;
		}
		
		check.close();

		if (pom[0]==pom[1]&&pom[1]==pom[2]) { // ilo�� rekord�w zgodna, w�a�ciwy proces wczytywania

			System.out.println(sc.findWithinHorizon("x:", 1000000));

			while (sc.hasNext() && !sc.hasNext("y:")) {
				String s;
				s = sc.next();
				System.out.println("Wczytalem: " + s);
				this.inputx.add(Double.parseDouble(s));

			}

			System.out.println(sc.findWithinHorizon("y:", 1000000));
			while (sc.hasNext() && !sc.hasNext("x:") && !sc.hasNext("z:")) {
				String s;
				s = sc.next();
				System.out.println("Wczytalem: " + s);
				this.inputy.add(Double.parseDouble(s));
			}
			
			
			
			System.out.println(sc.findWithinHorizon("z:", 1000000));
			while (sc.hasNext() && !sc.hasNext("x:") && !sc.hasNext("y:")) {
				String s;
				s = sc.next();
				System.out.println("Wczytalem: " + s);
				this.inputz.add(Double.parseDouble(s));
			}
			
			
			
			

			sc.close();

		}

		else {
			System.out.println("Liczba x'ow wynosi:" + pom[0] + " Liczba y: "+ pom[1] + " Liczba z: " + pom[2]);
			sc.close();
			throw new InputException();
		}
	}

}
