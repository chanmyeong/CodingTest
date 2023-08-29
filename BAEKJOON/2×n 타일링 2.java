import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 끝에만 보는 경우의 수
 * dp[n]=dp[n-1]+2*dp[n-2]
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[1001];
		dp[1]=1; dp[2]=3;
		for(int i=3; i<=n; i++) {
			// 타일 모양과는 관계없이 바로 이전의 끝값과 경우의 수만 고려한다
			// dp[i-1]에 1cm(2*1)타입 1가지만 올 수 있고
			// dp[i-2]에 2cm(1*2 2개 or 2*2)타입 2가지가 올 수 있다
			dp[i]=(dp[i-1]+2*dp[i-2])%10007; // 수가 long타입을 초과하므로 원하는 값을 배열에 저장			
		}

		System.out.println(dp[n]);
	}
}
