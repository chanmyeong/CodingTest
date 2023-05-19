import java.io.*;
import java.util.*;
public class Main {
    static int n,m,answer=Integer.MIN_VALUE;
    public void DFS(int L, int sum, int total, int[] score, int[] time) {
        if(sum>m) return;
        if(L==n) {
            answer=Math.max(answer, total);
        }
        else {
            DFS(L+1, sum+time[L], total+score[L], score, time); // 부분집합에 사용
            DFS(L+1, sum, total, score, time); // 부분집합에 미사용
        }
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[] score = new int[n];
        int[] time = new int[n];
        for(int i=0; i<n; i++) {
            score[i] = sc.nextInt();
            time[i] = sc.nextInt();
        }
        T.DFS(0, 0, 0, score, time);
        System.out.println(answer);
    }
}
