import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 탈주범은 탈출한 지 한 시간 뒤,
 * 맨홀뚜껑을 통해 지하터널 한 지점으로 들어가 은신 중
 * 
 * 터널이 연결된 경우 이동가능
 * 탈주범이 있을 수 있는 위치 계산
 * 
 * 탈주범은 시간 당 1의 거리 이동 가능
 * 
 * 터널 구조물 타입
 * 1~7 : 1 상하좌우, 2 상하, 3 좌우, 4 상우, 5 하우, 6 하좌, 7 상좌
 * 
 * 지도 크기 0부터 시작
 * 맨홀 뚜껑의 위치 붉은 색
 * 탈주범이 탈출 한 시간 뒤 도달할 수 있는 지점은 한 곳 : 붉은 색(1에서 시작)
 * 탈주 후 한 시간이 추가될 때마다 이어진 터널의 거리 1만큼의 위치에 대해 도달할 수 있는 지점이 증가
 * 
 * 2차원 배열 (5 ≤ N, M ≤ 50)
 * 맨홀 뚜껑 좌표 (0 ≤ R ≤ N-1, 0 ≤ C ≤ M-1)
 * 탈출 후 소요된 시간 L (1 ≤ L ≤ 20)
 * 지하 터널 지도에는 반드시 1개 이상의 터널이 있음이 보장
 * 맨홀 뚜껑은 항상 터널이 있는 위치에 존재
 * 
 * 출력해야 할 정답은 탈주범이 위치할 수 있는 장소의 개수
 */

/**
 * 터널끼리 연결되어 있는 경우 이동이 가능
 * 
 * 탈출범이 있을 수 있는 위치의 개수를 계산
 * 
 * 탈주범은 시간당 1의 거리 움직임
 * 
 * 
 * 7종류의 터널 구조물로 구성
 * 1. 상하좌우
 * 2. 상하
 * 3. 좌우
 * 4. 상우
 * 5. 하우
 * 6. 하좌
 * 7. 상좌
 * 
 * N: 세로크기
 * M: 가로크기
 * 
 * R, C: 맨홀 뚜껑의 위치
 * L : 탈출 후 소요된 시간
 * 
 * 시작 지점부터 1의 시간이 소요됨.
 * 
 * 맨홀 뚜껑은 항상 터널이 있는 위치에 존재.
 * 지하 터널 지도에는 반드시 1개 이상의 터널이 있음.
 * 
 * 문제 해결 프로세스
 * 1. 탈출범의 위치로부터 레별별 BFS 탐색을 통해 depth 1부터 L까지 탐색을 이어감
 *      이동 조건
 *          => 경계를 벗어X, 방문X, 지하도가 연결
 *      이동 가능할 시
 *          => 방문체크, 큐에 넣기, 위치 cnt
 *  
 *  => depth탐색을 마치고 depth가 증가했을 때 L이되면 탐색을 멈춤
 * 
 * 2. 탐색이 끝났을 때 위치할 수 있는 개수 출력
 * 
 * 
 * 지하도 연결
 * 현재 지하도에서 갈 수 있는 방향 - fromDir
 * 다른 지하도에서 올 수 있는 방향 - toDir
 * 상0 하1
 * 우3 좌2
 * 좌2 우3
 * 하1 상0
 * 가려는 방향과 다음 좌표의 구조물 정보를 주었을 때 해당 방향으로 이동가능한지를 반환해주는 메소드 작성
 * 
 * 
 * @author minho
 *
 */
public class Solution_1953_탈주범검거_연민호 {
    //상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
     
    //지하도에서 갈 수 있는 방향
    //dirs[1] : 1번 구조물에서 갈 수 있는 방향
    static int[][] dirs = {
            {},
            {0,1,2,3},  //1 상하좌우
            {0,1},  //2 상하
            {2,3},  //3 좌우
            {0,3},  //4 상우
            {1,3},  //5 하우
            {1,2},  //6 하좌
            {0,2},  //7 상좌
    };
     
    static int N, M;    //지하도의 세로, 가로 크기
    static int R, C;    //맨홀 뚜껑의 위치
    static int L;       //탈출 후 소요된 시간(탈출범이 이동 가능한 시간)
     
    static int[][] map; //지하도
     
    static int cnt; //위치할 수 있는 개수
     
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        //System.setIn(new FileInputStream("탈주범검거_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
             
            map = new int[N][M];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            cnt=0;
            bfs();
             
            sb.append('#').append(tc).append(' ').append(cnt).append('\n');
        }
         
        System.out.println(sb);
    } 
 
    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
         
        //시작점 큐에 넣고 방문 체크
        q.offer(new int[] {R,C});
        visited[R][C] = true;
        cnt++;  //위치 개수 cnt
         
        //L==1인 경우 탐색 하지 않고 종료
        if(L==1) return;
         
        int depth = 1;  //소요된 시간
        while(!q.isEmpty()) {
             
            int size = q.size();
            while(size-->0) {
                int[] position = q.poll();
                int r = position[0];
                int c = position[1];
                 
                //현재 위치에서 이동 가능한 방향 인덱스 가져오기
                for(int dir : dirs[ map[r][c] ]) {
                    //다음 좌표 받아오기
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];
                     
                    //경계 벗어나거나 방문했거나 연결되지 않았다면 다음 방향
                    if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
                    if(visited[nr][nc]) continue;
                    if(!isConnected( dir, map[nr][nc] )) continue;
                     
                    //모든 조건을 만족한다면 탐색을 이어감
                    q.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                    cnt++;  
                }
            }
            //탈출 후 소요된 시간만큼 탐색을 마쳤다면 종료
            if(++depth==L) return;
        }
    }
 
    /**
     * 
     * @param fromDir 출발점의 방향
     * @param target  도착점의 구조물 타입
     * @return  출발점과 도착점의 연결여부 반환
     */
    private static boolean isConnected(int fromDir, int target) {
//      상0 하1
//      우3 좌2
//      좌2 우3
//      하1 상0
//      위 결과로 보아 연결되어 있는 경우 두 방향의 합이 1 또는 5이므로 합이 1또는 5라면 연결되어 있다고 판단
        for(int targetDir : dirs[target]) {
            int sum = fromDir + targetDir;
            if(sum==1 || sum==5) return true;
        }
        return false;
    }
}
