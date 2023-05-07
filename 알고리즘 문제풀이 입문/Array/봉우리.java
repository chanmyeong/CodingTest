import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n+2][n+2];
        int answer = 0;
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++)
                map[i][j] = sc.nextInt();
        }

        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++) {
               if(map[i][j]>map[i+1][j] && map[i][j]>map[i][j+1] && map[i][j]>map[i-1][j] && map[i][j]>map[i][j-1]) {
                   answer++;
               }
            }
        }
        System.out.println(answer);
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        int answer = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++)
                map[i][j] = sc.nextInt();
        }

        for(int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                boolean flag = true;
                for(int k=0; k<4; k++) {
                    int nx = i+dx[k];
                    int ny = j+dy[k];
                    // 경계선 제약조건을 앞쪽에서 걸러내야 예외처리를 할 수 있다.
                    if(nx>=0 && nx<n && ny>=0 && ny<n && map[nx][ny]>=map[i][j]) {
                        flag=false;
                        break;
                    }
                }
                if(flag) answer++;
            }
        }
        System.out.println(answer);
    }
}
