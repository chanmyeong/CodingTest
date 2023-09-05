// 동적 계획법(DP) (상향식)
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int K = Integer.parseInt(line[1]);
		
		int[][] dp = new int[N+1][K+1];
		
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=Math.min(i, K); j++) {
				if(j==0 || i==j) {
					dp[i][j]=1;
				}
				else {
					dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N][K]);			
	}
}
====================================================================================================
// 재귀 (하향식) nCr = n!/r!(n-r)!
import java.io.*;
import java.util.*;

public class Main {
	
	static int fac(int n)
	{
		int ret = 1;
		for (int i = 1; i <= n; i++) {
			ret*=i;
			} return ret;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		int ANS = fac(N) / (fac(K)*fac(N-K)); 
		System.out.println(ANS);

	}
}
====================================================================================================
// 재귀 (하향식) nCr = n-1Cr-1+n-1Cr
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int MAX_N = 11;
	static int N;
	static int K;
	static int[][] combi = new int[MAX_N][MAX_N];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		System.out.println(combi(N, K));
	}	
	
	static int combi(int n, int k) {
		if(k == 0 || n == k) {
			return 1;
		}
		if(combi[n][k] > 0) {
			return combi[n][k];
		}
		return combi[n][k] = combi(n-1, k-1) + combi(n-1, k);
	}
}
