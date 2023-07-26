// [참고문헌]: https://dy-coding.tistory.com/entry/%EB%B0%B1%EC%A4%80-15663%EB%B2%88-N%EA%B3%BC-M-9-java

import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static int[] nArr, out;
    static boolean[] visited;
    public static void DFS(int L) {
        if(L == M) { // 깊이에 도달했을 때
            for(int i = 0; i<M; i++)
                System.out.print(out[i]+" "); // 출력할 배열에 골랐던 자연수들 출력
            System.out.println();
        }
        else { // 그렇지 못할 때
            int before = 0; // (1 ≤ M ≤ N ≤ 8)
            for(int i = 0; i<N; i++) {
                if (visited[i]) // 골랐으면 skip
                    continue;
                if(before != nArr[i]) { // before의 초깃값 통과 및 before의 초기화
                    visited[i] = true; // 고르지 않은 다음 자연수 골라주기
                    out[L] = nArr[i]; // 해당 자연수를 출력할 배열과
                    before = nArr[i]; // 이전 값을 저장하는 변수에 저장
                    DFS(L+1);
                    visited[i] = false; // 다음 탐색을 위해 골랐던거 초기화
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        nArr = new int[N];
        out = new int[M];
        visited = new boolean[N];
        for(int i=0; i<N; i++) {
            nArr[i] = sc.nextInt();
        }
        Arrays.sort(nArr); // 수열은 사전 순으로 증가해야함, 미리 정렬
        DFS(0);
    }
}
