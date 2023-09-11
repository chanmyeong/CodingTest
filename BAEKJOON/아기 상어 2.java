import java.awt.*;
import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader bf;
    private static StringTokenizer st;

    private static int N, M, maxVal=0;
    private static int [][] map;
    private static int [][] visited;
    private static Queue<Point> queue = new LinkedList<>();
    private static int [] dx = {-1,-1,-1,1,1,1,0,0};
    private static int [] dy = {-1,0,1,-1,0,1,-1,1};

    public static void main(String[] args) throws IOException {
        bf=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(bf.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new int[N][M];
        visited=new int[N][M];

        for(int r=0;r<N;r++) {
            st=new StringTokenizer(bf.readLine());
            for(int c=0;c<M;c++) {
                map[r][c]=Integer.parseInt(st.nextToken());
                visited[r][c]=Integer.MAX_VALUE;
                if(map[r][c]==1) {	// 아기 상어의 위치를 모두 queue에 넣음
                    queue.offer(new Point(r, c));
                    visited[r][c]=0;	// 아기 상어의 거리를 0으로 설정
                }
            }
        }

        bfs();	// 안전 거리 탐색
        System.out.println(maxVal);
    }

    public static void bfs() {

        while(!queue.isEmpty()) {
            Point p=queue.poll();

            for(int i=0;i<8;i++) {
                int nx=p.x+dx[i];
                int ny=p.y+dy[i];

                if(isIn(nx, ny)) {
                    if(visited[nx][ny]>visited[p.x][p.y]+1) {	// 아기 상어의 안전 거리 계산(최소)
                        visited[nx][ny]=visited[p.x][p.y]+1;
                        maxVal=Math.max(maxVal, visited[nx][ny]);	// 안전 거리 중 최대값 maxVal에 저장
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return r>=0 && r<N && c>=0 && c<M;
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[N][M];
		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0, index = 0; j < M; j++, index += 2) {
				map[i][j] = s.charAt(index);
				if(map[i][j] == '1') {
					visited[i][j] = true;
					queue.offer(new int[] {i, j});
				}
			}
		}

		int[] dx = { 1, 0, -1, 0, 1, 1, -1, -1};
		int[] dy = { 0, 1, 0, -1, 1, -1, 1, -1 };
		int cnt = -1;
		while (!queue.isEmpty()) {
			cnt++; // 큐의 크기가 한 사이클 줄어들었을 때, 방문하지 않은 곳까지의 최대 cnt
			int size = queue.size();
			while (size > 0) {
				size--;
				int[] q = queue.poll();
				int r = q[0];
				int c = q[1];
				
				for (int i = 0; i < 8; i++) {
					int nr = r + dx[i];
					int nc = c + dy[i];
					if(nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc]) continue;
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
				
			}
		}

		System.out.println(cnt);

	}// class
}// main
====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    static int[] dirX = {0,-1,-1,-1,0,1,1,1}; // 좌 좌상 상 상우 우 우하 하 하좌
    static int[] dirY = {-1,-1,0,1,1,1,0,-1};
    static int N, M;
    static int[][] map;
    static ArrayDeque<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if(Integer.parseInt(st.nextToken()) == 1) {
                    map[i][j] = 1;
                    q.offer(new int[] {i, j});
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int[] poll = null;

        while (!q.isEmpty()) {
            poll = q.poll();

            for (int d = 0; d < 8; d++) {
                int dx = poll[0] + dirX[d];
                int dy = poll[1] + dirY[d];

                if(0<=dx && dx<N && 0<=dy && dy<M && map[dx][dy] == 0) {
                    map[dx][dy] = map[poll[0]][poll[1]] + 1;
                    q.offer(new int[] {dx,dy});
                }
//                for(int[] i : map) {
//                    System.out.println(Arrays.toString(i));
//                }
//                System.out.println("==========");
            }
        }
//        for(int[] i : map) {
//            System.out.println(Arrays.toString(i));
//        }

        return map[poll[0]][poll[1]] - 1;
    }
}
