// 피보나치 수열 for문(배열) vs 재귀함수 : 재귀함수가 스택프레임으로 인한 메모리 낭비가 심하다.
import java.io.*;
import java.util.*;

public class Main {
    public int DFS(int n) {
        if(n==1) return 1;
        else if(n==2) return 1;
        else return DFS(n-2)+DFS(n-1);
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main T = new Main();
        for(int i=1; i<=n; i++)
            System.out.print(T.DFS(i)+" ");
    }
}

====================================================================================================
// 위의 코드 개선
import java.io.*;
import java.util.*;

public class Main {
    static int[] fibo;
    public int DFS(int n) {
        if(n==1) return fibo[n] = 1;
        else if(n==2) return fibo[n] = 1;
        else return fibo[n] = DFS(n-2)+DFS(n-1); // 배열에 저장 후 그 값을 리턴
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main T = new Main();
        fibo = new int[n+1];
        T.DFS(n);
        for(int i=1; i<=n; i++)
            System.out.print(fibo[i]+" ");
    }
}

====================================================================================================
// 위의 코드 개선(memoization, 메모이제이션) -> 시간복잡도 단축
import java.io.*;
import java.util.*;

public class Main {
    static int[] fibo;
    public int DFS(int n) {
        if(fibo[n]>0) return fibo[n]; // 이미 한 번 구했던 재귀함수는 fibo에 저장된 값을 리턴(memoization)
        if(n==1) return fibo[n] = 1;
        else if(n==2) return fibo[n] = 1;
        else return fibo[n] = DFS(n-2)+DFS(n-1); // 배열에 저장 후 그 값을 리턴
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main T = new Main();
        fibo = new int[n+1];
        T.DFS(n);
        for(int i=1; i<=n; i++)
            System.out.print(fibo[i]+" ");
    }
}

