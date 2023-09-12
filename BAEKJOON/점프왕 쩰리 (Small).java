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
