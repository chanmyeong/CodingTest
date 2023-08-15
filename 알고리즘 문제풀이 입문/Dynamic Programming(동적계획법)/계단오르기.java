// 동적계획법(DP) : 복잡도가 굉장히 큰 문제를 단계별로 (문제의 본질은 변하지 않은 채)작은 문제로 소형화(해답을 직관적으로 알 수 있는),
// 이전에 구현해놓았던 답을 memoization하여 그 다음 확장된 문제에 사용(Bottom Up)하는 알고리즘

// 메모이제이션은 컴퓨터 프로그램이 동일한 계산을 반복해야 할 때,
// 이전에 계산한 값을 메모리에 저장함으로써 동일한 계산의 반복 수행을 제거하여
// 프로그램 실행 속도를 빠르게 하는 기술이다. 동적 계획법의 핵심이 되는 기술이다.

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3; i<=n; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        System.out.println(dp[n]);
    }
}
