import java.io.*;
import java.util.*;

/**
 * 케빈 베이컨은 미국 헐리우드 영화배우들 끼리 케빈 베이컨 게임을 했을때 나오는 단계의 총 합이 가장 적은 사람
 * 케빈 베이컨의 수가 가장 작은 사람
 * 케빈 베이컨 수는 모든 사람과 케빈 베이컨 게임을 했을 때, 나오는 단계의 합이다.
 *
 * 한 점에서의 bfs level의 합이 가장 적은 사람 구하기
 *
 * 유저의 수 N (2 ≤ N ≤ 100)과 친구 관계의 수 M (1 ≤ M ≤ 5,000)
 * 친구 관계 :
 * A와 B가 같은 경우는 없다.
 * A와 B가 친구이면, B와 A도 친구
 * 친구 관계는 중복되어 들어올 수도 있으며,
 * 친구가 한 명도 없는 사람은 없다. 또, 모든 사람은 친구 관계로 연결되어져 있다
 * 사람의 번호는 1부터 N까지이며, 두 사람이 같은 번호를 갖는 경우는 없다.
 *
 * 케빈 베이컨의 수가 가장 작은 사람을 출력한다.
 * 그런 사람이 여러 명일 경우에는 번호가 가장 작은 사람을 출력
 */
public class Main {
    static int N,M;
    static int[][] adjMatrix;
    static boolean[] visited;
    static class Point {
        int num; int value;
        public Point(int num, int value) {
            this.num = num;
            this.value = value;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken()); // 유저의 수
        M = Integer.parseInt(st.nextToken()); // 친구 관계의 수
        adjMatrix = new int[N + 1][N + 1];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        //solve
        int answer = 0; // 최소 케빈 베이컨 수를 가진 번호가 가장 작은 사람
        int minCnt = Integer.MAX_VALUE; // 최소 케빈 베이컨 수

        for(int i=1; i<=N; i++) {
            visited = new boolean[N+1];
            int cnt = bfs(i);
            if(minCnt > cnt) { // 등호 포함X, 여러 명일 경우에는 번호가 가장 작은 사람 출력
                minCnt = cnt;
                answer = i;
            }
        }

        System.out.println(answer);
    }
    private static int bfs(int start) {
        Queue<Point> queue = new ArrayDeque<>();
        int sum = 0;

        visited[start] = true;
        queue.offer(new Point(start, 0));

        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            int n = cur.num;
            sum += cur.value;

            for(int i=1; i<=N; i++) {
                if(adjMatrix[n][i]==1 && !visited[i]) {
                    visited[i]=true;
                    queue.offer(new Point(i, cur.value+1));
                }
            }
        }
        return sum;
    }
}
