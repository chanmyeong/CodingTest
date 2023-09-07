import java.util.*;
import java.io.*;

/*
 * 어떤 단어를 뒤에서부터 읽어도 똑같다면 그 단어를 팰린드롬
 * 수의 숫자들을 뒤에서부터 읽어도 같다면 그 수는 팰린드롬수
 * 이번 문제에서는 무의미한 0이 앞에 올 수 없다고 하자.
 *
 * 문제 해결 전략 :
 * 절반까지만 앞과 뒤에서 읽어서 하나라도 다르면 팰린드롬수가 아니다
 */
public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader는 while 바깥쪽에 위치해야
        while (true) {
            String num = br.readLine();
            if (num.equals("0")) break;

            int[] charNum = new int[num.length()];
            for (int i = 0; i < charNum.length; i++) {
                charNum[i] = num.charAt(i) - '0';
            }

            boolean flag = true;
            for (int i = 0, j = num.length() - 1; i < num.length() / 2; i++, j--) {
                if (charNum[i] != charNum[j]) {
                    flag = false;
                }
            }
//            for (int i = 0; i < num.length() / 2; i++) {
//                if (num.charAt(i) != num.charAt(num.length() - 1 - i)) flag = false;
//            }

            if (flag) System.out.println("yes");
            else System.out.println("no");
        }
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            char[] num = br.readLine().toCharArray();
            if (num[0] == '0')
                break;
            else if (num.length == 1)
                sb.append("yes").append("\n");

            for (int i = 0; i < num.length/2; i++) {
                if (num[i] != num[num.length-i-1]) {
                    sb.append("no").append("\n");
                    break;
                }
                if (i == num.length/2-1)
                    sb.append("yes").append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}
