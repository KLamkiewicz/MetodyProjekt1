package polynomialOperators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import customExceptions.*;

public class DataManipulation {

	private List<Double> inputx = new ArrayList<Double>(); // lista x'ów dane wejsciowe
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
		Scanner sc = new Scanner(new File("myX.txt")); // docelowy scanner który czyta dane i podstawia
		Scanner check = new Scanner(new File("myX.txt")); // przelatuje po pliku by sprawdziæ czy podana iloœc x i y jest zgodna

		check.findWithinHorizon("x:", 1000000); // szuka wzoru "x:" w tekœcie 
		while (check.hasNext() && !check.hasNext("y:")&& !check.hasNext("z:")) { // jeœli jest nastêpny rekord i nie jest on wzorem "y:" 
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

		if (pom[0]==pom[1]&&pom[1]==pom[2]) { // iloœæ rekordów zgodna, w³aœciwy proces wczytywania

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
	
	public void readDataFromInput(){
		int liczbaWezlow = 0;
		String check;
		Scanner sc = new Scanner(System.in);
		boolean q;
		System.out.println("Podaj liczbe wezlow jakie chcesz wprowadzic: ");
		
		do{
			check = sc.next();
			if(!isInteger(check)){
				System.out.println("Podaj poprawne dane!");
			}
			else if(isInteger(check)){
				if(Integer.parseInt(check)<=0){
					check = "s";
					System.out.println("Ta liczba jest zbyt mala! Podaj wieksza liczbe");
				}
			}
		}while(!isInteger(check));
		liczbaWezlow = Integer.parseInt(check);

		/*
		 * Wczytywanie x'ow
		 */
        System.out.println("Wprowadz " + liczbaWezlow + " wezly x");
        for(int i=0; i<liczbaWezlow; i++){
                do{
                        check = sc.next();
                        if(!isDouble(check))
                                System.out.println("Podaj poprawne dane!");
                        else{
                        	if(!inputx.isEmpty()){
                        		if(inputx.contains(Double.parseDouble(check))){
                        			System.out.println("Taki wezel juz istnieje!");
                        			check = "s";
                        		}
                        		else
                                    inputx.add(Double.parseDouble(check));
                        	}
                        	else
                                inputx.add(Double.parseDouble(check));
                        }
                }while(!isDouble(check));
        }
		
		/*
		 * Wczytywanie y'ow
		 */
		System.out.println("Wprowadz " + liczbaWezlow + " wezly y");
		for(int i=0; i<liczbaWezlow; i++){
			do{
				check = sc.next();
				if(!isDouble(check)){
					System.out.println("Podaj poprawne dane!");
				}
			}while(!isDouble(check));
			inputy.add(Double.parseDouble(check));
		}
		
		/*
		 * Wczytywanie z'ow
		 */
		System.out.println("Wprowadz " + liczbaWezlow + " wezly z");
		for(int i=0; i<liczbaWezlow; i++){
			do{
				check = sc.next();
				if(!isDouble(check)){
					System.out.println("Podaj poprawne dane!");
				}
			}while(!isDouble(check));
			inputz.add(Double.parseDouble(check));
		}
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}

	public static boolean isDouble(String s) {
	    try { 
	        Double.parseDouble(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}

}
