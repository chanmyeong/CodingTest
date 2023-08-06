// [참고문헌]: https://st-lab.tistory.com/96
import java.io.*;
import java.util.*;

/**
 * 문제 접근 :
 * 1. 위의 n-1개 원판을 임시기둥으로 이동
 * 2. 시작기둥 n번 원판을 목적기둥으로 이동
 * 3. 임시기둥의 n-1개 원판을 목적기둥으로 이동
 * 시작기둥과 목적기둥이 정해지면 임시기둥은 나머지 하나(바뀔 수 있음)
 */
public class Main {
    static StringBuilder sb = new StringBuilder();
    private static void recursive(int n, int start, int tmp, int end) { // 시작 임시 목적
        if(n==1) { // 이동할 원판이 1개일 때 출력됨
            sb.append(start+" "+end+"\n");
            return;
        }
        recursive(n-1, start, end, tmp);
        sb.append(start+" "+end+"\n"); // n번 원판 1개를 목적기둥으로 이동하는 것은 바로 출력과 같음
        recursive(n-1, tmp, start, end);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        sb.append((int) (Math.pow(2, n) - 1)).append('\n');
        recursive(n,1,2,3);
        System.out.println(sb);
    }
}
