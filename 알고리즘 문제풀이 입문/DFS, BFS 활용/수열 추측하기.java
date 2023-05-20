// 순열 구하기 + 조합수(메모이제이션)
import java.io.*;
import java.util.*;
public class Main {
    static int[] b, p, ch;
    static int[][] dy = new int[35][35];
    static int n,f;
    boolean flag = false;
    public int combi(int n, int r) {
        if(dy[n][r]>0) return dy[n][r]; // memoization
        if(n==r || r==0) return 1;
        else return dy[n][r] = combi(n-1, r-1)+combi(n-1,r);
    }
    public void DFS(int L, int sum) {
        if(flag) return;
        if(L==n) {
            if(sum==f) {
                for(int x : p) System.out.print(x+" ");
                flag=true; // 정답을 찾은 이후 스택프레임의 DFS는 전부 바로 종료하기 위한 flag
            }
        }
        else {
            for(int i=1; i<=n; i++) {
                if(ch[i]==0) {
                    ch[i]=1;
                    p[L]=i;
                    DFS(L+1, sum+(p[L]*b[L])); // 첫줄의 (1~n)*(n-1C0~n-1Cn-1)를 sum에 누적
                    ch[i]=0;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        f = sc.nextInt();
        b = new int[n];
        p = new int[n];
        ch = new int[n+1];
        for(int i=0; i<n; i++)
            b[i]=T.combi(n-1, i);
        T.DFS(0,0);
    }
}
