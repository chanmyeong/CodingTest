import java.util.*;
import java.io.*;

/*
 * 자연수 N의 분해합은 N과 N을 이루는 각 자리수의 합을 의미
 * 어떤 자연수 M의 분해합이 N인 경우, M을 N의 생성자라 한다.
 * 예를 들어, 245의 분해합은 256(=245+2+4+5)이 된다. 따라서 245는 256의 생성자
 * 물론, 어떤 자연수의 경우에는 생성자가 없을 수도 있다. 반대로, 생성자가 여러 개인 자연수도 있을 수 있다.
 * 자연수 N이 주어졌을 때, N의 가장 작은 생성자를 구해내는 프로그램 N(1 ≤ N ≤ 1,000,000)
 * 생성자가 없는 경우에는 0을 출력
 *
 * 문제 해결 전략 :
 * 생성자가 가장 작으려면 첫째 자리부터 오름차순
 */
public class Main {
    static int N;
    static int answer = 0; // 생성자가 없는 경우에 대한 초기화

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine(); // 자릿수를 알기 위해 string 먼저 입력받기
        N = Integer.parseInt(num);

        int substractNum=0;
        for(int i=1*num.length(); i<=9*num.length(); i++) { // 뺄 수 있는 수의 범위만큼 완전 탐색
            substractNum = N-i;
            divideSum(substractNum, substractNum);
        }
        System.out.println(answer);
    }
    private static void divideSum(int substractNum, int m) {
        int sum = 0; // 생성자에 대한 각 자릿수의 합
        while(m!=0) {
            sum += m%10;
            m/=10;
        }
        if(sum+substractNum == N) { // 생성자라면
            answer = substractNum;
        }
    }
}
====================================================================================================
// [참고문헌]: https://st-lab.tistory.com/98
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {
    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
		// 자릿수의 길이를 알기위해 일단 문자열로 입력받는다.
		String str_N = br.readLine();
 
		// 해당 문자열의 길이 변수
		int N_len = str_N.length();
 
		// 문자열을 정수(int)로 변환 
		int N = Integer.parseInt(str_N);
		int result = 0;
 
		
		// i 는 가능한 최솟값인 N - 9 * N의 각 자릿수부터 시작 
		for(int i = (N - (N_len * 9)); i < N; i++) { // 최적화, i=0부터 탐색하게 된다면 완전탐색 
			int number = i;
			int sum = 0;	// 각 자릿수 합 변수 
			
			while(number != 0) {
				sum += number % 10;	// 각 자릿수 더하기
				number /= 10;
			}
			
			// i 값과 각 자릿수 누적합이 같을 경우 (생성자를 찾았을 경우) 
			if(sum + i == N) {
				result = i;
				break;
			}
			
		}
 
		System.out.println(result);
	}
 
}
====================================================================================================
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        int number = Integer.parseInt(n);

        int start = number - 9 * n.length();
        while (start < number && !isDecomposeSum(start, number)) {
            start += 1;
        }

        if (start == number){
            System.out.println(0);
            return;
        }
        System.out.println(start);
    }

    private static boolean isDecomposeSum(int start, int target){
        int sum = start;
        while (start >= 1){
            sum += start % 10;
            start /= 10;
        }

        return sum == target;
    }

}
