import java.io.*;
import java.util.*;
public class Main {
    static int c,n,answer=Integer.MIN_VALUE;
    public void DFS(int L, int sum, int[] arr) {
        if(sum>c) return;
        if(L==n) {
            answer=Math.max(answer, sum);
        }
        else {
            DFS(L+1, sum+arr[L], arr); // 부분집합에 사용
            DFS(L+1, sum, arr); // 부분집합에 미사용
        }
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        T.DFS(0,0, arr);
        System.out.println(answer);
    }
}
