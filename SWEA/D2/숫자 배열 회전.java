//            arr[maxi][j0] arr[maxi-1][j0] arr[maxi-2][j0]   arr[maxi][maxj] arr[maxi][maxj-1] arr[maxi][maxj-2]        arr[i][maxj] arr[i+1][maxj] arr[i+2][maxj]
//            arr[maxi][j1] arr[maxi-1][j1] arr[maxi-2][j1]   arr[maxi-1][maxj] arr[maxi-1][maxj-1] arr[maxi-1][maxj-2]  arr[i][maxj-1] arr[i+1][maxj-1] arr[i+2][maxj-1]
//            arr[maxi][j2] arr[maxi-1][j2] arr[maxi-2][j2]   arr[maxi-2][maxj] arr[maxi-2][maxj-1] arr[maxi-2][maxj-2]  arr[i][maxj-2] arr[i+1][maxj-2] arr[i+2][maxj-2]

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int cnt=1;
        while(t-->0) {
            int n = sc.nextInt();
            int[][] arr = new int[n][n];
            for(int i=0; i<n; i++)
                for(int j=0; j<n; j++)
                    arr[i][j] = sc.nextInt();

            int[][] shift = new int[n][n*3];
            int k=0, l=0;
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++)
                    shift[i][j] = arr[n-1-j][i];
                for(int j=n; j<2*n; j++) {
                    shift[i][j] = arr[n-1-i][n-1-k++];
                    if(k==n) k=0;
                }
                for(int j=2*n; j<3*n; j++) {
                    shift[i][j] = arr[l++][n-1-i];
                    if (l==n) l=0;
                }
            }

            System.out.println("#"+cnt);
            // 배열출력
            int num=0;
            for(int i=0; i<n; i++) {
                for (int j = 0; j < n * 3; j++) {
                    System.out.print(shift[i][j]); num++;
                    if(num%n==0) System.out.print(" ");
                }
                System.out.println();
            }
            cnt++;
        }
    }
}
====================================================================================================

import java.io.*;
import java.util.*;
public class Main {
    public static int[][] Rotation(int[][] arr) { // 함수 하나로 90,180,270 모두 처리
        int[][] result = new int[arr.length][arr.length];

        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                result[i][j] = arr[arr.length-j-1][i];
            }
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int cnt=1;
        while(t-->0) {
            int n = sc.nextInt();
            int[][] arr = new int[n][n];
            for(int i=0; i<n; i++)
                for(int j=0; j<n; j++)
                    arr[i][j] = sc.nextInt();

            int[][] result_90 = Rotation(arr);
            int[][] result_180 = Rotation(result_90);
            int[][] result_270 = Rotation(result_180);

            System.out.println("#"+cnt);
            // 배열출력
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    System.out.print(result_90[i][j]);
                }
                System.out.print(" ");
                for(int j=0; j<n; j++) {
                    System.out.print(result_180[i][j]);
                }
                System.out.print(" ");
                for(int j=0; j<n; j++) {
                    System.out.print(result_270[i][j]);
                }
                System.out.println();
            }
            cnt++;
        }
    }
}
