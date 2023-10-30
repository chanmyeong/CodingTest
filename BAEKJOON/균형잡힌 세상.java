import java.util.*;
import java.io.*;
/**
 * 괄호들의 균형이 잘 맞춰져 있는지 판단하는 프로그램
 * 
 * 문자열에 포함되는 괄호는 소괄호("()") 와 대괄호("[]")로 2종류
 * 
 * 모든 왼쪽 소괄호("(")는 오른쪽 소괄호(")")와만 짝을 이뤄야 한다.
 * 모든 왼쪽 대괄호("[")는 오른쪽 대괄호("]")와만 짝을 이뤄야 한다.
 * 모든 오른쪽 괄호들은 자신과 짝을 이룰 수 있는 왼쪽 괄호가 존재한다.
 * 모든 괄호들의 짝은 1:1 매칭만 가능하다. 즉, 괄호 하나가 둘 이상의 괄호와 짝지어지지 않는다.
 * 짝을 이루는 두 괄호가 있을 때, 그 사이에 있는 문자열도 균형이 잡혀야 한다.
 * 
 * 균형잡힌 문자열인지 아닌지를 판단
 * 괄호가 하나도 없는 경우도 균형잡힌 문자열로 간주
 * 
 * 입력의 종료조건으로 맨 마지막에 온점 하나(".")가 들어온다
 */
public class Main {
	static Stack<Character> stack;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while(true) {
			line = br.readLine();
			if(line.equals(".")) {
				break;
			}
			
			sb.append(solve(line)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static String solve(String line) {
		stack = new Stack<Character>(); // stack 초기화
		
		for(int i=0; i<line.length(); i++) {
			char c = line.charAt(i); // i번째 문자
			
			// 여는 괄호일 경우 스택에 push
			if(c == '(' || c=='[') {
				stack.push(c);
			}
			
			// 닫는 소괄호 일 경우
			else if(c==')') {
				// 스택이 비어있거나 pop할 원소가 소괄호랑 매칭이 안되는 경우 
				if(stack.isEmpty() || stack.peek()!='(') {
					return "no";
				} else {
					stack.pop();
				}
			} 
			
			// 닫는 대괄호 일 경우
			else if(c==']') {
				// 스택이 비어있거나 pop할 원소가 대괄호랑 매칭이 안되는 경우 
				if(stack.isEmpty() || stack.peek()!='[') {
					return "no";
				} else {
					stack.pop();
				}
			}
		}
		if(!stack.isEmpty()) {
			return "no";
		} else return "yes";
	}
}
