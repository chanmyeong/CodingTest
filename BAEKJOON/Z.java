// [참고문헌]: https://wiselog.tistory.com/133
// [참고문헌]: https://ilmiodiario.tistory.com/140

import java.io.*;
import java.util.*;

/**
 * 2^N × 2^N인 2차원 배열을 Z모양으로 탐색
 * 좌상, 우상, 좌하, 우하
 * N > 1인 경우, 배열을 크기가 2N-1 × 2N-1로 4등분 한 후에 재귀적으로 순서대로 방문
 * N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하는 프로그램
 * 1 ≤ N ≤ 15
 * 0 ≤ r, c < 2N
 */
public class Main {
    static int N,r,c;
    static int count=0; // 방문 순서
    private static void find(int size, int r, int c) {
        if(size == 1) {
            return;
        }
        if(r < size/2 && c < size/2) { // 1사분면
            find(size/2, r, c);
        }
        else if(r < size/2 && c >= size/2) { // 2사분면
            count += size * size / 4;
            find(size/2, r, c - size/2);
        }
        else if(r >= size/2 && c < size/2) { // 3사분면
            count += (size * size / 4) * 2;
            find(size/2, r - size/2, c);
        }
        else { // 4사분면
            count += (size * size / 4) * 3;
            find(size/2, r - size/2, c - size/2);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2,N); // 1변의 길이
        find(size,r,c);
        System.out.println(count);
    }
}
