import java.io.*;
import java.util.*;
class Point {
    public int x, y;
    public Point(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
public class Main {
    static int[][] board, dis;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public void BFS(int x, int y) {
        Queue<Point> Q = new LinkedList<>(); // queue에 좌표를 넣을 때는 Point 객체 만들어서 사용
        Q.offer(new Point(x, y));
        board[x][y]=1; // 출발점 체크
        while(!Q.isEmpty()) {
            Point tmp = Q.poll();
            for(int i=0; i<4; i++) {
                int nx = tmp.x+dx[i];
                int ny = tmp.y+dy[i];
                if(nx>=1 && nx<=7 && ny>=1 && ny<=7 && board[nx][ny]==0) {
                    board[nx][ny]=1;
                    Q.offer(new Point(nx,ny));
                    dis[nx][ny]=dis[tmp.x][tmp.y]+1; // dis 2차원
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        board = new int[8][8];
        dis = new int[8][8];
        for(int i=1; i<=7; i++)
            for(int j=1; j<=7; j++)
                board[i][j] = sc.nextInt();
        T.BFS(1, 1);
        if (dis[7][7]==0) System.out.println(-1);
        else System.out.println(dis[7][7]);
    }
}
