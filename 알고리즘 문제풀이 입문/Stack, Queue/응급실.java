// 실패한 미완성코드
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int answer=0; int max=Integer.MIN_VALUE;
        int[] danger = new int[n];
        for(int i=0; i<n; i++)
            danger[i]=sc.nextInt();

        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++) {
            q.offer(danger[i]);
            if (max < danger[i]) max = danger[i];
        }

        while(!q.isEmpty()) {
            int l = q.poll();
            if(l < max) q.offer(l);
            else {
                answer++;
                // max를 변경할 수 있도록 해주는 매개변수를 만들어 줄 수가 없다.
                if(l == danger[m]) System.out.println(answer);
            }
        }
    }
}

====================================================================================================
// 객체를 생성하여 자료구조<참조형>에 넣는 방법
import java.io.*;
import java.util.*;

class Person {
    int id;
    int priority;
    public Person(int id, int priority) {
        this.id=id;
        this.priority=priority;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        int answer=0;
        for(int i=0; i<n; i++)
            arr[i]=sc.nextInt();

        Queue<Person> q = new LinkedList<>();
        for(int i=0; i<n; i++)
            q.offer(new Person(i,arr[i]));

        while(!q.isEmpty()) {
            Person tmp = q.poll();
            for(Person x : q) { 
                if(x.priority > tmp.priority) { // 더 큰 우선순위가 있는지 반복문을 통해 전부 비교
                    q.offer(tmp);
                    tmp=null;
                    break;
                }
            }
            if(tmp!=null) {
                answer++;
                if(tmp.id==m) System.out.println(answer);
            }
        }
    }
}
