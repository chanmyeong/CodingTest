// 그래프 vs 트리 : 회로의 존재 유무(다시 정점으로 회귀가능)
// 트리는 정점이 n개이면 간선은 n-1개
import java.io.*;
import java.util.*;
class Edge implements Comparable<Edge> {
    public int v1, v2, cost;
    public Edge(int v1, int v2, int cost) {
        this.v1=v1;
        this.v2=v2;
        this.cost=cost;
    }
    @Override
    public int compareTo(Edge o) {
        return this.cost-o.cost;
    }
}

public class Main {
    static int[] unf;
    public static int Find(int v) {
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
        int v = sc.nextInt();
        int e = sc.nextInt();
        int answer = 0, cnt = 0;
        unf = new int[v+1];
        for(int i=1; i<=v; i++) unf[i]=i;
        ArrayList<Edge> arr = new ArrayList<>();
        for(int i=0; i<e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            arr.add(new Edge(a, b, c));
        }
        Collections.sort(arr); // 최소비용 기준으로 오름차순 정렬
        for(Edge ob : arr) {
            int fv1 = Find(ob.v1);
            int fv2 = Find(ob.v2);
            if(fv1!=fv2) { // 두 정점이 같은 집합이 아닐 경우에만(회로가 되지 않아야만) 간선을 선택
                answer+=ob.cost; // 최소비용누적
                Union(ob.v1, ob.v2); // 간선이 선택되면 두 정점은 Union 해주어야한다.
                cnt++;
            }
            if (cnt==v-1) break; // 간선이 많을 때 시간복잡도 단축
        }
        System.out.println(answer);
    }
}
