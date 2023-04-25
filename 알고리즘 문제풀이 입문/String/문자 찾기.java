import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str  = sc.next();
        char c = sc.next().charAt(0);
        int ans = 0;

        str=str.toUpperCase();
        c=Character.toUpperCase(c);

//        for(int i=0; i<str.length(); i++)
//            if(str.charAt(i)==c) ans++;
        for(char x : str.toCharArray()) // 향상된 for문에서의 2번째 인자는 배열류, iterator 형식만 가능하다
            if(x==c) ans++;

        System.out.println(ans);
    }
}
