import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String must = sc.next();
        String plan = sc.next();
        boolean answer = true;

        Queue<Character> q = new LinkedList<>();
        for(char x : must.toCharArray()) {
            q.offer(x);
        }
        for(int i=0; i<plan.length(); i++) {
            if(q.contains(plan.charAt(i))) {
                if(plan.charAt(i)==q.peek()) q.poll();
                else answer=false;
                // 위 2줄과 동일
                // if(plan.charAt(i)!=q.poll()) answer=false;
            }
        }
        if(!q.isEmpty()) answer=false;

        if(answer) System.out.println("YES");
        else System.out.println("NO");
    }
}
