import java.io.*;
import java.util.*;

/**
 * 3차원 토마토
 * 토마토에 인접한 곳은 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 여섯 방향
 * 1 익은 토마토, 0 익지 않은 토마토, -1 토마토가 들어있지 않은 칸
 * <p>
 * 토마토가 다 익는 최소 일수
 * 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
 * 2 ≤ M ≤ 100, 2 ≤ N ≤ 100, 1 ≤ H ≤ 100
 */
public class Main {
    static int[] dr = {-1, 1, 0, 0, 0, 0}; // 상하좌우앞뒤
    static int[] dc = {0, 0, -1, 1, 0, 0};
    static int[] dh = {0, 0, 0, 0, 1, -1};
    static int[][][] box;
    static int M, N, H;

    static class Point {
        int r, c, h;

        public Point(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }
    static ArrayList<Point> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // c
        N = Integer.parseInt(st.nextToken()); // r
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M]; // 3차원 배열은 2차원 배열이 여러 개가 모이는 것이므로 h가 앞에 오게 됨
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(st.nextToken());
                    if (box[h][n][m] == 1) {
                        list.add(new Point(n,m,h));
                    }
                }
            }
        }
        int result = Integer.MIN_VALUE;
        bfs();
//        for (int h = 0; h < H; h++) {
//            for (int n = 0; n < N; n++) {
//                for (int m = 0; m < M; m++) {
//                    System.out.print(box[h][n][m] + " ");
//                }
//                System.out.println();
//            }
//        }
        Loop:
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 0) {
                        result = -1;
                        break Loop;
                    }
                    result = Math.max(result, box[h][n][m]);
                }
            }
        }
        if(result==1) System.out.println(0);
        else if(result==-1) System.out.println(-1);
        else System.out.println(result-1); // 0부터 시작해야 했지만 익은 토마토를 의미하는 1에서 출발했기에 -1을 빼줌
    }

    private static void bfs() {
        Queue<Point> q = new ArrayDeque<>(); // bfs 는 원래 한 점에서 시작하지만
        for (int i = 0; i < list.size(); i++) { // 모든 익은 토마토들이 동시에 익지 않은 토마토에 영향을 주기 때문에
            q.offer(list.get(i)); // 모든 익은 토마토들을 큐에 넣고 시작
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int h = cur.h;
            for (int d = 0; d < 6; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                int nh = h + dh[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || nh < 0 || nh >= H) continue;
                if (box[nh][nr][nc] == 0) { // 방문 배열 조건을 대신함
                    q.offer(new Point(nr, nc, nh)); // <<
                    box[nh][nr][nc] = box[h][r][c] + 1; // 토마토가 익는 시간을 배열에 남김으로써 방문 배열이 따로 필요 없어짐
                }
            }
        }
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class PointXYZ{
    int height;
    int row;
    int col;

    public PointXYZ(int height, int row, int col){
        this.height = height;
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 6방향을 나타내기 위한 배열
    static int rowArr[] = {-1, 0, 1, 0, 0, 0};
    static int colArr[] = {0, 1, 0, -1, 0, 0};
    static int heightArr[] = {0, 0, 0, 0, 1, -1};
    static int m, n, h;
    static int arr[][][];
    static Queue<PointXYZ> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        arr = new int[h + 1][n + 1][m + 1];

        for(int i = 1; i <= h; i++){
            for(int j = 1; j <= n; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 1; k <= m; k++){
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                    // bfs를 시작하는 노드를 큐에 추가
                    if(arr[i][j][k] == 1) queue.add(new PointXYZ(i, j, k));
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        while (!queue.isEmpty()){
            PointXYZ point = queue.poll();

            int height = point.height;
            int row = point.row;
            int col = point.col;

            for(int i = 0 ; i < 6; i++){
                int moveHeight = height + heightArr[i];
                int moveRow = row + rowArr[i];
                int moveCol = col + colArr[i];
                // 6방향으로 토마토가 익을 수 있는지 여부 확인
                if(checkPoint(moveHeight, moveRow, moveCol)){
                    // 익은 토마토를 큐에 추가
                    queue.add(new PointXYZ(moveHeight, moveRow, moveCol));
                    // 익은 토마토의 값 = 이전에 익은 토마토의 값 + 1
                    arr[moveHeight][moveRow][moveCol] = arr[height][row][col] + 1;
                }
            }
        }
        // 최대 값을 구하기 위한 변수
        int result = Integer.MIN_VALUE;

        for(int i = 1; i <= h; i++){
            for(int j = 1; j <= n; j++){
                for(int k = 1; k <= m; k++){
                    // 하나라도 익지 않은 토마토가 있다면 -1을 반환
                    if(arr[i][j][k] == 0) return -1;
                    // 토마토가 익는데 걸리는 시간을 구함
                    result = Math.max(result, arr[i][j][k]);
                }
            }
        }
        // 최대 값이 1이라면 원래부터 모두 익어있었다는 것
        if(result == 1) return 0;
        // (최대 값 - 1) --> 걸린 일수
        else return (result - 1);
    }

    private static boolean checkPoint(int height, int row, int col){
        // 주어진 범위 밖인지 검사
        if(height < 1 || height > h || row < 1 || row > n || col < 1 || col > m) return false;
        // 아직 익지 않은 토마토라면 true 반환
        if(arr[height][row][col] == 0) return true;
        // 이미 익어있거나 빈 자리라면 false 반한
        else return false;
    }
}
