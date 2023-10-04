import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
0 x 0 0
x 0 x 1
0 x 1 1
0 1 1 0 
k=1
 */
public class Main_BJ_1600_말이되고픈원숭이 {
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	static int[] drHorse = {2,2,1,1,-1,-1,-2,-2};
	static int[] dcHorse = {-1,1,-2,2,-2,2,1,-1};
	static int[][] map;
	static boolean[][][] visited; // 메모리 초과가 발생하는 이유 : 제대로 방문체크를 하지 않아서 재방문하기 위해 new를 통한 객체(ex.90번째 줄)를 많이 만들어서
	static int K,C,R;
	static class Point {
		int r; int c; int k; int move;

		public Point(int r, int c, int k, int move) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.move = move;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", k=" + k + "]";
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		visited = new boolean[R][C][K+1]; // 3차원 : 말이 이동해서 방문한 경우와 4방 탐색을 해서 방문한 경우의 방문 처리를 구분 해주어야 한다.
		
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c]=Integer.parseInt(st.nextToken());
			}
		}

//		for(int[] i : map)
//			System.out.println(Arrays.toString(i));		
		
		
		//solve
		BFS();		
		
		System.out.println(-1);
	}

	private static void BFS() {		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0, K, 0));
		visited[0][0][K]=true;
		
		while(!queue.isEmpty()) {
			
			Point current = queue.poll();
			// 방문 처리를 큐에서 꺼낼 때 하게 된다면 이미 확인한 값을 큐에 중복해서 넣어주게 되어 시간복잡도가 증가하게 된다.
//			visited[current.r][current.c][current.k]=true;
			
			if(current.r==R-1 && current.c==C-1) { // 도착점에 도달
				System.out.println(current.move);
				System.exit(0);
			}
			
			if(current.k!=0) {
				for(int dir=0; dir<8; dir++) {
					int nrHorse = current.r + drHorse[dir];
					int ncHorse = current.c + dcHorse[dir];
					
					if(outOfRange(nrHorse, ncHorse)) continue;
					if(map[nrHorse][ncHorse]==1) continue;
					if(visited[nrHorse][ncHorse][current.k-1]) continue; // k를 가지고 이동했음(을 가정)
					queue.offer(new Point(nrHorse, ncHorse, current.k-1, current.move+1));
					visited[nrHorse][ncHorse][current.k-1]=true; // 방문 처리를 일반적으로 큐에 넣을 때 할 것
				}
			}
			for(int dir=0; dir<4; dir++) {
				int nr = current.r + dr[dir];
				int nc = current.c + dc[dir];
				
				if(outOfRange(nr, nc)) continue;
				if(map[nr][nc]==1) continue;
				if(visited[nr][nc][current.k]) continue; // k를 가지고 이동하지 않았음
				queue.offer(new Point(nr, nc, current.k, current.move+1));
				visited[nr][nc][current.k]=true;
			}
			
		}
		
	}

	private static boolean outOfRange(int nr, int nc) {
		return 0>nr || nr>=R || 0>nc || nc>=C;
	}
}
