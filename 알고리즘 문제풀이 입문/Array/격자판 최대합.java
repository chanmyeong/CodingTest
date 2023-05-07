import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        int[] sum = new int[n*2+2];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++)
                map[i][j] = sc.nextInt();
        }

        for(int i=0; i<n; i++) { // 각 행의 합
            for(int j=0; j<n; j++)
                sum[i] += map[i][j];
        }

        for(int j=0; j<n; j++) { // 각 열의 합
            for(int i=0; i<n; i++)
                sum[i+n] += map[j][i];
        }

        // 두 대각선의 합
        for(int i=0; i<n; i++) {
            sum[sum.length-2] += map[i][i];
            sum[sum.length-1] += map[i][n-i-1];
        }

        Arrays.sort(sum);
        System.out.println(sum[sum.length-1]);
    }
}
