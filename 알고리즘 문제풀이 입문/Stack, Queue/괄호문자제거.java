import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        Stack<Character> st = new Stack<>();

        for(char x : str.toCharArray()) {
            if(x==')') {
                // stack.pop()은 꺼낸 값을 반환
                while(st.pop()!='(');
            }
            else st.push(x);
        }
        for(char y : st)
            System.out.print(y);

//        for(int i=0; i<st.size(); i++)
//            System.out.print(st.get(i));
    }
}
