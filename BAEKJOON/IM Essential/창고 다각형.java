// [참고문헌]: https://january-diary.tistory.com/entry/BOJ-2304-%EC%B0%BD%EA%B3%A0%EB%8B%A4%EA%B0%81%ED%98%95-JAVA

import java.io.*;
import java.util.*;
/**
 * 문제 해결 전략 :
 * 오목하게 들어간 부분이 없어야 -> 기준에 대한 양 옆에 막대가 더 크면 안된다
 * 작은 막대가 큰 막대들의 사이에 있다면 묻힌다 -> 양 끝 막대 + 가운데에서 묻히지 않는 막대들의 높이가 중요
 * 
 * 실패 요인 : 한 방향으로 면적을 구하려다 실패
 * 해당 문제는 제일 높은 기둥을 기준으로 문제의 조건에 맞게 오른쪽은 작아지는 구간만을 계산을 하고,
 * 왼쪽 면적은 기둥이 커지는 구간만 계산을 한 뒤에 제일 큰 기둥의 면적을 더해주면 답이 구해집니다. 
 */

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(in.readLine());
      StringTokenizer st;
 
      int[][] col = new int[N][2];
      for (int i = 0; i < N; i++) {
         st = new StringTokenizer(in.readLine());
         col[i][0] = Integer.parseInt(st.nextToken());//위치
         col[i][1] = Integer.parseInt(st.nextToken());//높이
      }
 
      Arrays.sort(col, (o1, o2) -> o1[0] - o2[0]); // 막대 정렬
	   
      Arrays.sort(col, new Comparator<int[]>() { // 막대 정렬2
         @Override
	 public int compare(int[] o1, int[] o2) {
	    return o1[0]-o2[0]; // 첫번째 숫자 기준 오름차순
	 }
      });
 
      int area = 0;
      for(int i=0;i<N;){
         int j=i+1; int max = j;
         while(j<N && col[i][1]>col[j][1]){
            if(col[max][1]<col[j++][1]) max = j-1;
         }
 
         if(j>=N){
            area+=col[i][1];
            if(max<N) area+=col[max][1]*(col[max][0]-col[i][0]-1);
            i=max;
         }else{
            area+= col[i][1]*(col[j][0]-col[i][0]);
            i=j;
         }
 
      }
      System.out.println(area);
   }
}
====================================================================================================
// [참고문헌]: https://yoon990.tistory.com/37
// 여러개의 기둥들이 있고, 그 위에 지붕을 만드는 문제이다. 
// 문제의 포인트는 오목한 부분이 없어야하는 것이다. 그래서 나는 가장 높은 기둥을 찾고, 그 전과 후를 따로 계산했다.
// 우선 기둥들의 정보를 저장하고 정렬할 때 필요한 compareTo 함수를 재정의 해줬다. 기둥들의 순서가 순차적으로 들어오지 않기 때문에 정렬을 사용했다.
// 가장 높은 기둥을 기준으로 
// 앞부분에서는 현재 기둥보다 더 높은 기둥이 올 때만 값을 더해주고
// 뒷부분은 끝에서부터 오면서 더 높은 기둥이 올 때만 값을 더해주는 식으로 구현했다.

import java.io.*;
import java.util.*;
 
public class BJ_2304_창고다각형  {
	static class Top implements Comparable<Top> {
		int p, h;
		public Top(int p, int h) {
			this.p = p;
			this.h = h;
		}
		@Override
		public int compareTo(Top o) {
			return p-o.p;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 기둥 개수
		
		Top[] tops = new Top[N]; //기둥들 정보 저장
		int maxH = 0, max= 0; 
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()); //기둥 위치 
			int h = Integer.parseInt(st.nextToken()); //기둥 높이 
			tops[i]= new Top(p,h);
		}
		Arrays.sort(tops);
		for (int i = 0; i <N; i++) { //가장 높은 위치 찾기
			if(maxH<tops[i].h) {
				maxH = tops[i].h;
				max = i; 
			}
		}
		//가장 높은 기둥을 기준으로 반큼씩 계산
		int height =0, move = 0, sum =0; ; 
		for (int i = 0; i <= max; i++) {
			if(height < tops[i].h) {
				sum+= (tops[i].p - tops[move].p) * height;
				height =tops[i].h;
				move =i;
			}
		}
		sum+=tops[max].h;
		//가장 높은 기둥 이후의 기둥들 면적 계산
		int idx=0;
		height=0; move = 0;
		for (int i =N-1; i >=max; i--) {
			if(height < tops[i].h) {
				sum+= (tops[move].p -tops[i].p) * height;
				height =tops[i].h;
				move =i;
				idx = i;
			}
		}
		sum+= (tops[idx].p - tops[max].p) * tops[idx].h;
		
		System.out.println(sum);
	}
 
}  
