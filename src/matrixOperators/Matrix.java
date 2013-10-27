 package matrixOperators;



public class Matrix {

	/**
	 * Wyznacznik macierzy korzystaj¹c z metody Laplaca z rozszerzenie wzd³u¿
	 * pierwszego ( zerowego ) wiersza. Dane wejœciowe musz¹ reprezentowaæ
	 * macierz kwadratow¹.
	 * 
	 * @param m
	 *            Matrix
	 * @return determinant
	 */
	public static double det(double[][] m) {
		int n = m.length;
		if (n == 1) { // jeœli macierz ma rozmiar 1 na 1 zwróæ wyznacznik jako
						// element macierzy , jednoczeœnie to gwarantuje
						// zakonczenie metody rekurencyjnej !
			return m[0][0];
		} else {
			double det = 0;
			for (int j = 0; j < n; j++) {
				det += Math.pow(-1, j) * m[0][j] * det(minor(m, 0, j)); // rekurencyjny wzór metody Laplaza - wikipedia
			}
			return det;
		}
	}

	/**
	 * Computing the minor of the matrix m without the i-th row and the j-th
	 * column
	 * 
	 * @param m
	 *            input matrix
	 * @param i
	 *            usuwanie  i-tego wiersza z macierzy m
	 * @param j
	 *            usuwanie  j-tej kolumny z m
	 * @return pomniejszon¹ macierz o i-ty wiersz i j-t¹ kolumnê
	 */
	public static double[][] minor(final double[][] m, final int i, final int j) {
		int n = m.length;
		double[][] minor = new double[n - 1][n - 1];

		// index for minor matrix position:
		int r = 0, s = 0;
		for (int k = 0; k < n; k++) {
			double[] row = m[k];
			if (k != i) {
				for (int l = 0; l < row.length; l++) {
					if (l != j) {
						minor[r][s++] = row[l];
					}
				}
				r++;
				s = 0;
			}
		}
		return minor;
	}

	

	
	// dopisana funkcja - jarek
	
	public static double[][] swapMatrixcolumn(final double[][] m,final int i,final double[] column){
		int n=m.length;
		
		
		double[][] temp=new double[n][n];
		
		for(int k=0;k<n;k++)
			for(int j=0;j<n;j++)
				temp[k][j]=m[k][j];
				
				
		for(int p=0;p<n;p++){
			temp[p][i]=column[p];
		}
		
		
		return temp;
		
	}
	
	
	
	
	
	
	
	
	public static void printMatrix(double[][] m) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m.length; i++) {
			double[] row = m[i];
			sb.append("[");
			for (int j = 0; j < row.length; j++) {
				sb.append(" ");
				sb.append(row[j]);
			}
			sb.append(" ]\n");
		}
		sb.deleteCharAt(sb.length() - 1);

		System.out.println(sb.toString());
	}


}