import java.io.*;
import java.util.*;

public class Main {
    public void DFS(int n) {
        if(n==0) return; // void형에서의 return은 함수 종료의 의미포함
        else {
            DFS(n - 1);
            // 재귀함수는 자료구조를 Stack Frame(매개변수-지역변수-복귀주소) 사용
            // 호출스택에 따라 나중에 실행 DFS(0) return 시 pop(); > DFS(1)로 복귀하고 할 일 마치고 종료반복 > DFS(2) > DFS(3)
            System.out.print(n+" ");
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main T = new Main();
        T.DFS(3);
    }
}
