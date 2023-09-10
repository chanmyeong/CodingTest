// DFS 1
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 이동방향 오른쪽(0), 오른쪽아래(1), 아래(2)
 * 회전방향 45도
 * 	오른쪽 (0) - 0,1
 * 	오른쪽아래(1) - 0,1,2
 * 	아래(2) - 1,2
 * 
 * 벽 1
 * 	오른쪽 이동시 - 오른쪽 벽 체크
 * 	오른쪽아래 이동 시 - 오른쪽, 아래, 오른쪽아래 벽 체크
 * 	아래쪽 이동시 - 아래쪽 벽 체크
 * 
 * 인덱스 1부터 사용
 * 시작점 (1,2)로 사용
 * 
 * @author minho
 */
public class Main_BJ_17070_파이프옮기기1_연민호_dfs {
	//오른쪽, 오른쪽아래, 아래
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 1, 0};
	
	static int N;
	static int[][] map;
	
	static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		//맵 정보 입력
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(1, 2, 0);
		
		System.out.println(cnt);
	}

	/**
	 * 
	 * @param r 현재 좌표의 행 값
	 * @param c 현재 좌표의 열 값
	 * @param dir	파이프의 방향
	 */
	private static void dfs(int r, int c, int dir) {
		//(N,N) 에 도달한 경우 경우의 수 cnt
		if(r==N && c==N) {
			cnt++;
			return;
		}
		int start = 0;
		int end = 2;
		
		if(dir==0) end=1;			//오른쪽 방향의 경우 (0~1)
		else if(dir==2) start =1;	//아래방향의 경우 (1~2)
		
		for(int d=start; d<=end; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(r>N || c>N) continue;	//경계 벗어
			if(isWall(nr, nc, d)) continue;	//이동 경로에 벽이 있는 경우
			
			//경계를 벗어나지 않는 경우
			dfs(nr, nc, d);
		}
	}

	/**
	 * 해당방향으로 파이프롤 옮길 수 없는 경우 true반환
	 * @param r
	 * @param c
	 * @param dir
	 * @return
	 */
	private static boolean isWall(int r, int c, int dir) {
		
		//대각인 경우는 2가지 방향을 더 보아야 함
		if(dir==1) {
			if(map[r-1][c]==1||map[r][c-1]==1) return true;
		}
		
		if(map[r][c]==1) return true;
		
		return false;
	}
}
====================================================================================================
// DFS 2
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main_BJ_17070_파이프옮기기1_신예지_DFS {

	static int N;
	static int[][] map;
	static int cnt;
	static int[] dx = new int[] {0,1,1};  // 우 대각 하
	static int[] dy = new int[] {1,1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(1,2,0);  // pipe 0 : 가로, 1 : 대각, 2: 세로
		
		System.out.println(cnt);
	}

	private static void dfs(int x, int y, int pipe) {
		if(x == N && y == N) {
			cnt++;
		}
		for (int d = 0; d < dx.length; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx<0 || ny<0 || nx>N || ny>N)continue;
			if(map[nx][ny]==1)continue;
			
			if((pipe == 0 && d<2) || pipe == 1 || (pipe == 2 && d!=0)) { // 가로
			
				if(d==1 && map[nx-1][ny]!=1 && map[nx][ny-1]!=1) {
					dfs(nx, ny, d);
				}
				else if(d!=1) {
					dfs(nx, ny, d);		
				}
			
			}
		}
	}
	
}
  
====================================================================================================
// DFS 3
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17070_파이프옮기기1_곽미래_DFS {

	static int N, CNT;
	static boolean map[][];
	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N+2][N+2];
		
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) map[i][j] = true;
			}
		}
		for(int i=0; i<N+2; i++) {
			map[0][i] = map[N+1][i] = map[i][0] = map[i][N+1] = true; 
		}
		dfs(1, 2, 0);
		System.out.println(CNT);
	}
	
	// d : 0=가로, 1=세로, 2=대각선
	private static void dfs(int r, int c, int d) {
		// 벽이면 종료
		if(map[r][c]) return;
		
		if(r == N && c == N) {
			CNT++;
			return;
		}
		
		if(d!=1) dfs(r, c+1, 0); // 가로, 대각선일 경우 가로로 이동 가능
		if(d!=0) dfs(r+1, c, 1); // 세로, 대각선일 경우 세로로 이동 가능
		if(!map[r+1][c] && !map[r][c+1]) dfs(r+1, c+1, 2); // 범위내 벽이 없다면 모든 경우 대각선 이동 가능
	}
}

====================================================================================================
// DFS 4
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17070_파이프옮기기_김용준_Backtracking {
	static int N, cnt;
	static boolean[][] room;
	static int[] dr = {0, 1, 1}; // 우, 우하, 하
	static int[] dc = {1, 1, 0}; // 우, 우하, 하

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		room = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				room[i][j] = st.nextToken().equals("1");
			}
		}

		backTracking(1, 2, 0);
		System.out.println(cnt);
	}

	private static void backTracking(int r, int c, int dir) {
		if (r > N || c > N) return;
		if (room[r][c]) return;
		if (r == N && c == N) { // (N,N)에 도착하면 경우의 수 + 1
			cnt++;
			return;
		}

		// 방향에 따라 다음 깊이 탐색
		if (dir <= 1) backTracking(r, c + 1, 0); // 오른쪽 탐색
		if (dir >= 1) backTracking(r + 1, c, 2); // 아래쪽 탐색
		
		
		if (r == N || c == N || room[r + 1][c] || room[r][c + 1]) return;
		backTracking(r + 1, c + 1, 1); // 오른쪽 아래 탐색
	}
}
  
====================================================================================================
// DFS 5  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17070_파이프옮기기1_이주혁_백트래킹 {
	
	private static int N, map[][], ans;
	
	private static boolean isOutOfRange(int r, int c) {
		return 0>r || r>=N || 0 > c || c>=N;
	}
	
	private static boolean canDiagMove(int hr, int hc) {
		
		return !isOutOfRange(hr+1, hc+1) && map[hr+1][hc] == 0 && map[hr][hc+1] == 0 && map[hr+1][hc+1] == 0; 
		
	}
	
	private static void backtracking(int hr, int hc, int tr, int tc) {
		if(hr==N-1 && hc == N-1) {
			ans++;
			return;
		}
		
		// 가로 상태
		if(hr==tr) {
			if(!isOutOfRange(hr,hc+1) && map[hr][hc+1] == 0) backtracking(hr, hc+1, hr, hc);
			if(canDiagMove(hr, hc) && map[hr][hc+1] == 0) backtracking(hr+1, hc+1, hr, hc);
		}
		
		// 세로 상태
		else if(hc==tc) {
			if(!isOutOfRange(hr+1,hc) && map[hr+1][hc] == 0) backtracking(hr+1, hc, hr, hc);
			if(canDiagMove(hr, hc)) backtracking(hr+1, hc+1, hr, hc);
		}
		
		// 대각선 상태
		else {
			if(!isOutOfRange(hr+1,hc) && map[hr+1][hc] == 0) backtracking(hr+1, hc, hr, hc);
			if(!isOutOfRange(hr,hc+1) && map[hr][hc+1] == 0) backtracking(hr, hc+1, hr, hc);
			if(canDiagMove(hr, hc)) backtracking(hr+1, hc+1, hr, hc);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
    
		backtracking(0, 1, 0, 0);
		System.out.println(ans);
	}
	
}
====================================================================================================
// DP
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1]; // 1부터 사용

		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}

		int[][] garo = new int[N+1][N+1];	//garo[3][3] : (3,3)에 가로로 도달할 수 있는 경우의 수
		int[][] sero = new int[N+1][N+1];	//sero[3][3] : (3,3)에 세로로 도달할 수 있는 경우의 수
		int[][] daegag = new int[N+1][N+1];	//daegag[3][3] : (3,3)에 대각으로 도달할 수 있는 경우의 수

		// garo(1,2) = 1 초기화
		garo[1][2] = 1;

		// 1행 3열부터 garo, sero, daegag을 상향식으로 채우기
		for(int r=1; r<=N; r++) {
			for(int c=3; c<=N; c++) {
				//해당 좌표의 값이 1이라면 넘김
				if(map[r][c]==1) continue;
				garo[r][c] = garo[r][c-1] + daegag[r][c-1];
				sero[r][c] = sero[r-1][c] + daegag[r-1][c];

				//map[N-1][N] 또는 map[N][N-1] 이 1이라면 대각선 불가
				if(map[r-1][c]==1 || map[r][c-1]==1) continue;
				daegag[r][c] = garo[r-1][c-1] + sero[r-1][c-1] + daegag[r-1][c-1];
			}
		}

		// 구해진 garo(N,N) , sero(N,N), daegag(N,N)의 합을 정답으로 출력
		System.out.println( garo[N][N] + sero[N][N] + daegag[N][N] );

	}
}
