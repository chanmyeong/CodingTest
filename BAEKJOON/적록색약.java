import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] grid;
    static boolean visited[][];
    static int dx[] = {-1,0,0,1};
    static int dy[] = {0,1,-1,0};
    public void DFS(int x, int y) {
        visited[x][y] = true; // 해당 grid 영역 방문확인
        char tmpChar = grid[x][y]; // 해당 grid 문자 임시저장
        for(int i=0; i<4; i++) { // 4방 탐색
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0<=nx && nx<N && 0<=ny && ny<N) { // 경계값 체크
                // 4방탐색부분이 방문 안했었고 탐색전 문자와 같은 문자라면
                // 기저부분 : 4방탐색의 모든 부분이 탐색전 문자와 다른 문자인 경우 (DFS 종료)
                if(!visited[nx][ny] && grid[nx][ny]==tmpChar) { 
                    DFS(nx,ny);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); 
        sc.nextLine(); 
        grid = new char[N][N];
        visited = new boolean[N][N];
        for(int i=0; i<N; i++) {
            grid[i]=sc.nextLine().toCharArray();
        }
        Main M = new Main();

        // 정상인 경우
        int cntYg=0; // 초록색 보임
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    M.DFS(i,j);
                    cntYg++;
                }
            }
        }

        // 적록색약인 경우
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j]=false; // 재초기화
                if (grid[i][j]=='G') {
                    grid[i][j]='R';
                }
            }
        }

        int cntNg=0; // 초록색 안보임
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    M.DFS(i,j);
                    cntNg++;
                }
            }
        }

        System.out.println(cntYg+" "+cntNg);
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	/**
	 * 4방탐색 Flood Fill의 BFS그래프 탐색
	 */
	static int n;
	static char[][] arr;
	static boolean[][] visited;
	static int[][] drdc = {{-1,0}, {0,1}, {1,0}, {0,-1}}; //상우하좌 [4방위][행or열]
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			for(int j=0; j<n; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
		
//		for(char[] c : arr) {
//			System.out.println(Arrays.toString(c));
//		}
		int size1 = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visited[i][j]) { // BFS로 탐색한 색깔 영역 필터링
					size1++;
					BFS(i, j, true);
				}
			}
		}
		
		// Arrays.fill은 1차원 배열 초기화
		visited = new boolean[n][n]; // 새로운 객체 만들어서 초기화
		int size2 = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visited[i][j]) {
					size2++;
					BFS(i, j, false);
				}
			}
		}
		
		System.out.println(size1 + " " + size2);
		br.close();
	}
	private static void BFS(int r, int c, boolean normal) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r,c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll(); // (BFS 그래프 탐색에서는) 큐에 정점의 위치를 저장해서 탐색, 큐에 값을 넣어서는 안됨 
			
			for(int k=0; k<4; k++) {
				// int nr = r+drdc[k][0]; [X] 큐에서 꺼낸 위치 정보를 갱신해야 한다
				int nr = point[0]+drdc[k][0];
				int nc = point[1]+drdc[k][1];
				if(isOutOfRange(nr, nc) || visited[nr][nc]) continue; // 경계값 체크 및 방문 체크
				
				if(arr[r][c]=='B') { // B
					if(arr[nr][nc]=='B') {
						queue.offer(new int[] {nr, nc});
						visited[nr][nc]=true;
					}
				}
				else { // B말고 R또는G
					if(normal) { // 정상
						if(arr[r][c]!=arr[nr][nc]) continue; // 같은 영역이 아님
						queue.offer(new int[] {nr, nc});
						visited[nr][nc]=true;
					}
					else { // 적록색약
						if(arr[nr][nc]=='B') continue; // 같은 영역이 아님
						queue.offer(new int[] {nr, nc});
						visited[nr][nc]=true;
					}
					
				}
			}
			
		}
		
	}
	private static boolean isOutOfRange(int nr, int nc) {
		return 0>nr || nr>=n || 0>nc || nc>=n;
	}
}
