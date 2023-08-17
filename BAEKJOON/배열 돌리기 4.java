import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 배열 A의 값 = 각 행에 있는 모든 수의 합 중 최솟값
 * 회전 연산 : (r,c,s)에서
 * 가장 왼쪽 윗 칸이 (r-s, c-s), 가장 오른쪽 아랫 칸이 (r+s, c+s)인 정사각형을
 * 시계 방향으로 한 칸씩 돌리기
 * @author cm
 * 
 */

public class Main {
    static int ltr=0, ltc=0; // LeftTop 
    static int rbr=0, rbc=0; // RightBottom
    static int n,m,k;
    static int answer = Integer.MAX_VALUE; // 배열 각 행의 모든 수의 합 중 최솟값
    static int[] dr = {1,0,-1,0}; //하 우 상 좌  : 덮어쓰기 기준, 해당 위치보다 1하(우상좌 순서)인 곳에서 덮어씀
    static int[] dc = {0,1,0,-1};
    static int[][] arr, tempArr;
    static Rotate[] rotate;
    static int[] rotateIdx;
    static boolean[] isSelected;
    
    static class Rotate { // inner class를 사용하여 파일간의 class 겹침 방지
        int r; int c; int s;
        Rotate (int r, int c, int s) {
            this.r=r;
            this.c=c;
            this.s=s;
        }
    	@Override
    	public String toString() {
    		return "Rotate [r=" + r + ", c=" + c + ", s=" + s + "]";
    	}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        rotate = new Rotate[k];
        isSelected = new boolean[k];
        rotateIdx = new int[k];
        
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            // 문제를 잘 읽고서 0에서 시작하는지 1에서 시작하는지를 잘 보아야 한다.
            rotate[i] = new Rotate(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));  
        }
        
        permutation(0);    
        System.out.println(answer);
    }
    private static void permutation(int depth) {
        if(depth==k) {
//        	System.out.println(Arrays.toString(rotateIdx));
        	copyArr();
//        	System.out.println("원본");
//        	print();
            for(int l=0; l<k; l++) {
                findSquare(rotateIdx[l]);
//                rotateClockwise(Math.min(rbr-ltr, rbc-ltc)/2);
                rotateClockwise(rotate[rotateIdx[l]].s);
                
//                System.out.println(rotate[rotateIdx[l]]);
//                print();
            }
            calcArr();
            return;
        }
        for(int i=0; i<k; i++) {
            if(isSelected[i]) continue;
            isSelected[i]=true;
            rotateIdx[depth]=i;
            permutation(depth+1);
            isSelected[i]=false;
        }
        
    }
    private static void print() {
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m ;j++) {
    			System.out.print(tempArr[i][j]+" ");
    		}
    		System.out.println();
    	}
	}
	private static void findSquare(int l) {
        ltr = rotate[l].r-rotate[l].s;
        ltc = rotate[l].c-rotate[l].s;
        rbr = rotate[l].r+rotate[l].s;
        rbc = rotate[l].c+rotate[l].s;
    }
    public static void copyArr() {
        tempArr = new int[n][m]; // 임시로 배열을 만들어 원본 배열의 값이 변하지 않도록 하기 위함
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tempArr[i][j] = arr[i][j];
            }
        }
    }

    private static void rotateClockwise(int depth) {
        for(int k=0; k<depth; k++) {
        	//k=0 (ltr, ltc)
        	//k=1 (ltr+1, ltc+1)
            int r=ltr+k;
            int c=ltc+k;
            int tmp = tempArr[r][c]; // 시작점 저장 (덮어져서 기존 값을 잃게됨을 방지)
            int dir=0;
            while(dir<4) {
                int nr = r+dr[dir];
                int nc = c+dc[dir];
                if(nr >= ltr+k && nr <= rbr-k && nc >= ltc+k && nc <= rbc-k) {
                    tempArr[r][c]=tempArr[nr][nc]; // 시계 방향으로 덮어쓰기
                    r=nr;
                    c=nc;
                }
                else {
//                	System.out.printf("nr:%d, nc:%d, dir:%d%n", nr, nc, dir);
                    dir++;
                }
            }            
            tempArr[ltr+k][ltc+k+1]=tmp; // 덮어쓰느라 지워졌던 시작점 값을 시작점 바로 오른쪽에 담기
        }

    }
    private static void calcArr() {
        for(int i=0; i<n; i++) {
            int sum = 0;
            for(int j=0; j<m; j++) {
                sum += tempArr[i][j]; // 원본 배열이 아닌 복사한 배열에서 회전하고 그 결과를 따지는 것
            }
            answer = Math.min(answer, sum);
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
    static int n,m,k;
    static int answer = Integer.MAX_VALUE; // 배열 각 행의 모든 수의 합 중 최솟값
    static int[] dr = {1,0,-1,0}; //하 우 상 좌
    static int[] dc = {0,1,0,-1};
    static int[][] arr, tempArr;
    static Rotate[] rotate;
    static Rotate[] picked;
    static boolean[] isSelected;
    
    static class Rotate {
        int r; int c; int s;
        Rotate (int r, int c, int s) {
            this.r=r;
            this.c=c;
            this.s=s;
        }
    	@Override
    	public String toString() {
    		return "Rotate [r=" + r + ", c=" + c + ", s=" + s + "]";
    	}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        rotate = new Rotate[k];
        isSelected = new boolean[k];
        picked = new Rotate[k];
        
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            rotate[i] = new Rotate(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));  
        }
        
        permutation(0);    
        System.out.println(answer);
    }
    private static void permutation(int depth) {
        if(depth==k) {
        	copyArr();
            for(int l=0; l<k; l++) {
                rotateClockwise(picked[l]);
            }
            calcArr();
            return;
        }
        for(int i=0; i<k; i++) {
            if(isSelected[i]) continue;
            isSelected[i]=true;
            picked[depth]=rotate[i];
            permutation(depth+1);
            isSelected[i]=false;
        }
        
    }
    public static void copyArr() {
        tempArr = new int[n][m]; // 임시로 배열을 만들어 원본 배열의 값이 변하지 않도록 하기 위함
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tempArr[i][j] = arr[i][j];
            }
        }
    }

    private static void rotateClockwise(Rotate rotate) {
    	// 변수가 길어지면 변수의 길이를 축소하기
    	int cR = rotate.r;	//회전의 중앙값 행
    	int cC = rotate.c;	//회전의 중앙값 열
        for(int k=1; k<=rotate.s; k++) { // 중앙값부터 동심원 형태로 회전 테두리가 커져감
            int r = cR-k;
            int c = cC-k;
            int tmp = tempArr[r][c]; // 시작점 저장 (덮어져서 기존 값을 잃게됨을 방지)
            int dir=0;
            while(dir<4) {
                int nr = r+dr[dir];
                int nc = c+dc[dir];
                if(cR-k <= nr && nr <= cR+k && cC-k <= nc  && nc <= cC+k) {
                    tempArr[r][c]=tempArr[nr][nc]; // 시계 방향으로 덮어쓰기
                    r=nr;
                    c=nc;
                }
                else {
                    dir++;
                }
            }
            tempArr[cR-k][cC-k+1]=tmp; // 덮어쓰느라 지워졌던 시작점 값을 시작점 바로 오른쪽에 담기
        }

    }
    private static void calcArr() {
        for(int i=0; i<n; i++) {
            int sum = 0;
            for(int j=0; j<m; j++) {
                sum += tempArr[i][j];
            }
            answer = Math.min(answer, sum);
        }
    }
}
