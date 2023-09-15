import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램
 * [참고문헌]: https://st-lab.tistory.com/80
 * 제곱근 이상의 수로 나누면 두 가지 경우밖에 없다.
 * 1. 이전의 검사한 수 중 약수인 수를 약수로 갖는다.
 * 2. 약수가 아니다.
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		boolean isPrime = true;
		int cnt=0;
		for(int i=0; i<arr.length; i++) {
			isPrime = true;
			if(arr[i]==1) continue;
			for(int j=2; j<=Math.sqrt(arr[i]); j++) { // 합성수 Number = A × B 에서 A 와 B 중 적어도 하나는 Number의 제곱근보다 작거나 같다.
				if(arr[i]%j==0) {
					isPrime=false;
					break;
				}
			}
			if(isPrime) cnt++;
		}
		System.out.println(cnt);
	}
}
====================================================================================================
import java.io.*;
import java.util.*;
/**
 * 주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램
 * N은 100이하이다. 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수
 *
 * 문제 해결 전략 : 에라토스테네스의 체
 * 소수를 제외한 소수의 배수인 수를 모두 걸러낸다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] prime = new boolean[1001];
        int[] isPrime = new int[1001];
        int[] arr = new int[N];

        int max=0;
        for(int i=0; i<N; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
            if(max<arr[i]) max=arr[i];
        }

        for(int i=2; i<=max; i++) {
            if(!prime[i]) {
                prime[i]=true;
                isPrime[i]=1;
            }
            // 정석대로라면 j=i*2 부터 시작이지만 i=2에서 2의 배수가 걸러지므로 j=i*i, i의 제곱수부터 시작해도 된다.
            for(int j=i; j<=max; j=j+i) { 
                prime[j]=true;
            }
        }

        int cnt=0;
        for(int i=0; i<arr.length; i++) {
            if(isPrime[arr[i]]==1) cnt++;
        }
        System.out.println(cnt);

    }
}
