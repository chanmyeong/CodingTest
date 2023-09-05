import java.util.*;
import java.io.*;
/**
 * 4개의 직사각형 밑변은 모두 가로축에 평행
 * 4개의 직사각형들은 서로 떨어져 있을 수도 있고, 겹쳐 있을 수도 있고,
 * 하나가 다른 하나를 포함할 수도 있으며, 변이나 꼭짓점이 겹칠 수도 있다.
 * 직사각형들이 차지하는 면적 구하기
 * 왼쪽 아래 꼭짓점의 x좌표, y좌표, 오른쪽 위 꼭짓점의 x좌표, y좌표, 1이상 100이하 정수
 *
 * 문제 해결 전략 (처음) :
 * 2차원 배열을 1차원으로 -> 배열의 인덱스를 다루는 방식이라 점의 개수 세기를 1차원으로 만드는 것 밖에 되지 않음
 * 2차원 boolean 배열 (r,c) (r+1,c) (r,c+1) (r+1,c+1) 이면 크기 +1 cnt
 *
 * 문제 해결 전략 (수정) :
 * 점의 개수가 곧 직사각형의 면적이 되도록 값 저장
 * 저장된 개수 세기
 */
public class Main {
    static int cnt=0; // 1*1 직사각형 면적 개수
    static boolean[][] grid = new boolean[101][101]; // 1부터 시작
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int t=0; t<4; t++) {
            st = new StringTokenizer(br.readLine()," ");
            int leftR = Integer.parseInt(st.nextToken());
            int leftC = Integer.parseInt(st.nextToken());
            int rightR = Integer.parseInt(st.nextToken());
            int rightC = Integer.parseInt(st.nextToken());

//            for(int i=leftR; i<=rightR; i++) {
            for(int i=leftR; i<rightR; i++) { // rightR 이전까지만 값을 저장해서 점의 개수가 곧 면적이 됨
                for(int j=leftC; j<rightC; j++) {
                    grid[i][j] = true;
                }
            }
        }
//        printGrid();
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                // 직사각형끼리 1칸만 떨어진 경우, 실제 면적이 아니더라도 조건을 만족해 크기가 세지는 예외가 존재
//                if(grid[i][j] && grid[i+1][j] && grid[i][j+1] && grid[i+1][j+1]) {
                if(grid[i][j]) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
    private static void printGrid() {
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }
}
