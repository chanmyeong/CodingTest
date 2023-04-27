import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        char t = sc.next().charAt(0);

        int[] word = new int[s.length()];
        int p = 1000;

        for(int i=0; i<s.length(); i++) { // 왼쪽의 t를 기준으로 떨어진 거리
            if (s.charAt(i) == t) {
                p = 0;
                word[i] = p;
            }
            else {
                p++;
                word[i] = p;
            }
        }
        p=1000;
        for(int i=s.length()-1; i>=0; i--) { // 오른쪽의 t를 기준으로 떨어진 거리
            if (s.charAt(i) == t) {
                p = 0;
                word[i] = p;
            }
            else {
                if(word[i]>p) {
                    p++;
                    word[i] = p;
                }
            }
        }

        for(int i=0; i<word.length; i++) {
            System.out.print(word[i] + " ");

        }
    }
}

====================================================================================================
// 위의 코드를 조금 더 다듬은 코드
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        char t = sc.next().charAt(0);

        int[] word = new int[s.length()];
        int p = 1000;

        for(int i=0; i<s.length(); i++) { // 왼쪽의 t를 기준으로 떨어진 거리
            if (s.charAt(i) == t) {
                p = 0;
                word[i] = p;
            }
            else {
                p++;
                word[i] = p;
            }
        }
        p=1000;
        for(int i=s.length()-1; i>=0; i--) { // 오른쪽의 t를 기준으로 떨어진 거리
            if (s.charAt(i) == t) p=0;
            else {
                p++;
                word[i] = Math.min(word[i], p);
            }
        }

        for(int i=0; i<word.length; i++) {
            System.out.print(word[i] + " ");

        }
    }
}
