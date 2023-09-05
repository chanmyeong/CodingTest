import java.util.*;
import java.io.*;
/**
 * 나무 판자는 크기 1의 너비를 가졌고, 양수의 길이
 * 방은 직사각형 모양이고, 방 안에는 벽과 평행한 모양의 정사각형으로 나누어져 있다.
 * 만약 두 개의 ‘-’가 인접해 있고, 같은 행에 있다면, 두 개는 같은 나무 판자이고,
 * 두 개의 ‘|’가 인접해 있고, 같은 열에 있다면, 두 개는 같은 나무 판자이다.
 *
 * 방 바닥의 세로 크기 N과 가로 크기 M
 * N과 M은 50 이하인 자연수이다.
 *
 * 문제 해결 전략 (처음):
 * 1,1 시작해서 행과 열 2방 DFS 탐색 (우, 하)
 * 같은 나무판자일 경우 계속 탐색 진행(방문 체크)
 * 탐색 종료시 개수 1개 cnt
 *
 * 문제 해결 전략 (수정):
 * 무조건 출발을 왼쪽 상단부터 하기 때문에
 * 사방 탐색 없이 -일 경우에는 오른쪽, |일 경우에는 아래로만 이동하면 된다
 * 2가지 경우에 대한 DFS
 */
// 2가지 경우에 대한 DFS
public class Main {
    static int[][] drdc = {{0,1},{1,0}}; // [방향][행or열]우하
    static int N,M,cnt=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] room = new char[N][M];
        boolean[][] visited = new boolean[N][M];
        for(int i=0; i<N; i++) {
            String line = br.readLine();
//            room[i] = line.toCharArray();
            for (int j = 0; j < M; j++) {
                room[i][j] = line.charAt(j);
            }
        }
//        System.out.println(Arrays.deepToString(room));

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visited[i][j]) continue;
                if(room[i][j]=='-') DFS(i, j, true, room, visited);
                else DFS(i, j, false, room, visited);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
    private static void DFS(int r,int c, boolean row, char[][] room, boolean[][] visited) {
        visited[r][c]=true;
        if(row) { // 행에 대해서만 즉, 판자가 '-'이라면
            c++; // 열만 증가 (우 이동)
            if(c<M && room[r][c]=='-') DFS(r,c,true,room,visited);
        }
        else {
            r++; // 행만 증가 (하 이동)
            if(r<N && room[r][c]!='-') DFS(r,c,false,room,visited);
        }
    }
}
====================================================================================================
// 2방 DFS 탐색 (우, 하)
package study.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.management.Query;

public class BOJ1388_바닥장식_DFS {
	static class Node{
		int y,x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}
	static int N,M,cnt;
	static char map[][];
	static boolean visited[][];
	static int dy[]= {0,1};//우,하
	static int dx[]= {1,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		cnt=0;
		map=new char[N][];
		for (int i = 0; i < N; i++) {
			map[i]=br.readLine().toCharArray();
		}
		visited=new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					cnt++;
					int d=map[i][j]=='-'?0:1;
					dfs(i,j,d);
					
				}
			}
		}
		System.out.println(cnt);
	}
	private static void dfs(int i, int j,int go) {
		visited[i][j]=true;
		int ny=i+dy[go];
		int nx=j+dx[go];
				
		if(!inRange(ny, nx))return;
		if(visited[ny][nx])return;
		if(map[i][j]!=map[ny][nx])return;
		dfs(ny,nx,go);

	}
	private static boolean inRange(int y,int x) {
		if(y<N && y>=0 && x>=0 && x<M)
			return true;
		return false;

	}

}
====================================================================================================
// 2방 BFS 탐색 (우, 하)
package study.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.management.Query;

public class BOJ1388_바닥장식 {
	static class Node{
		int y,x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}
	static int N,M,cnt;
	static char map[][];
	static boolean visited[][];
	static int dy[]= {0,1};//우,하
	static int dx[]= {1,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		cnt=0;
		map=new char[N][];
		for (int i = 0; i < N; i++) {
			map[i]=br.readLine().toCharArray();
		}
		visited=new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					bfs(i,j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
	private static void bfs(int i, int j) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(i,j));

		while(!q.isEmpty()) {
			Node temp=q.poll();
			int d=map[temp.y][temp.x]=='-'?0:1;
			int ny=temp.y+dy[d];
			int nx=temp.x+dx[d];

			if(!inRange(ny, nx))continue;
			if(visited[ny][nx])continue;
			if(map[i][j]!=map[ny][nx])continue;
			visited[ny][nx]=true;
			q.offer(new Node(ny,nx));
		}
		
	}
	private static boolean inRange(int y,int x) {
		if(y<N && y>=0 && x>=0 && x<M)
			return true;
		return false;

	}
}
====================================================================================================
// 4방탐색
import java.util.*;
import java.io.*;

// 기훈이의 방 바닥을 장식하는데 필요한 나무 판자의 개수를 출력하는 프로그램을 작성하시오.
// -는 인접, 같은 행 , |는 인접 같은 열

public class Main {
	static int N;
	static int M;
	static char arr[][];
	static boolean visit[][];
	static int nowX; static int nowY;
	static int dirX[] = {0, 0, -1, 1};
	static int dirY[] = {-1, 1, 0, 0};
	
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = temp.charAt(j);
			}
		}

		int result = 0;
		// 가로 모양 타일 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visit[i][j] && arr[i][j] == '-') {
					DFS(i, j, 0, 2, '-');
					result++;
				}
			}
		}
		
		// 세로
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(!visit[j][i] && arr[j][i] == '|') {
					DFS(j, i, 2, 4, '|');
					result++;
				}
			}
		}

		System.out.println(result);
	} // End of main

	private static void DFS(int x, int y, int idxStart, int idxEnd, char ch) {
		visit[x][y] = true;
		
		for(int i=idxStart; i<idxEnd; i++) {
			nowX = dirX[i] + x;
			nowY = dirY[i] + y;
			
			if(range_check() && !visit[nowX][nowY] && arr[nowX][nowY] == ch) {
				DFS(nowX, nowY, idxStart, idxEnd, ch);
			}
		}
	} // End of DFS
	
	private static boolean range_check() {
		return nowX >= 0 && nowX < N && nowY >= 0 && nowY < M;
	} // End of range_check
} // End of Main class
  
====================================================================================================
// 구현
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = row.charAt(j);
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int tmp = 0;
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '|') {
                    tmp = 0;
                } else if (++tmp == 1) {
                    cnt++;
                }
            }
        }
        for (int j = 0; j < m; j++) {
            int tmp = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i][j] == '-') {
                    tmp = 0;
                } else if (++tmp == 1) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
====================================================================================================
// 구현2
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static char [][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		int result = 0;
		map = new char[N][M];
		for (int i = 0; i < N; i++) {

				map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(i != 0 && map[i][j]=='|' &&map[i-1][j]=='|') {
					continue;
				}else if(j != 0 && map[i][j]=='-' &&map[i][j-1]=='-') {
					continue;
				}
				result++;
			}
		}
    
		System.out.println(result);
	}
}
====================================================================================================
// 구현3
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] room = new char[N][M];
		// 세로 크기 N, 가로 크기 M 방

		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				room[i][j] = temp[j];
			}
		}
		// 입력 완료

		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				switch (room[i][j]) {
				case '|':
					// 세로 판자일때
					if (i == 0) {
						// 첫번째 행일 때
						cnt++;
					} else if (room[i - 1][j] != '|') {
						// 같은 열 바로 윗 판자가 다른 판자일 때
						cnt++;
					}
					break;
				default:
					// 가로 판자일때
					if (j == 0) {
						// 첫번째 열일때
						cnt++;
					} else if (room[i][j - 1] != '-') {
						// 같은 행 바로 왼쪽 판자가 다른 판자일 때
						cnt++;
					}
				}
			}
		}
		bw.write(Integer.toString(cnt));
		bw.flush();
		bw.close();
	}
}
