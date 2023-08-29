import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[1000001]; // 원소 : 연산이 실행되는 횟수  
		
		dp[1]=0; dp[2]=1; dp[3]=1;
		for(int i=4; i<=n; i++) { // 연산 실행시 마다 횟수+1 증가
			dp[i]=dp[i-1]+1; // 무조건 실행되어서 dp[i]의 초기값을 정해줌, dp값이 지정됨
			
			if(i%3==0) { // n->x, i->o
				if(dp[i]>dp[i/3]) { // 비교해서 최솟값을 dp배열에 저장
					dp[i]=dp[i/3]+1;					
				}
			}
			
			if(i%2==0) { // else if 불가, 6과 같은 2와 3 둘 다 나누어 떨어지는 수가 존재
				if(dp[i]>dp[i/2]) {
					dp[i]=dp[i/2]+1;
				}
			}
			
		}
		System.out.println(dp[n]);
		
	}
}
