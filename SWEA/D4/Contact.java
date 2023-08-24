import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 비상연락망과 연락을 시작하는 당번에 대한 정보
 * 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람을 구하는 함수
 * 각 원은 개개인을 의미하며,
 * 원 안의 숫자는 그사람의 번호를 나타내고 빨간원은 연락을 시작하는 당번을 의미
 * 비상연락망 정보는 사전에 공유되며 한 번 연락을 받은 사람에게 다시 연락을 하는 일은 없다.
 * 
 * 입력받는 데이터는 순서에 상관이 없음
 * 다음과 같이 동일한 {from, to}쌍이 여러 번 반복되는 경우도 있으며, 한 번 기록된 경우와 여러 번 기록된 경우의 차이는 없다.
 * 
 * 가중치가 동일한 유향 그래프 BFS
 * 방문배열 사용
 * 입력 받는 데이터의 길이와 시작점
 */

// BFS
public class Solution {
	static List<Integer>[] adjList;  //인접리스트
	static boolean[] visited;  // 방문체크 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());  // 입력받을 배열의 크기
			int start = Integer.parseInt(st.nextToken());  // 비상연락 시작하는 사람
			
			// 인접리스트 초기화
			adjList = new List[101];
			for(int i = 1; i< 101; i++)
				adjList[i] = new ArrayList<>();
			visited = new boolean[101];
			
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < size; i+=2) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from].add(to);
			}
			System.out.println("#" + tc + " " + BFS(start));
			
		}
	}
	public static int BFS(int start) {
		int maxCnt = 0; // 최대 연락 횟수
		int maxNumber = 0; // 최대 연락 횟수인 사람의 최대 번호 
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.offer(new int[] {start, 0}); // 시작 노드의 번호와 연락 순서 저장
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int[] node = queue.poll(); // 노드 정보
			int from = node[0]; // 현재 노드 번호
			int cnt = node[1]; // 몇 번째 연락인지
			
			if(cnt>maxCnt || (cnt==maxCnt && from>maxNumber)) {
				maxCnt=cnt; //최대 연락 순서 갱신
				maxNumber=from; //노드 번호 변경
			}
			
			for(int next : adjList[from]) {
				if(visited[next]) continue;
				queue.offer(new int[] {next, cnt+1});
				visited[next] = true;
			}
		}		
		return maxNumber;
	}
}

====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 레벨별 BFS
// 연락 횟수를 depth(level)로 두어서 BFS탐색하므로 일반 BFS탐색보다 변수가 하나 줄어든다
public class Solution {
	static List<Integer>[] adjList;  //인접리스트
	static boolean[] visited;  // 방문체크 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());  // 입력받을 배열의 크기
			int start = Integer.parseInt(st.nextToken());  // 비상연락 시작하는 사람
			
			// 인접리스트 초기화
			adjList = new List[101];
			for(int i = 1; i< 101; i++)
				adjList[i] = new ArrayList<>();
			visited = new boolean[101];
			
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < size; i+=2) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from].add(to);
			}
			System.out.println("#" + tc + " " + BFS(start));
			
		}
	}
	public static int BFS(int start) {
		int answer = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		visited[start] = true;
		queue.offer(start);
		
		
		while(!queue.isEmpty()) {
			int depthMaxNum = 0;
			
			// depth별 탐색
			int size = queue.size();
			while(size-- > 0) {
				
				int pollNum=queue.poll();
				depthMaxNum = Math.max(depthMaxNum, pollNum);				
				
				for(int next : adjList[pollNum]) {
					if(visited[next]) continue;
					visited[next] = true;
					queue.offer(next);
				}
			}
			
			answer = depthMaxNum;
		}
		return answer;
	}

}
