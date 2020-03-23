import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t, p, b, q;
		int i, j, k = 0;
		char[][] patroon, p_links, p_rechts;
		String string, lijn, lijn_1, lijn_2;
		Stack<char[][]> stapel = new Stack<char[][]>();
		t = Integer.parseInt(sc.nextLine()); // AANTAL OPGAVEN
		for (q = 0; q < t; q++) {
			p = Integer.parseInt(sc.nextLine()); // AANTAL BASISPATRONEN
			List<char[][]> basispatronen = new ArrayList<char[][]>(p);
			// inlezen basispatronen
			for (i = 0; i < p; i++) {
				patroon = new char[2][2];
				lijn_1 = sc.nextLine();
				lijn_2 = sc.nextLine();
				for (j = 0; j < 2; j++) {
					patroon[0][j] = lijn_1.charAt(j);
					patroon[1][j] = lijn_2.charAt(j);
				}
				basispatronen.add(patroon);
			}
			b = Integer.parseInt(sc.nextLine()); // AANTAL BEVELEN
			System.out.println(""); // spatie tussen outputs
			for (i = 0; i < b; i++) { // B
				lijn = sc.nextLine();
				try { // BEPAAL SOORT BEVEL (String of Integer)
					k = Integer.parseInt(lijn);
					string = "Integer";
				} catch (NumberFormatException | NullPointerException nfe) {
					string = lijn;
				}
				switch (string) { // VOER BEVELEN UIT
				case "Integer":
					stapel.add(basispatronen.get(k - 1));
					break;
				case "naai":
					p_links = stapel.pop();
					p_rechts = stapel.pop();
					stapel.add(naai(p_links, p_rechts));
					break;
				case "draai":
					patroon = stapel.pop();
					stapel.add(draai(patroon));
					break;
				case "teken":
					teken(stapel.peek());

					if (i != b - 2) {
						System.out.println("");
					}
					break;
				case "stop":
					/*
					 * stop niet nodig om naar volgende opdracht te gaan lijn 14+15 zorgen voor
					 * beginnen aan
					 */
					break;
				}
			}
		}
		sc.close();
	}

	// ............................................................................................
	public static char[][] draai(char[][] patroon_in) {
		char c;
		int i, j;
		int rows_in = patroon_in.length;
		int columns_in = patroon_in[0].length;
		int columns_out = rows_in; // columns out = rows in
		int rows_out = columns_in; // rows out= colmuns in
		// NIEUW PATROON MET VERWISSELDE DIMENSIES
		char[][] patroon_out = new char[rows_out][columns_out];
		// INVULLEN PATROON_OUT
		for (i = 0; i < rows_out; i++) {
			for (j = 0; j < columns_out; j++) {
				// INVULLEN
				patroon_out[i][j] = patroon_in[rows_in - 1 - j][i];
				c = patroon_out[i][j]; // tijdelijke opslag

				// SYMBOLEN AANPASSEN
				if (c == '-') {
					c = '|';
				} else if (c == '|') {
					c = '-';
				} else if (c == '/') {
					c = '\\';
				} else if (c == '\\') {
					c = '/';
				}
				patroon_out[i][j] = c; // tijdelijke opslag
			}
		}
		return patroon_out;
	}

	// ............................................................................................
	public static void teken(char[][] matrix_in) {
		for (int i = 0; i < matrix_in.length; i++) {
			for (int j = 0; j < matrix_in[0].length; j++) {
				System.out.print(matrix_in[i][j]);
			}
			System.out.println("");
		}
	}

	// ............................................................................................
	public static char[][] naai(char[][] patroon_links, char[][] patroon_rechts) {
		int M, N, m, n, R, K, i, j;
		// INLEZEN DIMENSIES INPUT MATRICES
		M = patroon_links.length;
		N = patroon_links[0].length;
		m = patroon_rechts.length;
		n = patroon_rechts[0].length;
		// DIMENSIES NIEUWE MATRIX BEREKENEN
		R = Math.max(M, m);
		K = N + n;
		// NIEUWE MATRIX (met berekende dimensies)
		char[][] patron_genaaid = new char[R][K];
		// VOLLEDIGE MATRIX (invullen met ' ')
		for (i = 0; i < R; i++) {
			for (j = 0; j < K; j++) {
				patron_genaaid[i][j] = ' ';
			}
		}
		// LINKER MATRIX INVULLEN IN NIEUWE MATRIX
		for (i = 0; i < M; i++) {
			for (j = 0; j < N; j++) {
				patron_genaaid[i][j] = patroon_links[i][j];
			}
		}
		// RECHTER MATRIX INVULLEN IN NIEUWE MATRIX
		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
				patron_genaaid[i][j + N] = patroon_rechts[i][j];
			}
		}
		return patron_genaaid;
	}
}