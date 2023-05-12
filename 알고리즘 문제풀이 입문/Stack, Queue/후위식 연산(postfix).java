import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Stack<Integer> st = new Stack<>();
        int answer = 0;

        for(char x : str.toCharArray()) {
            if(Character.isDigit(x)) st.push(x-48);
            else {
                int rt = st.pop();
                int lt = st.pop();
                if(x=='+') st.push(lt+rt);
                else if(x=='-') st.push(lt-rt); // 나중에 꺼낸 lt가 연산자 앞쪽에 와야 제대로 된 계산 가능
                else if(x=='*') st.push(lt*rt);
                else if(x=='/') st.push(lt/rt);
            }
        }
        answer = st.get(0);
        System.out.println(answer);
    }
}
