// Union&Find : 서로소 집합(Disjoint-set)을 만들 때 사용하는 알고리즘
import java.io.*;
import java.util.*;
public class Main {
    static int[] unf;
    public static int Find(int v) { // 매개변수의 집합 번호를 반환 (Find&Union 암기)
        if(v==unf[v]) return v;
        else return unf[v]=Find(unf[v]); // memoization(unf[v]=)
    }
    public static void Union(int a ,int b) {
        int fa = Find(a);
        int fb = Find(b);
        if(fa!=fb) unf[fa]=fb;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        unf = new int[n+1];
        for(int i=1; i<=n; i++) unf[i]=i;
        for(int i=1; i<=m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            Union(a,b);
        }
        int a = sc.nextInt();
        int b = sc.nextInt();
        int fa = Find(a);
        int fb = Find(b);
        if(fa==fb) System.out.println("YES");
        else System.out.println("NO");
    }
}
