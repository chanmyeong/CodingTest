// 정점의 개수가 많을 때 인접행렬은 비효율적 -> 인접리스트로
// graph[a][b]==1 로 확인하는 것이 아닌 갈 수 있는 정점만을 리스트에 add : 시간(공간)복잡도 단축

import java.io.*;
import java.util.*;

public class Main {
    static int n, m, answer=0;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] ch;
    public void DFS(int v) {
        if(v==n) answer++;
        else {
            for(int nv : graph.get(v)) { // ArrayList 탐색 시 for-each가 효율적
                if(ch[nv]==0) {
                    ch[nv]=1;
                    DFS(nv);
                    ch[nv]=0;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        Main T = new Main();
        graph = new ArrayList<ArrayList<Integer>>();
        ch=new int[n+1];
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b); // a번 ArrayList에 접근 후 거기에 b를 add
        }
        ch[1]=1;
        T.DFS(1);
        System.out.println(answer);
    }
}
