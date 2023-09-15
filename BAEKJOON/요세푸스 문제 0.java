import java.io.*;
import java.util.*;

/**
 * 요세푸스 문제
 * 1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다.
 * 순서대로 K번째 사람을 제거
 * 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다.
 * 이 과정은 N명의 사람이 모두 제거될 때까지 계속된다
 * 원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열
 * (7, 3)-요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.
 *  
 * 문제 해결 전략 :
 * 큐에서 꺼내자마자 K번째가 아니면 다시 집어넣기
 * 
 * 예외 테스트 케이스 :
 * 10 1
 * <1, 2, 3, 4, 5, 6, 7, 8, 9, 1 > -> 제대로 괄호가 닫히지 않는다
 * 수의 자릿 수가 한 자리만 있는 것이 아닌 1~3까지 있기 때문 
 */
// 실패한 코드
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			queue.offer(i);			
		}
		
		sb.append("<");
		int cnt=1;
		while(!queue.isEmpty()) {
			int out = queue.poll();			
			if(cnt%K==0) {
				sb.append(out+", ");
				cnt=0;
			}
			else {
				queue.offer(out);
			}
			cnt++;
		}
		
		sb.delete(3*N-1, 3*N+1);
		sb.append(">");
		System.out.println(sb);
	}
}
====================================================================================================
// [참고문헌]: https://st-lab.tistory.com/197
  
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> q = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		for(int i = 1; i <= N; i++) {
			q.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		
		/*
		 *  마지막 부분의 출력은 > 괄호 전에 공백이 없기 때문에
		 *  일괄적으로 출력하기 위해 마지막 원소만 남겨질 때까지만
		 *  반복하고 마지막 원소는 그대로 출력한다.
		 */
		while(q.size() > 1) {
			
			for(int i = 0; i < K - 1; i++) {
				q.offer(q.poll());
			}
			
			sb.append(q.poll()).append(", ");
		}
 
		// 마지막 원소 출력한 뒤 > 도 붙여준다.
		sb.append(q.poll()).append('>');
		System.out.println(sb);
	}
 
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			list.add(i);
		}
		
		
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		
		int index = 0;	// 리스트에서 삭제할 요소를 참조할 인덱스 변수

    		// 매번 리스트를 참조하여 사이즈를 알아내는 것이 불편하다면 list.size 대신 변수 N을 써서 1씩 감소시켜도 된다. 
		while(N > 1) {
			index = (index + (K - 1)) % N; // N = list.size()
			
			// index위치에 있는 요소를 삭제함과 동시에 출력 
			sb.append(list.remove(index)).append(", "); 
			N--;
		}
		
		// 마지막으로 남은 요소 삭제함과 동시에 출력
		sb.append(list.remove()).append('>');
		System.out.println(sb);
	}
}
