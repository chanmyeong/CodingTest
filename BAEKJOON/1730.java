// O[command.length()+N^2] => O[N^2]
import java.io.*;
import java.util.*;

public class Main {
        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);

            int N = sc.nextInt();
            String command = sc.hasNext() ? sc.next() : ""; // NoSuchElementException 예외처리

            boolean[][] passVertical = new boolean[N][N];
            boolean[][] passHorizontal = new boolean[N][N];
            int curR=0, curC=0;

            for(int i=0; i<command.length(); i++) {
                char cmd = command.charAt(i);
                if(cmd == 'D') { // 해당 '명령'으로 움직일 수 있다면 지금 칸과 다음 칸에 관련(2) 흔적을 남긴다.
                    if(curR == N-1) continue;
                    passVertical[curR][curC] = passVertical[curR+1][curC] = true;
                    curR++;
                }
                else if(cmd == 'U') {
                    if(curR == 0) continue;
                    passVertical[curR][curC] = passVertical[curR-1][curC] = true;
                    curR--;
                }
                else if(cmd == 'L') {
                    if(curC == 0) continue;
                    passHorizontal[curR][curC] = passHorizontal[curR][curC-1] = true;
                    curC--;
                }
                else {
                    if(curC == N-1) continue;
                    passHorizontal[curR][curC] = passHorizontal[curR][curC+1] = true;
                    curC++;
                }
            }

            for(int i=0; i<N; i++) {
                String ans = "";
                for (int j = 0; j < N; j++) {
                    if (passVertical[i][j] && passHorizontal[i][j]) ans += "+";
                    else if(passVertical[i][j]) ans += "|";
                    else if(passHorizontal[i][j]) ans += "-";
                    else ans += ".";
                }
                System.out.println(ans);
            }
    }
}
