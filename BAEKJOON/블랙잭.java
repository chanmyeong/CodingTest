import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 블랙잭 : 카드의 합이 21을 넘지 않는 한도 내에서, 카드의 합을 최대한 크게 만드는 게임
 * 각 카드에는 양의 정수
 * 딜러는 N장의 카드를 모두 숫자가 보이도록 바닥에 놓는다. 그런 후에 딜러는 숫자 M을 크게 외친다.
 * 제한된 시간 안에 N장의 카드 중에서 3장의 카드를 골라야 한다.
 * 플레이어가 고른 카드의 합은 M을 넘지 않으면서 M과 최대한 가깝게 만들어야 한다.
 * 
 * 문제 해결 전략 :
 * nC3해서 M보다 작은 것들 중 가장 큰 값
 * 
 */
public class Main {
	static int N,M,answer=0;
	static int[] cards,picked;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 카드 개수
		M = Integer.parseInt(st.nextToken()); // 3장의 카드 최대 합

		st = new StringTokenizer(br.readLine());
		cards = new int[N];
		picked = new int[3];
		for(int i=0; i<N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		combination(0,0);
		System.out.println(answer);
	}

	private static void combination(int depth, int start) {
		if(depth==3) {
//			System.out.println(Arrays.toString(picked));
			int cardSum=0;
			for(int i=0; i<picked.length; i++) {
				cardSum += picked[i];
			}
			if(cardSum<=M) {
				answer = Math.max(answer, cardSum);				
			}
			return;
		}
		
		for(int i=start; i<N; i++) {
			picked[depth] = cards[i];
			combination(depth+1, i+1);			
		}
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int blackJack(int[] arr,int m) {
		int result=0;
		int len=arr.length;
		for(int i=0;i<len-2;i++) {
			for(int j=i+1;j<len-1;j++) {
				for(int k=j+1;k<len;k++){
					int tmp=arr[i]+arr[j]+arr[k];
					if(tmp<=m&&tmp>result) {
						result=tmp;
					}
				}
			}
		}
		return result;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		StringTokenizer st2= new StringTokenizer(br.readLine());
		int[] arr=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st2.nextToken());
		}
    
		System.out.println(blackJack(arr, m));
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(token.nextToken()); // 카드의 개수
		int M = Integer.parseInt(token.nextToken()); // 조건
		int[] arr = new int[N];
		
		token = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		
		br.close();
		int answer = search(arr, N, M);
		System.out.println(answer);
	}

	private static int search(int[] arr, int N, int M) {
		int answer = 0;
		
		for(int i=0; i<N-2; i++) { // 3개를 고르기 때문에 첫번째 카드는 N-2 까지
			
			if(arr[i] > M) continue; // 첫 번째 카드가 M보다 크면 건너뛴다.
			
			for(int j=i+1; j<N-1; j++) { // 두번째 카드는 N-1 까지
				
				if(arr[i] + arr[j] > M) continue; // 두 카드의 합이 M보다 크면 건너뛴다.
				
				for(int k=j+1; k<N; k++) { // 세번째 카드는 N까지 
					int temp = arr[i] + arr[j] + arr[k];
					
					if(M == temp) { // M과 세 카드의 합이 같은지 확인
						return temp;
					}
					
					if(answer < temp && temp < M) { // 세 카드의 합이 이전 합보다 크면서 M보다 작을 경우 갱신
						answer = temp;
					}
				}
			}
		}
		
		// 순회를 마치면 리턴
		return answer;
	}
}
