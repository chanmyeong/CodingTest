// 프림 알고리즘(Prim's algorithm)은 가중치가 있는 연결된 무향 그래프의 모든 꼭짓점을 포함하면서
// 각 변의 비용의 합이 최소가 되는 부분 그래프인 트리, 즉 최소 비용 생성나무를 찾는 알고리즘
import java.io.*;
import java.util.*;
class Edge implements Comparable<Edge> {
    public int vex, cost;
    public Edge(int vex, int cost) {
        this.vex=vex;
        this.cost=cost;
    }
    @Override
    public int compareTo(Edge o) {
        return this.cost-o.cost;
    }
}

public class Main {
    public static int[] ch;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int answer = 0;
        ch = new int[v+1];
        ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
        for(int i=0; i<=v; i++) {
            graph.add(new ArrayList<Edge>());
        }
        for(int i=0; i<e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c)); // 무방향그래프 인접리스트
        }

        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(1,0));
        while(!pQ.isEmpty()) {
            Edge tmp = pQ.poll();
            int ev = tmp.vex;
            if(ch[ev]==0) { // 회로가 되지 않기 위해 정점 체크
                ch[ev]=1;
                answer+= tmp.cost;
                for(Edge ob : graph.get(ev)) { // if : 이미 체크한 정점방향으로의 간선을 다시 넣지 않기
                    if(ch[ob.vex]==0) pQ.offer(new Edge(ob.vex, ob.cost));
                }
            }
        }
        System.out.println(answer);
    }
}
