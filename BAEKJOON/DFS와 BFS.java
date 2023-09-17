import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고,
 * 더 이상 방문할 수 있는 점이 없는 경우 종료
 */
public class Main {
	static int N,M,V;
	
	static List<Integer>[] adjList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		adjList = new List[N+1]; // List 컬렉션 구현, 인접리스트
		boolean[] visited = new boolean[N+1]; // 1부터 시작
		
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
//		for(List list : adjList) {
//			System.out.println(list); // 출력 시 list와 배열(Arrays.toString)을 구분하자
//		}
		
		for(int i=1; i<=N; i++) {
			Collections.sort(adjList[i]); // 크기가 작은 정점부터 방문하기 위해 오름차순 정렬
		}
		
		DFS(V, visited);
		System.out.println();
		Arrays.fill(visited, false);
		BFS(V, visited);
	}
	private static void DFS(int current, boolean visited[]) {
		visited[current] = true;
		System.out.print(current+" ");
		
		for(int to : adjList[current]) {
			if(!visited[to]) { 
				DFS(to, visited);
			}
		}
		
	}
	private static void BFS(int current, boolean visited[]) {
		Queue<Integer> queue = new ArrayDeque<>();
		visited[current] = true;
		queue.offer(current);
		
		while(!queue.isEmpty()) {
			int from = queue.poll();
			System.out.print(from+" ");
			
			for(int to : adjList[from]) {
				if(!visited[to]) {
					visited[to] = true;
					queue.offer(to);
				}
			}
		}
		
	}
}
====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    static int n, m, v;
    static int[][] graph;
    static boolean[] check; // 트리가 아닌 그래프 탐색에서 간선이 여러개 존재하기 때문에 방문관리가 필요
    public static void DFS(int node) {
        check[node] = true;
        System.out.print(node+" ");
        for(int i=1; i<=n; i++) {
            if(graph[node][i]==1 && !check[i]) DFS(i);
        }
    }

    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        check[node]=true;
        queue.offer(node);
        while(!queue.isEmpty()) {
            int p = queue.poll();
            System.out.print(p+" ");
            for(int i=1; i<=n; i++) {
                if(graph[p][i]==1 && !check[i]) {
                    check[i]=true;
                    queue.offer(i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        v = sc.nextInt();
        graph = new int[n+1][n+1];
        check = new boolean[n+1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
        DFS(v);
        for(int i=1; i<=n; i++)
            check[i]=false;
        System.out.println();
        BFS(v);
    }
}
