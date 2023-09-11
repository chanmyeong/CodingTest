import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * 알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램
 * 길이가 짧은 것부터
 * 길이가 같으면 사전 순으로
 * 단, 중복된 단어는 하나만 남기고 제거
 * 첫째 줄에 단어의 개수 N이 주어진다. (1 ≤ N ≤ 20,000)
 * 
 * 문자열 compareTo 메서드의 경우
 * 기준값에 비교대상이 포함되어있을 경우 서로의 문자열 길이의 차이값을 리턴해준다.
 * compareTo는 같은 위치의 문자만 비교하기 때문에,
 * 첫번째 문자부터 순서대로 비교해서 다를 경우 바로 아스키값을 기준으로 비교처리를 한다.
 * compareTo의 경우 대소문자를 구분
 * 
 * [참고문헌]: https://st-lab.tistory.com/112
 */
public class Main {
	static int[][] apart;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		ArrayList<String> arrayList = new ArrayList<String>();
		for(int i=0; i<N; i++) {
			String word = br.readLine();
			if(!arrayList.contains(word)) {
				arrayList.add(word);				
			}
		}
		Collections.sort(arrayList, new Comparator<String>() { // 0,음수 : 위치고정 <-> 양수 : 위치변경
			@Override
			public int compare(String o1, String o2) {
				if(o1.length()==o2.length()) {
					return o1.compareTo(o2);
				}
				return o1.length()-o2.length();
			}
		});
		for(String str : arrayList) {
			sb.append(str).append('\n');
		}
		System.out.println(sb);
	}
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		
		for (int i=0; i<N; i++) {
			arr[i] = br.readLine();
		}
		
		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String s1, String s2) {
				if (s1.length() == s2.length()) {
					return s1.compareTo(s2);
				} else {
					return s1.length() - s2.length();
				}
			}
		});
		
		for (int i=0; i<N; i++) {
			bw.write(arr[i]);
			bw.newLine();
			
			while (i+1 < N && arr[i].equals(arr[i+1]))
				i++;
		}
		bw.flush();
		
	}
}
