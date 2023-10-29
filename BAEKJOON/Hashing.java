import java.io.*;
import java.util.*;

/**
 * 해시 함수란 임의의 길이의 입력을 받아서 고정된 길이의 출력을 내보내는 함수로 정의
 * a에는 1, b에는 2, c에는 3, ..., z에는 26으로 고유한 번호를 부여
 *
 * 비둘기 집의 원리 : 서로 다른 문자열이더라도 동일한 해시 값을 가질 수 있다.(해시 충돌)
 * 항의 번호에 해당하는 만큼 특정한 숫자를 거듭제곱해서 곱해준 다음 더하고 적당히 큰 수 M으로 나누기
 * 보통 r과 M은 서로소인 숫자로 정하는 것
 *
 * r : 31
 * M : 1234567891
 * 문자열은 모두 알파벳 소문자
 *
 * 문제 해결 전략 :
 * 최대 길이가 50이면 31의 49제곱까지 나올 건데 long형의 범위로는 31의 49제곱을 담을 수가 없다.
 * 그렇기 때문에 long형으로 풀 경우 31의 제곱을 구할때마다 1234567891을 나눠준다.
 */
public class Main {
    static int L;
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        str = br.readLine();
        int r = 31;
        long M = 1234567891;
        long sum = 0;
        long pow = 1;
        for(int i=0; i<str.length(); i++) {
            int num = str.charAt(i)-'a'+1;
            // System.out.println(i+": "+num);
            sum += num * pow;
            pow = pow * r % M; // pow는 31을 매번 곱해준다. 곱해줄 때마다 1234567891을 나눠주면 long을 넘지 않을 것이다.
        }
        System.out.println(sum%M);
    }
}

====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String S = br.readLine();
		BigInteger result = new BigInteger("0");
		for(int i = 0; i < L; i++) {
        // result에 현자 자릿수의 알파벳에서 정수 1~26중 해당 값을 더해주고
        // 그 값에 31의 i제곱한 값을 곱해주는 것이다.
			result = result.add(BigInteger.valueOf(S.charAt(i) - 96).multiply(BigInteger.valueOf(31).pow(i)));
		}
        // 출력할 때는 1234567891을 나눠주자.
		System.out.println(result.remainder(BigInteger.valueOf(1234567891)));
	}
}
