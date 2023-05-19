import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] pm;
    static int n,m;
    public void DFS(int L) {
        if(L==m) {
            for(int x : pm) sb.append(x+" ");
            sb.append('\n');
        }
        else {
            for(int i=1; i<=n; i++) {
                pm[L]=i;
                DFS(L+1);
                pm[L]=0;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pm = new int[m];
        T.DFS(0);
        System.out.println(sb);
    }
}
