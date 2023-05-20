import java.io.*;
import java.util.*;
class Time implements Comparable<Time> {
    public int time;
    public char state;
    Time(int time, char state) {
        this.time = time;
        this.state = state;
    }
    @Override
    public int compareTo(Time ob) {
        // 'e'를 먼저 만나서 cnt를 감소시키기 위해
        if(this.time==ob.time) return  this.state-ob.state; // 시간이 같으면 상황기준으로 정렬(알파벳 오름차순)
        else return this.time-ob.time;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Time> arr = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int st= sc.nextInt();
            int et = sc.nextInt();
            arr.add(new Time(st, 's')); // 기존 정보를 바탕으로 새로운 정보 자료를 생성
            arr.add(new Time(et, 'e'));

        }

        int cnt=0; int answer=Integer.MIN_VALUE;
        Collections.sort(arr); // compareTo 기준으로 정렬
        for(Time ob : arr) {
            if(ob.state=='s') cnt++;
            else cnt--;
            answer=Math.max(answer, cnt); // 연속적으로 답이 변화할 때 주로 쓰는 문구
        }
        System.out.println(answer);
    }
}
