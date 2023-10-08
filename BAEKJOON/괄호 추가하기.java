import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
 
public class Main {
    static int ans;
    static ArrayList<Character> ops;
    static ArrayList<Integer> nums;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();
 
        ops = new ArrayList<>();
        nums = new ArrayList<>();
 
        for (int i = 0; i < N; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                ops.add(c);
                continue;
            }
            nums.add(Character.getNumericValue(c));
        }
 
        ans = Integer.MIN_VALUE;
        DFS(nums.get(0), 0);
 
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
 
    // 연산
    public static int calc(char op, int n1, int n2) {
        switch (op) {
        case '+':
            return n1 + n2;
        case '-':
            return n1 - n2;
        case '*':
            return n1 * n2;
        }
        return -1;
    }
 
    // DFS, 백트래킹 활용.
    public static void DFS(int result, int opIdx) {
        // 주어진 연산자의 개수를 초과하였을 경우.
        if (opIdx >= ops.size()) {
            ans = Math.max(ans, result);
            return;
        }
 
        // 괄호가 없는 경우
        int res1 = calc(ops.get(opIdx), result, nums.get(opIdx + 1));
        DFS(res1, opIdx + 1);
 
        // 괄호가 있는 경우
        if (opIdx + 1 < ops.size()) {
            // result의 오른쪽에 있는 값을 연산함.
            int res2 = calc(ops.get(opIdx + 1), nums.get(opIdx + 1), nums.get(opIdx + 2));
 
            // 현재 result와 방금 구한 괄호 값을 연산한 결과와 괄호 오른쪽에 존재하는 연산자의 인덱스를 넘김.
            DFS(calc(ops.get(opIdx), result, res2), opIdx + 2);
        }
    }
 
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, result;
	static char[] input;

	public static void main(String[] args) throws NumberFormatException, IOException {
		/*
		 * 80ms
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		input = new char[N];

		input = br.readLine().toCharArray();

		result = Integer.MIN_VALUE;
		// 2번 인덱스의 숫자 (2번째 숫자)부터 괄호를 내 왼쪽에 칠건지(결국 안치는게 됨) 오른쪽에 칠건지 치지 않을건지
		solve(2, input[0] - '0');
		System.out.println(result);
	}

	private static void solve(int i, int sum) {
		
		if (i >= N) {
			result = Math.max(result, sum);
			return;
		}
		
		// 괄호 안 친 경우 지금까지의 합과 나를 계산한 결과를 다음 숫자 (index는 +2)에 넘긴다
		solve(i+2, cal(sum, input[i]-'0', input[i-1]));
		
		// 오른쪽에 괄호 친 경우
		if (i + 2< N) {
			// 옆 괄호 먼저 계산
			int right = cal(input[i]-'0', input[i+2]-'0' , input[i+1]);
			// 지금까지 결과와 합하기
			int left = cal(sum, right, input[i-1]);
			solve(i+4, left);
		}
	}

	private static int cal(int i, int j, char op) {
		if (op == '+')
			return i + j;
		else if (op == '-')
			return i - j;
		else
			return i * j;
	}
}
