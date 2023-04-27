import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().toUpperCase();
        String onlyAlpha = "";
        boolean fact = true;

        for(int i=0; i<str.length(); i++)
            if(Character.isAlphabetic(str.charAt(i))) onlyAlpha += str.charAt(i);

        for(int i=0; i<onlyAlpha.length()/2; i++)
            if(onlyAlpha.charAt(i)!=onlyAlpha.charAt(onlyAlpha.length()-1-i)) fact = false;

        if(fact) System.out.println("YES");
        else System.out.println("NO");
    }
}

====================================================================================================
  
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // .replace()에는 정규식 사용불가 .replaceALl()에서는 정규식 사용가능
        String str = sc.nextLine().toUpperCase().replaceAll("[^A-Z]",""); // 대문자 A-Z가 아닌것들을 빈문자로 치환

        String tmp = new StringBuilder(str).reverse().toString();

        if(str.equals(tmp)) System.out.println("YES");
        else System.out.println("NO");
    }
}
