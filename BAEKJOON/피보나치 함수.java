// [참고 문헌]: https://velog.io/@ddo_0/%EB%B0%B1%EC%A4%80-1003%EB%B2%88-%ED%94%BC%EB%B3%B4%EB%82%98%EC%B9%98-%ED%95%A8%EC%88%98-by-Java%EC%9E%90%EB%B0%94
// [참고 문헌]: https://st-lab.tistory.com/124

import java.io.*;
import java.util.*;

/**
 * fibonacci(N)을 호출했을 때, 0과 1이 각각 몇 번 출력되는지 구하는 프로그램
 * 피보나치 수를 구하는 것이 아니라 피보나치 수에서 0과 1이 호출되는 횟수 구하기
 * 문제 해결 전략 :
 * N이 최대 40까지 주어지고, 각 N에 0과 1이 호출된 횟수를 담아야 하므로 2차원 배열
 */
public class Main {
    static int[][] dp = new int[41][2]; // n번째 까지의 합에 대한 0과 1의 호출 횟수
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            int N = Integer.parseInt(br.readLine());
            dp = new int[41][2];

            //solve
            dp[0][0] = 1; // N=0 일 때의 0 호출 횟수
            dp[0][1] = 0; // N=0 일 때의 1 호출 횟수
            dp[1][0] = 0; // N=1 일 때의 0 호출 횟수
            dp[1][1] = 1; // N=1 일 때의 1 호출 횟수

            for(int n=2; n<=N; n++) {
                dp[n][0] = dp[n-1][0] + dp[n-2][0];
                dp[n][1] = dp[n-1][1] + dp[n-2][1];
            }

            sb.append(dp[N][0]+" "+dp[N][1]).append("\n");
        }
        System.out.println(sb);
    }
}
