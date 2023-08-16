// 플로이드 와샬 알고리즘은 모든 정점에서 모든 정점으로의 최단거리를 구하는 알고리즘이다.
// 다익스트라 알고리즘이나 벨만 포드 알고리즘은 한 정점에서 다른 모든 정점의 최단거리를 구하는 것과 차이가 있다.
// 플로이드 와샬 알고리즘의 핵심 아이디어는 '거쳐가는 정점'을 기준으로 한다는 것이다.
// 즉, i에서 j까지 가는 것과 i에서 k로 가고, k에서 j로 가는 것은 같다는 의미이다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] graph = new int[n][n];
		
		for(int i=0; i<graph.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<graph.length; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// i → k → j
		// k : 거쳐가는 노드 
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) { // i : 출발 노드 
				for(int j=0; j<n; j++) { // j : 도착 노드 
					if(graph[i][k]==1 && graph[k][j]==1) { // 단순히 갈 수 있는 경로가 있는지만 체크
						graph[i][j]=1;
					}
//					if(graph[i][k]+graph[k][j]<graph[i][j]) {
//						graph[i][j] = graph[i][k]+graph[k][j];
//					}
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
	}
}
====================================================================================================
import java.io.*;
import java.util.*;

// BJ #11403 - 경로 찾기
// Strategy: BFS
public class Main {
	static int N;
	static int[][] graph;
	
	public static boolean[] BFS(int v) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		
		q.add(v);
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			for(int i=1; i<=N; i++) {
				if(graph[tmp][i] == 1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
		return visited;
	}
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        //
        N = Integer.parseInt(br.readLine());
        graph = new int[N+1][N+1];
        
        // 그래프 정보 입력
        for(int i=1; i<N+1; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j=1; j<N+1; j++) {
        		graph[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        for(int i=1; i<N+1; i++) {
        	boolean[] tmp = BFS(i);
        	for(int j=1; j<N+1; j++) {
        		if(tmp[j]) {
        			sb.append(1).append(" ");
        		}else {
        			sb.append(0).append(" ");
        		}
        	}
        	sb.append("\n");
        }
        System.out.println(sb);
    }
}
