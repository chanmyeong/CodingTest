import java.io.*;
import java.util.*;

/**
 * N*N 디저트 카페
 * 2차원 배열의 주소 = 디저트 카페
 * 2차원 배열의 index = 팔고 있는 디저트의 종류
 * 
 * 카페들 사이에는 대각선 방향으로 움직일 수 있는 길 존재
 * 
 * 디저트 카페 투어 : 어느 한 카페에서 출발하여 대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 복귀
 * 디저트 카페 투어를 하는 도중 해당 지역을 벗어나기 금지 -> 2차원 배열 경계 체크
 * 
 * 카페 투어 중 같은 숫자의 디저트를 팔고 있는 카페를 방문해서는 안 된다 -> 배열 인덱스 체크
 * 하나의 카페에서 디저트를 먹는 것도 안 된다. -> 바로 종료 불가
 * 같이 왔던 길을 다시 돌아가는 것도 안 된다. -> 방문 체크
 * 
 * 서로 다른 디저트를 가장 많이 먹을 수 있는 경로를 찾아 그 때의 디저트 수 출력
 * 디저트를 먹을 수 없다면 -1 출력
 * 
 * Java 3초
 * 4 ≤ N ≤ 20
 * 1 ≤ 디저트 종류 ≤ 100
 * 
 * 나의 문제 해결 전략 :
 * 1. 완전탐색으로 2차원 배열의 각 좌표에 대해 BFS를 돌린다
 * 2. 다음에 따라 탐색을 이어갈지 정한다
 *  - 경계값 체크, 같은 숫자 체크(순회하면서 디저트 종류 저장 자료구조), 바로 종료 불가, 방문 체크
 * 3. 디저트 카페 투어가 가능하다면 디저트 종류의 최댓값을 갱신
 *  - 없다면 -1 출력
 * 
 * 문제 해결 전략 :
 * 어떤 지점에서 출발할 때 윗쪽으로 순회하는 것은 생각할 필요 X
 * 0,0부터 N-1,N-1순의 좌표로 출발할 때 y,x에서의 윗쪽에 대한 순회는
 * 이미 그 윗 좌표에서 전부 진행이 되었기 때문에 y,x 아래 대각선으로의 순회만 생각해 주면 된다.
 * 또한 시계방향으로 순회할지 반시계방향으로 순회할지도 한가지에 대해서만 정해주면 된다.
 * 시계든 반시계든 어차피 먹는 디저트의 순서만 다를 뿐 같은 디저트를 거치기 때문에 한가지 방향에서만 생각
 * 방향이 대각선이며 이전의 방향을 기억 -> 이전의 방향을 같이 보내서 그 방향 다음 방향만 찾도록
 * - 왼쪽 아래 대각선으로 내려간 경우 오른쪽 아래 대각선으로 내려갈 수 없도록 하기 위해서
 * 
 * 1. 최소 양 옆이 있어야 하고 밑에 2칸까지는 여유가 있어야 사각형이 만들어지므로 미리 조건 처리
 * 2. 먹은 디저트 방문처리 및 위치 방문 처리
 * 3. dfs를 돌면서 이전 방향의 다음 방향만 탐색
 * 4. 자기 자신한테 돌아온 경우(최소 3번 이상 탐색) 종료 및 디저트 종류 최댓값 갱신
 */
public class Solution_2105_디저트카페 {
	static int[] dr = {1,1,-1,-1}; // 대각선 4방 탐색 하우-하좌-상좌-상우
	static int[] dc = {1,-1,-1,1};
	static int N;
	static int[][] map;
	static int cntMaxDessert;
	static boolean[][] visited;
	static boolean[] dessert;

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new StringReader(str));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N]; // 0부터 시작
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			//System.out.println(Arrays.deepToString(arr));

			cntMaxDessert = 0;
			//solve
			// 양 옆과 밑에 2칸이 있어야 사각형 가능
			for(int r=0; r<N-2; r++) {
				for(int c=1; c<N-1; c++) {
					visited = new boolean[N][N];
					dessert = new boolean[101];
					visited[r][c] = true;
					dessert[map[r][c]] = true;
					dfs(1, r, c, r, c, 0);
				}
			}
			
			if(cntMaxDessert==0) cntMaxDessert=-1; // 없다면 -1 출력
			sb.append("#"+tc+" "+cntMaxDessert).append("\n");
		}
		System.out.println(sb);
	}
	private static void dfs(int cnt, int r, int c, int sr, int sc, int prevD) {
		// prevD : 이전 방향을 넘겨서 그 방향보다 같거나 다음 방향으로만 넘긴다. 이전 방향으로는 이동X
		for(int i=prevD; i<4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			if((nr==sr)&&(nc==sc)&&cnt>2) { // 기저조건, 바로 종료 불가
				cntMaxDessert = Math.max(cntMaxDessert, cnt);
				return;
			}
			// 반복 조건
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue; // 경계값 체크
			if(visited[nr][nc]) continue; // 방문 체크
			if(dessert[map[nr][nc]]) continue; // 같은 숫자 체크
			visited[nr][nc] = true;
			dessert[map[nr][nc]] = true;
			// 조합에서 중복 조합을 하지 않기 위해 start를 넘기듯이 방향을 넘겨 사각형을 만들도록 방향의 길을 정해둠
			dfs(cnt+1, nr, nc, sr, sc, i);
			visited[nr][nc] = false;
			dessert[map[nr][nc]] = false;
		}
	}
	static String str = "2\r\n" + 
			"4\r\n" + 
			"9 8 9 8\r\n" + 
			"4 6 9 4\r\n" + 
			"8 7 7 8\r\n" + 
			"4 5 3 5\r\n" + 
			"5\r\n" + 
			"8 2 9 6 6\r\n" + 
			"1 9 3 3 4\r\n" + 
			"8 2 3 3 6\r\n" + 
			"4 3 4 4 9\r\n" + 
			"7 4 6 3 5";
}
