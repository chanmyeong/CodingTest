// PriorityQueue는 PriorityQueue 조건에 부합하는 값을 우선순위로 반환
import java.io.*;
import java.util.*;
class Money implements Comparable<Money> {
    public int d,m;
    Money (int m, int d) {
        this.m=m;
        this.d=d;
    }
    @Override
    public int compareTo(Money o) {
        return o.d-this.d;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Money> arr = new ArrayList<>();
        int cnt=0; int answer=0; int due=0;
        for(int i=0; i<n; i++) {
            int m = sc.nextInt();
            int d = sc.nextInt();
            arr.add(new Money(m, d));
            if(d>due) due=d;
        }
        Collections.sort(arr); // compareTo : 날짜기준 내림차순 정렬
//        for (Money ob : arr)
//            System.out.println(ob.m+" "+ob.d);

        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder()); // default 최솟값을 우선순위
        int j=0;
        for(int i=due; i>=1; i--) {
            for( ; j<n; j++) {
                if(arr.get(j).d < i) break;
                pQ.offer(arr.get(j).m);
            }
            if(!pQ.isEmpty()) answer += pQ.poll(); // nullPointException 방지
        }
        System.out.println(answer);
    }
}
