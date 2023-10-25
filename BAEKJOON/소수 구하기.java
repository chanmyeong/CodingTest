import java.io.*;
import java.util.*;

/**
 * M이상 N이하의 소수를 모두 출력
 * (1 ≤ M ≤ N ≤ 1,000,000)
 *
 * 문제 해결 전략 :
 * 2초 1,000,000 * 1,000
 */
public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        M = Integer.parseInt(line[0]);
        N = Integer.parseInt(line[1]);

        boolean isPrime;
        for(int i=M; i<=N; i++) {
            isPrime=true;
            if(i==1) isPrime=false;
            for(int j=2; j<=Math.sqrt(i); j++) {
                if(i%j==0) {
                    isPrime=false;
                    break;
                }
            }
            if(isPrime) sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        boolean[] prime = new boolean[N + 1];
        
        for(int i = 2; i <= N; i++) {
        	if(prime[i]) continue;
        	
        	if(i >= M) sb.append(i).append('\n');
        	
        	for(int j = i + i; j <= N; j += i) {
        		prime[j] = true;
        	}
        }
        
        System.out.println(sb);
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}
