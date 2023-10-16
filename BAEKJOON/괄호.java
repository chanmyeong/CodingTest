import java.io.*;
import java.util.*;

/**
 * 괄호 문자열(Parenthesis String, PS)은 두 개의 괄호 기호인 ‘(’ 와 ‘)’ 만으로 구성되어 있는 문자열
 * 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열(Valid PS, VPS)
 * x 가 VPS 라면 이것을 하나의 괄호에 넣은 새로운 문자열 “(x)”도 VPS
 * 두 VPS x 와 y를 접합(concatenation)시킨 새로운 문자열 xy도 VPS
 *
 * 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 YES 와 NO
 *
 * 입력
 * T
 * 하나의 괄호 문자열의 길이는 2 이상 50 이하
 *
 * 문제 해결 전략 :
 * 여는 괄호를 스택에 담아두고 닫힌 괄호를 만날 때마다 하나씩 pop
 * 계산이 끝났는데 여는 괄호가 스택에 남아있거나 닫힌 괄호를 만났는데 꺼낼 여는 괄호가 없다면 NO
 */
public class Main {
    static boolean answer; // 정답 flag
    static char[] ch; // 입력 괄호 문자열 배열
    static Stack<Character> stack; // 괄호 담아둘 stack
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=TC; tc++) {
            ch = br.readLine().toCharArray();
//            System.out.println(Arrays.toString(ch));
            answer = true;
            stack = new Stack<>(); // stack 초기화

            // solve
            for(int i=0; i<ch.length; i++) {
                if(ch[i]=='(') {
                    stack.push(ch[i]);
                }
                else if(ch[i]==')') {
                    if(!stack.isEmpty()) stack.pop();
                    else {
                        answer = false;
                        break;
                    }

                }
            }
            if(!stack.isEmpty()) {
                answer = false;
            }
            if(answer) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        System.out.println(sb);
    }
}
====================================================================================================
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int cnt = 0, j = 0;
		
		for(int i = 0; i < cases; i++) {
			char arr[] = br.readLine().toCharArray();
			if(arr[0]==')') sb.append("NO\n"); // 가지치기
			else {
				for(j = 0; j < arr.length; j++) {
					if(arr[j] == '(') cnt ++;
					if(arr[j] == ')') cnt --;
					
					if(cnt < 0) {
						sb.append("NO\n");
						break;
					}
				}
				
				if(j == arr.length) { // 입력 괄호 문자열 배열을 전부 확인하고
					if(cnt == 0) sb.append("YES\n"); // cnt값이 0이면 올바른 VPS
					else sb.append("NO\n");
				}
				
				cnt = 0; // cnt 초기화
			}
		}
		
		System.out.println(sb);
	}

}
