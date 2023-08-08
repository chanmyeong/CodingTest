import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 배열의 한 테두리를 기준으로 1회 연산 시 반시계 방향으로 돌리는 연산 수행
 * r번 연산 시 결과 구하기
 * 제한 : n과 m은 2이상이며 min(N, M) mod 2 = 0
 * @author cm
 * 문제 접근 :
 * 4방 탐색을 하여 모서리에 닿게 되면 방향 전환
 * 마지막 원소는 최후에 덮어질 것이므로 따로 저장 필요
 * 
 */
public class Main {
    static int[][] arr;
    static int n,m,r;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};    		
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        // 깊이 알기 (몇 개의 테두리가 생성되는지)
        int depth=0;
        depth = Math.min(n, m)/2;
        
        for(int i=0; i<r; i++) {
            rotate(depth);
        }
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(arr[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
            
    }
    
    private static void rotate(int depth) {
        for(int k=0; k<depth; k++) {
        	// (0,0), (1,1), (2,2)처럼 대각선으로 가면서 시작
        	int x=k;
        	int y=k;
        	int tmp = arr[x][y]; // 시작점 저장 (덮어져서 기존 값을 잃게됨을 방지)
        	int dir=0;
        	while(dir<4) {
        		int nx = x+dx[dir];
        		int ny = y+dy[dir];
        		if(k <= nx && nx < n-k && k<=ny && ny<m-k) {
        			arr[x][y]=arr[nx][ny]; // 반시계 방향으로 덮어쓰기
        			x=nx;
        			y=ny;
        		}
        		else {
        			dir++;
        		}
        	}
        	arr[k+1][k]=tmp; // (1,0), (2,1), (3,2)처럼 마지막에 시작값을 넣어야하는 곳에 저장했던 시작점 넣어주기	
        }
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = Math.min(N, M) / 2; //한 바퀴를 도는 라인의 수
		for(int i=0; i<R ; i++) {
			
			for(int j=0; j<count; j++) { 
				int temp = arr[j][j]; //첫 번째 원소(맨 왼쪽 위)는 따로 저장해놓고 마지막에 대입해준다.
				
				for(int k=j+1; k<M-j; k++)	//라인의 위쪽 
					arr[j][k-1] = arr[j][k];
				
				for(int k=j+1; k<N-j; k++)	//라인의 오른쪽 
					arr[k-1][M-1-j] = arr[k][M-1-j];
				
				for(int k=M-2-j; k>=j; k--)	//라인의 아래쪽 
					arr[N-1-j][k+1] = arr[N-1-j][k];
				
				for(int k=N-2-j; k>=j; k--)	//라인의 왼쪽 
					arr[k+1][j] = arr[k][j];
				
				arr[j+1][j] = temp;	//아까 저장해놓은거 넣기
			}
		}
		
		for(int j=0; j<N; j++) {
			for(int k=0; k<M; k++) {
				System.out.print(arr[j][k] + " ");
			}
			System.out.println();
		}
	}
}

====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * NxM 크기 배열에서 배열 회전 R번 적용
 * 2 ≤ N, M ≤ 300
 * 1 ≤ R ≤ 1,000
 * min(N, M) mod 2 = 0
 * 1 ≤ 원소 값 ≤ 108
 * 
 * 회전은 바깥부터 같은 범위에 있는 것 끼리 반시계방향 회전
 * 
 * 바깥줄부터 안쪽 줄로 이동하면서 한칸 씩 돌리기
 * @author SSAFY
 *
 */
public class Main {
	
	private static int N, M, R;
	private static int[][] numbers;
	private static int[] dr = {0, 1, 0, -1}; // 우 -> 하 -> 좌 -> 상
	private static int[] dc = {1, 0, -1, 0};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		R = Integer.parseInt(line[2]);
		
		numbers = new int[N][M];
		for(int i=0; i<N; i++) {
			line = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				numbers[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		// R번의 회전 시작
		for(int r=0; r<R; r++) {
			rotate();			
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(numbers[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	/**
	 * 각 방향 별 이동 횟수를 계산해 방향 별로 이동하는 방법
	 * 첫 번째 값을 저장해두고 반시계 방향대로 값 이동
	 */
	private static void rotate() {
		int circleNum = Math.min(N, M) /2; // 배열의 원 횟수 
		for(int i=0, space=1; i<circleNum; i++, space+=2) { 
			// space는 현재 자신의 위치를 제외하기 위한 초기값 1,
			// 안쪽 원의 경우 양옆 공백을 계산하기 위함
			int temp = numbers[i][i];
			int r = i;
			int c = i;
			for(int j=0; j<M-space; j++) {
				numbers[r][c] = numbers[r][c+1];
				c = c+1;
			}
			for(int j=0; j<N-space; j++) {
				numbers[r][c] = numbers[r+1][c];
				r = r+1;
			}
			for(int j=0; j<M-space; j++) {
				numbers[r][c] = numbers[r][c-1];
				c = c-1;
			}
			for(int j=0; j<N-space-1; j++) {
				numbers[r][c] = numbers[r-1][c];
				r = r-1;
			}
			numbers[r][c] = temp;
		}
	}
	
	/**
	 * 각 방향 별 이동 횟수를 설정하여 최종 위치(출발위치에서 행-1)까지 이동하는 방법
	 * 첫 번째 값을 저장해두고 반시계 방향대로 값 이동
	 */
	private static void rotate2() {
		int circleNum = Math.min(N, M) /2;
		int d = -1;
		for(int i=0, space=1; i<circleNum; i++, space+=2) {
			int first = numbers[i][i];
			int r=i, c=i;
			int goCnt = 0;
			while(r!=i+1 || c!=i) {
				if(goCnt == 0) {
					d = (d+1)%4;
					goCnt = d%2==0 ? M-space : N-space;
					continue;
				}
				numbers[r][c] = numbers[r+dr[d]][c+dc[d]];
				r = r+dr[d];
				c = c+dc[d];
				goCnt--;
			}
			numbers[r][c] = first;
		}
	}
}
