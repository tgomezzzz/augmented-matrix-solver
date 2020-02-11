import java.util.*;

import javax.swing.ImageIcon;
public class AugMatrixSolver {

	public static void main(String[] bears) {

		Scanner s = new Scanner(System.in);
		int[][] matrix;

		matrix = loadMatrix(s);	
		
		while (true){
			if (isRREF(matrix)){
				break;
			}
		}

		s.close();

	}

	private static boolean isRREF(int[][] m){
		//check indices of 1s, making sure they form a staircase
		//check that everything else in the col is 0

		int[] pivotCols = new int[m.length];
		int ind = 0;
		for (int i = 0; i < m.length; i++){
			for (int j = 0; j < m[0].length; j++){
				if (m[i][j] == 1){
					pivotCols[ind++] = j;
					break;
				}
			}
		}

		int prev = -1;
		for (int i = 0; i < pivotCols.length; i++){
			if (pivotCols[i] <= prev){
				//return false;
				System.out.println("Found a pivot column too far left, not RREF");
			}
			if (!isPivotCol(i, pivotCols[i], m)){
				//return false;
				System.out.println(pivotCols[i] + " is not a pivot column");
			}
			prev = pivotCols[i];
		}

		return true;
	}

	private static boolean isPivotCol(int pivRow, int col, int[][] m){
		for (int i = 0; i < m.length; i++){
			if (i != pivRow){
				if (m[i][col] != 0){
					return false;
				}
			}
		}
		return true;
	}

	private static int[][] loadMatrix(Scanner s){
		List<List<Integer>> rows;
		while (true){
			rows = new ArrayList<>();

			System.out.println("Enter the coefficients of the matrix, row by row.");
			System.out.println("Enter 'd' when you're done.");

			String input = s.nextLine();
			String[] inputs = input.split(" ");
			final int ROW_SIZE = inputs.length;
			while (!input.equals("d")) {
				try {
					if (inputs.length != ROW_SIZE){
						throw new Exception();
					}
					List<Integer> row = new LinkedList<>();
					for (String str : inputs){
						int t = Integer.parseInt(str);
						row.add(t);
					}
					rows.add(row);
				} catch (NumberFormatException e) {
					System.out.println("Error: matrix can only contain numerical values.");
				} catch (Exception e){
					System.out.println("Error: rows must all be the same length: " + ROW_SIZE);
				}
				input = s.nextLine();
				inputs = input.split(" ");
			}
	
			System.out.println("Is this the correct augmented matrix?");
			for (List<Integer> r : rows) {
				for (int i = 0; i < r.size(); i++) {
					System.out.print(r.get(i) + " ");
					if (i == r.size() - 2) {
						System.out.print("| ");
					}
				}
				System.out.println();
			}
	
			System.out.println("Enter 'y' to solve, or anything else to re-enter the matrix");
			input = s.nextLine();
	
			if (input.equals("y")){
				break;
			}
		}
		
		int[][] ret = new int[rows.size()][rows.get(0).size()];
		for (int i = 0; i < ret.length; i++){
			for  (int j  = 0; j < ret[0].length; j++){
				ret[i][j] = rows.get(i).get(j);
			}
		}
		return ret;
	}
}	
