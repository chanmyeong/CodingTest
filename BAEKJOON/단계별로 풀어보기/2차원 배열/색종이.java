// 넓이가 100N 이 되도록 모서리만 겹쳐도 배열의 개수가 중복될 일은 없다
// 확인(방문한)배열을 만들 때에는 int형 배열보다 boolean배열을 선언하는 것이 좋다

import java.io.*;
import java.util.*;
public class Main {
    static int size = 100;
    static int total=0;

    static boolean[][] wp = new boolean[size][size];
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for(int s=0; s<num; s++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int ld = x-1; // 배열의 원소는 0부터 시작 
            int bd = y-1;

            for(int i=bd; i<bd+10; i++) {
                for (int j=ld; j<ld+10; j++) {
                    if(!wp[i][j]) {
                        wp[i][j] = true;
                        total++;
                    }
                }
            }
        }
        System.out.println(total);
    }
}
