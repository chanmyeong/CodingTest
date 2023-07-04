import java.io.*;
import java.util.*;
public class Main {
    static int answer=0, n;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public void DFS(int x, int y, int[][] board) {
        for(int i=0; i<8; i++) {
            int nx=x+dx[i];
            int ny=y+dy[i];
            if(nx>=0 && nx<n && ny>=0 && ny<n && board[nx][ny]==1) { // 경계선 조건 필수
                board[nx][ny]=0; // 뻗어나가는 지점들 0으로 변경
                DFS(nx,ny,board);
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
                    T.DFS(i, j, arr);
                }
            }
        }
        System.out.println(answer);
    }
}
