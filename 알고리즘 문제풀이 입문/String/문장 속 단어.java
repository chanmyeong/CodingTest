import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        StringTokenizer st = new StringTokenizer(str, " ");
        String[] words = new String[st.countTokens()];
        int wordsIndex = 0;
        while(st.hasMoreTokens()) {
            words[wordsIndex] = st.nextToken();
            wordsIndex++;
        }
        int wordMax = 0;
        int wordMaxIdx = 0;
        for(int i=0; i<words.length; i++)
            if(words[i].length() > wordMax) {
                wordMax = words[i].length();
                wordMaxIdx = i;
            }
        System.out.println(words[wordMaxIdx]);
    }
}

====================================================================================================
  
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        String answer = "";
        int m = Integer.MIN_VALUE;
        String[] s = str.split(" ");

        for(String x : s) {
            int len = x.length();
            if(len>m) {
                m=len;
                answer=x;
            }
        }
        System.out.println(answer);
    }
}

====================================================================================================

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        String answer = "";
        int m = Integer.MIN_VALUE, pos;

        while((pos = str.indexOf(' ')) != -1) {
            String tmp = str.substring(0, pos);
            int len = tmp.length();
            if(len>m){ // >= (x) 앞쪽 단어유지
                m=len;
                answer=tmp;
            }
            str=str.substring(pos+1);
        }
        if(str.length()>m) answer=str; // 마지막 단어를 포함시키기 위함
        System.out.println(answer);
    }
}
  
