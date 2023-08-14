// 분할정복

import java.awt.BufferCapabilities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 쿼드 트리 : 흑백영상 압축 표현
 * 흰점0 검은점1
 * @author cm
 * 2차원 배열을 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래로 4등분
 * 
 */
public class Main {
	static int spaces[][];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		spaces = new int[n][n];
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			for(int j=0; j<n; j++) {
				spaces[i][j]= line.charAt(j)-'0';
			}
		}
		
//		for(int[] c : spaces) {
//			System.out.println(Arrays.toString(c));
//		}
		
		
		quadtree(0,0,n);
		System.out.println(sb);
		
	}
	private static void quadtree(int sr, int sc, int size) {
		int sum = 0;
		
		for(int r=sr; r<sr+size; r++) {
			for(int c=sc; c<sc+size; c++) {
				sum+=spaces[r][c];
			}
		}
		
		if(sum==0) {
			sb.append("0");
		} else if(sum==size*size) {
			sb.append("1");
		} else {
			int half = size/2;
			sb.append("("); // 구획이 나누어지기 시작할 때 괄호가 생성
			quadtree(sr, sc, half);
			quadtree(sr, sc+half, half);
			quadtree(sr+half, sc, half);
			quadtree(sr+half, sc+half, half);
			sb.append(")"); // 괄호 소멸
		}
		
	}
}
