// [참고문헌]: https://data-make.tistory.com/185

import java.util.*;
import java.io.*;
/**
 * 최종적으로 보드에 자신의 돌이 많은 사람이 이김
 * 
 * 4x4, 6x6, 8x8
 * 동일하게 정가운데에 흰검흰검 배치하고 시작
 * 검 먼저 선공
 * 
 * 자신이 놓을 돌과 자신의 돌 사이(가로,세로,대각선)에 상대편의 돌이 있을 경우에만 그 곳에 돌을 놓을 수 있고,
 * 그 때의 상대편의 돌은 자신의 돌로 만들 수 있다.
 * 
 * 보드에 빈 곳이 없거나
 * 양 플레이어 모두 돌을 놓을 곳이 없으면
 * 게임 종료, 돌 개수 많은 사람이 승리
 * 
 * 보드의 한 변의 길이 N, N은 4, 6, 8 중 하나
 * 플레이어가 돌을 놓는 횟수 M
 * 
 * M줄에는 돌을 놓을 위치(r,c)와 돌의 색
 * 1 흑돌 2 백돌
 * 돌을 놓을 수 없는 곳은 입력으로 주어지지 않는다.
 * 
 * 게임이 끝난 후 보드 위의 흑돌, 백돌의 개수를 출력
 * 
 * 문제 해결 전략 :
 * 돌 놓기
 * 8방 탐색으로 돌 바꿀꺼 확인?
 * 최종 돌 개수 세기 
 */
public class Solution_4615_재미있는오셀로게임 {
	static int N,M,white,black;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/res/input_4615.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N+1][N+1]; // 1부터 시작

			map[N/2][N/2]=2;
			map[N/2][N/2+1]=1;
			map[N/2+1][N/2]=1;
			map[N/2+1][N/2+1]=2;			

			for(int m=0; m<M; m++) {
				st = new StringTokenizer(br.readLine()," ");
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int color = Integer.parseInt(st.nextToken());
				map[r][c]=color;
				//solve
				othello(r,c,color);
			}
//			for(int[] i : map) {
//				System.out.println(Arrays.toString(i));				
//			}
			white=0; black=0;
			
			// 최종 돌 개수 세기
			for(int[] tmp : map) {
				for(int i : tmp) {
					if(i==1) black++;
					else if(i==2) white++;
				}
			}

			sb.append("#"+t+" "+black+" "+white).append("\n");
		}
		System.out.println(sb);
	}
	private static void othello(int r, int c, int color) {
		// 8방 탐색으로 돌 바꾸기
		for(int dr=-1; dr<=1; dr++) {
			for(int dc=-1; dc<=1; dc++) {
				if(dr==0 && dc==0) continue; // 자기 자신 위치는 생략
				
				int nr = r+dr; // 돌 놓은 주변 돌 좌표
				int nc = c+dc;
				
				boolean check = false; // (nr,nc)가 자신의 돌인지 체크
				while(nr>=1&&nr<N+1&&nc>=1&&nc<N+1&&map[nr][nc]!=0) {
					if(map[nr][nc]==color) { // 주변 돌의 색깔이 놓은 돌과 일치한다면
						check = true;
						break;
					}
					// 일치하지 않는다면 놓은 돌 색깔과 일치한 색깔이 나올 때까지 탐색(사이의 돌들을 바꾸기 위해)
					nr+=dr;
					nc+=dc;
				}
				while(check) {
					if(nr==r&&nc==c) break; // 놓은 돌까지 왔으면 종료
					map[nr][nc]=color; // 사이의 돌들을 놓은 돌 색깔로 변경
					nr-=dr;
					nc-=dc;
				}
			}
		}
	}
}
