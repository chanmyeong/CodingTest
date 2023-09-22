import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 카드는 차례로 1부터 N까지의 번호
 * 1번 카드가 제일 위에, N번 카드가 제일 아래인 상태
 * 우선, 제일 위에 있는 카드를 바닥에 버린다.
 * 그 다음, 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
 * 제일 마지막에 남게 되는 카드를 구하는 프로그램
 * 1 ≤ N ≤ 500,000
 * 
 * 문제 해결 전략 :
 * 구현
 */
public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N  = Integer.parseInt(br.readLine());
        int A = 0; // 마지막에 남는 카드
        
        //solve
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=N; i++) {
        	queue.offer(i);        	
        }

        while(!queue.isEmpty()) {
        	int first = queue.poll();
        	if(queue.isEmpty()) {
        		System.out.println(first);
        		break;
        	}
        	int second = queue.poll();
        	queue.offer(second);
        }
    }
}
====================================================================================================
// 규칙 찾기?
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        if(n==1){
            System.out.println(1);
            return;}
        int a = 1;
        while (n > a) {
            a *= 2; // 2번째것만 선택
        }
        a/=2;
        System.out.println((n-a)*2);
    }
}
====================================================================================================
import java.util.Queue;
import java.util.LinkedList;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Queue<Integer> q = new LinkedList<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= N; i++) {
			q.offer(i);
		}
		
		while(q.size() > 1) {
			q.poll();	// 맨 앞의 원소 버림 
			q.offer(q.poll());	// 맨 앞의 원소를 버림과 동시에 버려진 원소를 맨 뒤에 삽입 
		}
		
		System.out.println(q.poll());	// 마지막으로 남은 원소 출력 
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
		
		/**
		 *  한 턴에 1개 씩 삭제되고 뒤에 1개가 추가 되므로
		 *  2 * N - 1 의 공간이 필요하다.
		 *  다만 index는 1부터 시작할 것이기 때문에
		 *  2 * N 공간으로 생성한다.   
		 */
		int[] q = new int[2 * N];	  
		for(int i = 1; i <= N; i++) {
			q[i] = i;
		}
		int prev_index = 1;
		int last_index = N;
		
		while(N-- > 1) {
			prev_index++;
			q[last_index + 1] = q[prev_index];
			last_index++;
			prev_index++;
		}
 
		System.out.println(q[prev_index]);
	}
}
