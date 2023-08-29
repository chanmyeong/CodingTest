import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  dp[n] = Math.max(dp[n-2]+score[n], dp[n-3]+score[n-1]+score[n]);
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] score = new int[301];
		int[] dp = new int[301]; // 원소 : 지금까지 오른 계단들의 점수의 누적합, 인덱스 : 계단의 번호
		for(int tc=1; tc<=N; tc++) {
			score[tc]=Integer.parseInt(br.readLine());
		}
		// 시작지점(기저)의 예외처리
		dp[1] = score[1];
		dp[2] = Math.max(score[1], score[1]+score[2]);
		dp[3] = Math.max(score[1]+score[3], score[2]+score[3]);
		
		// 2가지 경우의 수 중 최댓값
		// 1. 두 칸 내려간 것까지의 누적합 + 지금 오른 계단 점수, 두칸
		// 2. 세 칸 내려간 것까지의 누적합 + 이전 계단 점수 + 지금 오른 계단 점수, 두칸 한칸
		// 한 칸이 내려왔으면 무조건 다음 번에는 2칸이 내려가야 한다
		for(int i=4; i<=N; i++) {
			dp[i] = Math.max(dp[i-2]+score[i], dp[i-3]+score[i-1]+score[i]);
		}
		System.out.println(dp[N]);
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
 
		int[] DP = new int[N + 1]; // 배열의 크기를 입력값 만큼 설정해준 경우
		int[] arr = new int[N + 1];
 
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
 
		// index = 0 은 시작점이다. 초기화해줄 필요 없다.
		DP[1] = arr[1];
		
		// N이 1이 입력될 수도 있기 때문에 예외처리를 해줄 필요가 있다.
		if (N >= 2) {
			DP[2] = arr[1] + arr[2];
		}
 
		for (int i = 3; i <= N; i++) {
      // 현재 인덱스 i 에 대해 두 칸 전(i - 2)의 '메모이제이션 값'과
      // 첫 칸 전(i - 1)의 값 + 셋 째칸 전(i - 3)의 '메모이제이션 값' 중 큰 값을
      // 현재 i 계단의 값과 합하여 DP배열에 저장(Memoization)
			DP[i] = Math.max(DP[i - 2] , DP[i - 3] + arr[i - 1]) + arr[i];
		}
 
		System.out.println(DP[N]);
	}
}
