import java.io.*;
import java.util.*;

/**
 * 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅
 * 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로
 * 인접해 있는(4방) 배추들끼리에는 최소 한 마리의 배추흰지렁이가 필요
 * 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있음
 *
 * 배추밭 M*N (1 ≤ M,N ≤ 50)
 * 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)
 * 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)
 *
 * 두 배추의 위치가 같은 경우는 없다
 *
 * 필요한 최소의 배추흰지렁이 마리 수를 출력
 *
 * 문제 해결 전략 :
 * BFS로 인접해있는지 조사, 인접하지 않은 그룹 등장시 지렁이 수 증가
 */
public class Main {
    static int[] dr = {1,-1,0,0}; //하상우좌
    static int[] dc = {0,0,1,-1};
    static int M,N,K,min;
    static int[][] map;
    static List<int[]> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static void bfs(int[] i, boolean visited[][]) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(i);
//        System.out.println("in bfs:"+i[0]+" "+i[1]);
        visited[i[0]][i[1]] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            for(int d=0; d<4; d++) {
                int nr = r+dr[d];
                int nc = c+dc[d];
                if(nr<0||nr>=N||nc<0||nc>=M) continue; // 경계 체크
                if(visited[nr][nc]) continue; // 방문 체크
                if(map[nr][nc]==0) continue; // 유기농 배추여부 체크
                queue.offer(new int[]{nr,nc});
                visited[nr][nc]=true;
            }
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine()," ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            list.clear();

            for(int k=0; k<K; k++) {
                st = new StringTokenizer(br.readLine()," ");
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                map[r][c] = 1;
                list.add(new int[]{r,c}); // 배추들만 list에 담아서
            }

            min = 0;
            //solve
            boolean[][] visited = new boolean[N][M];
            for(int[] i : list) {
                if(visited[i[0]][i[1]]) continue; // 인접 여부 조사, 인접하지 않다면
                bfs(i, visited); // 해당 영역 bfs 돌려서 그룹핑
                min++; // 해당 영역에 최소 지렁이 추가
            }

//            for(int r=0; r<N; r++) {
//                for(int c=0; c<M; c++) {
//                    System.out.print(visited[r][c]+" ");
//                }
//                System.out.println();
//            }

            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }
}
====================================================================================================
// BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Exam1012 {

	static int M, N, K;
	static int[][] cabbage;
	static boolean[][] visit;
	static int count;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };

	static void bfs(int x, int y) {
		Queue<int[]> qu = new LinkedList<int[]>();
		qu.add(new int[] { x, y });

		while (!qu.isEmpty()) {
			x = qu.peek()[0];
			y = qu.peek()[1];
			visit[x][y] = true;
			qu.poll();
			for (int i = 0; i < 4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];

				if (cx >= 0 && cy >= 0 && cx < M && cy < N) {
					if (!visit[cx][cy] && cabbage[cx][cy] == 1) {
						qu.add(new int[] { cx, cy });
						visit[cx][cy] = true;
					}
				}

			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			 M = Integer.parseInt(st.nextToken());
			 N = Integer.parseInt(st.nextToken());
			 K = Integer.parseInt(st.nextToken());
			cabbage = new int[M][N];
			visit = new boolean[M][N];
			count=0;

			for (int i = 0; i < K; i++) {
				st=new StringTokenizer(br.readLine()," ");
				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				cabbage[p1][p2] = 1;

			}

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (cabbage[i][j] == 1 && !visit[i][j]) {
						bfs(i, j);
						count++;
					}
				}
			}
			System.out.println(count);
		}
		
	}
}
====================================================================================================
// DFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Exam1012_dfs {

	static int M, N, K;
	static int[][] cabbage;
	static boolean[][] visit;
	static int count;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };

	static void dfs(int x, int y) {
		visit[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];

			if (cx >= 0 && cy >= 0 && cx < M && cy < N) {
				if (!visit[cx][cy] && cabbage[cx][cy] == 1) {
					dfs(cx, cy);
				}
			}

		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int i = 0; i < tc; i++) {
			count = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			cabbage = new int[M][N];
			visit = new boolean[M][N];

			K = Integer.parseInt(st.nextToken());
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				cabbage[p1][p2] = 1;
			}

			for (int x = 0; x < M; x++) {
				for (int y = 0; y < N; y++) {
					if (cabbage[x][y] == 1 && !visit[x][y]) {
						dfs(x, y);
						count++;
					}
				}
			}

			System.out.println(count);
		}

	}

}
