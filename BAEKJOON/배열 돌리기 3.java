import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 주어진 nxm배열에서 1~6번 연산들을 수행가능하여 최종 결과를 도출 
 * @author cm
 * 문제 접근 : 
 * 1,2번 연산은 행,열 반전
 * 3,4번 연산은 행열을 읽는 시작위치를 잘 정해주기?
 * 5,6번 연산은 4개의 동일한 자료구조를 만들어 담고 3,4번 연산을 수행 >> X
 * 5,6번 연산도 메서드 따로 생성
 */
public class Main {
	static int n,m,r; // 3,4번 연산에서부터 배열의 크기가 달라질 수 있음
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int[] num = new int[r+1]; // index 1부터 시작
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=r; i++) {
			num[i]=Integer.parseInt(st.nextToken());
		}		
		
//		System.out.println(Arrays.toString(num));
		
		for(int k=1; k<=r; k++) {
			if(num[k]==1) arr = oper1(arr);
			else if(num[k]==2) arr = oper2(arr);
			else if(num[k]==3) arr = oper3(arr);
			else if(num[k]==4) arr = oper4(arr);
			else if(num[k]==5) arr = oper5(arr);
			else if(num[k]==6) arr = oper6(arr);
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(arr[i][j]+" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int[][] oper1(int[][] arr) {
		int[][] tmp = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				tmp[n-1-i][j]=arr[i][j];
			}
		}
		return tmp;
	}
	
	private static int[][] oper2(int[][] arr) {
		int[][] tmp = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				tmp[i][m-1-j]=arr[i][j];
			}
		}
		return tmp;
	}
	
	private static int[][] oper3(int[][] arr) {
		int[][] tmp = new int[m][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				tmp[j][n-1-i]=arr[i][j];
			}
		}
		
		// 배열 바꾸기 => 결과 배열 크기가 달라지므로
		int w=n;
		n=m;
		m=w;
		return tmp;
	}
	
	private static int[][] oper4(int[][] arr) {
		int[][] tmp = new int[m][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				tmp[m-1-j][i]=arr[i][j];
			}
		}
		int w=n;
		n=m;
		m=w;
		return tmp;
	}
	
	private static int[][] oper5(int[][] arr) {
		int[][] tmp = new int[n][m];
		for(int i=0;i<arr.length;i++) {
			tmp[i] = arr[i].clone();
		}

		//i값이랑 j값이 N/2, M/2보다 작으면 밑으로
		//i값이 N/2보다 작고 j값이 M/2보다 크거나 같으면 왼쪽으로
		//i값과 j값이 N/2, M/2보다 크거나 같으면 위으로
		//i값이 N/2보다 크거나 같고 j값이 M/2보다 작으면 오른쪽으로
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(i<n/2&&j<m/2) {	//1
					arr[i][j]=tmp[i+n/2][j];
				}
				else if(i<n/2&&j>=m/2) {	//2
					arr[i][j]=tmp[i][j-m/2];
				}
				else if(i>=n/2&&j>=m/2) {	//3
					arr[i][j]=tmp[i-n/2][j];
				}
				else if(i>=n/2&&j<m/2) {	//4
					arr[i][j]=tmp[i][j+m/2];
				}
			}
		}
		return arr;
	}
	
	private static int[][] oper6(int[][] arr) {
		int[][] tmp = new int[n][m];
		for(int i=0;i<arr.length;i++) {
			tmp[i] = arr[i].clone();
		}

		//1.i값이랑 j값이 N/2, M/2보다 작으면 오른쪽으로
		//2.i값이 N/2보다 작고 j값이 M/2보다 크거나 같으면 밑으로
		//3.i값과 j값이 N/2, M/2보다 크거나 같으면 왼쪽으로
		//4.i값이 N/2보다 크거나 같고 j값이 M/2보다 작으면 위로

		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(i<n/2&&j<m/2) {	//1
					arr[i][j]=tmp[i][j+m/2];
				}
				else if(i<n/2&&j>=m/2) {	//2
					arr[i][j]=tmp[i+n/2][j];
				}
				else if(i>=n/2&&j>=m/2) {	//3
					arr[i][j]=tmp[i][j-m/2];
				}
				else if(i>=n/2&&j<m/2) {	//4
					arr[i][j]=tmp[i-n/2][j];
				}
			}
		}
		return arr;
	}
}
