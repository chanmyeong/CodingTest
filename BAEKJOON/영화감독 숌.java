import java.io.*;
import java.util.*;
/**
 * 종말의 수란 어떤 수에 6이 적어도 3개 이상 연속으로 들어가는 수
 * 제일 작은 종말의 수는 666이고, 그 다음으로 큰 수는 1666, 2666, 3666, ....
 * N번째 영화의 제목은 세상의 종말 (N번째로 작은 종말의 수) 와 같다.
 * N번째 영화의 제목에 들어간 수를 출력
 * N은 10,000보다 작거나 같은 자연수
 *
 * 문제 해결 전략 :
 * [참고문헌]: https://st-lab.tistory.com/103
 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int num = 666;
        int count = 1;

        while(count != N) {
            num++;
            if(String.valueOf(num).contains("666")) {
                count++;
            }
        }
        System.out.println(num);
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {
    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
 
		if (N > 1) {
			func(N);
		} 
		else {
			System.out.println(666);
		}
	}
 
	public static void func(int n) {
		int count = 1;
		int prev_digit = 0;	// 선수 자릿수
		int num = 0;	// 선수 자릿수를 제외한 나머지 뒷 자릿수
		
		/*
		 
		   설명 표현 방법
		   '_'(언더바)를 기준으로 표현한다.  ex) (prev_digit) _ num
		   이 때, 자릿수에 따라서 num 은 0 또는 600, 660, 666 을 갖는다.
		   
		 */
		while (true) {
 
			/*
			 *  선수 자릿수가 X...666X 이면서 X...6666 이 아닐 경우 
			 *  (ex. 6660_000, 6660_001, ...)
			 */
			if (((prev_digit % 10000) / 10) == 666 && prev_digit % 10 != 6) {
				for (int i = 0; i < 1000; i++) {
					if (count == n) {
						System.out.print(prev_digit * 1000 + num);
						return;
					}
					num++;
					count++;
				}
				prev_digit++;
			}
 
			// 선수 자릿수가 X...666 일 경우 (ex. 666_000, 1666_004, ...)
			else if (prev_digit % 1000 == 666) {
				num = 0;
				for (int i = 0; i < 1000; i++) {
 
					if (count == n) {
						System.out.print(prev_digit * 1000 + num);
						return;
					}
					count++;
					num++;
				}
				prev_digit++;
			}
 
			// 선수 자릿수가 X...66 일 경우 (ex. 66_600, 166_600, ...)
			else if (prev_digit % 100 == 66) {
				num = 600;
				for (int i = 0; i < 100; i++) {
					if (count == n) {
						System.out.print(prev_digit * 1000 + num);
						return;
					}
					count++;
					num++;
				}
				prev_digit++;
 
			}
 
			
			// 선수 자릿수가 X...6 일 경우 (ex. 6_660, 16_663, ...) 
			else if (prev_digit % 10 == 6) {
				num = 660;
				for (int i = 0; i < 10; i++) {
					if (count == n) {
						System.out.print(prev_digit * 1000 + num);
						return;
					}
					num++;
					count++;
				}
				prev_digit++;
			} 
			
			// 그 외의 경우 (ex. 241_666, 23_666 ...)
			else {
				num = 666;
				if (count == n) {
					System.out.print(prev_digit * 1000 + num);
					return;
				}
				count++;
				prev_digit++;
 
			}
		}
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static void solve(int n) {
		int ret;
		int i = 0;
		for(int prev=0;true;prev++) {
			if(prev%10 != 6) {
				i+=1;
				ret = 1000 * prev;
				ret += 666;
				if(i==n) {
					System.out.println(ret);
					return;
				}
			}else if(prev%100/10 != 6) {
				for(int post=0;post<10;post++) {
					i+=1;
					ret = 1000 * prev;
					ret += 10 * 66;
					ret += post;
					if(i==n) {
						System.out.println(ret);
						return;
					}
				}
			}else if(prev%1000/100 != 6) {
				for(int post=0;post<100;post++) {
					i+=1;
					ret = 1000 * prev;
					ret += 100 * 6;
					ret += post;
					if(i==n) {
						System.out.println(ret);
						return;
					}
				}
			}
			else if(prev%10000/1000 != 6) {
				for(int post=0;post<1000;post++) {
					i+=1;
					ret = 1000 * prev;
					ret += post;
					if(i==n) {
						System.out.println(ret);
						return;
					}
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		solve(n);
	}
}
