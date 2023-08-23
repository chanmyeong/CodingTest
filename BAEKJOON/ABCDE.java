import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 총 N명이 참가하고 있다. 사람들은 0번부터 N-1번으로 번호
 * 일부 사람들은 친구(서로소)
 * 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.
 *
 * 사람의 수 N (5 ≤ N ≤ 2000)과 친구 관계의 수 M (1 ≤ M ≤ 2000)
 * 정수 a와 b가 주어지며, a와 b가 친구라는 뜻이다. (0 ≤ a, b ≤ N-1, a ≠ b)
 * 같은 친구 관계가 두 번 이상 주어지는 경우는 없다.
 * 
 * 가중치x 무방향 -> BFS or DFS -> BFS가 안되는 이유 : 방문배열로 탐색을 하는데 돌아갈 수 있는 길을 방문체크로 막아버림 
 * 따라서 DFS + 백트래킹(모든 정점을 다 탐색 시 종료)
 * 정점기준으로 이동가능 시 cnt++ 
 */
public class Main {
	static List<Integer>[] adjList;
	static boolean visited[];
	static boolean end;
	static int n,m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		adjList = new List[n];
		for(int i=0; i<n; i++) {
			adjList[i] = new ArrayList<>(); // 인접리스트 : 배열 내부에 ArrayList 생성
		}
		
		visited = new boolean[n];
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		for(int i=0; i<n; i++) { // 임의의 노드들을 시작점으로 잡고서 DFS
			// DFS 함수에서 방문처리하기
			DFS(i, 1);
			
			// 시작지점에서 방문처리하고 -1-
//			visited[i]=true;
//			DFS2(i,1);
//			visited[i]=false;
			
			if(end==true) {
				System.out.println(1);
				return;
			}
		}

		System.out.println(0);
	}
	private static void DFS(int cur, int cnt) { // cur : 현재 탐색한 정점, cnt : 정점 탐색이 가능 시 증가
		visited[cur]=true;
		
		if(cnt==5) {
			end = true;
			return;
		}

		for(int to : adjList[cur]) {
			if(!visited[to]) { // 방문하지 않았으며 인접한 경우
				DFS(to, cnt+1);
				if(end) return;
			}
		}
		visited[cur]=false;
		
	}
	
	private static void DFS2(int cur, int cnt) { // cur : 현재 탐색한 정점, cnt : 정점 탐색이 가능 시 증가
		
		if(cnt==5) {
			end = true; // sys.exit을 쓰지 않고 재귀를 종료시키는 방법 -1-
			return;
		}

		for(int i=0; i<adjList[cur].size(); i++) { // 해당 정점에서 연결되어있는 정점들을 깊이 탐색
			if(!visited[adjList[cur].get(i)]) { // 방문하지 않았으며 인접한 경우
				// 현재지점에서 방문처리하기 -2-
				visited[adjList[cur].get(i)] = true;
				DFS(adjList[cur].get(i), cnt+1);
				visited[adjList[cur].get(i)] = false;
				if(end) return; // sys.exit을 쓰지 않고 재귀를 종료시키는 방법 -2-, BJ빵집
			}
		}
	}
}
