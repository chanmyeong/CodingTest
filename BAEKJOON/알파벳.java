import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * RxC보드 1행1열에 말은 상하좌우 이동가능
 * 새로 이동한 칸에 적혀있는 알파벳은 지금까지 지나온 모든 칸에 적혀있는 알파벳과는 달라야함
 * 같은 알파벳이 적힌 칸을 두 번 지날 수 없다
 * 좌측 상단에서 시작해 말의 최대 이동 칸 수 구하기
 * @author cm
 * DFS+방문배열
 */

// 선체크 후넣기
public class Main {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int R,C,answer=0;
	static int[][] board;
	static boolean[] alphabet;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		board = new int[R][C];
		for(int i=0; i<R; i++) {
			String line2 = br.readLine();
			for(int j=0; j<C; j++) {
				// 알파벳 boolean배열을 만들기 위해 (board의 값을 index로 활용)
				board[i][j] = line2.charAt(j)-'A';
			}
		}
		
//		for(char[] c : board) {
//			System.out.println(Arrays.toString(c));
//		}
		
		alphabet = new boolean[26];
		alphabet[board[0][0]]=true;
		DFS(0,0,1);
		System.out.println(answer); // 말이 지날 수 있는 최대 칸 수(시작 칸 포함)
		
	}
	private static void DFS(int r, int c, int len) {
		
		answer=Math.max(answer, len);
		
		for(int k=0; k<4; k++) {
			int nr = r+dr[k];
			int nc = c+dc[k];
			if(inRange(nr, nc)) continue;
			if(!alphabet[board[nr][nc]]) {
				alphabet[board[nr][nc]]=true;
				DFS(nr,nc,len+1);
				alphabet[board[nr][nc]]=false;
			}
		}
	}
	private static boolean inRange(int nr, int nc) {
		return 0>nr || nr>=R || 0>nc || nc>=C;
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 먼저넣고 후체크
public class Main {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int R,C,answer=0;
	static int[][] board;
	static boolean[] alphabet;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		board = new int[R][C];
		for(int i=0; i<R; i++) {
			String line2 = br.readLine();
			for(int j=0; j<C; j++) {
				// 알파벳 boolean배열을 만들기 위해 (board의 값을 index로 활용)
				board[i][j] = line2.charAt(j)-'A';
			}
		}
		
//		for(char[] c : board) {
//			System.out.println(Arrays.toString(c));
//		}
		
		alphabet = new boolean[26];
		DFS(0,0,1);
		System.out.println(answer); // 말이 지날 수 있는 최대 칸 수(시작 칸 포함)
		
	}
	private static void DFS(int r, int c, int len) {
		if(inRange(r, c)) return;
		if(alphabet[board[r][c]]) return;
		
		answer=Math.max(answer, len);
		
		alphabet[board[r][c]]=true;
		for(int k=0; k<4; k++) {
			DFS(r+dr[k],c+dc[k],len+1);
		}
		alphabet[board[r][c]]=false;
	}
	private static boolean inRange(int nr, int nc) {
		return 0>nr || nr>=R || 0>nc || nc>=C;
	}
}
