// [참고문헌]: https://st-lab.tistory.com/108

import java.io.*;
import java.util.*;

/**
 * 산술평균 : N개의 수들의 합을 N으로 나눈 값, 소수점 이하 첫째 자리에서 반올림한 값을 출력
 * 중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
 * 최빈값 : N개의 수들 중 가장 많이 나타나는 값, 여러 개 있을 때에는 최빈값 중 두 번째로 작은 값을 출력
 * 범위 : N개의 수들 중 최댓값과 최솟값의 차이
 * <p>
 * N(1 ≤ N ≤ 500,000)이 주어진다.
 * 단, N은 홀수이다. 그 다음 N개의 줄에는 정수들이 주어진다.
 * 입력되는 정수의 절댓값은 4,000을 넘지 않는다.
 */
public class Main {
    static int N;
    static int[] arr = new int[8001]; // 입력값의 범위 : -4000 ~ 4000
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        /*
         *  sum = 총 합계
         *  max = 최댓값
         *  min = 최솟값
         *  median = 중앙값
         *  mode = 최빈값
         */
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int n = 0; n < N; n++) {
            int value = Integer.parseInt(br.readLine());
            sum += value;
            arr[value + 4000]++;
            if (max < value) {
                max = value;
            }
            if (min > value) {
                min = value;
            }
        }

        sb.append(mean(sum)).append("\n");
        sb.append(median(max, min)).append("\n");
        sb.append(mode(max, min)).append("\n");
        sb.append(range(max, min)).append("\n");
        System.out.println(sb);
    }
    private static int range(int max, int min) {
        return max - min;
    }

    private static int mode(int max, int min) {
        // median 과 mode 는 -4000~4000 을 제외한 수로 초기화하면 된다.
        int mode = 10000;
        int mode_max = 0; // 최빈값의 최댓값
        boolean flag = false; // 이전의 동일한 최빈값이 1번만 등장하였을 경우 true, 아닐 경우 false
        for (int i = min + 4000; i <= max + 4000; i++) {
            if (mode_max < arr[i]) {
                mode_max = arr[i];
                mode = i - 4000;
                flag = true;
            } else if (mode_max == arr[i] && flag == true) {
                mode = i - 4000;
                flag = false; // 2번째로 작은 최빈값이기 위한 flag=false (3번째부터는 거르기)
            }
        }
        return mode;
    }

    private static int median(int max, int min) {
        int count = 0; // 중앙값 빈도 누적 수
        int median = 10000;
        for (int i = min + 4000; i <= max + 4000; i++) {
            if (arr[i] > 0) {
                // <중앙값 찾기> 누적횟수가 전체 전체 길이의 절반에 못 미친다면
                if (count < (N+1)/2) {
                    count += arr[i];    // i값의 빈도수를 count 에 누적
                    median = i - 4000;
                }
            }
        }
        return median;
    }
    private static int mean(int sum) {
        return (int)Math.round((double)sum/N);
    }
}

====================================================================================================
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int[] num = new int[8001];
        for (int i = 0; i < N; i++) {
            num[nextInt() + 4000]++;
        }

        int count = 0;
        int sum = 0;
        int med = 0;
        int mode = 0;
        int modeCount = 0;
        boolean isSameMode = false;
        int range = 0;
        for (int i = 0; i < 8001; i++) {
            if (num[i] > 0) {
                int value = i - 4000;
                if (num[i] == modeCount && isSameMode) {
                    mode = value;
                    isSameMode = false;
                }
                if (num[i] > modeCount) {
                    mode = value;
                    modeCount = num[i];
                    isSameMode = true;
                }
                for (int j = 0; j < num[i]; j++) {
                    count++;
                    sum += value;
                    if (count == (N / 2 + 1)) med = value;
                    if (count == N) range += value;
                    if (count == 1) range -= value;
                }
            }
            if (count == N) break;
        }

        int avg = (int)Math.round((double)sum / N);
        System.out.println(avg + "\n" + med + "\n" + mode + "\n" + range);
    }

    public static int nextInt() throws IOException {
        int n = 0;
        boolean isNeg = false;
        while (true) {
            int input = System.in.read();
            if (input == '-') isNeg = true;
            else if (input >= '0' && input <= '9') {
                n = (n << 3) + (n << 1) + (input & 15);
            } else return (isNeg) ? n * (-1) : n;
        }
    }
}
