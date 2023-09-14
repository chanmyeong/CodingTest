import java.io.*;
import java.util.*;
/**
 * 쩰리의 출발점은 항상 정사각형의 가장 왼쪽, 가장 위의 칸이다. 다른 출발점에서는 출발하지 않는다.
 * 쩰리가 이동 가능한 방향은 오른쪽과 아래 뿐
 * 쩰리가 가장 오른쪽, 가장 아래 칸에 도달하는 순간, 그 즉시 ‘쩰리’의 승리로 게임은 종료된다.
 * 쩰리가 한 번에 이동할 수 있는 칸의 수는, 현재 밟고 있는 칸에 쓰여 있는 수 만큼이다.
 * >> 칸에 쓰여 있는 수 초과나 그 미만으로 이동할 수 없다. <<
 * 끝 점(오른쪽 맨 아래 칸)까지 도달할 수 있는지를 알아보기
 *
 * 문제 해결 전략 :
 * 오른쪽, 아래만 이동이 가능하므로 방문배열이 필요없는 유향 그래프 DFS
 * but 메모리 초과로 메모이제이션이 필요 -> 방문배열
 * 배열에 적힌 숫자만큼 이동이 가능 -> 이동을 할 때 그 수만큼을 곱해 주면 된다
 */
public class Main {
    static int[] dr = {1,0}; // 하우
    static int[] dc = {0,1};
    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DFS(0,0, map, visited);
        System.out.println("Hing");
    }
    private static void DFS(int r, int c, int[][] map, boolean[][] visited) {
        if(map[r][c]==-1) { // 도착 시
            System.out.println("HaruHaru");
            System.exit(0);
        }

        for(int k=0; k<2; k++) {
            int nr = r+dr[k]*map[r][c];
            int nc = c+dc[k]*map[r][c];
            if(isOutOfRange(nr,nc) || visited[nr][nc]) continue;
            visited[nr][nc]=true;
            DFS(nr,nc,map,visited);
        }
    }
    private static boolean isOutOfRange(int nr, int nc) {
        return 0>nr || nr>=N || 0>nc || nc>=N;
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, arr[][];
	static String result = "Hing";
	static boolean visit[][];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		visit = new boolean[n][n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		fn(0,0);
		
		System.out.println(result);
	}
	
	public static void fn(int x, int y) {
		if(arr[x][y] == 0) return;
		if(arr[x][y] == -1) {
			result = "HaruHaru";
			return;
		}
		
		if(0 < x+arr[x][y] && x + arr[x][y] < n) {
			fn(x+arr[x][y], y);
		}
		if(0 < y+arr[x][y] && y+arr[x][y] < n) {
			fn(x,y+arr[x][y]);
		}
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N*N(2<=N<=3) 정사각형 구역 내부만 존재 가능 
 * 장외 죽음
 * 쩰리 시작점(0,0) 패딩x
 * 쩰리 이동 방향 => 오른쪽|아래
 * 쩰리 도착점 (N-1)(N-1) 패딩x  -1이라 써있음
 * 쩰리 이동 가능 칸 == 현재 밟고 있는 칸에 쓰인 수만큼. 초과/미만 x
 * 					0<=x<=100
 * output
 * 도착점 도달 -> HaruHaru
 * 도달x -> Hing 
 *
 * [문제해결 프로세스]
 * bfs
 * 1.이동 방향(오른쪽,아래) =>{0,1},{1,0}
 * 		이동 = 이동방향 * 현재 밟고 있는 값
			방문체크
 * 2.경계 나가면 바로 종료, Hing 출력
 * 3.현재 밝고 있는 칸이 0이면 바로 종료,Hing 출력
 * 
 *
 */
public class Main {

	static int dn[][]= {{0,1},{1,0}};//우,하
	static int N,map[][];
	static boolean flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		System.out.println(flag?"HaruHaru":"Hing");
		


	}
	private static void bfs() {
		boolean visited[][]=new boolean[N][N];
		Queue<Node> q = new ArrayDeque<Node>();
		q.offer(new Node(0,0));
		while(!q.isEmpty()) {
			Node cur = q.poll();
			//방문체크
			if(visited[cur.y][cur.x])continue;
			visited[cur.y][cur.x]=true;
			int move=map[cur.y][cur.x];//현재 밟고 있는 칸의 이동값

			// 방문배열을 쓰지 않을 경우의 (small) -> 인덱스가 0일 경우를 고려해주어야 메모리 초과가 나지 않는다
			if(move==0)return;//밟은 칸의 값이 0일 경우 종료
			if(move==-1) {//목표지 도달
				flag=true;
				return;
			}
			
			for (int i = 0; i < 2; i++) {//우,하 이동
				//값만큼 이동
				int dy=cur.y+dn[i][0]*move;
				int dx=cur.x+dn[i][1]*move;
				if(!inRange(dy, dx)&&!visited[dy][dx]) {
				q.offer(new Node(dy,dx));
				}
			}
		}
	}
	private static boolean inRange(int i, int j) {
		if(i>=N || j>=N) return true;
		return false;
	}
	static class Node{
		int y,x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

}
