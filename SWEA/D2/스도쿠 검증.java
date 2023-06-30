import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int cnt=1;
        while(t-->0) {
            int[][] sudoku = new int[9][9];
            boolean solve=true; int answer=0;

            for(int i=0; i<9; i++) {

                for(int j=0; j<9; j++) {
                    sudoku[i][j] = sc.nextInt();
                }
            }
            // 행체크
            for(int i=0; i<9; i++) {
                int[] arr = new int[9];
                for(int j=0; j<9; j++) {
                    arr[(sudoku[i][j])-1]++;
                    // 스도쿠 내부 숫자는 1~9, arr 인덱스는 0~8이므로 -1
                }
                for(int k=0; k<9; k++) {
                    if(arr[k]==0) {
                        solve=false;
                        break;
                    }
                }
            }
            // 열체크
            for(int i=0; i<9; i++) {
                int[] arr = new int[9];
                for(int j=0; j<9; j++) {
                    arr[(sudoku[j][i])-1]++;
                }
                for(int k=0; k<9; k++) {
                    if(arr[k]==0) {
                        solve=false;
                        break;
                    }
                }
            }
            // 격자체크
            for(int i=0; i<=6; i+=3) {
                for(int j=0; j<=6; j+=3) {
                    int[] arr = new int[9];
                    int r = i+3;
                    int c = j+3;
                    for(int a=i; a<r; a++) {
                        for(int b=j; b<c; b++) {
                            arr[(sudoku[a][b])-1]++;
                        }
                    }
                    for(int k=0; k<9; k++) {
                        if(arr[k]==0) {
                            solve=false;
                            break;
                        }
                    }
                }
            }

            if(solve) answer=1;
            System.out.println("#"+cnt+" "+answer);
            cnt++;
        }
    }
}
