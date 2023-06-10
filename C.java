import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

5 6
......
..#.#.
..###.
..###.
......

---------------------

3 2
#.
##
##

---------------------

6 6
...###
..####
..####
..####
..####
......

---------------------

3x3:

##.
###
###

both column and row:
first occurrence = get min
second occurrence = get max

 */

public class C {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
//		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt(), m = fs.nextInt();
			char[][] s = new char[n][m];
			for (int i = 0; i < n; i++) {
				s[i] = fs.next().toCharArray();
			}
			int columnLower = n, columnUpper = 0;
			for (int i = 0; i < n; i++) {
				int start = -1, end = -1;
				for (int j = 0; j < m; j++) {
					if (s[i][j] == '#') {
						if (start == -1) {
							start = j;
						} else {
							end = j;
						}
					}
				}
				if (start > -1) {
					columnLower = Math.min(columnLower, start);
				}
				if (end > -1) {
					columnUpper = Math.max(columnUpper, end);
				}
			}
//			System.out.println(columnLower + " " + columnUpper);
			int rowLower = n, rowUpper = 0;
			for (int i = 0; i < m; i++) {
				int start = -1, end = -1;
				for (int j = 0; j < n; j++) {
					if (s[j][i] == '#') {
						if (start == -1) {
							start = j;
						} else {
							end = j;
						}
					}
				}
				if (start > -1) {
					rowLower = Math.min(rowLower, start);
				}
				if (end > -1) {
					rowUpper = Math.max(rowUpper, end);
				}
			}
//			System.out.println(rowLower + " " + rowUpper);
			outer: for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (s[i][j] == '.' && withinRange(i, j, rowLower, rowUpper, columnLower, columnUpper)) {
						System.out.println((i + 1) + " " + (j + 1));
						break outer;
					}
				}
			}
		}
		out.close();
	}
	
	static boolean withinRange(int i, int j, int rl, int ru, int cl, int cu) {
		return (rl <= i && i <= ru) && (cl <= j && j <= cu);
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
