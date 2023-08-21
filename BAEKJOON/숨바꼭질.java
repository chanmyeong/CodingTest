import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_BJ_1697_숨바꼭질_우찬명 {
	/**
	 * 수빈이 N(0 ≤ N ≤ 100,000), 동생은 점 K(0 ≤ K ≤ 100,000)
	 * 수빈이는 걷거나 순간이동 가능
	 * 걸으면 x-1 or x+1
	 * 순간이동 시 2*x
	 * 
	 * 문제 해결 전략 :
	 * 일차원 배열에서 시작지점을 0으로 초기화하고 나머지 부분은 최장거리로 초기화
	 * 갈 수 있는 3가지 경로로 초가 지날 때마다 원소에 지난 시간을 갱신
	 * BFS로 정답에 도달할 때까지 탐색
	 * 
	 * 막힌 부분 :
	 * 탐색 시 최단거리가 아니면 갱신하지 않음 -> 과거의 값과 큐에서 꺼낸 값 어떻게 비교?
	 * ->  수빈이가 가지 않았던 부분에만 시간을 써주면 과거의 값과 비교할 필요가 없다
	 * BFS는 재귀가 아니라 큐에 반복해서 넣는 것  
	 */
	static int n,k;
	static int[] bfs; // 도착 시간을 담는 1차원 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		k = Integer.parseInt(line[1]);
		
		bfs = new int[100001];
		int result = BFS(n);
		System.out.println(result);
		
	}
	private static int BFS(int node) {
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.add(node);
		int index = node;
		int m = 0;
		bfs[index]=1;
		
		while(!queue.isEmpty()) {
			m = queue.remove();
			if(m==k) {
				return bfs[m]-1;
			}
			if(m-1>=0 && bfs[m-1]==0) {
				bfs[m-1]=bfs[m]+1;
				queue.add(m-1);
			}
			if(m+1<=100000 && bfs[m+1]==0) {
				bfs[m+1]=bfs[m]+1;
				queue.add(m+1);
			}
			if(2*m<=100000 && bfs[2*m]==0) {
				bfs[2*m]=bfs[m]+1;
				queue.add(2*m);
			}
		}
		return -1;
	}
}
