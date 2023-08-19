import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * N×M인 격자판
 * 각 칸에 포함된 적의 수는 최대 하나
 * 격자판의 N번행의 바로 아래(N+1번 행)의 모든 칸에는 성 존재
 * 궁수는 성이 있는 칸에 3명 배치
 * 각 턴마다 모든 궁수는 동시 공격, 거리가 D이하인 적 중에서 가장 가까운 적이 여럿이면 가장 왼쪽부터 공격
 * 같은 적이 여러 궁수에게 공격 받기 가능
 * 적은 공격받거나 성으로 이동 시 게임에서 제외, 모든 적이 제외 시 게임종료
 * 궁수의 공격으로 제거할 수 있는 적의 최대 수
 * 격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|
 * 상하 : 거리 1, 대각선 : 거리 2
 * @author cm
 * 문제 해결 전략 :
 * 항상 완전탐색부터 생각해서 최적화를 나중에 고려하기
 * 각 열에 적이 최대인 곳에 궁수를 배치하는 것이 유리 -> 예외 테스트케이스가 존재
 * mC3해서 궁수 배치하기
 * 각 행에 적이 3개보다 많고 거리에 미치지 못할 시 남는 적은 제외됨
 * 
 * 턴 수가 지날 때마다 공격 거리 제한을 중첩시키기
 * 턴 수가 지날 때마다 제외된 적은 0 처리하기
 * 
 * 막힌 부분 :
 * 1. 다 뽑고 어떻게 해야 될지 막막했음 -> 게임 돌린다
 * 2. BFS에서 큐에 무엇을 넣고 -> 궁수의좌표값
 * 3. 거리조건 + 같은 적을 죽이는 상황고려를 어떻게 설정해야 될지 막막함
 *   
 */
public class Main_BJ_17135_캐슬디펜스_우찬명 {
    static class Node{
        int r; int c;
        int dis;
        public Node(int r, int c, int dis){
            this.r=r;
            this.c=c;
            this.dis=dis;
        }
    }
    final static int[] dr = {0, -1, 0};
	final static int[] dc = {-1, 0, 1};

    static int N,M,D,kill,maxkill=0;
    static int[][] origin, map;
    static int[] hunters = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]); 
        M = Integer.parseInt(line[1]);
        D = Integer.parseInt(line[2]); // 공격 거리
        
        origin = new int[N][M];
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            String[] line2 = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                origin[i][j] = Integer.parseInt(line2[j]); // map에 적 넣기
            }
        }
        
        combination(0, 0);
        System.out.println(maxkill);
        
    }
    private static void combination(int depth, int start) { // mC3해서 궁수 배치하기
    	// 기저 조건
    	if(depth == 3) {
    		// kill 값 초기화
    		kill = 0;
    		arraycopy();
    		// n만큼 적이 이동하면 모든 적이 사라지므로 n만큼 반복
    		for(int i=0; i<N; i++) {
    			// 활쏴서 적 죽이기
    			shoot();
    			
    			// 적 이동
    			move();
    		}
    		// 현재 궁수 배치 상태에서 죽인 수가 최댓값이라면 갱신
    		maxkill = Math.max(maxkill, kill);
    		return;
    	}
    	
    	// 반복 조건
    	for(int i=start; i<M; i++) {
    		hunters[depth] = i; // 궁수 배치
    		combination(depth+1, i+1); // 다음 궁수 배치는 재귀 호출	
    	}
    }
    // 적 이동
    private static void move() {
    	for(int i=N-2; i>=0; i--) {
    		map[i+1]=map[i];
    	}
    	map[0] = new int[M]; // 배열의 0행을 0으로 초기화
	}
    private static void shoot() {
    	// 3명의 궁수가 죽일 적 위치 찾기
    	List<Node> enemies = new ArrayList<>(); // 죽일 적 좌표 정보 담기
    	
    	// 궁수 3명 BFS탐색 (BFS로 적을 탐색에 ArrayList에 담음)
    	A : for(int hunterC : hunters) {
    		boolean[][] visited = new boolean[N][M];
    		Queue<Node> q = new ArrayDeque<>();
    		
    		q.offer(new Node(N-1, hunterC, 1)); // dis가 1부터인 이유?? -> 가장 가까운 적부터
    		visited[N-1][hunterC] = true;
    		
    		while(!q.isEmpty()) {
    			// 좌표 정보 뽑아오기
    			Node n = q.poll();
    			int r = n.r;
    			int c = n.c;
    			int dis = n.dis; // 궁수로부터 현재좌표까지의 거리
    			
    			// 적정보를 뽑아 왔다면 ?
    			if(map[r][c]==1) {
    				//적 좌표정보 추가 후 다음 궁수 bfs 탐색으로
    				enemies.add(n);
    				continue A;
    			}
    			//좌=> 상=> 우 탐색
				for(int d=0; d<3; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if(nr<0 || nr>=N || nc<0 || nc>=M) continue;	//경계 벗어나는 경우
					if(visited[nr][nc]) continue;	//이미 방문한 경우
					if(dis+1 >D) continue;		//궁수로부터의 거리(dis+1)가 D를 초과

					q.offer(new Node(nr,nc, dis+1));
					visited[nr][nc] = true;
				}
		
    		}
    	}
    	// 적 죽이기
    	for(Node n : enemies) {
    		// 같은 적을 죽이는 상황 고려
    		if(map[n.r][n.c]==1) {
    			map[n.r][n.c]=0; // 죽이기
    			kill++; // 죽인 수 카운트
    		}
    	}
    	
	}
    private static void arraycopy() {
		for(int i=0; i<N; i++) {
			System.arraycopy(origin[i], 0, map[i], 0, M);
		}

	}
    
    private static void printMap() {
        for(int[] i : map) {
            System.out.println(Arrays.toString(i));
        }
    }
}

====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 설계 시작시간: 오후3시 46분
 * 설계 종료시간: 오후4시 00분
 * 
 * 문제해석)
 * N*M 격자판
 * 1x1 칸에 포함된 적의 수는 최대 하나
 * 격자판의 N번행 의 바로 아래(N+1번행)의 모든칸에는 성이 있음
 * 
 * 궁수 3명 배치
 * 성이 있는 칸에 배치할 수 있음
 * 한칸에 최대 한명
 * 각 턴마다 궁수는 적 하나를 공격&모든 궁수는 동시공격
 * 	- 거리가 D 이하인 적 중에서 가장 가까운 적 공격
 * 	- 가장 가까운 적이 여럿일 경우 가장 왼쪽에 있는 적 공격
 * 	- 같은 적이 여러 궁수에게 공격당할수도 있음
 * 
 * 궁수의 공격이 끝나면 적이 아래로 한칸 이동
 *  	- 성이 있는 칸으로 이동하면 사라짐
 * 
 * end : 모든 적이 격자판에서 제외되면 끝
 * 
 * 궁수의 위치가 중요함.
 * 격자판이 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수 계산
 * 
 * 거리 : |r1-r2| + |c1-c2|
 * 
 * 입력: 0은 빈칸, 1은 적이 있는 칸
 * 3<= N, M <= 15
 * 1<= D <= 10
 * 
 * 무식하게 봤을때 최대 시간복잡도
 * 15C3(궁수배치) * 15(턴) * 10(사거리 for문) * 3 (궁수) 
 * 
 * => 무식하게 짜도 될듯?
 * => 사거리 for문이 시간초과가 난다면 BFS로 짜보자
 * 
 * 궁수의 배치는?
 * 최적배치가 없는 이상 조합으로 찾아내야함
 * 최적배치는 적의 배치에 따라 달라지므로 불가능
 * 조합으로 찾아내보자
 * 
 * 구현해야할 기능
 * 1) 궁수 배치 (조합)
 * 	- 넥퍼 조합 or 재귀 => 재귀가 빠를거같다
 * 
 * 2) 궁수 사격
 *  - 맨왼쪽 궁수부터 사격(for문으로 안될거같다 => BFS로)
 *  	- 델타 설정 : 맨 왼쪽부터 탐색하도록 델타배열 배치
 *  	- 적을 찾았을 시 해당 적의 target 성질을 true로
 *  
 *  - 사격이 끝났다면 적 배열을 돌면서 target == true라면 
 *  	kill++ && 맵에서 제외
 * 	
 * => archer 클래스, enemy 클래스 만들기
 * => 맵에 enemy를 반영해줘야하므로 enemy 클래스는 사용 X
 * 
 * 3) 적의 이동
 * 	- 한칸 아래로.
 * 	- row+1 == N+1 이라면 사라지게 만들기 (kill에 포함 x)
 * 
 * 
 * 
 * @author SSAFY
 *
 */
public class Main_BJ_17135_캐슬디펜스_이주혁 {
	
	private static int N, M, D, ans;
	private static int[] archerLocList;
	private static int[][] deltas = {{0, -1}, {-1, 0}, {0, 1}};
	private static Archer[] archers;
	private static int[][] map;
	private static List<int[]> targetList;
	
	
	private static int getDistance(int ar, int ac, int er, int ec) {
		return Math.abs(ar - er) + Math.abs(ac - ec);
	}
	private static boolean isOutOfRange(int r, int c) {
		return r<0 || r>=N || c<0 || c>=M;
	}
	
	public static class Archer {
		
		int row;
		int col;
		int shootRange = D;
		
		public Archer(int col) {
			row = N;
			this.col = col;
		}

		// BFS - 적 찾기
		private void shoot(int[][] copiedMap, boolean[][] visited) {
			
			Queue<int[]> findQueue = new ArrayDeque<int[]>();
			findQueue.add(new int[] {this.row, this.col});
			
			// BFS 시작
			while(!findQueue.isEmpty()) {
				
				int[] rowAndCol = findQueue.poll();
				visited[rowAndCol[0]][rowAndCol[1]] = true;
				for(int[] d : deltas) {
					int nr = rowAndCol[0] + d[0];
					int nc = rowAndCol[1] + d[1];
					if(isOutOfRange(nr, nc) || visited[nr][nc] || getDistance(row, col, nr, nc) > D) continue;
					
					// 적을 찾았다면 리턴
					if(copiedMap[nr][nc] > 0 && getDistance(row, col, nr, nc) <= D) {
						targetList.add(new int[] {nr, nc});
						return;
					}
					findQueue.add(new int[] {nr, nc});
				}
				
			}

		}
		
	}
	
	
	public static void main(String[] args) throws IOException{
		
		// 입출력 관련
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		archers = new Archer[3];
		
		// 적 생성
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 궁수 3명의 위치 찾는 조합 메소드
		archerLocList = new int[3];
		archerLocSet(0, 0);
		System.out.println(ans);
	}
	
	private static void archerLocSet(int cnt, int start) {
		
		if(cnt == 3) {
			for(int i=0; i<3; i++) {
				archers[i]  = new Archer(archerLocList[i]);
			}
			gameStart();
			return;
		}
		
		for(int i = start; i < M; i++) {
			archerLocList[cnt] = i;
			archerLocSet(cnt+1, i+1);
		}

	}
	
	private static void gameStart() {
		
		int killPoint = 0;
		int[][] copiedMap = copyMap();
		int phase = 1;
		
		game : while(true) {
			
			targetList = new ArrayList<int[]>();
			
			// 궁수 사격
			for(int i=0; i<3; i++) {
				archers[i].shoot(copiedMap, new boolean[N+1][M+1]);
			}
			
			// 적 사망
			for(int i=0; i<targetList.size(); i++) {
				int[] enemyLoc = targetList.get(i);
				if(copiedMap[enemyLoc[0]][enemyLoc[1]] != 0) {
					copiedMap[enemyLoc[0]][enemyLoc[1]] = 0;
					killPoint++;
				}
			}
			
			// 적 이동
			for(int i=N-1; i>=1; i--) {
				System.arraycopy(copiedMap[i-1], 0, copiedMap[i], 0, M);
			}
			
			Arrays.fill(copiedMap[0], 0);
			
			
			// 적 체크
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					// 적이 생존해있다면 game 진행
					if(copiedMap[i][j] != 0) {
						phase++;
						continue game;
					}
				}
			}
			
			// 적이 존재하지 않는다면 game 종료
			break game;
		}
		ans = Math.max(ans, killPoint);
		
	}
	
	private static int[][] copyMap() {
		
		int[][] copiedMap = new int[N][M];
		
		for(int i=0; i<N; i++) {
			System.arraycopy(map[i], 0, copiedMap[i], 0, M);
		}
		
		return copiedMap;
	}

}
