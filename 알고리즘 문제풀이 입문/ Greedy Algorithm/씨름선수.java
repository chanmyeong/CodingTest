// 복습) 좌표정렬.java
// 선정렬 + 최선의 선택 -> 그리디로 쳐준다(포괄적인 그리디)
import java.io.*;
import java.util.*;
class Body implements Comparable<Body> {
    public int h, w;
    Body(int h, int w) {
        this.h = h;
        this.w = w;
    }
    @Override
    public int compareTo(Body o) {
        return o.h-this.h; // this-o가 음수값이어야 오름차순 (여기선 o-this로 반대가 되었으므로 내림차순)
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Body> arr = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            arr.add(new Body(h, w));
        }

        int cnt=0;
        Collections.sort(arr); // 재정의된 compareTo에 의해 arr정렬
        int max = Integer.MIN_VALUE; // 1번째 사람은 무조건 선발
        for(Body ob : arr) { // 내림차순된 키를 기준으로 몸무게만 비교
            if(ob.w > max) {
                max=ob.w;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
