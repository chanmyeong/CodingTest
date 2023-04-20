// O[T*64*logN]
import java.io.*;
import java.util.*;

public class Main {
    public static boolean isPalindrome(int[] digit, int length) {

        for(int i=0; i<length/2; i++) {
            if(digit[i] != digit[length-1-i]) return false;
        }
        return true;
    }

    public static int convertBase(int x, int base, int[] reverseDigit) {
        int len = 0;
        while(x>0) {
            reverseDigit[len++] =  x % base;
            x /= base;
        }
        return len;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            boolean ans = false;
            for(int b=2; b<=64; b++) {
                int[] baseDigit = new int[20]; // 2^20 > 1000000
                int baseLength = convertBase(N, b, baseDigit);
                if(isPalindrome(baseDigit, baseLength)) {
                    ans = true;
                    break;
                }
            }
            bw.write(ans ? "1"+"\n" : "0"+"\n");
        }

        bw.flush();
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static boolean isPalindrome(int[] digit) {

        for(int i=0; i<digit.length/2; i++) {
            if(digit[i] != digit[digit.length-1-i]) return false;
        }
        return true;
    }

    public static int[] convertBase(int x, int B) {
        int len = 0, copyX = x;
        while(copyX>0) {
            copyX /= B;
            len++;
        }
        int[] digit = new int[len]; // 필요한 만큼만 길이를 생성
        len=0;
        while(x>0) {
            digit[len++] = x%B;
            x=x/B;
        }
        return digit;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            boolean ans = false;
            for(int b=2; b<=64; b++) {
                int[] digit = convertBase(N, b);
                if(isPalindrome(digit)) {
                    ans = true;
                    break;
                }
            }
            bw.write(ans ? "1"+"\n" : "0"+"\n");
        }

        bw.flush();
    }
}

