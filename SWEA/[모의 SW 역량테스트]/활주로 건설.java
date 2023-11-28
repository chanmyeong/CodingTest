import java.io.*;
import java.util.*;

/**
 * N*N 크기의 절벽지대에 활주로를 건설
 * 가로 또는 세로 방향으로 건설할 수 있는 가능성을 확인
 * 
 * 활주로는 높이가 동일한 구간에서 건설이 가능하다.
 * 
 * 높이가 다른 구간의 경우 경사로를 설치
 * 
 * 경사로의 길이 X 와 절벽지대의 높이 정보
 * 활주로를 건설할 수 있는 경우의 수를 계산
 * 
 * 경사로 길이보다 짧아서 활주로를 설치 할 수 없다
 * 경사로를 지형 밖까지 설치해야 되기 때문에 활주로를 설치할 수 없다
 * 
 * 입력
 * 6 ≤ N ≤ 20
 * 경사로의 높이는 항상 1 이고, 길이 X 는 2 이상 4 이하의 정수이다. ( 2 ≤ X ≤ 4 )
 * 지형의 높이는 1 이상 6 이하의 정수
 *   
 */
public class Solution_4014_활주로건설 {
	static int N,X;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new StringReader(str));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {	
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			//solve
			int answer=0; // 활주로 건설 가능 개수
			//각 행, 열 별로 활주로 건설 가능여부 체크
			for(int r=0; r<N; r++) {
				if(checkRow(r)) answer++;
			}
			for(int c=0; c<N; c++) {
				if(checkCol(c)) answer++;
			}

			sb.append("#"+tc+" "+answer).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean checkRow(int r) {
		boolean[] visited = new boolean[N]; // 경사로 설치여부 확인 배열
		for(int i=0; i<=N-2; i++) {
			int diff = map[r][i] - map[r][i+1]; //왼쪽 높이 - 오른쪽 높이

			//a. 높이차가 2 이상인 경우(설치 불가능)
			if(Math.abs(diff)>=2) return false;

			//b. 왼쪽이 1 낮은 경우
			else if(diff==-1) {
				//경사로 설치
				// 높이가 1차이나는 지점에서 낮은 지대가 길이X만큼 뻗어있는지를 확인
				for(int j=i; j>=i-(X-1); j--) { // i에서 시작, i인 낮은 지대를 포함
					if(j<0) return false;			 //왼쪽 경계 벗어남(설치 불가능)
					if(visited[j]) return false;		 //이미 경사로 설치됨(설치 불가능)
					if(map[r][i]!=map[r][j]) return false;   //경사로의 시작높이와 다름(설치 불가능)

					visited[j] = true;	//경사로 설치
				}
			}

			//c. 오른쪽이 1 낮은 경우
			else if(diff==1) {
				//경사로 설치
				for(int j=i+1; j<=i+X; j++) { // i+1에서 시작, i+1+(X-1)
					if(j>=N) return false;			 //오른쪽 경계 벗어남(설치 불가능)
					if(visited[j]) return false;		 //이미 경사로 설치됨(설치 불가능)
					if(map[r][i+1]!=map[r][j]) return false; //경사로의 시작높이와 다름(설치 불가능)

					visited[j] = true; //경사로 설치					
				}
			}			
		}
		//활주로 건설이 가능하므로 true 반환
		return true;
	}

	private static boolean checkCol(int c) {
		boolean[] visited = new boolean[N];
		for(int i=0; i<=N-2; i++) {
			int diff = map[i][c]-map[i+1][c]; //위쪽 높이 - 아래쪽 높이

			//a. 높이차가 2 이상인 경우(설치 불가능)
			if(Math.abs(diff)>=2) return false;

			//b. 위쪽이 1 낮은 경우
			else if(diff==-1) {
				//경사로 설치
				for(int j=i; j>=i-(X-1); j--) {
					if(j<0) return false;			//위쪽 경계 벗어남(설치 불가능)
					if(visited[j]) return false;		//이미 경사로 설치됨(설치 불가능)
					if(map[i][c]!=map[j][c]) return false;  //경사로의 시작높이와 다름(설치 불가능)
					
					visited[j]= true; //경사로 설치
				}
			}
			//c. 아래쪽이 1 낮은 경우
			else if(diff==1) {
				//경사로 설치
				for(int j=i+1; j<=i+X; j++) {
					if(j>=N) return false;			 //아래쪽 경계 벗어남(설치 불가능)
					if(visited[j]) return false;		 //이미 경사로 설치됨(설치 불가능)
					if(map[i+1][c]!=map[j][c]) return false; //경사로의 시작높이와 다름(설치 불가능)

					visited[j] = true; //경사로 설치					
				}
			}		
		}
		//활주로 건설이 가능하므로 true 반환
		return true;
	}

	static String str = "2\r\n" + 
			"6 2\r\n" + 
			"3 3 3 2 1 1\r\n" + 
			"3 3 3 2 2 1\r\n" + 
			"3 3 3 3 3 2\r\n" + 
			"2 2 3 2 2 2\r\n" + 
			"2 2 3 2 2 2\r\n" + 
			"2 2 2 2 2 2\r\n" + 
			"6 4\r\n" + 
			"3 2 2 2 1 2\r\n" + 
			"3 2 2 2 1 2\r\n" + 
			"3 3 3 3 2 2\r\n" + 
			"3 3 3 3 2 2\r\n" + 
			"3 2 2 2 2 2\r\n" + 
			"3 2 2 2 2 2";
}
