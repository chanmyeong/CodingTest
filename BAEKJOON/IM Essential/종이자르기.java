import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 입력으로 종이의 가로 세로 길이, 그리고 잘라야할 점선들이 주어질 때,
 * 가장 큰 종이 조각의 넓이가 몇 ㎠인지를 구하는 프로그램
 * 
 * 가로로 자르는 점선은 0과 점선 번호가 차례로 주어지고, 세로로 자르는 점선은 1과 점선 번호가 주어진다
 * 
 * 문제 해결 전략 : 가장 긴 가로의 길이와 세로의 길이를 각 각 구하여 곱한다.
 */

public class Main {
	static int r, c;
	static int cutCnt,maxR,maxC;
	static int[] rCut, cCut;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine()," ");
		c = Integer.parseInt(tokens.nextToken());
		r = Integer.parseInt(tokens.nextToken());
		cutCnt = Integer.parseInt(br.readLine());
		
		cCut = new int[c];
		rCut = new int[r];
		
		for(int i=0; i<cutCnt; i++) {
			tokens = new StringTokenizer(br.readLine(), " ");
			int flag = Integer.parseInt(tokens.nextToken());
			if(flag==0) {
				rCut[Integer.parseInt(tokens.nextToken())]++;
			}
			if(flag==1) {
				cCut[Integer.parseInt(tokens.nextToken())]++;
			}
		}
		
		int preR = 0, preC = 0;
		int lastR = 0, lastC = 0;
		List<Integer> rLength = new ArrayList<>();
		List<Integer> cLength = new ArrayList<>();
		for(int i=1; i<r; i++) {
			if(rCut[i]!=0) {
//				System.out.println((i-preR)+" "+(r-i));
				rLength.add(i-preR);
				lastR = r-i;
				preR = i;
			}
		}
		rLength.add(lastR);
//		System.out.println("==========");
		for(int j=1; j<c; j++) {
			if(cCut[j]!=0) {
//				System.out.println((j-preC)+" "+(c-j));
				cLength.add(j-preC);
				lastC = c-j;
				preC = j;
			}
		}
		cLength.add(lastC);
		
		for(int i : rLength) {
			maxR = Math.max(maxR, i);
		}
		for(int j : cLength) {
			maxC = Math.max(maxC, j);
		}
		if(maxR==0) maxR=r; // 잘린 길이가 없다면 최대 길이는 잘리지 않은 길이
		if(maxC==0) maxC=c;
//		System.out.println(rLength);
//		System.out.println(cLength);
		System.out.println(maxR*maxC);
	}
}
====================================================================================================
import java.io.*;
import java.util.*;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
 
		int M = Integer.parseInt(st.nextToken()); // 열
		int N = Integer.parseInt(st.nextToken()); // 행
 
		int[] cutM = new int[M + 1];
		int[] cutN = new int[N + 1];
 
		st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken()); // 점선 개수
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			int mode = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (mode == 0) { // 가로로
				cutN[num] = 1; // 행 배열
			} else
				cutM[num] = 1;
		}
		int temp = 0;
		int maxN = 0;
		for (int i = 1; i <= N; i++) {
			temp++;
			if (cutN[i] == 1 || i == N) {
				maxN = Math.max(maxN, temp);
				temp = 0;
			}
		}
		temp = 0;
		int maxM = 0;
		for (int i = 1; i <= M; i++) {
			temp++;
			if (cutM[i] == 1 || i == M) {
				maxM = Math.max(maxM, temp);
				temp = 0;
			}
		}
		System.out.println(maxM * maxN);
	}
 
}
