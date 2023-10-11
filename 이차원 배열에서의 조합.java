package test;
import java.util.Arrays;

public class Combination_이차원배열 {

	static int R = 3;	//행
	static int C = 4;	//열
	
	static int[][] arr = {
			{1,2,3,4},
			{5,6,7,8},
			{9,10,11,12}
	};
	
	static int K=3;		//뽑는 개수
	
	static int[] picked = new int[K];	//뽑은 요소
	
	public static void main(String[] args) {
		combination(0, 0, 0);		
	}
	
	private static void combination(int cnt, int startR, int startC) {
		if(cnt==K) {
			System.out.println(Arrays.toString(picked));
			return;
		}
		
		//유도파트 
		//2차원 배열의 요소중 하나 뽑고 다음 요소 뽑기는 재귀로 넘기기
		for(int i=startR; i<R; i++) {
			for(int j= i==startR? startC : 0; j<C; j++) {
				
				picked[cnt] = arr[i][j];	//요소 뽑기
				
				//다음요소 뽑기는 재귀로 넘김
				if(j==C-1)  combination(cnt+1, i+1, 0);	//현재 뽑은 요소가 마지막 열인 경우
				else combination(cnt+1, i, j+1);			
			}
		}
	}
}
====================================================================================================
package test;
import java.util.Arrays;

public class Combination_이차원배열_1차원배열처럼사용 {

	static int R = 3;	//행
	static int C = 4;	//열
	
	static int[][] arr = {
			{1,2,3,4},
			{5,6,7,8},
			{9,10,11,12}
	};
	
	static int K=3;		//뽑는 개수
	
	static int[] picked = new int[K];	//뽑은 요소
	
	public static void main(String[] args) {
		combination(0, 0);
	}
	
	private static void combination(int cnt, int start) {
		if(cnt==K) {
			System.out.println(Arrays.toString(picked));
			return;
		}
		
		//유도파트 요소 뽑기
		for(int idx=start; idx<R*C; idx++) {
			//좌표 뽑아오기
			int r = idx/C;
			int c = idx%C;
			
			//요소 뽑기
			picked[cnt] = arr[r][c];
			
			//다음 요소뽑기
			combination(cnt+1, idx+1);
		}
	}
}
