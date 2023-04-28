import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String str2 = str+" "; // ArrayIndexOutOfBoundsException 방지
        char[] ch = str2.toCharArray();
        int cnt = 1;
        String answer = "";

        for(int i=0; i<str.length(); i++) {
            if(ch[i]!=ch[i+1]) {
                answer += ch[i];
                if(cnt!=1) answer += cnt;
                cnt=1;
            }
            else cnt++;
        }
        System.out.print(answer);
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        str += " "; // ArrayIndexOutOfBoundsException 방지
        int cnt = 1;
        String answer = "";

        for(int i=0; i<str.length()-1; i++) {
            if(str.charAt(i)!=str.charAt(i+1)) {
                answer += str.charAt(i);
                if(cnt>1) answer += String.valueOf(cnt);
                cnt=1;
            }
            else cnt++;
        }
        System.out.print(answer);
    }
}
