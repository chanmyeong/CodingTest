import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String word = sc.next();

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if ('a' <= ch && ch <= 'z') ch = (char) ('A' + ch - 'a');
            else if('A' <= ch && ch <= 'Z') ch = (char) ('a' + ch - 'A');
            System.out.print(ch);
        }
    }
}
