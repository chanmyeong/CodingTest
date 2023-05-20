import java.io.*;
import java.util.*;
public class Main {
    static int[][] map = new int[8][8];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int answer=0;
    public void DFS(int x, int y) {
        if(x==7 && y==7) answer++;
        else {
           for(int i=0; i<4; i++) {
               int nx = x + dx[i];
               int ny = y + dy[i];
               if (nx >= 1 && nx <= 7 && ny >= 1 && ny <= 7 && map[nx][ny] == 0) { // 경계선 + 갈 수 있는 조건
                   map[nx][ny] = 1;
                   DFS(nx, ny);
                   map[nx][ny] = 0;
               }
           }
        }
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        for(int i=1; i<=7; i++)
            for(int j=1; j<=7; j++)
                map[i][j] = sc.nextInt();
        map[1][1]=1; // 출발점 check
        T.DFS(1, 1);
        System.out.println(answer);
    }
}
