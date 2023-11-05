import java.io.*;
import java.util.*;

/**
 * 구슬은 N번 쏘기 가능
 * 벽돌 정보는  W*H 배열 (0-빈 공간, 그 외 숫자-벽돌)
 * 
 * 1. 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
 * 2. 벽돌은 숫자 1 ~ 9 로 표현되며,구술이 명중한 벽돌은 상하좌우로 ( 벽돌에 적힌 숫자 - 1 ) 칸 만큼 같이 제거
 * 3. 제거되는 범위 내에 있는 벽돌은 동시에 제거 (연쇄 작용)
 * 4. 빈 공간이 있을 경우 벽돌은 밑으로 떨어지게 된다.
 * 
 * 최대한 많은 벽돌을 제거하고 남은 벽돌의 개수 구하기
 *  
 * 1 ≤ N ≤ 4
 * 2 ≤ W ≤ 12
 * 2 ≤ H ≤ 15
 * 
 */
public class Solution_5656_벽돌깨기 {
	static class Point {
		int r,c,cnt; // 벽돌 위치, 크기
		public Point(int r, int c, int cnt) {
			super();
			this.r=r;
			this.c=c;
			this.cnt=cnt;
		}
	}
	static int[] dr = {0,1,0,-1}; // 우하좌상
	static int[] dc = {1,0,-1,0};	
	static int N,W,H;
	static int min; // 남은 벽돌의 개수
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken()); // 구슬 던지는 횟수
			W = Integer.parseInt(st.nextToken()); // 가로
			H = Integer.parseInt(st.nextToken()); // 세로
			int[][] map = new int[H][W];
			for(int r=0; r<H; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c=0; c<W; c++) {
					map[r][c]=Integer.parseInt(st.nextToken());
				}
			} // 2차원 map 입력

			min = Integer.MAX_VALUE;
			//solve
			drop(0, map);

			sb.append("#"+tc+" "+min).append("\n");
		}
		System.out.println(sb);
	}

	/**
	 * 구슬 던지기 : 중복 순열
	 * @param 구슬 떨어뜨리기 cnt : 직전까지 떨어뜨린 구슬 수
	 * @param map : 직전 상태까지의 map
	 * @return 값 : 모든 벽돌이 제거되었는지 여부
	 */
	private static boolean drop(int cnt, int[][] map) {
		// 구슬을 던지기 전에 현 상태로 남은 벽돌 수 체크
		int result = getRemain(map);
		// 남은 벽돌 수가 0이면 모든 벽돌이 제거된 가장 최적의 상태이므로 최소값 0으로 갱신 후 return true
		if(result==0) {
			min=0;
			return true;
		}
		// 모든 구슬을 다 던졌다면 남은 벽돌 수로 최소값 갱신 후 return false
		if(cnt==N) {
			if(min>result) min = result;
			return false;
		}

		int[][] newMap = new int[H][W];
		for(int c=0; c<W; c++) { // 모든 열에 대해 시도
			// 해당 열에 떨어뜨릴 경우 제거되게 되는 맨 욋 벽돌 찾기
			int r=0;
			while(r<H && map[r][c]==0) ++r;

			// 벽돌이 존재하지 않는다면 (해당 열은 모두 빈칸) 다음 열로 건너뛰기
			if(r==H) continue;

			// 벽돌이 존재한다면 기존 상태에서 복사
			copy(map, newMap); // 디버깅 출력
			// 함께 제거될 인접벽돌 연쇄 찾기
			boom(newMap,r,c); // 디버깅 출력
			// 제거 처리(벽돌 내리기)
			down(newMap); // 디버깅 출력

			// 다음 구슬 던지러 가기 : 재귀 호출
			// 재귀 호출의 결과가  true이면 가장 최적해의 상황이므로  return true
			if(drop(cnt+1, newMap)) return true;
		}
		return false;		
	}

	// 인접한 제거 벽돌 찾기 : Flood Fill(4방 BFS)
	private static void boom(int[][] map, int r, int c) {
		Queue<Point> queue = new ArrayDeque<>();

		if(map[r][c]>1) queue.offer(new Point(r,c,map[r][c]));
		map[r][c]=0; // 방문처리(벽돌 제거 표시)

		while(!queue.isEmpty()) {
			Point cur = queue.poll();

			for(int d=0; d<4; d++) {
				int nr = cur.r;
				int nc = cur.c;
				for(int i=1; i<cur.cnt; i++) { // cnt-1만큼 주변 벽돌 찾기
					nr+=dr[d];
					nc+=dc[d];
					if(nr>=0&&nr<H&&nc>=0&&nc<W&&map[nr][nc]>0) {
						if(map[nr][nc]>1) queue.offer(new Point(nr, nc, map[nr][nc]));
						map[nr][nc]=0; // 방문처리
					}
				}
			}
		}
	}

	// 배열 복사하기 
	private static void copy(int[][] map, int[][] newMap) {
		//				for(int r=0; r<H; r++) {
		//					for(int c=0; c<W; c++) {
		//						newMap[r][c] = map[r][c];
		//					}
		//				}

		//		for(int r=0; r<H; r++) {
		//			newMap[r]=map[r].clone();
		//		}

		//		for(int r=0; r<H; r++) {
		//			newMap[r] = new int[map[r].length];
		//			System.arraycopy(map[r], 0, newMap[r], 0, map[r].length);
		//		}

		for(int r=0; r<H; r++) {
			newMap[r] = Arrays.copyOf(map[r], map[r].length);
		}
	}

	// 벽돌 내리기1 : 빈 자리에 위쪽 벽돌 찾아 내리기
	// 벽돌 내리기2 : 매 열마다 맨 윗행부터 벽돌 칸 모두 스택에 넣고 빈칸 만들기
	private static void down(int[][] map) {
		// 매 열 기준으로 내리기
		for(int c=0; c<W; c++) {
			int r = H-1, nr = -1;
			while(r>0) {
				if(map[r][c]==0) { // 빈칸이면 내릴 벽돌 찾기
					nr = r-1; // 바로 윗행부터 찾기

					while(nr>0 && map[nr][c]==0) --nr;
					map[r][c] = map[nr][c];
					map[nr][c] = 0; // 허공, 빈칸 처리
				}

				if(nr == 0) break; // 더 이상 해당 열에 내릴 벽돌이 없음 
				--r;
			}
		}

	}

	// 남은 벽돌 개수 구하기 : 매번 구슬 던지기 전에 사용할 목적
	private static int getRemain(int[][] map) {
		int cnt = 0;
		for(int r=0; r<H; r++) {
			for(int c=0; c<W; c++) {
				if(map[r][c]!=0) cnt++;
			}
		}
		return cnt;
	}

	// 디버깅용 : 상태 출력
	// 2차원 배열에 헤더 붙여서 출력 시 몇 번째 구슬을 던졌는지.. 전/후
}

====================================================================================================
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Solution_5656_벽돌깨기_연민호 {
	static int T;
	static int N;
	static int W;
	static int H;
	static Queue<Node> q = new LinkedList<>();
	static int result;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();


		for(int test_case = 1; test_case <= T; test_case++)
		{
			N=sc.nextInt();
			W = sc.nextInt();
			H= sc.nextInt();

			result=Integer.MAX_VALUE;
			int[][] matrix = new int[H][W];
			for(int i=0;i<H;i++) {
				for (int j=0;j<W;j++) {
					matrix[i][j]=sc.nextInt();
				}
			}
			solution(matrix,0);
			/////////////////////////////////////////
			//매트릭스 잘 들어갔는지 확인 코드
			//for(int i=0;i<H;i++) {
			//for (int j=0;j<W;j++) {
			//System.out.print(matrix[i][j]);
			//}
			//System.out.println();
			//}
			/////////////////////////////////////////
			System.out.printf("#%d %d\n",test_case,result);
		}

	}
	
	static void solution(int[][] matrix, int depth) {
		if(depth==N) {
			int count=0;
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(matrix[i][j]!=0) {
						count++;
					}
				}
			}
			if(count<result) {
				result=count;
			}
			return;
		}
		
		for(int i=0;i<W;i++) {
			int[][] temp = copy(matrix);
			for(int j=0;j<H;j++) {
				if(temp[j][i]!=0) {
					q.offer(new Node(j,i));

					while(!q.isEmpty()) {
						Node ind = q.poll();
						int range = temp[ind.x][ind.y]-1;
						temp[ind.x][ind.y]=0;
						for(int h= ind.y-range<0?0:ind.y-range;h<=(ind.y+range>W-1?W-1:ind.y+range);h++) {
							if(temp[ind.x][h]==0)continue;
							else {
								q.offer(new Node(ind.x,h));
							}
						}
						for(int v= ind.x-range<0?0:ind.x-range;v<=(ind.x+range>H-1?H-1:ind.x+range);v++) {
							if(temp[v][ind.y]==0) continue;
							else {
								q.offer(new Node(v,ind.y));
							}
						}
					}
					//System.out.println("깊이 : "+depth);
					//for(int o=0;o<H;o++) {
					//for(int e=0;e<W;e++) {
					//System.out.print(temp[o][e]+" ");
					//}
					//
					//System.out.println();
					//}

					temp=clear_block(temp);
					//System.out.println("깊이 : "+depth+"clear");
					//for(int o=0;o<H;o++) {
					//for(int e=0;e<W;e++) {
					//System.out.print(temp[o][e]+" ");
					//}
					//System.out.println();
					//
					//}

					solution(temp,depth+1);
					break;
				}
			}
			if(i==9&&result==Integer.MAX_VALUE) {
				result=0;
			}
		}
	}
	
	static int[][] clear_block(int[][] matrix){
		for(int j=0;j<W;j++) {
			for(int i=H-2;i>-1;i--) {
				if(matrix[i][j]!=0) {
					int range = matrix[i][j];
					matrix[i][j]=0;
					int temp=i;
					while(true) {
						if(temp+1>=H||matrix[temp+1][j]!=0)break;
						temp++;
					}
					matrix[temp][j]=range;
				}
			}
		}
		return matrix;
	}
	
	static int[][] copy(int[][] matrix){
		int[][] arr = new int[H][W];
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				arr[i][j]=matrix[i][j];
			}
		}
		return arr;
	}
	
	static class Node{
		int x;
		int y;
		Node(int x,int y) {
			this.x=x;
			this.y=y;
		}
	}
}  
