// 중복 제거를 위한 check배열 ch[]사용
import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] arr, pick, ch;
    static int n,m;
    public void DFS(int L) {
        if(L==m) {
            for(int i=0; i<m; i++) sb.append(pick[i]+" ");
            sb.append('\n');
        }
        else {
            for(int i=0; i<n; i++) {
                if(ch[i]==0) {
                    ch[i]=1;
                    pick[L] = arr[i];
                    DFS(L + 1);
                    ch[i]=0;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];
        ch = new int[n];
        pick = new int[m];
        for(int i=0; i<n; i++) arr[i]=sc.nextInt();
        T.DFS(0);
        System.out.println(sb);
    }
}
