import java.util.*;
import java.io.*;

/*
 * M×N 크기의 보드, 어떤 정사각형은 검은색으로 칠해져 있고, 나머지는 흰색
 * 이 보드를 잘라서 8×8 크기의 체스판 만들기
 * 체스판은 검은색과 흰색이 번갈아서, 변을 공유하는 두 개의 사각형은 다른 색으로
 * 체스판을 색칠하는 경우는 두 가지뿐이다. 하나는 맨 왼쪽 위 칸이 흰색인 경우, 하나는 검은색인 경우
 * 보드가 체스판처럼 칠해져 있다는 보장이 없어서 8*8 크기는 아무데서나 골라도 된다.
 * 지민이가 다시 칠해야 하는 정사각형의 최소 개수
 * N과 M은 8보다 크거나 같고, 50보다 작거나 같은 자연수
 *
 * 문제 해결 전략 :
 * [참고문헌]: https://st-lab.tistory.com/101
 * 최소 크기가 8×8 일 때 경우의 수가 1이기 때문에
 * 경우의 수는 (가로 칸 개수 - 7) × (세로 칸 개수 - 7)
 * 기본적으로 체스판은 상하좌우가 교대로 색이 배치된다.
 * 이 때 첫 칸이 검은색일 경우의 올바르게 색칠하기 위해 변경되어야 할 색들의 개수와
 * 첫 칸이 하얀색일 경우의 올바르게 색칠하기 위해 변경되어야 할 색들의 개수를 비교
 * 총 경우의 수 = 2 × (가로 칸 개수 - 7) × (세로 칸 개수 - 7)
 * boolean 2차원 배열, W 일 때는 true, B 일때는 false
 */
public class Main {

    public static boolean[][] arr;
    public static int min = 64;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new boolean[N][M];

        // 배열 입력
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'W') {
                    arr[i][j] = true;		// W일 때는 true
                } else {
                    arr[i][j] = false;		// B일 때는 false
                }
            }
        }

        int N_row = N - 7;
        int M_col = M - 7;

        for (int i = 0; i < N_row; i++) {
            for (int j = 0; j < M_col; j++) {
                find(i, j);
            }
        }
        System.out.println(min);
    }

    public static void find(int x, int y) {
        int end_x = x + 8;
        int end_y = y + 8;
        int count = 0;

        boolean TF = arr[x][y];	// 첫 번째 칸의 색

        for (int i = x; i < end_x; i++) {
            for (int j = y; j < end_y; j++) {

                // 올바른 색이 아닐경우 count 1 증가
                if (arr[i][j] != TF) {
                    count++;
                }
              
                /*
                 * 다음 칸은 색이 바뀌므로
                 * true라면 false로, false 라면 true 로
                 * 값을 변경한다.
                 */
                TF = (!TF);
            }
            TF = !TF;
        }
      
        /*
         *  첫 번째 칸을 기준으로 할 때의 색칠 할 개수(count)와
         *  첫 번째 칸의 색의 반대되는 색을 기준으로 할 때의
         *  색칠 할 개수(64 - count) 중 최솟값을 count 에 저장
         */
        count = Math.min(count, 64 - count);

        /*
         * 이전까지의 경우 중 최솟값보다 현재 count 값이
         * 더 작을 경우 최솟값을 갱신
         */
        min = Math.min(min, count);
    }
}
====================================================================================================
import java.io.*;
import java.util.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M], sum = new int[N][M-7];
		for(int i=0 ; i<N ; i++) {
			String line = br.readLine();
			for(int j=0 ; j<M ; j++)
				map[i][j] = line.charAt(j)=='W' ? (i+j)%2 : (i+j+1)%2;
		}
		for(int i=0; i<N; i++)
			for(int j=0 ; j<M-7 ; j++)
				for(int k=j ; k<j+8 ; k++)
					sum[i][j] += map[i][k];
		int min=64, num;
		for(int x=0 ; x<N-7 ; x++)
			for(int y=0 ; y<M-7 ; y++) {
				num=0;
				for(int i=x ; i<x+8 ; i++)
					num += sum[i][y];
				if(num>32) num = 64-num;
				min = min>num ? num : min;
			}
		System.out.print(min);
	}
}

====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int check(char[][] tree, int si, int sj){
		int count = 0;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(tree[i + si][j + sj] != ((i + j) % 2 == 0 ? 'W' : 'B'))
					count++;
			}
		}
		
		return Math.min(count, 64 - count);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		char[][] tree = new char[N][M];
		int result = 64;
		
		for(int i = 0; i < N; i++){
			String str = br.readLine();
			
			for(int j = 0; j < M; j++){
				tree[i][j] = str.charAt(j);
			}
		}
		
		for(int i = 0 ; i <= N - 8; i++){
			for(int j = 0; j <= M - 8; j++){
				result = Math.min(result, check(tree, i, j));
			}
		}
		
		System.out.println(result);
	}
}
