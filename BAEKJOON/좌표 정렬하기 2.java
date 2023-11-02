import java.io.*;
import java.util.*;

/**
 * 2차원 평면 위의 점 N개가 주어진다.
 * 좌표를 y좌표가 증가하는 순으로, y좌표가 같으면 x좌표가 증가하는 순서로 정렬한 다음 출력
 * 점의 개수 N (1 ≤ N ≤ 100,000)
 * i번점의 위치 xi와 yi가 주어진다. (-100,000 ≤ xi, yi ≤ 100,000)
 *
 */
public class Main {
    static int N;
    static class Point implements Comparable<Point> {
        int x; int y;
        public Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
        @Override
        public int compareTo(Point o) {
            if(this.y==o.y) return this.x-o.x;
            return this.y-o.y;
        }
    }
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        Point[] point = new Point[N];
        for(int n=0; n<N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            point[n] = new Point(x,y);
        }
        Arrays.sort(point);
        for(int i=0; i<N; i++) {
            sb.append(point[i].x+" "+point[i].y).append("\n");
        }
        System.out.println(sb);
    }
}
====================================================================================================
// [참고문헌]: https://st-lab.tistory.com/111
  
import java.util.Scanner;
import java.util.Arrays;
 
public class Main {
	public static void main(String[] args) {
 
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			// 위치 주의
			arr[i][1] = in.nextInt();
			arr[i][0] = in.nextInt();
		}
		
		Arrays.sort(arr, (e1, e2) -> {
			if(e1[0] == e2[0]) {
				return e1[1] - e2[1];
			} else {
				return e1[0] - e2[0];
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			// 위치 주의
			sb.append(arr[i][1] + " " + arr[i][0]).append('\n');
		}
		System.out.println(sb);
	}
}
====================================================================================================
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
                // return o2[1] - o1[1];
            }
        });
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long forGreaterY = 1000000;
        long forNegativeNum = 100000;
        long xy[] = new long[n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken())+forNegativeNum;
            long y = (Long.parseLong(st.nextToken())+forNegativeNum)*forGreaterY;
            xy[i] = x+y;
        }
        Arrays.sort(xy);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            long x = xy[i]%forGreaterY-forNegativeNum;
            long y = xy[i]/forGreaterY-forNegativeNum;
            sb.append(x+" "+y+"\n");
        }
        System.out.println(sb);
    }
}
