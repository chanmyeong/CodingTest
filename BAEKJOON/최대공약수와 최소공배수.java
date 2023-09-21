// [참고문헌]: https://st-lab.tistory.com/154
// 최대공약수(Greatest Common Divisor) : 유클리드 호제법(Euclidean algorithm)
// GCD(a, b) = GCD(b, r) => a 와 b의 최대공약수를 (a, b)라고 할 때 (a, b)의 최대공약수는 (b, r)의 최대공약수와 같다.
// 최소공배수(Least Common Mulitple) : A=ad, B=bd 에서 a와 b는 서로소이고, d는 최대공약수 => 두 수의 최소 공배수는 a×b×d == A×B÷d

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
 
		int d = gcd(a, b);
 
		System.out.println(d);
		System.out.println(a * b / d);
 
	}
 
	// 최대공약수 반복문 방식
	public static int gcd(int a, int b) {
 
		while (b != 0) {
			int r = a % b; // 나머지를 구해준다.
 
			// GCD(a, b) = GCD(b, r)이므로 변환한다.
			a = b;
			b = r;
		}
		return a;
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
 
		int d = gcd(a, b);	// 최대공약수
 
		System.out.println(d);
		System.out.println(a * b / d);
 
	}
 
	// 최대공약수 재귀 방식
	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
            
		// GCD(a, b) = GCD(b, r)이므로 (r = a % b)
		return gcd(b, a % b);
	}
}  
====================================================================================================
import java.io.*;
import java.util.*;

/**
 * 두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램
 *
 * 문제 해결 전략 :
 * 두 수의 약수들로 최소 공배수 제작
 */
public class Main {
    static int minBoth=0,maxBoth=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int max = Math.max(A,B);

        // solve
        for(int i=1; i<=max; i++) {
            if(A%i==0 && B%i==0) {
                maxBoth = Math.max(maxBoth, i);
            }
        }
        // minBoth = A*B/maxBoth;
	A : for(int j=max, k=2; j<=A*B; j=max*k, k++) {
            for(int i=min, l=2; i<=j; i=min*l, l++) {
                // System.out.println(i+" "+j);
                if(i==j) {
                    minBoth = j;
                    break A;
                }
            }
        }

        System.out.println(maxBoth);
        System.out.println(minBoth);

    }
}	
