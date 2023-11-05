// [참고문헌]: https://st-lab.tistory.com/182

import java.io.*;
import java.util.*;

/**
 * 1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓음으로써, 수열 생성
 * 스택에 push하는 순서는 반드시 오름차순을 지킴
 *
 * 첫 줄에 n (1 ≤ n ≤ 100,000)에 대해 만들 수열이 주어자면
 * push연산은 +로, pop 연산은 -로 표현하여 필요한 연산을 한 줄에 한 개씩 출력
 * 불가능한 경우 NO를 출력
 *
 * 문제 해결 전략 :
 * (1 ≤ n ≤ 100,000)이므로 n을 배열에 저장해서 다시 꺼내는데 시간복잡도가 소요 (이중 for문 시 시간초과)
 */
public class Main {
    static int N;
    static Stack<Integer> stack = new Stack<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //solve
        int start = 0; // 이전에 어디까지 입력 받았는지를 알기 위한 변수

        // N번 반복
        while(N-- > 0) {
            // 하나의 입력에 대한 결과를 바로 나타낼 수 있다면 자료구조에 저장없이 바로 사용
            int value = Integer.parseInt(br.readLine());

            if(value > start) {
                // start + 1부터 입력받은 value 까지 push를 한다.
                for(int i=start+1; i<=value; i++) {
                    stack.push(i);
                    sb.append('+').append('\n');	// + 를 저장한다.
                }
                start = value; 	// 다음 push 할 때의 오름차순을 유지하기 위한 변수 초기화
            }

            // top에 있는 원소가 입력받은 값과 같이 않은 경우
            else if(stack.peek() != value) {
                System.out.println("NO");
                return; // 또는 System.exit(0); 으로 대체해도 됨.
            }
            stack.pop();
            sb.append('-').append('\n');
        }
        System.out.println(sb);
    }
}
