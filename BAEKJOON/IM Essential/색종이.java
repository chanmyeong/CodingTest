import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 즉, 모든 색종이의 변은 서로 평행하거나, 서로 수직이거나 둘 중 하나
 * N장의 색종이가 주어진 위치에 차례로 놓일 경우, 각 색종이가 보이는 부분의 면적을 구하는 프로그램
 * 
 * 색종이의 장수를 나타내는 정수 N (1 ≤ N ≤ 100)
 * 색종이가 놓이는 평면은 가로 최대 1001칸, 세로 최대 1001칸으로 구성된 격자 모양
 * 색종이가 놓인 상태는 가장 왼쪽 아래 칸의 번호와 너비, 높이를 나타내는 네 정수로 표현
 * 
 * 입력에서 주어진 순서대로 각 색종이가 보이는 부분의 면적을 한 줄에 하나씩 하나의 정수로 출력한다.
 * 만약 색종이가 보이지 않는다면 정수 0을 출력
 * 
 * 문제 해결 전략 :
 * 색종이를 계속 덮어쓰기, 가로 = 열(C), 세로 = 행(R)
 */
public class Main_BJ_10163_색종이_우찬명 {
	static int N;
	static int[][] map = new int[1001][1001];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int n=1; n<=N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int C = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			
			for(int r=R; r<R+H; r++) {
				for(int c=C; c<C+W; c++) {
					map[r][c] = n; 
				}
			}
		}
//		for(int i=0; i<map.length; i++) {
//			for(int j=0; j<map[i].length; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		for(int n=1; n<=N; n++) {
			int area = 0;
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[i].length; j++) {
					if(map[i][j]==n) {
						area++;
					}
				}
			}
			sb.append(area+"\n");
		}
		
		System.out.println(sb);
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    int[][] world = new int[1001][1001];

    for (int i = 1; i <= T; i++) {
      String[] xyrc = br.readLine().split(" ");
      int x = Integer.parseInt(xyrc[0]);
      int y = Integer.parseInt(xyrc[1]);
      int r = Integer.parseInt(xyrc[2]);
      int c = Integer.parseInt(xyrc[3]);

      for (int j = x; j < x + r; j++) {
        for (int k = y; k < y + c; k++) {
          world[j][k] = i;
        }
      }
    }

    int[] answer = new int[T + 1]; // 정답을 담을 1차원 배열 자료구조 생성

    for (int i = 0; i < 1001; i++) {
      for (int j = 0; j < 1001; j++) {
        answer[world[i][j]]++;
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < T + 1; i++) {
      sb.append(answer[i]).append('\n');
    }

    System.out.print(sb.deleteCharAt(sb.length() - 1));
  }
}
