import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int answer=0;
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            q.offer(i);
        }
        while(!q.isEmpty()) {
            for(int i=1; i<k; i++) q.offer(q.poll()); // poll() queue에서 꺼내서 반환 비어있으면 null 반환
            q.poll();
            if(q.size()==1) answer=q.poll();
        }
        System.out.println(answer);
    }
}
