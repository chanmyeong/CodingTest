import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] pick;
    static int n,m;
    public void DFS(int L, int s) {
        if(L==m) {
            for(int i=0; i<m; i++) sb.append(pick[i]+" ");
            sb.append('\n');
        }
        else { // 조합은 암기하면 편하다
            for(int i=s; i<=n; i++) {
                pick[L] = i;
                DFS(L+1, i+1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        pick = new int[m];
        T.DFS(0, 1);
        System.out.println(sb);
    }
}
