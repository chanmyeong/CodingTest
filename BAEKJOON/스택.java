import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 입력
 * 명령의 수 N (1 ≤ N ≤ 10,000)
 * 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다.
 * 
 * push X: 정수 X를 스택에 넣는 연산이다.
 * pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * size: 스택에 들어있는 정수의 개수를 출력한다.
 * empty: 스택이 비어있으면 1, 아니면 0을 출력한다.
 * top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * 
 */
public class Main {
	static int[] stack;
	static int size=0;
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stack = new int[N];
		StringTokenizer st;

		while(N-->0) {
			st = new StringTokenizer(br.readLine(), " ");

			switch(st.nextToken()) {
			case "push":
				push(Integer.parseInt(st.nextToken()));
				break;

			case "top":
				sb.append(top()).append("\n");
				break;

			case "size":
				sb.append(size()).append("\n");
				break;

			case "empty":
				sb.append(empty()).append("\n");
				break;

			case "pop":
				sb.append(pop()).append("\n");
				break;

			}
		}

		System.out.println(sb);
	}
	private static void push(int item) {
		stack[size] = item;
		size++;
	}

	private static int pop() {
		if(size==0) {
			return -1;
		} else {
			int res = stack[size-1];
			stack[size-1]=0;
			size--;
			return res;
		}
	}

	private static int size() {
		return size;
	}

	private static int empty() {
		if(size==0) {
			return 1;
		} else {
			return 0;
		}
	}

	private static int top() {
		if(size==0) return -1;
		return stack[size-1];
	}
}
