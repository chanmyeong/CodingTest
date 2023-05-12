import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Stack<Character> st = new Stack<>();
        int answer=0;
        
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i)=='(') st.push(str.charAt(i));
            else {
                st.pop();
                // 문자열에서의 인덱스를 비교해야하므로 향상된 for가 아닌 일반 for를 사용하는 것이 적합하다
                // if (st.peek()=='(') -> X : 스택이 아닌 문자열에서 조건을 따져야하는데 엉뚱한 곳에서 헤맸다
                if(str.charAt(i-1)=='(') answer+=st.size();
                else answer++;
            }
        }
        System.out.println(answer);
    }
}
