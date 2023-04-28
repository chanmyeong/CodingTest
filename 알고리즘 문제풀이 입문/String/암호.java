import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String code = sc.next();

        while(n-- > 0) {
            String alpha = code.substring(0,7);
            code = code.substring(7);
            alpha = alpha.replace('#','1');
            alpha = alpha.replace('*','0');
            char[] alphaArray = alpha.toCharArray();

            int num = 0;
            if(alphaArray[0]=='1') num += 64;
            if(alphaArray[1]=='1') num += 32;
            if(alphaArray[2]=='1') num += 16;
            if(alphaArray[3]=='1') num += 8;
            if(alphaArray[4]=='1') num += 4;
            if(alphaArray[5]=='1') num += 2;
            if(alphaArray[6]=='1') num += 1;

            System.out.print((char)num);
        }
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String code = sc.next();

        while(n-- > 0) {
            String alpha = code.substring(0,7);
            code = code.substring(7);
            alpha = alpha.replace('#','1').replace('*','0');
            int num = Integer.parseInt(alpha, 2); // 2진수로
            
            System.out.print((char)num);
        }
    }
}
