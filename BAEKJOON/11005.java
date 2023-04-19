import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int indexCount = 0;
        int[] quotient = new int[50];
        int[] remainder = new int[50];
        while(N!=0) {
            quotient[indexCount]=N/B;
            remainder[indexCount]=N%B;
            N=N/B;
            indexCount++;
            if(N==0) break;
        }
        indexCount--;
        if(quotient[indexCount]<10) bw.write((quotient[indexCount]==0) ? "" : quotient[indexCount]+"");
        else bw.write((char)(quotient[indexCount])+55 +"");
        for(int i=indexCount; i>=0; i--) {
            if (remainder[i] < 10) bw.write(remainder[i] + "");
            else bw.write((char)(remainder[i]+55) + "");
        }
        bw.flush();
    }
}

====================================================================================================
//O[logB^N]
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int B = sc.nextInt();

        // 1. N을 B로 나눈 나머지를 구하고, B로 나누자
        // 2. 이때 가장 마지막 나머지부터 가장 앞 자릿값이 된다.
        String ans = "";
        while(N>0) {
            int D = N%B;
            if(D<10) ans += D;
            else ans += (char)(D - 10 + 'A');
            N=N/B;
        }

        for(int i=ans.length()-1; i>=0; i--)
            System.out.print(ans.charAt(i));
        System.out.println();
    }
}
  
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();		
		
		int n = Integer.parseInt(st.nextToken());	 	
		int b = Integer.parseInt(st.nextToken());		
		
		while(n>0) {
			int num = n%b;   
			if(num>9)
				sb.append((char)(num - 10 + 'A'));
			else
				sb.append(num);
			
			n /= b;	
		}
		System.out.println(sb.reverse());
	}
}

====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        List<Character> list = new ArrayList<>();

        int N = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);

        while (N > 0) {
            if (N % B < 10) list.add((char) (N % B + '0'));
            else list.add((char) (N % B - 10 + 'A'));
            N = N / B;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i));
        }
    }
}
