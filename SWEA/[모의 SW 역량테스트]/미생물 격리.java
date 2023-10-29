import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * N*N개의 동일한 크기의 정사각형 셀 안에 K개의 미생물 군집
 * 가장 바깥쪽 가장자리 부분에 위치한 셀들에는 특수한 약품 칠
 * 
 * 최초 각 미생물 군집의 위치와 군집 내 미생물의 수, 이동 방향
 * 약품이 칠해진 부분에는 미생물이 배치되어 있지 않다. 이동방향은 상, 하, 좌, 우
 * 
 * 각 군집들은 1시간마다 이동방향에 있는 다음 셀로 이동
 * 
 * 미생물 군집이 이동 후 약품이 칠해진 셀에 도착하면 군집 내 미생물의 절반이 죽고, 이동방향이 반대로 바뀐다
 * 살아남은 미생물 수 = 원래 미생물 수를 2로 나눈 후 소수점 이하를 버림 한 값
 * 미생물이 한 마리이면 살아남은 미생물 수가 0으로 군집 소멸
 * 
 * 이동 후 두 개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐짐
 * 합쳐 진 군집의 미생물 수는 군집들의 미생물 수의 합이며, 이동 방향은 군집들 중 미생물 수가 가장 많은 군집의 이동방향
 * 합쳐지는 군집의 미생물 수가 같은 경우는 주어지지 않으므로 고려하지 않아도 된다.
 * 
 * M 시간 동안 이 미생물 군집들을 격리하였다. M시간 후 남아 있는 미생물 수의 총합
 * 
 * 한 변의 셀의 개수 (5 ≤ N ≤ 100)
 * 최초 배치되어 있는 미생물 군집의 개수 (5 ≤ K ≤ 1,000)
 * 격리 시간 M (1 ≤ M ≤ 1,000)
 * 각 군집 내 미생물 수는 1 이상 10,000 이하의 정수
 * (상: 1, 하: 2, 좌: 3, 우: 4)
 * 각 군집의 정보는 세로 위치, 가로 위치, 미생물 수, 이동 방향 순으로 주어진다. 각 위치는 0번부터 시작한다.
 * 
 * 1) 1시간마다 이동방향의 다음 셀로 군집 이동
 * 2) 이동 후 약품이 칠해진 셀에 도착 시 미생물의 절반이 죽고, 이동방향이 반대로 바뀜
 *    두 개 이상의 군집이 한 셀에 모이는 경우 더 큰 군집 방향 및 미생물 수의 합으로 합쳐짐
 * 3) M시간 이후 남아있는 미생물의 총합 구하기
 * 
 * 문제 해결 전략 : 
 * 1. 모든 미생물 정보 리스트로 저장
 * 2. 객체 배열 만들기
 * 3. 미생물 이동 메소드 만들기
 *  - 미생물 한칸 이동
 *  - 이동한 칸이 경계에 걸치면 미생물 개수/2
 *  - 이동한 칸에 미생물이 존재한다면
 *  	1) 작은 미생물 리스트에서 삭제
 *  	2) 큰 미생물 정보에 작은 미생물 정보 더하기
 *  	3) 객체 정보에 가장 큰 미생물의 크기 함께 저장
 * 4. M시간 동안 반복
 * 5. K 미생물의 개수만큼 반복
 * 6. 리스트 돌며 미생물의 수 계산
 * 
 * 96,432 kb 메모리, 384 ms 실행시간
 */
public class Solution_2382_미생물격리 {
	//0번 사용X 1:상, 2:하, 3:좌, 4:우
	final static int[] dr = {0, -1, 1, 0, 0};
	final static int[] dc = {0, 0, 0, -1, 1};

	static int N,M,K;
	static long total; // 총 미생물의 크기
	static Microbe[][] map;
	static List<Microbe> list = new LinkedList<>();

	static class Microbe {
		int r,c;
		int dir; // 방향
		int large; // 합쳐질 때 가장 큰 미생물의 정보를 저장해놓기 위해
		int cnt; // 미생물 숫자
		public Microbe(int r, int c, int cnt, int dir) {
			this.r=r;
			this.c=c;
			this.cnt=cnt;
			this.dir=dir;
		}

		@Override
		public String toString() {
			return "Microbe [r=" + r + ", c=" + c + ", dir=" + dir + ", cnt=" + cnt + "]";
		}

		public void move() {
			//합칠 때 담았던 large가 새로 이동시에는 size와 같아져야 함.
			this.large = this.cnt;

			//좌표 이동
			r += dr[dir];
			c += dc[dir];

			//맵이 비어있는 경우
			if(map[r][c]==null) {
				//이동한 좌표가 경계라면 방향 바꾸고, large/2, cnt/2
				if(r==0 || r==N-1 || c==0 || c==N-1) {
					//방향 전환
					if(dir%2==0) dir--;
					else dir++;

					//빼질 값
					int minus = cnt-cnt/2;

					large-=minus;
					cnt-=minus;

					//총 미생물의 크기에 해당값 minus
					total-=minus;

					//사이즈가 0이 된 경우 리스트에서 객체 정보 지우고 끝냄
					if(cnt==0) {
						list.remove(this);
						return;
					}
				}
				//map에 객체 정보 저장
				map[r][c]=this;
			}
			// 맵이 비어있지 않은 경우 - 이동한 좌표에 다른 미생물이 있음
			else {
				//원래 맵에 있던 미생물 정보
				Microbe origin = map[r][c];

				//large 비교해서 새로운 미생물 정보가 더 크다면 large와 방향 정보 변경
				if(this.large>origin.large) {
					origin.large = this.large;
					origin.dir = this.dir;
				}
				// size는 더해줌
				origin.cnt = this.cnt + origin.cnt;

				// 현재 객체 리스트에서 삭제
				list.remove(this);
			}
		}
	}

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new StringReader(str));
		StringTokenizer st; 

		int TC = Integer.parseInt(br.readLine());

		for(int tc=1; tc<=TC; tc++) {

			list.clear();

			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			total = 0;

			//map과 list에 미생물 정보 담기
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				list.add(new Microbe(r, c, cnt, dir));

				total+=cnt;
			}
//			for(Microbe m : list) {
//				System.out.println(m);				
//			}
			//M시간 동안 반복
			while(M-->0) {
				map = new Microbe[N][N];
				//미생물의 개수만큼 반복
				for(int i=list.size()-1; i>=0; i--) {
					//미생물 정보 가져오기
					Microbe microbe = list.get(i);

					//미생물 이동
					microbe.move();
				}
			}
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.printf("%5s",map[i][j]!=null?map[i][j].cnt:0);
//				}
//				System.out.println();
//			}

			sb.append("#"+tc+" "+total).append("\n");
		}	
		System.out.println(sb);
	}
	static String str = "1\r\n" + 
			"7 2 9\r\n" + 
			"1 1 7 1\r\n" + 
			"2 1 7 1\r\n" + 
			"5 1 5 4\r\n" + 
			"3 2 8 4\r\n" + 
			"4 3 14 1\r\n" + 
			"3 4 3 3\r\n" + 
			"1 5 8 2\r\n" + 
			"3 5 100 1\r\n" + 
			"5 5 1 1";
}

====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 98,716 kb 메모리 389 ms
public class Solution_2382_미생물격리 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;

    static int T;
    static int N, M, K;
    static int[][] deltas = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } }; // 상, 좌, 우, 하
    static List<Microbe> microbes;
    static int A;

    public static void main(String[] args) throws NumberFormatException, IOException {
        input = new BufferedReader(new StringReader(str));
        T = Integer.parseInt(input.readLine());
        for (int t = 1; t <= T; t++) {
            tokens = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokens.nextToken());
            M = Integer.parseInt(tokens.nextToken());
            K = Integer.parseInt(tokens.nextToken()); // 바이러스 군집 수

            microbes = new ArrayList<>();
            for (int k = 0; k < K; k++) {
                Microbe m = new Microbe(input.readLine());
                microbes.add(m);
            }
            // 입력 끝!!!!
            A = 0;
            // solve
            solve();
            output.append("#").append(t).append(" ").append(A).append("\n");
        }
        System.out.println(output);
    }

    static void solve() {
        for (int m = 0; m < M; m++) {
            // 더이상 돌릴 필요가 없다면 그만!!!
            int size = microbes.size();
            if (size == 0) {
                break;
            }

            // 큰녀석부터 처리하자!
            Collections.sort(microbes);
            Microbe[][] map = new Microbe[N][N];
            // 바이러스 배치 시키기
            for (int i = 0; i < size; i++) {
                Microbe microbe = microbes.remove(0);
                // 이동 후 사이즈가 0 이상이면 다시 추가
                if (microbe.move()) {
                    // 이동한 좌표가 무주공산이면 내가 입주!!!
                    if (map[microbe.r][microbe.c] == null) {
                        map[microbe.r][microbe.c] = microbe;
                        microbes.add(microbe);
                    }
                    // 기존에 터줏대감이 있다.
                    else {
                        map[microbe.r][microbe.c].s += microbe.s;
                    }
                }
            }
        }// 취합 완료

        for (Microbe m : microbes) {
            A += m.s;
        }
    }

    static class Microbe implements Comparable<Microbe> {
        int r, c, s, d;

        public Microbe(String line) {
            tokens = new StringTokenizer(line);
            this.r = Integer.parseInt(tokens.nextToken());
            this.c = Integer.parseInt(tokens.nextToken());
            this.s = Integer.parseInt(tokens.nextToken());
            this.d = Integer.parseInt(tokens.nextToken());
            // 방향 변환을 쉽게 해주기 위해 3의 보수 형태로 변경하기
            if (this.d == 1) {
                this.d = 0;
            } else if (this.d == 2) {
                this.d = 3;
            } else if (this.d == 3) {
                this.d = 1;
            } else {
                this.d = 2;
            }
        }

        public boolean move() {
            this.r += deltas[this.d][0];
            this.c += deltas[this.d][1];
            // 약품인지 확인
            if (this.r == 0 || this.r == N - 1 || this.c == 0 || this.c == N - 1) {
                this.d = 3 - this.d;
                this.s /= 2;
            }
            return this.s > 0;
        }

        @Override
        // 크기가 큰 순으로 나와서 선점될 수 있게 하자. - 내림차순
        public int compareTo(Microbe o) {
            // TODO Auto-generated method stub
            return Integer.compare(this.s, o.s) * -1;
        }
    }

    static String str = "1\r\n" + 
			"7 2 9\r\n" + 
			"1 1 7 1\r\n" + 
			"2 1 7 1\r\n" + 
			"5 1 5 4\r\n" + 
			"3 2 8 4\r\n" + 
			"4 3 14 1\r\n" + 
			"3 4 3 3\r\n" + 
			"1 5 8 2\r\n" + 
			"3 5 100 1\r\n" + 
			"5 5 1 1";
}
