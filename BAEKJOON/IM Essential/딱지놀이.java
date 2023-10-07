import java.io.*;
import java.util.*;

/**
 * 별 > 동그라미 > 네모 > 세모의 개수 순서로 딱지 놀이의 승자 판단
 * 4개 전부 개수가 같을 시 무승부
 * 별, 동그라미, 네모, 세모를 각각 숫자 4, 3, 2, 1로 표현
 * 
 * N 은 1 이상 1,000 이하, A-B순서
 * 1<=a,b<=100 
 * 문제 해결 전략 : 배열의 인덱스를 딱지 번호로 설정
 */

public class Main {
	static int[] A,B;
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[5];
		B = new int[5];
		
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int aNum = Integer.parseInt(st.nextToken());
			for(int a=0; a<aNum; a++) {
				A[Integer.parseInt(st.nextToken())]++;
			}
			st = new StringTokenizer(br.readLine(), " ");
			int bNum = Integer.parseInt(st.nextToken());
			for(int b=0; b<bNum; b++) {
				B[Integer.parseInt(st.nextToken())]++;
			}
//			System.out.println(Arrays.toString(A));
//			System.out.println(Arrays.toString(B));
//			System.out.println("=====");

			//A,B 승부
			for(int i=4; i>=1; i--) {
				if(A[i]>B[i]) {
					sb.append("A").append("\n");
					break;
				} else if(A[i]<B[i]) {
					sb.append("B").append("\n");
					break;
				} else {
					if(i==1) {
						sb.append("D").append("\n");
						break;
					}
					continue;
				}
			}
			// A,B 초기화
			Arrays.fill(A, 0);
			Arrays.fill(B, 0);
		}
		System.out.println(sb);
	}
}
