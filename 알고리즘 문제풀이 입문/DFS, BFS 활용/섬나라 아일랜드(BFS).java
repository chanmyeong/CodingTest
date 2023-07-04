import java.io.*;
import java.util.*;
class Point {
    int x, y;
    Point(int x, int y){
        this.x=x;
        this.y=y;
    }
}
public class Main {
    static int answer=0, n;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    Queue<Point> queue = new LinkedList<>();
    public void BFS(int x, int y, int[][] board) {
        queue.add(new Point(x,y));
        while(!queue.isEmpty()) {
            Point pos = queue.poll();
            for(int i=0; i<8; i++) {
                int nx = pos.x+dx[i];
                int ny = pos.y+dy[i];
                if(nx>=0 && nx<n && ny>=0 && ny<n && board[nx][ny]==1) { // 경계선 조건 필수
                    board[nx][ny]=0; // 뻗어나가는 지점들 0으로 변경
                    queue.add(new Point(nx,ny));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] arr = new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                arr[i][j]=sc.nextInt();
        Main T = new Main();

        for(int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if(arr[i][j]==1) {
                    answer++;
                    arr[i][j]=0; // 섬 시작 출발점 0으로 변경
                    T.BFS(i, j, arr);
                }
            }
        }
        System.out.println(answer);
    }
}
