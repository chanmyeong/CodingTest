import java.io.*;
import java.util.*;

/**
 * 상근이는 자신의 결혼식에 학교 동기 중 자신의 친구와 친구의 친구를 초대
 * 상근이의 동기는 모두 N명이고, 이 학생들의 학번은 모두 1부터 N까지이다. 상근이의 학번은 1이다.
 * 상근이는 동기들의 친구 관계를 모두 조사한 리스트를 가지고 있다.
 * 이 리스트를 바탕으로 결혼식에 초대할 사람의 수를 구하는 프로그램
 * 
 * 동기의 수 n (2 ≤ n ≤ 500)
 * 리스트의 길이 m (1 ≤ m ≤ 10000)
 * (1 ≤ ai < bi ≤ n) ai와 bi가 친구라는 뜻이며, bi와 ai도 친구관계
 * 
 * 문제 해결 전략 :
 * 1에서 시작한 레벨별 BFS가 최대 2인 정점의 개수
 * 
 */

public class Main {
	static int N,M;
	static int answer=0;
	static List<Integer>[] adjList;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjList = new List[N+1];
		
		for(int i=1; i<=N; i++) {
			adjList[i]= new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			String[] line = br.readLine().split(" ");
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			adjList[from].add(to);
			adjList[to].add(from);
		}
		visited = new boolean[N+1];
		
//		for(List<Integer> i : adjList) {
//			System.out.println(i);
//		}
		//solve
		BFS(1);
		
		System.out.println(answer);
	}
	private static void BFS(int start) {
		visited[start] = true;
		int cnt=0;
		int depthTwo=0;
		
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i : adjList[start]) {
			queue.offer(i);
		}
		depthTwo=queue.size();
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			if(!visited[current]) {
				visited[current] = true;
				//System.out.println("current: "+current);
				cnt++;				
			}
			
			if(depthTwo==0) {
				answer=cnt;
				break;
			}
			
			for(int i : adjList[current]) {
				if(!visited[i]) {
					visited[i]=true;
					//System.out.println("i: "+i);
					cnt++;
					queue.offer(i);					
				}
			}
			depthTwo--;
		}
	}
}
====================================================================================================
// 인접행렬
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int graph[][];
    static boolean[] visited;
    static int ans = 0;

    public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new int[n+1][n+1];
        visited = new boolean[n+1];
        for(int i = 0;i<m;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = 1;
        }
        for(int i = 2;i<=n;i++){
            if(graph[1][i] == 1){
                visited[i] = true;
                ans++;
                for(int j = 2;j<=n;j++){
                    if(graph[i][j] == 1 && graph[1][j] != 1 &&!visited[j]){
                        visited[j] = true;
                        ans++;
                    }
                }
            }
        }
        System.out.print(ans);
    }
}
====================================================================================================
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder("");

        int ans = 0;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<Integer> al[] = new ArrayList[n+1];
        boolean visit[] = new boolean[n+1];
        for (int i = 0; i <= n; i++) {
            al[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == 1){
                ans++;
                visit[b] = true;
            }
            al[a].add(b);
            al[b].add(a);
        }
        visit[1] = true;
        for(int i : al[1]){
            for(int x : al[i]){
                if(!visit[x]){
                    ans++;
                    visit[x] = true;
                }
            }
        }
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
    }
}

====================================================================================================
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int M = readInt();
		ArrayList<Integer>[] g = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		int[] visit = new int[N+1];
		for (int i = 0; i < M; i++) {
			int a = readInt();
			int b = readInt();
			g[a].add(b);
			g[b].add(a);
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		int res = -1;
		while(!q.isEmpty()) {
			int v = q.poll();
			res++;
			if(visit[v] == 0 || visit[v] == 1) {
				for (int i = 0; i < g[v].size(); i++) {
					int nv = g[v].get(i);
					if(visit[nv] == 0 && nv != 1) {
						visit[nv] = visit[v]+1;
						q.offer(nv);
					}
					
				}
			}
		}
		
		System.out.println(res);
	}

	public static int readInt() throws Exception {
		int val = 0;
		int c = System.in.read();
		while (c <= ' ') {
			c = System.in.read();
		}
		boolean flag = (c == '-');
		if (flag)
			c = System.in.read();
		do {
			val = 10 * val + c - 48;
		} while ((c = System.in.read()) >= 48 && c <= 57);

		if (flag)
			return -val;
		return val;
	}
}
