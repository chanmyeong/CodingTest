import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());
        int answer=0;
//        System.out.println((int)'Z'); // 90

        int cnt=0;
        for(int i=N.length()-1; i>=0; i--) {
            int num=0;
            char word = N.charAt(i);
            if(Character.isAlphabetic(word)) num = word-55;
            else num=word-'0';
            answer+=num*Math.pow(B,cnt);
            cnt++;
        }
        System.out.println(answer);
    }
}
====================================================================================================
// Integer.parseInt(String s, int radix)
// 첫번째 인자로 변환하고자 하는 숫자형의 문자열을 전달받는다.
// 두번째 인자에는 문자열을 변환할 진수 값을 전달한다. 변환할 문자열이 2진수로 표현되어 있다면 2를, 8진수로 표현되어있다면 8을 입력하면 된다.
// 반환값은 항상 10진수이다.
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());
        
        System.out.println(Integer.parseInt(N,B));

        br.close();
    }
}
====================================================================================================
import java.util.*;
public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String s = sc.next();
		int N = sc.nextInt();
		int tmp = 1;
		int ans = 0;
		
		for (int i = s.length()-1; i >= 0; i--) {
			char c = s.charAt(i);
			
			if('A'<= c && c <= 'Z') {
				ans += (c-'A'+10)*tmp;
			}else {
				ans += (c-'0')*tmp;
			}
			tmp *= N;
		}
		System.out.println(ans);
	}
}
