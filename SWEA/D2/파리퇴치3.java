import java.io.*;
import java.util.*;
public class Main {
    static int[] dpx = {-1,0,1,0};
    static int[] dpy = {0,1,0,-1};
    static int[] dmx = {1,-1,1,-1};
    static int[] dmy = {1,1,-1,-1};
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int cnt=1;
        while(t-->0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] arr = new int[n][n];
            int answer=0; // answer 초기화를 반드시 while문 안쪽에 해야한다.
            // TestCase 비교시 이전 TestCase의 answer가 더 클 경우 답이 바뀌지 않는다.

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    int sumA=arr[i][j];
                    int sumB=arr[i][j];

                    for(int k=0; k<4; k++) {
                        for(int l=1; l<m; l++) {
                            int ax = i + dpx[k]*l;
                            int ay = j + dpy[k]*l;
                            int bx = i + dmx[k]*l;
                            int by = j + dmy[k]*l;
                            if(-1<ax && ax<n && -1<ay && ay<n)
                                sumA+=arr[ax][ay];
                            if(-1<bx && bx<n && -1<by && by<n)
                                sumB+=arr[bx][by];
                        }
                    }
                    int sum=Math.max(sumA,sumB);
                    answer=Math.max(answer,sum);
                }
            }
            System.out.println("#"+cnt+" "+answer);
            cnt++;
        }
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] pcol = {-1,0,1,0};
        int[] prow = {0,1,0,-1};
        int[] xcol = {1,-1,1,-1};
        int[] xrow = {1,1,-1,-1};
 
        for(int t=1;t<=T;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] board = new int[n][n];
            int max = 0;
 
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    int sumPlus = board[i][j];
                    int sumX = board[i][j];
                    for(int k=1;k<m;k++){
                        for(int v=0;v<4;v++){
                            int nx = i+pcol[v]*k;
                            int ny = j+prow[v]*k;
 
                            if(nx >=0 && nx< n && ny >=0 && ny <n){
                                sumPlus += board[nx][ny];
                            }
                            nx = i+xcol[v]*k;
                            ny = j+xrow[v]*k;
 
                            if(nx >=0 && nx< n && ny >=0 && ny <n){
                                sumX += board[nx][ny];
                            }
                        }
                    }
                    max = Math.max(max,Math.max(sumPlus,sumX));
                }
            }
 
            sb.append("#"+t+" "+max+"\n");
        }
        System.out.println(sb);
    }
}
====================================================================================================
import java.util.Scanner;
 
class Solution {
    static int sprayPlus(int[][] matrix, int N, int M, int row, int col) {
        int total = matrix[row][col];
        for(int i = 1; i < M; i++) {
            if(row + i < N) total += matrix[row + i][col];
            if(row - i >= 0) total += matrix[row - i][col];
            if(col + i < N) total += matrix[row][col + i];
            if(col - i >= 0) total += matrix[row][col - i];
        }
        return total;
    }
     
    static int sprayCross(int[][] matrix, int N, int M, int row, int col) {
        int total = matrix[row][col];
        for(int i = 1; i < M; i++) {
            if(row + i < N && col + i < N) total += matrix[row + i][col + i];
            if(row + i < N && col - i >= 0) total += matrix[row + i][col - i];
            if(row - i >= 0 && col + i < N) total += matrix[row - i][col + i];
            if(row - i >= 0 && col - i >= 0) total += matrix[row - i][col - i];
        }
        return total;
    }
     
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
         
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
             
            int[][] matrix = new int[N][N];
             
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }
            int max = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    max = Math.max(max, Math.max(sprayPlus(matrix, N, M, i, j), sprayCross(matrix, N, M, i, j)));
                }
            }
             
            System.out.println("#" + test_case + " " + max);
        }
         
        sc.close();
    }
}
  
