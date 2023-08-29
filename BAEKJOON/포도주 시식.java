// [Bottom-Up] DP,반복문
import java.io.*;
import java.util.*;

/**
 * [참고문헌]: https://st-lab.tistory.com/135
 * 문제 해결 전략 :
 * 끝에서 보기
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] wine = new int[N+1];
        int[] dp = new int[N+1];

        for(int i=1; i<=N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = wine[1];
        if(N>1) { // N이 1로 주어질 수 있으므로
            dp[2] = wine[1]+wine[2];
        }

        for(int i=3; i<=N; i++) {
            // 비연속적인 값을 탐색해나가는 방법이 필요 -> N번째 값에 대하여 (N-2)번째 값과 (N-3)번째 값을 탐색
            // 마지막 dp의 값이 항상 최댓값을 보장하는 것이 아니기 때문에 dp[N-1]값과도 비교
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2]+wine[i],dp[i-3]+wine[i-1]+wine[i]));
        }
        System.out.println(dp[N]);
    }
}
====================================================================================================
// [Top-Down] 재귀
  
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	
	static Integer[] dp;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		dp = new Integer[N + 1];
		arr = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 0;
		dp[1] = arr[1];
		
		/*
		 *  (N이 1로 주어질 수 있으므로 이럴 때를 위해 조건식을 달아둔다.
		 *  또한 dp[2]는 어떤 경우에도 첫 번째와 두 번째를 합한 것이 최댓값이다. 
		 */
		if(N > 1) {
			dp[2] = arr[1] + arr[2];
		}
		
		System.out.println(recur(N));
	}
	
	static int recur(int N) {
		
		if(dp[N] == null) { // static 변수 초기화를 하지 않았으므로 null(Integer -> wrapper class 참조타입)
			dp[N] = Math.max(Math.max(recur(N - 2), recur(N - 3) + arr[N - 1]) + arr[N], recur(N - 1));
		}
		
		return dp[N];
	}
}
