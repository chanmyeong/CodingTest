// 시간초과 O[n^2]
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n = Integer.parseInt(br.readLine());

        int[] sequence = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++)
            sequence[i] = Integer.parseInt(st.nextToken());

        int x = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++)
            if(x-sequence[i] == sequence[j]) cnt++;
        }
        bw.write(cnt/2+"");
        bw.flush();
    }
}

====================================================================================================
// O[1000000]

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n = Integer.parseInt(br.readLine());

        int[] sequence = new int[1000001];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++)
            sequence[Integer.parseInt(st.nextToken())]++;

        int x = Integer.parseInt(br.readLine());

        int cnt = 0;
        for(int i=0; i<1000001; i++) {
            if(0 <= x-i && x-i <= 1000000) // 없으면 런타임 에러 (ArrayIndexOutOfBounds)
                if(sequence[i]==1 && sequence[x-i]==1) cnt++;
        }
        bw.write(cnt/2+"");
        bw.flush();
    }
}

====================================================================================================
// O[n]
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++)
            a[i]=Integer.parseInt(st.nextToken());

        int x = Integer.parseInt(br.readLine());

        boolean[] exist = new boolean[1000001];

        for(int i=0; i<n; i++) {
            exist[a[i]]=true;
        }

        int ans = 0;
        for(int i=0; i<n; i++) {
            int pairValue = x-a[i];
            if(0 <= pairValue && pairValue <= 1000000)
                ans += exist[pairValue] ? 1 : 0;
        }
        bw.write(ans/2+"");
        bw.flush();
    }
}

====================================================================================================
// O[(x-1)/2]
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++)
            a[i]=Integer.parseInt(st.nextToken());

        int x = Integer.parseInt(br.readLine());

        boolean[] exist = new boolean[1000001];

        for(int i=0; i<n; i++) {
            exist[a[i]]=true;
        }

        int ans = 0;
        for(int i=1; i<= (x-1)/2; i++) {
            if(i <= 1000000 && x-i <= 1000000)
                ans += (exist[i] && exist[x-i]) ? 1 : 0;
        }
        bw.write(ans+"");
        bw.flush();
    }
}
