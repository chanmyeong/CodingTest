// [참고문헌]: https://velog.io/@yun12343/Java-%EB%B0%B1%EC%A4%80-10974%EB%B2%88
// 중복이 불가능하기 때문에 boolean 타입의 visited 배열로 check

import java.io.*;
import java.util.*;
public class Main {
    public static int N; // 전역 변수들로 선언하는 것이 포인트
    public static int arr[];
    public static boolean visited[];
    public static void DFS(int depth) {
        if(depth == N) { // 입력받은 수까지 도달했다면(마지막 숫자라면) arr배열에 저장했던 수들 출력
            for(int i=0; i<N; i++) {
                System.out.print(arr[i]+" ");
            }
            System.out.println(); // 줄바꿈
            return;
        }

        for(int i=0; i<N; i++) {
            if(visited[i]) continue; // 이미 사용했으면 중복 불가능이므로 continue
            arr[depth] = i+1; // 출력은 0이 아닌 1부터 시작
            visited[i] = true; // 사용한 숫자 체크
            DFS(depth+1);
            visited[i] = false; // 사용 끝났으면 다시 미사용으로 바꿔주기
        }

    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        visited = new boolean[N];

        DFS(0);
    }
}
