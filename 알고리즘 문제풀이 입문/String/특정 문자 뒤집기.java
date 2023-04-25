import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] ch = str.toCharArray();

        int lt = 0, rt=ch.length-1;
        while(lt<rt) {
            if (('A' <= ch[lt] && ch[lt] <= 'Z') || ('a' <= ch[lt] && ch[lt] <= 'z')) {
                if (('A' <= ch[rt] && ch[rt] <= 'Z') || ('a' <= ch[rt] && ch[rt] <= 'z')) {
                    char tmp = ch[lt];
                    ch[lt]=ch[rt];
                    ch[rt]=tmp;
                    lt++; rt--;
                }
                else rt--;
            }
            else lt++;
        }
        System.out.println(ch);
    }
}

====================================================================================================

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] ch = str.toCharArray();

        int lt = 0, rt=ch.length-1;
        while(lt<rt) {
            if(!Character.isAlphabetic(ch[lt])) lt++;
            else if(!Character.isAlphabetic(ch[rt])) rt--;
            else {
                char tmp = ch[lt];
                ch[lt] = ch[rt];
                ch[rt] = tmp;
                lt++; rt--;
            }
        }
        System.out.println(ch);
    }
}
