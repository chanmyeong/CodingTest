import java.util.*;
import java.io.*;
/**
 * 한 컴퓨터가 웜 바이러스에 걸리면 네트워크 상 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다.
 * 1번 컴퓨터가 웜 바이러스에 걸렸다
 * 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력
 * 컴퓨터의 수(100 이하인 양의 정수)와
 * 네트워크 상에서 서로 연결되어 있는 정보(네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수)
 *
 * 문제 해결 전략 :
 * 가중치 없는 무향 그래프 탐색, 1번 제외 탐색 노드 개수 파악
 * 방문 배열 필요(그래프), BFS가 DFS보다 빠르다
 * 1번이 누구와도 연결 안되어있으면 0이 답
 */
// BFS
public class Main {
    static int N, M, cnt=0;
    static class Node {
        int vertex; Node next;
        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", next=" + next +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 컴퓨터 수
        M = Integer.parseInt(br.readLine()); // 연결 간선 수

        Node[] adjList = new Node[N+1]; // 1부터 시작
        boolean[] visited = new boolean[N+1];

        for(int i=1; i<=M; i++) {
            String[] line = br.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }

//        for(Node node : adjList) {
//            System.out.println(node);
//        }
        visited[1] = true;
        BFS(adjList, visited);
//        System.out.println(Arrays.toString(visited));
        System.out.println(cnt);

    }
    private static void BFS(Node[] adjList, boolean[] visited) {
        if(adjList[1]==null) return; // 1번이 누구와도 연결 되어있지 않은 경우
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(adjList[1]);

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            for(Node temp=current; temp!=null; temp=temp.next) {
                if(!visited[temp.vertex]) {
                    cnt++;
                    visited[temp.vertex] = true;
                    queue.offer(adjList[temp.vertex]);
                }
            }
        }

    }
}
====================================================================================================
// DFS
import java.io.*;
public class Main {
    static int[][] arr;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        arr = new int[N+1][N+1];
        visited = new boolean[N+1];

        for(int i=0;i<M;i++){
            String[] tmp = br.readLine().split(" ");
            int x = Integer.parseInt(tmp[0]);
            int y = Integer.parseInt(tmp[1]);
            arr[x][y] = 1;
            arr[y][x] = 1;
        }

        DFS(1);

        int ans = 0;
        for(int i=1;i<N+1;i++){ // DFS 탐색 후 방문한 정점들 개수 세기
            if(visited[i]) {
                ans++;
            }
        }
        System.out.println(ans-1);
    }
    static void DFS(int index){
        visited[index] = true;
        
        for(int i = 1; i< arr[index].length; i++){
            if(arr[index][i]>0 && !visited[i]){ // 인접 정점이고 방문하지 않았으면
                DFS(i);
            }
        }
    }

}
====================================================================================================
// DFS + 향상된 for문 + readInt()
import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int cnt=0;
    public static void main(String args[]) throws Exception {
        int v = readInt();
        int e = readInt();
        adj = new ArrayList[v+1];
        for(int i=1;i<=v;i++)
            adj[i] = new ArrayList<>();
        visited = new boolean[v+1];

        while(e-->0){
            int v1 = readInt(), v2 = readInt();
            adj[v1].add(v2); adj[v2].add(v1);
        }
        dfs(1);
        System.out.println(cnt-1);
    }

    static void dfs(int i){
        if(visited[i]) return;
        visited[i] = true; cnt++;
        for(int next : adj[i])
            if(!visited[next])
                dfs(next);
    }
    //////////////////////////////////////////////////

    static int readInt() throws IOException {
        int n = 0;
        boolean isNegative = false;
        while (true) {
            int input = System.in.read();
            if (input<=32)
                return isNegative ? n * -1 : n;
            else if(input=='-')
                isNegative = true;
            else
                n = (n<<3) + (n<<1) + (input&15);
        }
    }
}
====================================================================================================
// Union-Find
import java.io.*;
import java.util.*;
public class Main {
    static int[] computer;
    static int[] rank ;
    public static void main(String[] args)throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader bf = new BufferedReader(new FileReader(new File("data.txt")));

        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        computer = new int[n+1]; // 1부터 시작
        rank = new int[n+1];
        for(int i = 0 ; i <= n ; i++){
            computer[i] = i;
            rank[i] = 1;
        }
        for(int i = 0 ; i < m ; i ++){
            String[] tmp = bf.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            union(a,b);
        }
        int ans = 0;
        for(int i = 2 ; i <= n ; i ++){ // 2부터
            if(find(i) == 1){ // 대표자가 1인 정점들은 연결되어있음
                ans ++; // 개수 증가
            }
        }
        System.out.println(ans);
    }
    static int find(int a){
        if(a == computer[a]) {
            return a;
        }
        return computer[a] = find(computer[a]);
    }
    static void union(int x , int y){
        x = find(x);
        y = find(y);
        if(x == y) // 대표자가 같으므로 union X
            return ;
        if(y == 1){ // 뒤쪽 정점이 1인 경우, 1번 정점과 연결되어있으므로 바꿔서 아래 줄 수행
            int tmp = x;
            x = y;
            y = tmp;
        }
        if(x == 1 || rank[x] >= rank[y]){ // x가 1인 경우 union(x<-y), rank 관리
            computer[y] = x;
            rank[x] += rank[y];
        }
        else{ // union(y<-x), rank 관리
            computer[x] = y;
            rank[y] += rank[x];
        }
    }
}
