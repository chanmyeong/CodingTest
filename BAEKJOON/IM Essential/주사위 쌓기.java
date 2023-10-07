import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 주사위의 모양은 모두 크기가 같은 정육면체
 * 보통 주사위처럼 마주 보는 면에 적혀진 숫자의 합이 반드시 7이 되는 것은 아니다.
 * 
 * 주사위 쌓기 놀이는 아래에서부터 1번 주사위, 2번 주사위, 3번 주사위, … 의 순서로 쌓는 것
 * 두 개의 주사위에서 아래에 있는 주사위의 윗면에 적혀있는 숫자는 위에 있는 주사위의 아랫면에 적혀있는 숫자와 같아야 한다.
 * 1번 주사위 윗면의 숫자는 2번 주사위 아랫면의 숫자와 같고, 2번 주사위 윗면의 숫자는 3번 주사위 아랫면의 숫자와 같아야 한다.
 * 
 * 사각 기둥에는 4개의 긴 옆면이 있다.
 * 이 4개의 옆면 중에서 어느 한 면의 숫자의 합이 최대가 되도록 주사위를 쌓고자 한다.
 * 한 옆면의 숫자의 합의 최댓값을 구하는 프로그램
 * 
 * 첫줄에는 주사위의 개수가 입력
 * 그 다음 줄부터는 한 줄에 하나씩 주사위의 종류가 1번 주사위부터 주사위 번호 순서대로 입력
 * 
 * 문제 해결 전략 :
 * 아래-위, 오-왼, 앞-뒤로 주사위 재배열
 * 가장 최하위의 주사위의 윗면을 정한 완전탐색
 * 나머지 4면은 주사위를 돌려서 면을 맞출 수 있으므로 주사위들의 아래-위면만 신경쓰면 된다.
 */

// 재귀
public class Main {
	static int N, Max;
	static int[][] dice;
	static int[] op = { 5, 3, 4, 1, 2, 0 }; //각 마주보는 면의 인덱스를 저장, if문을 쓰더라도 마주보는 면 구해주기
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 주사위 개수
 
		dice = new int[N][6];
		
		for (int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			 for (int j = 0; j < 6; j++) {
				 dice[i][j] = Integer.parseInt(st.nextToken());
			 }
		}
		Max = -1;
		//윗면정하기
		for(int i=0; i<6; i++) {
			int max = 0;
			for(int j=0; j<6; j++) { //옆면 중에서 가장 큰 값
				if(j==i||j==op[i]) continue; // 윗면과 그 마주보는 면 제외한 4면
				max = Math.max(max, dice[0][j]);
			}
			up(dice[0][i], max, 1); 
		}
		System.out.println(Max);
	}
	private static void up(int top, int sum, int cnt) { // 윗면의 값, 옆면 중에서 가장 큰 값, 현재까지 올린 주사위 개수
		if(cnt == N) {
			Max = Math.max(sum, Max);
			return;
		}
		// 현재 주사위에서 top에 해당하는 인덱스를 찾아야함
		int idx=0;
		for(int i=0; i<6; i++) { // 이전 주사위의 탑에 해당하는 값을 현재 주사위에서 찾기
			if(dice[cnt][i]==top) {
				idx=i;
				break;
			}
		}
		int nextTop = op[idx]; // 현재 주사위 윗면의 인덱스 결정
		int m=0;
		for(int j=0; j<6; j++) { // 옆면 중에서 가장 큰 값
			if(j==nextTop || j==idx) continue; // 윗면과 그 마주보는 면 제외한 4면
			m = Math.max(m, dice[cnt][j]);
		}
		up(dice[cnt][nextTop], sum+m, cnt+1);	
	}

}
====================================================================================================
// 3중 for문 완전탐색
import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] dices;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        dices = new int[N][6];

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<6; c++){
                dices[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }


        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 5);
        map.put(1,3);
        map.put(2,4);
        map.put(3,1);
        map.put(4,2);
        map.put(5,0);

        int max = Integer.MIN_VALUE;
        // 맨 밑 숫자 고르기
        for(int i=0; i<6; i++){
            int sum =0;
            int bottom = i;

            // 주사위 수만큼 반복
            for(int n=0; n<N; n++){
                int top = map.get(bottom);
                
                int side_max = Integer.MIN_VALUE;
                // 옆면 중 가장 큰 수 고르기
                for(int j=0; j<6; j++){
                    if(j == bottom || j == top) continue;

                    side_max = Math.max(side_max, dices[n][j]);
                }

                // sum에 더하기
                sum += side_max;

                // 그 다음 주사위의 바닥 값 = 현재 주사위의 맨 위 값
                // 바닥값의 인덱스 찾기
                if(n < N-1){
                    for(int index =0; index<6; index++){
                        if(dices[n][top] == dices[n+1][index]){
                            bottom = index;
                            break;
                        }
                    }

                }
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
