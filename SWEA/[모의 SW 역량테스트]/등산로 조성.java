import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
/**
 * NxN
 * 
 * 최대한 긴 등산로 만들기
 * 
 * 각 숫자는 지형의 높이를 의미
 * 
 * 등산로 만드는 규칙
 * 1. 가장 높은 봉우리에서 시작
 * 2. 등산로는 높은 곳에서 낮은 곳으로 가로 또는 세로 방향이어야 함.
 *  => 같은 높이나 대각선 불가능
 * 3. 한 곳을 최대 K깊이만큼 지형을 깎는 공사를 할 수 있음.
 * 
 * NxN 크기의 지도와 최대공사가능깊이 K가 주어짐
 * 
 * 만들 수 있는 가장 긴 등산로를 찾아 그 길이를 출력하는 프로그램 만들기
 * 
 * 문제 해결 프로세스
 * 
 * 1. 입력값을 받으며 가장 높은 높이를 구하기
 * 2. 가장 높은 높이의 좌표값 구하기
 *      => 가장 높이가 높은 좌표의 높이도 깎을 수 있다는 것을 고려
 * 3. 좌표하나를 골라 해당 좌표를 공사하지 않는 것부터 K 만큼 공사하는 것으로 설정
 *      => 지형을 깎아 높이를 1보다 작게 만드는 것도 가능 (0까지만 시도해보면 됨)
 *      => 다른 곳에선 1보다 작은 높이가 없기 떄문에 0이나 -1이나 같은 결과가 나오기 때문
 * 
 * 4. 가장 높은 높이의 좌표부터 dfs 탐색을 통해 해당 탐색의 길이가 최대값이라면 갱신
 *      => 경계
 *      => 현재 높이 > 다음 좌표
 *      함수의 가장 윗부분에서 등산로 길이 갱신 코드
 * 
 * 시간 복잡도.
 * N 최대 8
 * K 최대 5
 * 가장 높은 봉우리는 최대 5개
 * 
 * 5 x 64 x 16x5
 * = 9만 6천
 * 문제 없을 듯
 * 
 * 
 * @author minho
 *
 */
public class Solution_1949_등산로조성 {
 
    //상하좌우 델타값
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
     
    static int N,K; //지형의 길이, 최대 가능 가공 높이
     
    static int[][] map; //지형의 높이 정보
 
    static int maxRoad; //최대 등산로 길이
     
    static List<int[]> maxHeightList = new ArrayList<>();
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        //System.setIn( new FileInputStream("등산로조성_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new  StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            int maxHeight = 0;
            map = new int[N][N];
            for(int i=0; i<N; i++) {
                st = new  StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxHeight = Math.max(maxHeight, map[i][j]);
                }
            }
             
            //step 01. 가장 높은 높이의 좌표값 구하기
            maxHeightList.clear();
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j]==maxHeight) maxHeightList.add(new int[] {i, j});
                }
            }
             
            //step 02. 좌표하나를 골라 해당 좌표를 공사하지 않는 것부터 K 만큼 공사하는 것으로 설정
            maxRoad = 0;    //최대 높이 초기화
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    int curHeight = map[i][j];  //현재 높이
                    for(int minus=0; minus<=K && curHeight-minus>=0; minus++) {
                        map[i][j] = curHeight-minus;    //깎은 높이를 적용
                         
                        //step 03. 가장 높은 높이의 좌표부터 dfs 탐색을 통해 해당 탐색의 길이가 최대값이라면 갱신
                        for(int[] position : maxHeightList) {
                            dfs(position[0], position[1], 1);
                        }
                    }
                    //탐색이 끝난 후 원래 높이로 되돌리기
                    map[i][j] = curHeight;
                }
            }
             
             
             
            sb.append('#').append(tc).append(' ').append(maxRoad).append('\n');
        }
        System.out.println(sb);
         
    }
 
    /**
     * 
     * @param r
     * @param c
     * @param roadCnt 현재까지 만든 등산로의 길이
     */
    private static void dfs(int r, int c, int roadCnt) {
        //현재까지 만든 등산로가 최대라면 갱신
        maxRoad = Math.max(maxRoad, roadCnt);
         
         
        for(int d=0; d<4; d++) {
            //다음 좌표
            int nr = r+dr[d];
            int nc = c+dc[d];
             
            //경계 벗어나면 다음 방향
            if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
            //현재 높이 <= 다음 좌표의 높이 다음 방향
            if(map[r][c] <= map[nr][nc]) continue;
             
            dfs(nr, nc, roadCnt+1);
        }
         
    }
 
}
====================================================================================================
import java.util.*;
import java.io.*;

/**
 * N * N 크기
 * 2차원배열의 원소 : 지형의 높이
 * 
 * 등산로 만들기
 * 1. 가장 높은 봉우리에서 시작
 * 2. 높은 지형에서 낮은 지형 방향으로 가로세로 연결
 * 3. 긴 등산로 만들기 위해 딱 한곳을 정해 K만큼 깎기 가능
 * 
 * (3 ≤ N ≤ 8)
 * (1 ≤ K ≤ 5)
 * 지형의 높이는 1 이상 20 이하의 정수
 * 가장 높은 봉우리는 최대 5개
 * 정수 단위로만 깎을 수 있다.
 * 지형을 깎아 높이를 1보다 작게 만드는 것도 가능
 * 
 * 가장 높은 봉우리 값 리스트에 넣기
 * Point객체에 length가 최대인 것을 찾기
 * 높은 봉우리보다 1씩 작은 길을 갈 때 최대
 * 
 */
public class Solution_1949_등산로조성 {
	static int N,K;
	static int answer; // 등산로의 최대 길이
	static int[][] map;
	static class Point {
		int r,c,length;

		public Point(int r, int c, int length) {
			super();
			this.r = r;
			this.c = c;
			this.length = length;
		}
	}
	static ArrayList<Point> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			int max=-1;
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if(map[r][c]>max) {
						max=map[r][c];
					}
				}
			}
//			for(int[] i : map) System.out.println(Arrays.toString(i));			
			answer=0;
			list.clear();
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(map[r][c]==max) {
						list.add(new Point(r, c, 1));
					}
				}
			}			
			
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					for(int h=0; h<=K; h++) {
						if(map[r][c]<h) break; // 1보다 작은 높이가 없기 때문에 0이나 -1이나 같은 결과
						map[r][c]-=h;
						for(Point p : list) {
							bfs(p.r, p.c);
						}
						map[r][c]+=h;
					}
				}
			}
			
			sb.append("#"+tc+" "+answer).append("\n");
		}
		System.out.println(sb);
	}
	private static void bfs(int sr, int sc) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(sr, sc, 1));
		int[] dr = {-1,0,1,0};
		int[] dc = {0,1,0,-1};
		while(!queue.isEmpty()) {
			Point cur = queue.poll();	
			for(int d=0; d<4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				if(nr<0||nr>=N||nc<0||nc>=N) continue;
				if(map[cur.r][cur.c]>map[nr][nc]) {
					queue.offer(new Point(nr, nc, cur.length+1));
				}
			}
			answer = Math.max(answer, cur.length);
		}
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution{
 
    static int N,K,map[][],res;
    static boolean mapCheck[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            res = Integer.MIN_VALUE;
            int start = -1;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j]>start) start = map[i][j];
                }
            }
            mapCheck = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] == start) {
                        mapCheck[i][j] = true;
                        dfs(i,j,1,1);
                        mapCheck[i][j] = false;
                    }
                }
            }
            System.out.println("#"+tc+" "+res);
        }
         
    }
     
    // 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    private static void dfs(int x, int y, int cut, int cnt) {
         
        //먼저 사방 탐색을 진행해야지
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            // 범위 넘어가면 안됨
            if(nx<0 || ny<0 || nx>=N || ny>=N)continue;
            // 이미 방문한 곳이여도 안됨
            if(mapCheck[nx][ny]) continue;
            // 더 작은 값이면?
            if(map[nx][ny]<map[x][y]) {
                mapCheck[nx][ny] = true;
                dfs(nx,ny,cut,cnt+1);
                mapCheck[nx][ny] = false;
            // 같거나 큰 값인데 공사가 가능하다면
            }else if(cut==1) {
                // 최대 공사가능 깊이만큼 다시 해보자?
                for (int i = 1; i <= K; i++) {
                    if(map[nx][ny]-i < map[x][y]) {
                        map[nx][ny]-=i;
                        dfs(nx,ny,cut-1,cnt+1);
                        map[nx][ny]+=i;
                    }
                }
            }
        }
        res = Math.max(res, cnt);
    }
}
