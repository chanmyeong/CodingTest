// char 배열의 초기 값은 '\0'이다
import java.io.*;
import java.util.*;
public class Main {
    static char[][] ch = new char[5][15];
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<5; i++) {
            String str = sc.nextLine();
            for (int j=0; j<str.length(); j++) {
                ch[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<15; i++) {
            for(int j=0; j<5; j++) {
                if (ch[j][i] == '\0') continue;
                System.out.print(ch[j][i]);
            }
        }
    }
}
