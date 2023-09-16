import java.io.*;
import java.util.*;
/**
 * N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램
 *
 * 문제 해결 전략 :
 * 두 배열 비교 시 100,000*100,000=10억
 * 이분 검색
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<A.length; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<B.length; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        for(int i=0; i<B.length; i++) {
            boolean flag = false;
            int lt=0;
            int rt=N-1;
            while(lt<=rt) {
                int mid=(lt+rt)/2; // << 연산자 우선순위!

                if(B[i]<A[mid]) {
                    rt=mid-1;
                }
                else if(B[i]>A[mid]) {
                    lt=mid+1;
                }
                else {
                    flag=true;
                    break;
                }
            }
            if(flag) sb.append('1').append('\n');
            else sb.append('0').append('\n');
        }
        System.out.println(sb);
    }
}
====================================================================================================
import java.util.Arrays;

public class Main {

	private static int N, M;
	private static int[] input;
	private static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws Exception {

		N = readInt();
		input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = readInt();
		}

		Arrays.sort(input);
		
		M = readInt();	
		while (M-- > 0) {
			result.append(search(readInt())).append('\n');
		}

		System.out.println(result);
	}

	private static int search(int num) {
		
		int lt = 0, rt = N -1;
		int mid;
		
		while (lt <= rt) {
			mid = (lt + rt) / 2;
			if (input[mid] < num) {
				lt = mid + 1;
			} else if (input[mid] > num) {
				rt = mid - 1;
			} else {
				return 1;
			}
		}

		return 0;
	}
	
	private static int readInt() throws Exception {
		int input, output = 0;
		while (!((input = System.in.read()) == 10 || input == 13 || input == 32)) {
			output = (output << 3) + (output << 1) + (input & 15);
		}
		
		return output;
	}
}
