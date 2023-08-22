import java.io.*;
import java.util.*;

/**
 * 비례식 -> 1 : 1m^2에 차라는 참외 수 = 밭의 면적 : 밭에서 자란 참외 수
 * 밭의 면적 = 왼쪽 위 꼭지점에서 시작해 모든 육각형의 길이를 더한 값
 *
 * 문제 해결 전략 :
 * 밭의 면적이 ㄱ자 형태를 회전한 육각형이지만 결국은 직사각형의 면적과 동일
 * 전체 직사각형의 가로와 세로의 길이를 구하고 가장 작은 직사각형의 가로와 세로의 길이를 구해 넓이를 빼기
 * 임의의 한 점에서 시작해 반시계방향으로 변들의 길이가 주어지므로
 * 모든 변의 길이 저장해서 전체 직사각형 길이/2 한 값을 더해서 만들 수 있는 두 수 조합
 * or 변의 방향이 1개만 주어진 길이들 = 전체 직사각형의 변의 길이
 *
 * 작은 직사각형의 세로 길이 = 전체 가로길이 양 옆 길이 중 작은 길이
 * 전체 넓이 - 작은 부분 넓이
 * 비례식으로 답을 도출하기
 */
// 틀렸던 나의 풀이
public class Main {
    static int k, answer=0;
    static ArrayList<Integer>[] squareLines;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        squareLines = new ArrayList[5]; // 0제외, 4방위 index
        for(int i=0; i<5; i++) {
            squareLines[i] = new ArrayList<>();
        }

        for(int i=1; i<=6; i++) { // 육각형 변의 길이 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken()); // 방위
            int len = Integer.parseInt(st.nextToken()); // 변 길이
            squareLines[pos].add(len); // 방위 index에 변 길이 저장
            // squareLines[pos].add(len);이 틀린 자료구조 저장 방식인 이유
            // 작은 직사각형의 가로, 세로를 구하기 위해서는 순서대로 저장된 방위 및 변 길이 데이터가 필요하다
            // 작은 직사각형의 세로 길이 = 가장 긴 가로 길이의 접한 두 값(=가장 긴 가로 길이 저장 인덱스 값의 앞과 뒤)의 차
            // 작은 직사각형의 가로 길이 = 가장 긴 세로 길이의 접한 두 값(=가장 긴 세로 길이 저장 인덱스 값의 앞과 뒤)의 차
            // 앞과 뒤를 비교 시 시작에서는 시작+1,끝 비교
            // 끝에서는 끝-1,시작 비교임에 유의한다
        }

        int bw=0, bh=0, sw=0, sh=0;
        bw = findBigSquare()[0];
        bh = findBigSquare()[1];
        findSmallSquare();

        int totalArea = bw*bh-sw*sh;
        answer = k*totalArea;
        System.out.println(answer);
    }
    private static int[] findBigSquare() {
        int[] length = new int[2];
        int lengthIdx=0;
        for(int i=1; i<=4; i++) {
            if(squareLines[i].size()==1) {
                length[lengthIdx] = squareLines[i].get(0);
                lengthIdx++;
            }
        }
        return length;
    }
    private static void findSmallSquare() {
        for(int i=1; i<=4; i++) {
            if(squareLines[i].size()==1) {
                squareLines[i].remove(0);
            }
        }
//        for(ArrayList i : squareLines)
//            System.out.println(i);
//        System.out.println(squareLines[0].size());

    }
}
====================================================================================================
// [참고문헌]: https://angelplayer.tistory.com/396
  
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		
		int[]arr = new int[6];
		int wLongIdx = 5;
		int hLongIdx = 5;
		
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			
			int direction = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			
			arr[i] = length;
			if (direction == 1 || direction == 2) {
				if (arr[wLongIdx] < arr[i]) {
					wLongIdx = i;
				}
			}
			
			if (direction == 3 || direction == 4) {
				if (arr[hLongIdx] < arr[i]) {
					hLongIdx = i;
				}
			}
		}
		
		int wMinusIdx = wLongIdx - 1;
		if (wMinusIdx == -1) {
			wMinusIdx = 5;
		}
		
		int hMinusIdx = hLongIdx - 1;
		if (hMinusIdx == -1) {
			hMinusIdx = 5;
		}
		
		int hShort = arr[hLongIdx] - Math.min(arr[wMinusIdx], arr[(wLongIdx + 1)%6]); // 50-30, Math.min(가장 긴 가로길이 앞값=50, 가장 긴 가로길이 뒷값=30)=30
		int wShort = arr[wLongIdx] - Math.min(arr[hMinusIdx], arr[(hLongIdx + 1)%6]);
		
		int bigRect = arr[wLongIdx] * arr[hLongIdx];
		int smallRect = hShort * wShort;
		
		System.out.println((bigRect - smallRect) * K);
	}
}
====================================================================================================
[참고문헌]: https://good-or-bad.tistory.com/21
// 전체 넓이를 파인 변의 길이를 기점으로 나눈 두 직사각형의 합으로 구한 풀이
  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2477_참외밭 {
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine()); //참외 개수
		int[] input=new int[6];
		int maxR=0,maxC=0; // 최대 세로길이, 최대 가로길이
		int indexR=0,indexC=0; // 최대 세로길이에서의 index, 최대 가로길이에서의 index

		for (int i = 0; i < 6; i++) {
			st=new StringTokenizer(br.readLine());
			int dir=Integer.parseInt(st.nextToken());
			int distance=Integer.parseInt(st.nextToken());
			if(dir==3 || dir==4) {// r
				maxR=maxR<distance?distance:maxR;
				if (maxR==distance) indexR=i;
			}else { // c
				maxC=maxC<distance?distance:maxC;
				if (maxC==distance) indexC=i;
			}
			input[i]=distance;
			
		}
    
    // 최대 길이에서의 앞과 뒤 비교 시, 시작과 끝 비교가 애매한 부분을 초기화를 통해서 해결
		int nextR1=input[5],nextC1=input[5]; // nextR1,R2 => 세로길이 후보 
		int nextR2=input[0],nextC2=input[0]; // nextC1,C2 => 가로길이 후보
    
		if (indexC-1>-1) nextR1=input[indexC-1];
		if (indexC+1<6) nextR2=input[indexC+1];
		if (indexR-1>-1) nextC1=input[indexR-1];
		if (indexR+1<6) nextC2=input[indexR+1];
		
		// 최대 가로길이*세로길이후보 중 작은 길이 + 가로길이후보 중 작은 길이*(최대 세로길이-세로길이후보 중 작은 길이)
		int area=maxC*Math.min(nextR1, nextR2)+Math.min(nextC1, nextC2)*(maxR-(Math.min(nextR1, nextR2)));
		
		System.out.println(area*N);

	}
}
