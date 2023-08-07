import java.io.*;
import java.util.*;

/**
 * 왼쪽으로만 레이저를 송신하는 탑들이 입력으로 주어졌을 때
 * 수신하는 탑의 번호를 입력에 맞게 출력하는 문제 
 * 수신하는 탑이 없으면 0, 레이저는 하나의 탑에서만 수신 가능
 * @author cm
 * 문제 접근 :
 * 자료구조에 탑들의 높이를 넣어두고 높이가 다음에 꺼내는 높이보다 작으면 해당 탑번호 마크 >> X
 * [참고문헌]: https://steady-coding.tistory.com/15
 * 1. 스택이 비어있다면 0을 출력하고, 현재 탑을 스택에 push한다.
 * 2. 스택이 비어있지않다면, 아래 2가지 케이스를 검사한다.
 * 2-1. 스택에 peek한 탑의 높이가 현재 탑의 높이보다 높다면, peek한 탑의 번호를 출력하고, 현재 탑을 스택에 push한다.
 * 2-2. 스택에 peek한 탑의 높이가 현재 탑의 높이보다 낮다면, peek한 탑을 pop하고 2번으로 다시 돌아간다.
 */

class Top { // 탑에 대한 정보
	int num; // 탑의 번호
	int height; // 탑의 높이

	Top(int num, int height) {
		this.num = num;
		this.height = height;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		Stack<Top> stack = new Stack<>();
		StringBuilder answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int height = Integer.parseInt(st.nextToken());

			if (stack.isEmpty()) { // 스택이 비어있다면, 0을 출력하고 탑을 push한다. (초기 과정에 속함.)
				answer.append("0 ");
				stack.push(new Top(i, height));
			} else {
				while (true) { 
					if (stack.isEmpty()) { // 스택이 비어있다면, 0을 출력하고 탑을 push한다.
						answer.append("0 ");
						stack.push(new Top(i, height));
						break;
					}

					Top top = stack.peek();

					if (top.height > height) { // peek한 탑의 높이가 현재 탑의 높이보다 높다면,
						answer.append(top.num + " "); // peek한 탑의 번호를 출력하고,
						stack.push(new Top(i, height)); // 현재 탑을 스택에 push한다.
						break;
					} else { // peek한 탑의 높이가 현재 탑의 높이보다 낮다면,
						stack.pop(); // 스택에서 pop하고 다시 반복문을 돌린다.
					}
				}
			}
		}

		bw.write(answer + "\n"); // bw.write(answer.toString() + "\n");과 동일
		bw.flush();
		bw.close();
		br.close();
	}
}
