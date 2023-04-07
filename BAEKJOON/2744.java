import java.util.*;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        char[] ans = s.toCharArray();
        if(s.length()<100)
        {
            for(int i=0; i<s.length(); i++) {
                char ch = s.charAt(i);
                if('A' <= ch && ch <= 'Z')  ans[i] = (char) ('a' + ch - 'A');
                else ans[i] = (char)('A' + ch - 'a');
            }
        }
        System.out.println(ans);
    }
}
