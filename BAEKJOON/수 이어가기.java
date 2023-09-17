import java.io.*;
import java.util.*;

/**
 * 첫 번째 수로 양의 정수가 주어진다.
 * 두 번째 수는 양의 정수 중에서 하나를 선택한다.
 * 세 번째부터 이후에 나오는 모든 수는 앞의 앞의 수에서 앞의 수를 빼서 만든다.
 * 음의 정수가 만들어지면, 이 음의 정수를 버리고 더 이상 수를 만들지 않는다.
 * 첫 번째 수가 주어질 때, 이 수에서 시작하여 위의 규칙으로 만들어지는 최대 개수의 수들을 구하는 프로그램
 * 최대 개수의 수들이 여러 개일 때, 그중 하나의 수들만 출력하면 된다.
 * 첫 번째 수는 30,000보다 같거나 작은 양의 정수
 * 
 * 문제 해결 전략 :
 * 앞이 항상 더 큰 수여야 더 많은 뺄셈이 가능하여 최대 개수를 만들 가능성이 커지므로
 * 1번 수/2 ~ 1번 수에서 2번째 수를 고름 
 * 
 * while 부분에서 tmp를 안 써서 0에서 무한 반복 되었음 
 */

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int first = N;
		int secondMin = N/2;
		int secondMax = N;
		
		int answer = 0;
		int secondNum=-1;
		for(int i=secondMin; i<=secondMax; i++) {
			int maxCnt = findMaxCnt(N, i);
			if(answer<maxCnt) {
				answer=maxCnt;
				secondNum=i;
			}
			answer = Math.max(answer, maxCnt);
		}
		
		int firstNum = N;
		sb.append(firstNum+" ");
		while(secondNum>=0) {
			sb.append(secondNum+" ");
			int tmp = secondNum;
			secondNum=firstNum-secondNum;
			firstNum=tmp;
		}
		System.out.println(answer);
		System.out.println(sb);		
	}

	private static int findMaxCnt(int first, int second) {
		int cnt=1;

		while(second>=0) {
			int tmp = second;
			second=first-second;
			first=tmp;
			cnt++;
		}
		return cnt;
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int max = 0; 
        ArrayList<Integer> maxlist = new ArrayList<Integer>();    // 최대 개수의 숫자 조합을 저장할 리스트
        
        for(int i = 1; i <= n; i++) {
            ArrayList<Integer> nowlist = new ArrayList<Integer>();    // 현재경우의 숫자 조합을 저장할 리스트
            nowlist.add(n);                                        // n을 리스트의 1번 i를 리스트의 2번에 저장해두고 계산 시작 
            nowlist.add(i);
            int prev1 = n;
            int prev2 = i;
            
            while(true) {                                        // 결과가 0보다 큰 동안 앞앞 숫자에서 앞 숫자를 빼준값을 리스트에 저장 
                int temp = prev1 - prev2;
                if(temp>=0) {
                    nowlist.add(temp);
                }else {
                    break;
                }
                prev1 = prev2;                                    // 앞앞 숫자와 앞 숫자를 새로 지정
                prev2 = temp;
            }
            if(max < nowlist.size()) {                            // 이번 회차가 최대 개수의 숫자라면 해당 개수를 max에 저장하고 리스트의 숫자 조합을 maxlist에 저장
                max = nowlist.size();
                maxlist = nowlist;
            }
        }
        
        System.out.println(max);                                // 최대 값과 그 조합을 출력
        for(int i = 0; i < maxlist.size(); i++) {
            System.out.print(maxlist.get(i) + " ");
        }
    }
 
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine()); 
		int k = (int)(float)Math.ceil(n * 0.61803398); // 황금비는 어떤 두 수의 비율이 그 합과 두 수 중 큰 수의 비율과 같도록 하는 비율로, 근사값이 약 1.618인 무리수이다.
		int cnt = 2;
		sb.append(n+" "+k+" ");
		int temp=0;
		while(n-k>=0) {
			cnt++;
			sb.append(n-k+" ");
			temp = k;
			k = n - k;
			n = temp;
		}
		System.out.println(cnt+"\n"+sb);
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{
	static int max = 0;
	static ArrayList<Integer> arr;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int start = Integer.parseInt(br.readLine());
		int idx = 0;
		
		for(int i = start; i > 0; i--) {
			int tmp = recursive(start, i, 2);
			if(max < tmp) {
				max = tmp;
				idx = i;
			}
		}
		System.out.println(max);
		arr = new ArrayList<>();
		find(start, idx);
		for(int i = arr.size()-1; i >=0; i--) {
			sb.append(arr.get(i)).append(" ");
		}
		sb.append(arr.get(1) - arr.get(0));
		System.out.println(sb.toString());
	}
	
	private static int recursive(int one, int two, int cnt) {
		if(one - two >= 0) {
			return recursive(two, one-two, cnt+1);
		}
		return cnt;
	}
	
	private static void find(int one, int two) {
		if(one - two >= 0) {
			find(two, one-two);
		}
		arr.add(one);
		return;
	}

}
