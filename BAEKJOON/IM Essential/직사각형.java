import java.io.*;
import java.util.*;
/**
 * 왼쪽 아래 꼭짓점 좌표 (x, y)와 오른쪽 위 꼭짓점 좌표 (p, q)의 직사각형
 * 4개의 정수 x y p q 로 표현된다. 단 항상 x<p, y<q
 *
 * 두 개의 직사각형은 그 겹치는 부분의 특성에 따라 다음 4가지 경우로 분류
 * 겹치는 부분이 직사각형 3가지 a
 * 겹치는 부분이 선분 b
 * 겹치는 부분이 점 c
 * 겹치는 부분이 없이 분리 d
 *
 * 문제 해결 전략 :
 * 2개 직사각형 입력 받아서
 * 겹치는 부분이 직사각형 a : 나머지?
 * 겹치는 부분이 선분 b : x1==p2&&(y2<=y1&&y1<q2||y1<y2,q2) || ...
 * 겹치는 부분이 점 c : x1==p2&&y1==q2 || p1==x2&&y1==q2 || p1==x2&&q1==y2 || x1==p2&&q1==y2
 * 겹치는 부분이 없이 분리 d : x1>p2 || p1<x2 || y1>q2 || q1<y2
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int x1,y1,p1,q1,x2,y2,p2,q2;
        for(int ts=1; ts<=4; ts++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            p1 = Integer.parseInt(st.nextToken());
            q1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            p2 = Integer.parseInt(st.nextToken());
            q2 = Integer.parseInt(st.nextToken());

            if(x1>p2 || p1<x2 || y1>q2 || q1<y2) {
                System.out.println('d');
            }
            else if(x1==p2&&y1==q2 || p1==x2&&y1==q2 || p1==x2&&q1==y2 || x1==p2&&q1==y2) {
                System.out.println('c');
            }
            else if(x1==p2 || p1==x2 || y1==q2 || y2==q1) { // ex) x1==p2여도 분리된 경우를 선행조건인 d에서 필터링 가능
                System.out.println('b');
            }
            else {
                System.out.println('a');
            }
        }
    }
}
====================================================================================================
// 각 직사각형의 변의 길이의 합과 두 직사각형의 전체 거리를 비교
  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int min = 1;
	static int max = 50000;

	public static void main(String[] s1rgs) throws IOException {
		BufferedReader s2r = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 4;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(s2r.readLine(), " ");
			int[] s1 = new int[4];
			int[] s2 = new int[4];
			for (int i = 0; i < 4; i++) {
				s1[i] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < 4; j++) {
				s2[j] = Integer.parseInt(st.nextToken());
			}
			System.out.println(classify(s1,s2));
		}
	}

	static char classify(int[] s1, int[] s2) {
		int w_sum = s1[2] - s1[0] + s2[2] - s2[0];
		int h_sum = s1[3] - s1[1] + s2[3] - s2[1];
		int w_endtoend = Integer.max(s1[2], s2[2]) - Integer.min(s1[0], s2[0]);
		int h_endtoend = Integer.max(s1[3], s2[3]) - Integer.min(s1[1], s2[1]);
		if (w_sum > w_endtoend) {
			if (h_sum > h_endtoend) {
				return 'a';
			} else if (h_sum == h_endtoend) {
				return 'b';
			} else
				return 'd';
		} else if (w_sum == w_endtoend) {
			if (h_sum > h_endtoend)
				return 'b';
			else if (h_sum == h_endtoend)
				return 'c';
			else
				return 'd';
		} else
			return 'd';
	}
}
