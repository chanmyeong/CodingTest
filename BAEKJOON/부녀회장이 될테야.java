import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * a층의 b호에 살려면 자신의 아래(a-1)층의 1호부터 b호까지 사람들의 수의 합만큼 사람들을 데려와 살아야 한다
 * 양의 정수 k와 n에 대해 k층에 n호에는 몇 명이 살고 있는지 출력
 *  단, 아파트에는 0층부터 있고 각층에는 1호부터 있으며, 0층의 i호에는 i명이 산다
 *  1 ≤ k, n ≤ 14
 * 
 * [참고문헌]: https://st-lab.tistory.com/78
 * 부분 합(= 누적 합) - 0~b까지 원소의 합
 * 구간 합 - a~b까지 원소의 합
 */
public class Main {
	static int[][] apart;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		apart = new int[15][15]; // 14층까지
		for(int i=0; i<apart.length; i++) {
			apart[0][i]=i; // i층의 1호
			apart[i][1]=1; // 0층 i호
		}

		sum();
		
		int T = Integer.parseInt(br.readLine());
				
		for(int tc=1; tc<=T; tc++) {
			int K = Integer.parseInt(br.readLine());
			int N = Integer.parseInt(br.readLine());
			System.out.println(apart[K][N]);
		}

	}

	private static void sum() {
		for(int i=1; i<apart.length; i++) {
			for(int j=2; j<apart[i].length; j++) {
				apart[i][j] = apart[i-1][j]+apart[i][j-1]; // 누적합(부분합)				
			}
		}
	}
	
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[][] apt = new int[15][15];
	public static void main(String[] args) throws IOException{
		
		// 0층 1호~14호
		for(int i=1;i<=14;i++) {
			apt[0][i] = i;
		}
		// 1층~14층 1호~14호
		for(int i=1;i<=14;i++) { // 1층~14층
			for(int j=1;j<=14;j++) { // i층 1호~14호
				for(int k=1;k<=j;k++) { // (i-1)층 1호~j호
					apt[i][j] += apt[i-1][k];
				}
			}
		}
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=T;test_case++) {
			int K = Integer.parseInt(br.readLine());
			int N = Integer.parseInt(br.readLine());
			sb.append(apt[K][N]+"\n");
		}
		System.out.println(sb);
	}
}
