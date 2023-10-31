import java.io.*;
import java.util.*;

/**
 * 현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인한다.
 * 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면,
 * 이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치 한다. 그렇지 않다면 바로 인쇄를 한다.
 *
 * 각 테스트케이스는 두 줄
 * 첫 번째 줄에는 문서의 개수 N(1 ≤ N ≤ 100)과,
 * 몇 번째로 인쇄되었는지 궁금한 문서가 현재 Queue에서 몇 번째에 놓여 있는지를 나타내는 정수 M(0 ≤ M < N)
 * 맨 왼쪽은 0번째
 *
 * 두 번째 줄에는 N개 문서의 중요도가 차례대로 주어진다.
 * 중요도는 1 이상 9 이하의 정수이고, 중요도가 같은 문서가 여러 개 있을 수도 있다.
 *
 * 문제 해결 전략 : 우선순위 큐(중요도)와 큐(FIFO) 활용하여 프린터 큐 구현
 */
public class Main {
    static class info {
        int index, importance;
        public info (int index, int importance) {
            this.index = index;
            this.importance = importance;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static int N,M,mark;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken()); // 0부터 시작
            arr = new int[N];
            PriorityQueue<info> pq = new PriorityQueue<>(new Comparator<info>() {
                @Override
                public int compare(info o1, info o2) {
                    return (o1.importance-o2.importance)*-1;
                }
            });
            Queue<info> q = new ArrayDeque<info>();

            st = new StringTokenizer(br.readLine(), " ");
            for(int n=0; n<N; n++) {
                arr[n] = Integer.parseInt(st.nextToken());
                pq.offer(new info(n, arr[n]));
                q.offer(new info(n, arr[n]));
            }
            int answer = 1; // 인쇄 순서 번호는 1번부터 시작
            
            while(true) {
                if(q.peek().importance==pq.peek().importance) {
                    if(q.peek().index==M) break;
                    else {
                        answer++;
                        q.poll();
                        pq.poll();
                    }
                } else {
                    q.offer(q.poll());
                }
            }
            pq.clear();
            q.clear();
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}

====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
import java.util.StringTokenizer;
import java.util.LinkedList;
 
public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스 
 
		while (T-- > 0) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			LinkedList<int[]> q = new LinkedList<>();	// Queue로 활용 할 연결리스트
			st = new StringTokenizer(br.readLine());
 
			for (int i = 0; i < N; i++) {
				// {초기 위치, 중요도}
				q.offer(new int[] { i, Integer.parseInt(st.nextToken()) });
			}
 
			int count = 0;	// 출력 횟수
			
			while (!q.isEmpty()) {	// 한 케이스에 대한 반복문
				
				int[] front = q.poll();	// 가장 첫 원소
				boolean isMax = true;	// front 원소가 가장 큰 원소인지를 판단하는 변수
				
				// 큐에 남아있는 원소들과 중요도를 비교 
				for(int i = 0; i < q.size(); i++) {
					
					// 처음 뽑은 원소보다 큐에 있는 i번째 원소가 중요도가 클 경우 
					if(front[1] < q.get(i)[1]) {
						
						// 뽑은 원소 및 i 이전의 원소들을 뒤로 보낸다.
						q.offer(front);
						for(int j = 0; j < i; j++) {
							q.offer(q.poll());
						}
						
						// front원소가 가장 큰 원소가 아니였으므로 false를 하고 탐색을 마침
						isMax = false;
						break;
					}
				}
				
				// front 원소가 가장 큰 원소가 아니였으므로 다음 반복문으로 넘어감
				if(isMax == false) {
					continue;
				}
				
				// front 원소가 가장 큰 원소였으므로 해당 원소는 출력해야하는 문서다.
				count++;
				if(front[0] == M) {	// 찾고자 하는 문서라면 해당 테스트케이스 종료
					break;
				}
 
			}
 
			sb.append(count).append('\n');
 
		}
		System.out.println(sb);
	}
 
}
