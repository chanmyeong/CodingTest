// 1차원 배열 dis에 BFS 이용하여 최소이동 간선수를 기록 (2차원배열로 확장가능)
import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] ch, dis;
    public void BFS(int v) {
        Queue<Integer> Q = new LinkedList<>();
        ch[v]=1;
        dis[v]=0;
        Q.offer(v);
        while(!Q.isEmpty()) {
            int cv = Q.poll();
            for(int nv : graph.get(cv)) {
                if(ch[nv]==0) {
                    ch[nv]=1;
                    Q.offer(nv);
                    dis[nv]=dis[cv]+1; // 다음노드기록 = 이전노드기록+1
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
        dis=new int[n+1];
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b); // a번 ArrayList에 접근 후 거기에 b를 add
        }
        T.BFS(1);
        for(int i=2; i<=n; i++)
            System.out.println(i+" : "+dis[i]);
    }
}

====================================================================================================
// 1차원 배열 dis에 BFS 이용하여 level을 기록
import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] ch, dis;
    public void BFS(int v) {
        Queue<Integer> Q = new LinkedList<>();
        int L=0;
        Q.offer(v);
        while(!Q.isEmpty()) {
            int len = Q.size();
            for(int i=0; i<len; i++) {
                int cur = Q.poll();
                dis[cur]=L;
                for(int nv : graph.get(cur)) {
                    if(ch[nv]==0) {
                        ch[nv]=1;
                        Q.offer(nv);
                    }
                }
            }
            L++;
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        Main T = new Main();
        graph = new ArrayList<ArrayList<Integer>>();
        ch=new int[n+1];
        dis=new int[n+1];
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b); // a번 ArrayList에 접근 후 거기에 b를 add
        }
        T.BFS(1);
        ch[1]=1;
        for (int i = 2; i <= n; i++) {
            System.out.println(i + " : " + dis[i]);
        }
    }
}
