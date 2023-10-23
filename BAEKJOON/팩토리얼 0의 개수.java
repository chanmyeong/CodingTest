import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * N!에서 뒤에서부터 처음 0이 아닌 숫자가 나올 때까지 0의 개수를 구하는 프로그램
 * 첫째 줄에 N이 주어진다. (0 ≤ N ≤ 500)
 *
 * 문제 해결 전략 : 일반 재귀는 시간초과
 * BigInteger or 수학 규칙
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        BigInteger bi = new BigInteger("1");
        for(int i=1; i<=N; i++) {
            bi = bi.multiply(bi.valueOf(i));
        }

        String str = bi.toString();
        int cnt=0;
        for(int i=str.length()-1; i>=0; i--) {
            if(str.charAt(i)-'0'==0) cnt++;
            else break;
        }
        System.out.println(cnt);
    }
}
====================================================================================================
// [참고문헌]: https://st-lab.tistory.com/165
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        /**
         * 뒷자리가 0이 n개 있다는 의미는 2와 5가 n개씩 짝지어 존재한다는 것이다. (2×5 = 10 이므로)
         * 팩토리얼 값을 보면 2는 5보다 작은 수여서 연속된 수를 곱하게 되면 자연스레 모든 값들의 소인수 분해들은 2의 개수가 5의 개수보다 많다.
         * 즉, 기본적으로 5의 개수에 따라 값이 변화한다고 보면 된다.
         * 5의 n승에 따라 카운트 값을 한 개 더 추가 -> 단순히 5로 나눌 것이 아니라 반복문을 통해 5로 나누면서 누적합
         */
        while (N >= 5) {
            cnt += N / 5;
            N /= 5;
        }
        System.out.println(cnt);
    }
}
