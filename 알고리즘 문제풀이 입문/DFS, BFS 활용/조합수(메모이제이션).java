// nCr = n-1Cr-1 + n-1Cr
import java.io.*;
import java.util.*;
public class Main {
    static int[][] dy = new int[35][35];
    static int n,r;
    public int DFS(int n, int r) {
        if(dy[n][r]>0) return dy[n][r]; // memoization
        if(n==r || r==0) return 1; // 언제 DFS가 끝나는지
        else return dy[n][r] = DFS(n-1, r-1)+DFS(n-1,r); // DFS 재귀부분
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        r = sc.nextInt();
        System.out.println(T.DFS(n,r));
    }
}
