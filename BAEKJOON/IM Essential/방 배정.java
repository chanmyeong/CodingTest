import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 100점 코드
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] room = new int[2][7]; // 성별 , 학년
		int count = 0 ;
		int s , g;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			room[s][g]++;			
		}
		
    // 수용 인원으로 나누어주며 계산 
		for(int i = 0 ; i < 2 ; i ++) {
			for(int j = 1 ; j <7 ; j++) {
				count += room[i][j] / K ;
				if(room[i][j]% K !=0) {
					count++;
				}
			}
		}
		System.out.print(count);

	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 남학생은 남학생끼리, 여학생은 여학생끼리 방을 배정해야 한다.
 * 한 방에는 같은 학년의 학생들을 배정해야 한다. 
 * 한 방에 한 명만 배정하는 것도 가능하다.
 * 
 * 학생의 성별 S와 학년 Y(1 ≤ Y ≤ 6)가 공백으로 분리되어 주어진다.
 * 성별 S는 0, 1중 하나로서 여학생인 경우에 0, 남학생인 경우에 1
 */
public class Main {
	static int N,K; // K: 한 방에 배정할 수 있는 최대 인원 수
	static int minRoomCnt;
	static class Student {
		int gender; int grade;
		
		public Student(int gender, int grade) {
			super();
			this.gender = gender;
			this.grade = grade;
		}

		@Override
		public String toString() {
			return "student [gender=" + gender + ", grade=" + grade + "]";
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Student[] girl = new Student[N];
		Student[] boy = new Student[N];
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			if(gender==0) {
				girl[n] = new Student(gender, grade);
			} if(gender==1) {
				boy[n] = new Student(gender, grade);
			}
		}
		
		//solve
		int g = findRoomCnt(girl);
		int b = findRoomCnt(boy);
		
		minRoomCnt = g+b; 
		
//		System.out.println(Arrays.toString(arr));
		System.out.println(minRoomCnt);
	}
	private static int findRoomCnt(Student[] arr) {
//		System.out.println(Arrays.toString(arr));
		int[] cnt = new int[6+1];
		int roomCnt=0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i]==null) continue;
			if(arr[i].grade==1) {
				cnt[1]++;
			} else if(arr[i].grade==2) {
				cnt[2]++;
			} else if(arr[i].grade==3) {
				cnt[3]++;
			} else if(arr[i].grade==4) {
				cnt[4]++;
			} else if(arr[i].grade==5) {
				cnt[5]++;
			} else if(arr[i].grade==6) {
				cnt[6]++;
			}
		}
//		System.out.println("cnt :"+Arrays.toString(cnt));
		// 수정 후
		for(int i=1; i<cnt.length; i++) {
			roomCnt += cnt[i]/K;
			if(cnt[i]%K!=0) {
				roomCnt++;
			}
		}
		// 수정 전
//		for(int i=1; i<cnt.length; i++) {
//			roomCnt += cnt[i]/K+cnt[i]%K;
//		}
		return roomCnt;
	}
}
