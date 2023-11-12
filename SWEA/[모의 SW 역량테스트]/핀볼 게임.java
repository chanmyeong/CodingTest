import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 0빈공간
 * 1좌하벽삼각형 2좌상벽삼각형 3상우벽삼각형 4우하벽삼각형 5사각형
 * 6~10웜홀 -1블랙홀
 * 
 * 핀볼은 웜홀 또는 블랙홀을 만나지 않는 한 현재 방향을 유지하면서 계속 직진
 * 블록의 수평면 또는 벽 -> 반대 방향으로 돌아오기
 * 블록의 수직면 -> 직각으로 진행방향이 꺾임
 * 웜홀에 빠지면 진행 방향 유지한 채 동일한 숫자의 반대편 웜홀로 나옴
 * 블랙홀을 만나면 게임 종료 
 * 핀볼이 출발 위치로 돌아오거나 블랙홀에 빠지면 게임 종료
 * 
 * 점수 : 벽이나 블록에 부딪힌 횟수(웜홀 제외)
 * 게임판 위에서 출발위치와 진행방향을 임의로 선정 가능 할 때(블록, 웜홀, 블랙홀 제외)
 * 게임에서 얻을 수 있는 점수의 최댓값 구하기
 * 
 * 게임판 한 변의 길이 (5 ≤ N ≤ 100)
 * 게임판에서 웜홀 또는 블랙홀이 존재하지 않는 경우도 존재
 * 웜홀이 주어지는 경우 최대 5쌍 존재
 * 블랙홀은 최대 5개
 * 
 * 문제 해결 전략 :
 * 1. 빈 공간 0에서 임의의 진행방향 가지고 시작
 * 2. 각 블록의 특성에 맞게 게임 진행하면서 점수 저장
 *   - 처음 위치 저장, 방향 저장 -> 방향은 계속 바뀔 듯
 * 3. 처음 위치 돌아오거나 블랙홀 만나면 게임 종료
 * 4. 점수 최댓값 갱신
 */

/**
 * N*N
 * 
 * 핀볼게임판 형태 정사강형 블록 4가지 형태의 삼각형 블록 웜홀, 블랙홀 1 : 왼쪽아래 삼각형 2 : 오른쪽위 삼각형 3 : 왼쪽위 삼각형
 * 4 : 오른쪽아래 삼각형 5 : 정사각형 6~10 : 웜홀 -1 : 블랙홀
 * 
 * 핀볼하나가 상하좌우로 움직임 직진 - 블록, 웜홀, 블랙홀을 만나지 않으면 직진
 * 
 * 반대방향 - 블록의 수평면이나 수직면을 만날 경우 반대방향으로 바뀜 - 벽을 만나는 경우 방향이 반대로 꺾임.
 * 
 * 직각 - 블록의 경사면을 만날 경우엔 직각으로 진행방향이 꺾임.
 * 
 * //블록을 만나는 모든 경우의 수 진행중이던 방향 다음 위치의 블록 상(0) 1, 4 => 하(2) 상(0) 2 => 우(1) 상(0) 3
 * => 좌(3)
 * 
 * 우(1) 1,2 => 좌(3) 우(1) 3 => 하(2) 우(1) 4 => 상(0)
 * 
 * 하(2) 2,3 => 상(0) 하(2) 1 => 우(1) 하(2) 4 => 좌(3)
 * 
 * 좌(3) 3,4 => 우(1) 좌(3) 1 => 상(0) 좌(3) 2 => 하(2)
 * 
 * 웜홀 - 동일한 숫자를 가진 다른 반대편 웜홀로 빠져 나옴 - 진행방향은 그대로 유지됨 - 웜홀은 반드시 쌍으로 주어짐
 * 
 * 맵에 <Integer, int[2][2] > 형태로 저장해놓기 웜홀을 발견하는 경우 일단 이차원 배열을 꺼내와서 r==int[0][0]
 * && c==int[0][1] 과 일치한다면 r = int[1][0] c = int[1][1] 로 변경 일치하지 않는 다면 r =
 * int[0][0]; c = int[0][1]; 로 변경
 * 
 * 
 * 블랙홀 - 게임 종료
 * 
 * 
 * 반대로 꺾이는 상황을 0 : 상, 1: 우, 2:하, 3: 좌 반대방향 = (원래 방향 +2)%4 으로 구할 수 있을 듯.
 * 
 * 점수 - 벽 또는 블록에 부딪힌 횟수
 * 
 * 핀볼 출발 위치와 진행 방향을 임의로 설정했을 떄 게임에서 얻을 수 있는 점수의 최댓값을 구하기 ( 블록, 웜홀 블랙홀의 위치에선 출발
 * 불가능 )
 * 
 * 
 * 문제 해결 프로세스 1. 핀볼의 위치와 방향 정하기 - 0인 경우를 출발지로 설정하고 4방향을 모두 시도해보기
 * 
 * 2. 핀볼을 현재 위치에서 해당 방향으로 한칸씩 이동
 * 
 * 3-1. 블록의 수평면이나 벽을 만나는 경우 - 점수 cnt - 핀볼의 방향을 반대방향으로 전환 => 반대방향 = (원래 방향 +2)%4
 * 
 * 3-2. 블록의 경사면을 만나는 경우 - 점수 cnt - 핀볼의 방향을 직각으로 전환
 * 
 * 3-3. 웜홀을 만나는 경우 - 해당 웜홀의 짝이 되는 웜홀로 핀볼의 위치를 변경 맵에 <Integer, int[2][2] > 형태로
 * 저장해놓기 웜홀을 발견하는 경우 일단 이차원 배열을 꺼내와서 r==int[0][0] && c==int[0][1] 과 일치한다면 r =
 * int[1][0] c = int[1][1] 로 변경 일치하지 않는 다면 r = int[0][0]; c = int[0][1]; 로 변경
 * 
 * 
 * 3-4. 블랙홀을 만나는 경우 - 현재 위치에서의 게임을 종료 - 현재 점수가 최댓값이라면 갱신
 * 
 * 시작점을 만나도 게임이 종료
 * 
 * @author minho
 *
 */
public class Solution_5650_핀볼게임 {
    // 상 우 하 좌
    final static int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
 
    // 상 우 하 좌
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
 
    static int N; // 게임판 변의 길이
 
    static int[][] map; // 게임판
 
    static int[][][] wormhole = new int[5][2][2];
 
    static int maxScore; // 해당 게임판에서 얻을 수 있는 최대 점수
 
    public static void main(String[] args) throws NumberFormatException, IOException {
//      System.setIn(new FileInputStream("핀볼게임_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
 
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            maxScore = 0; // 최대 점수 초기화
 
            N = Integer.parseInt(br.readLine().trim());
 
            for(int i=0; i<5; i++) {
                wormhole[i][0][0] = -1;
                wormhole[i][0][1] = -1;
                wormhole[i][1][0] = -1;
                wormhole[i][1][1] = -1;
            }
 
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 웜홀을 발견한 경우
                    if (6 <= map[i][j] && map[i][j] <= 10) {
                        int[][] hole = wormhole[map[i][j] - 6];
                        // 첫번째 정보가 비어있다면 첫번째에
                        if (hole[0][0] == -1) {
                            hole[0][0] = i;
                            hole[0][1] = j;
                        }
                        // 첫번째 정보가 들어있다면 두 번째에
                        else {
                            hole[1][0] = i;
                            hole[1][1] = j;
                        }
                    }
                }
            }
            // step 01. 핀볼의 위치와 방향 정하기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // 빈 칸인 경우 해당 위치와 방향을 정해 게임시작
                    if (map[r][c] == 0) {
                        for (int dir = 0; dir < 4; dir++) {
                            gameStart(r, c, dir);
                        }
                    }
                }
            }
            sb.append('#').append(tc).append(' ').append(maxScore).append('\n');
        }
 
        System.out.println(sb);
 
    }
 
    /**
     * 시작 위치를 (r,c)로 하고 방향이 dir인 핀볼 게임
     * 
     * @param r
     * @param c
     * @param dir
     */
    private static void gameStart(int sr, int sc, int dir) {
        int score = 0;
        int r = sr;
        int c = sc;
        // step 02. 핀볼을 현재 위치에서 해당 방향으로 한칸씩 이동
//      System.out.println("시작");
        while (true) {
 
//          print(r, c, score);
 
            // 다음 위치 좌표
            int nr = r + dr[dir];
            int nc = c + dc[dir];
 
            // step 03-1. 벽을 만나거나 정사각형 블록인 경우
            if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 5) {
                score++; // 점수 cnt
 
                // 반대 방향 전환
                dir = getReverseDir(dir);
            }
            // step 03-2. 삼각형 블록을 만나는 경우
            else if (1 <= map[nr][nc] && map[nr][nc] <= 4) {
                score++; // 점수 cnt
                // 방향 전환 후
                dir = getBlockDir(dir, map[nr][nc]);
            }
            // step 03-3. 웜홀을 만나는 경우
            else if (6 <= map[nr][nc] && map[nr][nc] <= 10) {
                // 웜홀에 들어갔을 때 나와야할 위치정보 가져오기
                int[] position = getPosition(nr, nc);
                // 해당 위치로 변경 후, 다시 이동
                r = position[0];
                c = position[1];
                continue;
            }
            // step 03-4. 시작점에 도착하거나 블랙홀을 만나는 경우
            else if ((sr == nr && sc == nc) || map[nr][nc] == -1) {
                // 현재 점수가 최대라면 갱신
                maxScore = Math.max(maxScore, score);
                return; // 게임 종료
            }
 
            // 이동
            r = nr;
            c = nc;
        }
    }
 
    private static void print(int r, int c, int score) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == r && j == c)
                    System.out.printf("%3s", "X");
                else
                    System.out.printf("%3d", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
 
    }
 
    /**
     * 웜홀의 반대편 좌표 정보 반환
     * 
     * @param r
     * @param c
     * @return
     */
    private static int[] getPosition(int r, int c) {
        int[][] hole = wormhole[map[r][c] - 6];
 
        // 0번에 있는 웜홀 좌표와 일치한다면 1번 좌표를 반환
        if (hole[0][0] == r && hole[0][1] == c) {
            return hole[1];
        }
        // 1번 좌표와 일치한다면 0번 좌표를 반환
        else
            return hole[0];
    }
 
    private static int getBlockDir(int dir, int blockNum) {
         
        //반시계 
        //(dir+3) % 4 == blockNum %4
         
        //시계
        //(dir+2) % 4 == blockNum %4
         
        //그외는 반대
//      blockNum %= 4;
//      if((dir+3)%4 == blockNum) return dir==0? dir=3 : dir-1;
//      else if((dir+2)%4==blockNum) return ++dir % 4;
//      else return getReverseDir(dir);
//      상(0) 1, 4 => 하(2)
//      상(0) 3 => 좌(3) //반시계 90도 
//      상(0) 2 => 우(1)   //시계 90도
//      
//      우(1) 1,2 => 좌(3)
//      우(1) 4 => 상(0)   //반시계 90도
//      우(1) 3 => 하(2)   //시계 90도
//      
//      하(2) 2,3 => 상(0)
//      하(2) 1 => 우(1)   //반시계 90도
//      하(2) 4 => 좌(3)   //시계 90도
//      
//      좌(3) 3,4 => 우(1)
//      좌(3) 2 => 하(2)   //반시계 90도
//      좌(3) 1 => 상(0)   //시계 90도
        if (dir == UP) {
            if (blockNum == 1 || blockNum == 4)
                return DOWN;
            else if (blockNum == 2)
                return RIGHT;
            else
                return LEFT;
        } else if (dir == RIGHT) {
            if (blockNum == 1 || blockNum == 2)
                return LEFT;
            else if (blockNum == 3)
                return DOWN;
            else
                return UP;
        } else if (dir == DOWN) {
            if (blockNum == 2 || blockNum == 3)
                return UP;
            else if (blockNum == 1)
                return RIGHT;
            else
                return LEFT;
        } else {
            if (blockNum == 3 || blockNum == 4)
                return RIGHT;
            else if (blockNum == 1)
                return UP;
            else
                return DOWN;
        }
    }
 
    /**
     * dir의 반대 방향 정보 반환
     * 
     * @param dir
     * @return
     */
    private static int getReverseDir(int dir) {
    	//return (dir+2)%4;
        if(dir==0) return 2;
        else if(dir==1) return 3;
        else if(dir==2) return 0;
        else return 1;
    }
 
}
====================================================================================================
// [참고문헌]: https://excited-hyun.tistory.com/238

import java.util.Scanner;
import java.util.*;

public class Solution {
  // 충돌 전 방향과 충돌 후 방향이 어떻게 변하는지를 미리 생각해두고 문제를 해결
	static int[][] dirChange = {{0, 0, 0, 0}, {1, 2, 3, 0}, {1, 3, 0 ,2}, {3, 0, 1, 2}, {2, 0, 3, 1}, {1, 0, 3, 2}};
	static int[] moveR = {0, 0, -1, 1};
	static int[] moveC = {1, -1, 0, 0};
	
	static int[][] map;
  // 웜홀의 경우는 ArrayList배열을 만들어 같은 숫자에 해당하는 위치들을 해당 인덱스에 저장해 두고 사용
	static ArrayList<Pos>[] wormhole;
	
	static int N;
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc = 1; tc<= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			wormhole = new ArrayList[5];
			
			for(int i=0; i<5; i++) {
				wormhole[i] = new ArrayList<>();
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] >= 6) {
						wormhole[map[i][j]-6].add(new Pos(i, j));
					}
				}
			}
			
			
			answer = 0;
		
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]!=0)
						continue;
					for(int dir=0; dir<4; dir++) {
						
						int score = check(i, j, dir);
						if(score > answer)
							answer = score;
						
					}
				}
			}
			
			System.out.println("#"+tc+" "+answer);
		}

	}
	
	static int check(int r, int c, int dir) {
		int score = 0;
		
		int nowR = r;
		int nowC = c;
		int move = 0;
		
		while(true) {
			
			//제자리로 
			if(nowR == r && nowC == c && move !=0) 
				break;
			
			//블랙홀
			if(map[nowR][nowC] == -1)
				break;
			
			nowR = nowR + moveR[dir];
			nowC = nowC + moveC[dir];
			move++;
			
			
			//벽에 부딪힘 
			if(nowR<0 || nowR>=N || nowC<0 || nowC>=N) {
				nowR = nowR - moveR[dir];
				nowC = nowC - moveC[dir];
				
				if(dir == 0)
					dir = 1;
				else if(dir == 1)
					dir = 0;
				else if(dir == 2)
					dir = 3;
				else if(dir == 3)
					dir = 2;
				
				score++;
				
			}
			
			//블록에 부딪힘 
			if(map[nowR][nowC] >= 1 && map[nowR][nowC] <=5) {
				int block = map[nowR][nowC];
				
				dir = dirChange[block][dir];
				score++;
				continue;
			}
			
			//웜홀
			if(map[nowR][nowC] > 5) {
				int block = map[nowR][nowC];
				int r1 = wormhole[block-6].get(0).r;
				int c1 = wormhole[block-6].get(0).c;
				
				int r2 = wormhole[block-6].get(1).r;
				int c2 = wormhole[block-6].get(1).c;
				
				if(nowR == r1 && nowC == c1) {
					nowR = r2;
					nowC = c2;
				}
				else {
					nowR = r1;
					nowC = c1;
				}
			}
			
			
		}
		
		return score;
	}
	
	static class Pos {
		int r, c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
		
	}

}
