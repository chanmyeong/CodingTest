import java.util.*;
import java.io.*;

/*
 * 부모와 자식 사이를 1촌으로 정의하고 이로부터 사람들 간의 촌수를 계산
 * 나와 할아버지는 2촌이 되고, 아버지 형제들과 할아버지는 1촌, 나와 아버지 형제들과는 3촌
 * 부모 자식들 간의 관계가 주어졌을 때, 주어진 두 사람의 촌수를 계산
 *
 * 전체 사람의 수 n(1 ≤ n ≤ 100), 부모 자식들 간의 관계의 개수 m
 * 앞에 나오는 번호 x는 뒤에 나오는 정수 y의 부모 번호
 * 각 사람의 부모는 최대 한 명
 * 두 사람의 친척 관계가 전혀 없어 촌수를 계산할 수 없을 때가 있다. 이때에는 -1을 출력
 *
 * 문제 해결 전략 :
 * 그래프 BFS 탐색
 * 인접하면 촌수 출력 아니면 -1
 * 0촌도 존재한다
 */
public class Main {
    static List<Integer>[] adjList;
    static int N, M, A, B, answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        adjList = new List[N + 1]; // 1부터 시작
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        for (int i = 1; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

//        for (List list : adjList) {
//            System.out.println(list);
//        }

        BFS(A, visited, dist);
        System.out.println(answer);
    }

    private static void BFS(int start, boolean[] visited, int[] dist) { // A가 B와 촌수 관계인지
        Queue<Integer> queue = new ArrayDeque<>();
//        visited[start] = true;
        queue.offer(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if(current==B) {
                answer = dist[current];
                return;
            }
            for (int i : adjList[current]) {
//                if (visited[i]) continue;
                if(dist[i]!=-1) continue;
                dist[i] = dist[current] + 1;
//                visited[i] = true;
                queue.offer(i);
            }
        }
    }
}
====================================================================================================
[참고문헌]: https://loosie.tistory.com/165

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<Integer>[] relation;
	static boolean[] checked;
	static int res = -1;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		relation = new ArrayList[n+1];
		checked = new boolean[n+1];
		for(int i=1; i<n+1; i++) {
			relation[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int l = Integer.parseInt(br.readLine());
		
		for(int i=0; i<l; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			relation[p].add(c);
			relation[c].add(p);
		}
		
		dfs(x,y, 0);
		System.out.println(res);
	}
	
	static void dfs(int start, int end, int cnt) {
		if(start == end) {
			res = cnt;
			return; 
		}
		
		checked[start] = true;
		for(int i=0; i<relation[start].size(); i++) { 
			int next = relation[start].get(i);
			if(!checked[next]) {
				dfs(next, end, cnt+1);
			}
		}
	}
}
