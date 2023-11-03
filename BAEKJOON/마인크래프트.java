/**
 * N*M 크기의 집터 (0,0)시작
 *
 * 두 가지 작업 종류
 * 1. 좌표 (i, j)의 가장 위에 있는 블록을 제거하여 인벤토리에 넣는다.
 * 2초 소요
 *
 * 2. 인벤토리에서 블록 하나를 꺼내어 좌표 (i, j)의 가장 위에 있는 블록 위에 놓는다.
 * 1초 소요
 *
 * 3차원?
 * 땅 고르기 작업의 최소 시간과 그 경우 땅의 높이 출력
 * 답이 여러 개 있다면 그중에서 땅의 높이가 가장 높은 것을 출력하시오.
 *
 * 음수 및 경계에는 블록 쌓기 불가
 * 작업 시작 시 인벤토리에 B개 블록 존재
 * 땅의 높이는 0~256
 *
 * 첫째 줄에 N, M, B가 주어진다. (1 ≤ M, N ≤ 500, 0 ≤ B ≤ 6.4 × 10^7 == 64000000)
 * 땅의 높이가 주어진 N*M 크기 집터
 *
 * 문제 해결 전략 :
 * 땅 높이를 높게하여 땅을 고를수록 시간과 인벤토리 블록의 개수는 줄어든다.
 * 인벤토리의 블록이 0보다 낮아지기 전까지만 땅 고르는 높이를 설정하여 최소 시간 갱신
 *
 */

// [참고문헌]: https://wonit.tistory.com/540

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nmb = br.readLine().split(" ");
        int n = Integer.parseInt(nmb[0]);
        int m = Integer.parseInt(nmb[1]);
        int b = Integer.parseInt(nmb[2]);

        int[][] ground = new int[n][m];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < ground.length; i++) {

            String[] groundRow = br.readLine().split(" ");

            for (int j = 0; j < m; j++) {
                int value = Integer.parseInt(groundRow[j]);
                ground[i][j] = value;

                max = Math.max(max, value);
                min = Math.min(min, value);
            }
        }

        int answerSeconds = Integer.MAX_VALUE; // 시간
        int answerHeight = -1; // 층

        for (int i = min; i <= max; i++) { // 최소층 부터 최대층 까지

            int seconds = 0;
            int inventory = b;

            for (int j = 0; j < ground.length; j++) {
                for (int k = 0; k < ground[j].length; k++) {
                    int diff = ground[j][k] - i;

                    if(diff > 0) { // 제거해야 함
                        seconds += Math.abs(diff) * 2;
                        inventory += Math.abs(diff);
                    }else if(diff < 0){ // 추가해야 함
                        seconds += Math.abs(diff);
                        inventory -= Math.abs(diff);
                    }
                }
            }

            if(inventory >= 0) {
                if(seconds <= answerSeconds) { // == 가 포함되어야 한다 -> 그렇지 않으면 최대 높이를 판별 하지 못함
                    answerSeconds = seconds;
                    answerHeight = i;
                }
            }
        }

        bw.write(answerSeconds + " " + answerHeight);

        bw.flush();
        bw.close();
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine()," ");

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int[] arr = new int[h * w]; // 높이만 같으면 되므로 1차원 배열 사용
        int arrIdx = 0;
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0; j<w; j++) {
                int value = Integer.parseInt(st.nextToken());
                arr[arrIdx++] = value;

                max = Math.max(max, value);
                min = Math.min(min, value);
            }
        }

        int put, rm, floor = 0;
        int minTime = Integer.MAX_VALUE;
        for (int height = min; height <= max; height++) {
            put = 0;
            rm = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > height) { // 제거해야 함
                    rm += arr[i] - height;
                } else if (arr[i] < height) { // 추가해야 함
                    put += height - arr[i];
                }
            }
            if (put <= b + rm) {
                if (put + 2 * rm <= minTime) {
                    minTime = put + 2 * rm;
                    floor = height;
                }
            }
        }

        System.out.println(minTime + " " + floor);

    }
}
