import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while(n-- > 0) {
            String word = sc.next();
            for(int i=word.length()-1; i>=0; i--) {
                System.out.print(word.charAt(i));
            }
            System.out.println();
        }
    }
}

====================================================================================================
// String 연산이 많을 때 StringBuilder 고려
// StringBuilder는 원본 String객체에 대한 여러 연산시 새로운 String객체를 생성x (메모리 낭비x)
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] str = new String[n];

        for(int i=0; i<n; i++) {
            str[i]=sc.next();
        }

        ArrayList<String> answer = new ArrayList<>();

        for(String x : str) {
            String tmp = new StringBuilder(x).reverse().toString();
            answer.add(tmp);
        }
        
        for(String x : answer)
            System.out.println(x);
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] str = new String[n];

        for(int i=0; i<n; i++) {
            str[i]=sc.next();
        }

        ArrayList<String> answer = new ArrayList<>();

        for(String x : str) {
            char[] s = x.toCharArray();
            int lt=0, rt=x.length()-1;
            while(lt<rt) {
                char tmp = s[lt];
                s[lt]=s[rt];
                s[rt]=tmp;
                lt++;
                rt--;
            }
            String tmp = String.valueOf(s); // valueOf는 static method
            answer.add(tmp);
        }


        for(String x : answer)
            System.out.println(x);
    }
}
