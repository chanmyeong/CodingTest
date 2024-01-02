package boj;

import java.io.*;
import java.util.*;

/**
 * 최소 힙 자료 구조 구현
 * 빈 배열에서 시작
 * 1. 배열에 자연수 x 넣기
 * 2. 배열에서 가장 작은 값 출력, 해당 값 배열에서 제거
 * 
 * -입력
 * 첫째 줄에 연산의 개수 N(1 ≤ N ≤ 100,000)
 * 다음 N개의 줄에는 연산에 대한 정보 정수
 * x가 자연수라면 배열에 x라는 값을 넣는 추가 연산
 * x가 0이라면 배열에서 가장 작은 값을 출력하고 그 값을 배열에서 제거 연산
 * 
 * -출력
 * 입력에서 0이 주어진 횟수만큼 답을 출력
 * 배열이 비어 있는 경우인데 가장 작은 값을 출력하라고 한 경우에는 0을 출력
 * 
 * 힙 : 힙은 최댓값 및 최솟값을 찾아내는 연산을 빠르게 하기 위해 고안된 완전이진트리를 기본으로 한 자료구조
 * 우선순위 큐 : 우선순위 큐(Priority Queue)는 들어간 순서에 상관없이 우선순위가 높은 데이터가 먼저 나오는 것
 * 우선순위 큐는 힙(Heap)이라는 자료구조를 가지고 구현 가능
 * [참고 문헌]: https://st-lab.tistory.com/219
 * @author chanm
 *
 */

public class Main_1927_최소힙 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int x;
		PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>();
//		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순 정렬로 큰 값 먼저 출력하고 싶은 경우
		for(int n=0; n<N; n++) {
			x = Integer.parseInt(br.readLine());
			if(x>0) {
				minQueue.offer(x);
			} else {
				if(!minQueue.isEmpty()) {
					sb.append(minQueue.poll()).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}
