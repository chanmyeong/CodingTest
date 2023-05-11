// 괄호문제 대부분 Stack
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        boolean answer=true;
        Stack<Character> st = new Stack<>();

        for(char x : str.toCharArray()) {
            if(x=='(') st.push(x);
            else if(x==')') {
                if(st.isEmpty()) answer=false;
                else st.pop();
            }
        }
        if(!st.isEmpty()) answer=false;
        if(answer) System.out.println("YES");
        else System.out.println("NO");
    }
}
