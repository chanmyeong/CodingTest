// 좌표 -> Point class 생성
// 정렬 기준을 재정의해서 Collections.sort()
// Comparable -> compareTo(T o) 자기 자신과 매개변수의 객체를 비교
// Comparator -> compare(T o1, T o2) 두 매개변수의 객체를 비교
// [참고 문헌]: https://st-lab.tistory.com/243

import java.io.*;
import java.util.*;
class Point implements Comparable<Point>{
    public int x,y;
    Point(int x, int y) {
        this.x=x;
        this.y=y;
    }
    @Override
    public int compareTo(Point o) {
        if(this.x==o.x) return this.y-o.y; // (this->o) 순서로 하려면 (작은 값이 빼기연산자 앞에) 음수값을 리턴
        else return this.x-o.x; // 내림차순을 원하면 반대로
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Point> arr = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            arr.add(new Point(x, y));
        }

        Collections.sort(arr);

        for(Point o : arr) {
            System.out.println(o.x+" "+o.y);
        }
    }
}
