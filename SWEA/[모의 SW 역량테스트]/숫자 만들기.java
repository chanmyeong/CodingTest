import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [문제 해석]
 * 연산자 4개
 * 
 * 연산자 우선순위 없음
 * 
 * 나눗셈 계산 시 소수점 이하는 버림
 * 
 * 연산 중의 값은 -1억 ~ 1억 범위를 벗어나지 않음 
 * 	=> int 써도 됨
 * 
 * 목표 : 
 * 주어진 연산자카드를 이용하여 수식을 만들었을 때, 최대, 최소 값의 차이를 구하기
 * 
 * [문제 해결을 위한 고민]
 * 
 * N-1개의 위치에 4개의 연산자를 넣어볼 수 있음
 * 
 * 중복 순열로 볼 수 있음.
 * 
 * N = 최대 12
 * 
 * 4^11 = 2^22 = 약 400만 문제 없음
 * 
 * 이 때, 4개의 연산자를 모두 넣어보는 것이 아닌 각 연산자마다 사용갯수가 정해짐
 * 	=> 연산자를 선택할 때마다 체크할 필요가 있음
 * 		연산자 선택 가능 여부, 연산자 선택 시 체크, 되돌리기
 * 
 * 결론
 * - 중복 순열에서 연산자의 사용가능여부에 따라 
 * 		소진된 연산자는 선택하지 않는(가지치기) 백트래킹 문제
 * 
 * [재귀 메소드 설계]
 * void bactracking ( int idx, int result)
 * idx : 연산자를 넣을 위치
 * result : 현재까지 연산한 결과
 * 
 * 반복파트
 * 1. idx 번째 위치에 넣을 연산자를 결정
 * 		연산자 결정 시, 고려사항
 * 			a. 소진되지 않은 연산자
 * 			b. 선택 시, 선택 여부 체크
 * 			c. 다음 연산자를 넣어보기위해 연산자 선택여부 되돌리기
 * 2. 다음(idx+1) 연산자 결정은 재귀로 넘김
 * 
 * 기저조건
 * idx==N-1
 * 	=> 모든 연산자 결정이 끝났을 때
 * 	- 연산 결과가 최댓값, 최솟값인 경우 갱신
 * 
 * @author minho
 *
 */
public class Solution_4008_숫자만들기 {
	static int N;

	//operators[0] : 사용가능한 (+)연산자의 갯수
	static int[] operators = new int[4];	

	static int[] numbers;

	static int max, min;	//최댓값, 최솟값
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) operators[i] = Integer.parseInt(st.nextToken());

			numbers= new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) numbers[i] = Integer.parseInt(st.nextToken());

			//초기화
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			backtracking(0, numbers[0]);

			sb.append('#').append(tc).append(' ').append(max-min).append('\n');
		}
		System.out.println(sb);
	}

	/**
	 * idx번째 위치에 들어갈 연산자를 결정하고 다음 연산자 결정은 재귀로 넘김
	 * @param idx 		현재 결정할 연산자의 위치 인덱스
	 * @param result	현재까지 결정된 연산자로 연산한 결과값
	 */
	private static void backtracking(int idx, int result) {
		//모든 연산자를 결정했을 때
		if(idx==N-1) {
			max = Math.max(max, result);	//수식의 결과가 최댓값이라면 갱신
			min = Math.min(min, result);	//수식의 결과가 최솟값이라면 갱신
			return;
		}

		for(int operator=0; operator<4; operator++) {	//연산자 선택
			if(operators[operator]==0) continue;	//연산자 사용불가

			operators[operator]--;	//연산자 사용여부체크

			backtracking( idx+1 , calculation(result, numbers[idx+1], operator));

			operators[operator]++;	//연산자 사용여부되돌리기
		}
	}

	/**
	 * x,y 연산 결과 반환
	 * @param x
	 * @param y
	 * @param operator
	 * @return
	 */
	private static int calculation(int x, int y, int operator) {
		if(operator==0) return x+y;
		else if(operator==1) return x-y;
		else if(operator==2) return x*y;
		else return x/y;
	}
}
