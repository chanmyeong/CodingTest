[참고문헌]: https://m.blog.naver.com/ka28/221850826909
[참고문헌]: https://st-lab.tistory.com/30

// BufferedWriter 사용
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        double T = Double.parseDouble(br.readLine());

        StringTokenizer st;

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine()," ");
            bw.write(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + "\n");
        }
        bw.flush();
        bw.close();
    }
}

====================================================================================================

// StringBuilder 사용
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int N = Integer.parseInt(br.readLine());
        
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
        
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			sb.append(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())).append('\n');
		}
		br.close();
 
		System.out.println(sb);
 
	}
}

====================================================================================================

// substring 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
 
		for (int i = 0; i < N; i++) {		
			String str = br.readLine();
			int target = str.indexOf(" ");
			int result = Integer.parseInt(str.substring(0,target)) + Integer.parseInt(str.substring(target + 1));		
			sb.append(result+"\n");
		}
		
		br.close();
		System.out.print(sb);
    }
}

