// [참고문헌]: https://st-lab.tistory.com/267
// [참고문헌]: https://passionfruit200.tistory.com/439

import java.io.*;
import java.util.*;

/**
 * 숫자 카드는 정수 하나가 적혀져 있는 카드
 * 숫자 카드 N개를 가지고 있다. 정수 M개
 * 이 수가 적혀있는 숫자 카드를 상근이가 몇 개 가지고 있는지 구하기
 *
 * 입력
 * N(1 ≤ N ≤ 500,000)
 * 숫자 카드에 적혀있는 수는 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.
 * M(1 ≤ M ≤ 500,000)
 * 몇 개 가지고 있는 숫자 카드인지 구해야 할 M개의 정수
 *
 * 출력
 * 각 수가 적힌 숫자 카드를 상근이가 몇 개 가지고 있는지를 공백으로 구분해 출력
 *
 * 문제 해결 전략 :
 * HashMap
 * 이분 검색
 */
public class Main {
    static int N,M;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(map.containsKey(n)) map.put(n, map.get(n)+1);
            else map.put(n,1);
        }
        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine()," ");
        for(int i=0; i<M; i++) {
            int m = Integer.parseInt(st.nextToken());
            if(map.containsKey(m)) sb.append(map.get(m));
            else sb.append("0");
            sb.append(" ");
        }
        
        System.out.println(sb);
    }
}
====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int cards[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            cards[i] = n;
        }

        Arrays.sort(cards);

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine()," ");
        for(int i=0; i<M; i++) {
            int m = Integer.parseInt(st.nextToken()); // m=target
            int upperBoundValue = upperBound(m);
            int lowerBoundValue = lowerBound(m);
            sb.append(upperBoundValue-lowerBoundValue).append(" ");
        }

        System.out.println(sb);
    }

    //target의 가장 작은 Index
    public static int lowerBound(int target) {
        int startIdx = 0;
        int endIdx = N;

        while(startIdx < endIdx) { // startIdx endIdx가 같아질 때 종료, 등호(=)가 들어갈 경우 무한루프
            int midIdx = (startIdx + endIdx) / 2;
            if(target <= cards[midIdx]) { // target과 cards[mid]가 같다면 endIdx를 줄인다
                endIdx = midIdx;
            } else {
                startIdx = midIdx + 1;
            }
        }
        // startIdx를 return 하던 endIdx를 return 하던 결과는 동일
        return startIdx;
    }

    //target의 가장 큰 Index 보다 한 칸 더 높음
    public static int upperBound(int target) {
        int startIdx = 0;
        int endIdx = N;

        while(startIdx < endIdx) { // startIdx endIdx가 같아질 때 종료, 등호(=)가 들어갈 경우 if절 조건에서 ArrayIndexOutOfBoundsException
            int midIdx = (startIdx + endIdx ) / 2;
            if(target < cards[midIdx]) {
                endIdx = midIdx;
            } else { // target과 cards[mid]가 같다면 startIdx를 줄인다
                startIdx = midIdx + 1;
            }
        }
        return endIdx;
    }
    // 중복 원소의 경우 == target과 cards[mid]가 같은 경우가 여러 개일 때
}
