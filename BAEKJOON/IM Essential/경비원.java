import java.io.*;
import java.util.*;

/**
 * nxm 직사각형 테두리에서 상점의 개수와 동근이의 위치가 주어질 때
 * 동근이와 각 상점 사이의 최단 거리의 합 구하기
 * 직사각형 크기 - 상점 수 - (북남서동, 남북-왼쪽 경계로부터의 거리//동서-위쪽 경계로부터의 거리) - 동근이 위치
 * 
 * 나의 틀렸던 문제 해결 전략 :
 * 직사각형의 변을 마주보는 곳으로 이동 시 행or열의 길이가 추가됨
 * 시작점에서 왼쪽vs오른쪽 or 위쪽vs아래쪽 거리 비교해서 Math.min
 *
 * 문제 해결 전략 :
 * 북쪽 맨 왼쪽을 시작점으로 삼아 4방위의 좌표들을 시작점에 맞추어 좌표 재정의
 * 북쪽 0,0을 기준으로 직선으로 만드는 법
 * 1. 북쪽은 입력받은 좌표를 그대로 넣는다.
 * 2. 동쪽은 북쪽 너비+좌표를 넣는다.
 * 3. 남쪽은 북쪽 너비+동쪽 높이+(남쪽 너비-좌표)를 넣는다. 시계방향이기 때문에 가로길이에서 좌표를 빼준다.
 * 4. 서쪽은 북쪽 너비+동쪽 높이+남쪽 너비+(서쪽 높이-좌표)를 넣는다.
 * 상점들 위치와 동근이 위치를 비교할 때, 어떤 좌표가 더 큰 지 모르기 때문에 절댓값으로 계산한다.
 * 계산한 길이와 좌표의 총 길이(너비*2+높이*2)에서 그 길이를 빼준 값 둘 중에 더 작은 값을 합계에 넣는다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // 열
        int n = Integer.parseInt(st.nextToken()); // 행
        int shopCnt = Integer.parseInt(br.readLine()); // 상점 개수
        int[] dists = new int[shopCnt+1]; // 각 위치별 거리 (하나의 직선으로 생각)
        for(int i=0; i<shopCnt+1; i++) { // 상점 개수만큼 반복
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken()); // 방향
            int val = Integer.parseInt(st.nextToken()); // 값
            int dist = 0; // 임시 거리 변수
            if(dir == 1) dist = val; // 북
            else if(dir == 2) dist = 2*m+n-val; // 남
            else if(dir == 3) dist = 2*(m+n)-val; // 서
            else if(dir == 4) dist = m+val; // 동
            dists[i] = dist; // 거리들을 넣어준다
        }
        int me = dists[shopCnt]; // 내 거리값 저장
        int sum = 0;
        for(int i=0; i<shopCnt; i++) { // 각 상점의 거리값 비교
            int clockwise = Math.abs(me-dists[i]); // 시계방향 거리값과
            sum += Math.min(clockwise, 2*(n+m)-clockwise); // 반시계방향 거리값을 비교해서 작은 값을 더하기
        }
        System.out.println(sum); // 결과 출력
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class BOJ_2564_경비원 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
 
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int total = (width + height) << 1;
 
        int N = Integer.parseInt(in.readLine());
 
        //마지막 방은 동근이 위치
        int[] spots = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            int way = Integer.parseInt(st.nextToken());
            spots[i] = Integer.parseInt(st.nextToken());
 
            if (way == 2) spots[i] = width - spots[i];
            else if (way == 3) spots[i] = height - spots[i];
 
            switch (way) { // break문이 없기 때문에 해당 way부터 아래로 쭉 더해나가짐
                case 3: spots[i] += width;
                case 2: spots[i] += height;
                case 4: spots[i] += width;
            }
        }
 
        //상점-동근위치 빼고, 그 길이와 토탈-길이 비교하기
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int len = Math.abs(spots[N] - spots[i]);
            sum += Math.min(len, total - len);
        }
 
        System.out.println(sum);
    }
}
