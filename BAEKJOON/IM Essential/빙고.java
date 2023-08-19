import java.io.*;
import java.util.*;

public class Main {
    static int bingo, answer;
    static int[][] map;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[5][5];
        for(int i=0; i<5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        for(int[] i : map) {
//            System.out.println(Arrays.toString(i));
//        }

        for(int a=0; a<5; a++) {
            st = new StringTokenizer(br.readLine());
            for (int b = 0; b < 5; b++) {
                answer++;
                int num = Integer.parseInt(st.nextToken());
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (map[i][j] == num) {
                            map[i][j] = 0; // 의미없는 수로 빙고 칠하기
                        }
                    }
                }
                rowBingo();
                colBingo();
                xrightBingo();
                xleftBingo();
                if (bingo >= 3) {
                    System.out.println(answer);
                    return; // 정답 출력 후 프로그램 종료
                }
                bingo = 0;
            }
        }
    }
    private static void rowBingo() {
        for(int i=0; i<5; i++) {
            int cnt=0;
            for (int j=0; j<5; j++) {
                if(map[i][j]==0) cnt++;
            }
            if(cnt==5) bingo++;
        }
    }
    private static void colBingo() {
        for(int i=0; i<5; i++) {
            int cnt=0;
            for (int j=0; j<5; j++) {
                if(map[j][i]==0) cnt++;
            }
            if(cnt==5) bingo++;
        }
    }
    private static void xrightBingo() {
        int cnt=0;
        for(int i=0; i<5; i++) {
            if(map[i][4-i]==0) cnt++;
        }
        if(cnt==5) bingo++;
    }
    private static void xleftBingo() {
        int cnt=0;
        for(int i=0; i<5; i++) {
            if(map[i][i]==0) cnt++;
        }
        if(cnt==5) bingo++;
    }
}
