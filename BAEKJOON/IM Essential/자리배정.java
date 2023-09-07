import java.util.*;
import java.io.*;

/*
 * 가로로 C개, 세로로 R개의 좌석이 C×R격자형으로 배치
 * 각 좌석의 번호는 해당 격자의 좌표 (x,y)로 표시
 * (1,1)위치 좌석부터 시작하여 시계방향으로 돌아가면서 비어있는 좌석에 관객을 순서대로 배정
 * 대기 순서가 K인 관객에게 배정될 좌석 번호 (x,y)를 찾는 프로그램
 *
 * 모든 좌석이 배정되어 해당 대기번호의 관객에게 좌석을 배정할 수 없는 경우 0(숫자 영)을 출력
 * 5 ≤ C, R ≤ 1,000
 * 관객의 대기번호 K가 주어진다. 단 1 ≤ K ≤ 100,000,000
 *
 * 문제 해결 전략 :
 * 4방탐색, 경계값 닿으면 배열 방향 변경
 */
public class Main {
    static int[] dr = {1,0,-1,0}; // 하우상좌
    static int[] dc = {0,1,0,-1};
    static int[][] map;
    static boolean[][] visited;
    static int R,C,K;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        C = Integer.parseInt(line[0]);
        R = Integer.parseInt(line[1]);
        K = Integer.parseInt(br.readLine());

        map = new int[R][C];
        visited = new boolean[R][C];
        if(C*R<K) { // 모든 좌석이 배정되어 좌석 배정 불가
            System.out.println(0);
        }
        else {
            int num = 1; // 좌석 시작 번호
            int r=0, c=0;
            int nd = 0;
            int nr=0, nc=0;
            while(num<K) {
                map[r][c]=num;
                visited[r][c]=true;
                nr = r + dr[nd];
                nc = c + dc[nd];
                if(isOutOfRange(nr,nc)||visited[nr][nc]) { // 경계값을 만나거나 이미 방문했으면
                    nd = (nd+1)%4; // 방향전환 + 4방탐색 배열의 순환
                    nr = r + dr[nd];
                    nc = c + dc[nd];
                }
                r=nr;
                c=nc;
                num++;
            }
            System.out.println((c+1)+" "+(r+1)); // 문제에서는 1부터 시작
        }
    }
    private static boolean isOutOfRange(int nr, int nc) {
        return 0>nr || nr>=R || 0>nc || nc>=C;
    }
}
====================================================================================================
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(br.readLine());

		int x = 1;
		int y = 1;

		if (k > c * r) {
			System.out.println("0");
			return;
		}

		while (true) {
			k -= (2 * c + 2 * r - 4); // 시계방향 동심원
			if (k <= 0) {
				k += (2 * c + 2 * r - 4);
				break;
			}
			c -= 2;
			r -= 2;
			x++;
			y++;
		}

		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };

		k--;

		int initX = 0;
		int initY = 0;

		for (int i = 0; i < 4; i++) {
			while (k > 0) {
				int nx = initX + dx[i];
				int ny = initY + dy[i];
				if (nx < 0 || nx >= c || ny < 0 || ny >= r)
					break;
				k--;
				initX = nx;
				initY = ny;
			}
		}

		System.out.println((x + initX) + " " + (y + initY));

	}

}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int C = Integer.parseInt(st.nextToken()), R = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		int cnt = 0, i = 1, j = 1;

		if (K > C * R)
			System.out.println(0);
		else {
			while (C != 0 && R != 0) {
				if (K <= cnt + 2 * R + 2 * C - 4) {
					if (K - cnt <= R)
						j += K - cnt - 1;
					else if (K - cnt <= C + R - 1) {
						i += K - cnt - R;
						j += R - 1;
					} else if (K - cnt <= C + 2 * R - 2) {
						i += C - 1;
						j += (2 * R + C - 2) - (K - cnt);
					} else {
						i += (2 * R + 2 * C - 4) - (K - cnt) + 1;
					}
					break;
				}
				cnt += 2 * R + 2 * C - 4;
				i++;
				j++;
				C -= 2;
				R -= 2;
			}
			System.out.println(i + " " + j);
		}
	}
}
