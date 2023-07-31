// input.txt
// 5
// RRRBB
// GGBBB
// BBBRR
// BBRRR
// RRRRR

====================================================================================================

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String S;
    static char[][] grid;
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        // toCharArray() 이용
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 공백문자 직전까지
        sc.nextLine(); // 개행문자까지
        grid = new char[N+1][N+1];
        visited = new boolean[N+1][N+1];
        for(int i=0; i<N; i++) {
            grid[i]=sc.nextLine().toCharArray(); // toCharArray()로 초기화 시 CharArray의 길이만큼만 다시 재구성?
        }

        // charAt() 이용
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        grid = new char[N + 1][N + 1];
//        visited = new boolean[N + 1][N + 1];
//        for (int i = 0; i < N; i++) {
//            S = br.readLine(); // RRRBB
//            for (int j = 0; j < N; j++) {
//                grid[i][j] = S.charAt(j); // R R R B B
//            }
//        }

        // 2차원 배열 출력해보기
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("==========");
        System.out.println(grid[0][4]);
        System.out.println(grid[0][5]); // 마지막 열의 원소 유무 확인하기
    }
}

// 결과 : toCharArray()로 초기화 시 ArrayIndexOutOfBoundsException
// 결과 : charAt() 이용 시 '\0' 출력
