import java.util.*;
import java.io.*;
/**
 * The Game of Death 규칙:
 * 플레이어는 각자 한 명씩 지목을 한다(자신도 가능).
 * 처음 시작하는 사람은 임의의 자연수 K를 말한다.
 * 시작한 사람부터 지목한 사람을 차례대로 따라가다가 K번째 지목당한 사람이 걸리게 된다.
 * 주경이가 걸리기
 * N(1 ≤ N ≤ 10,000)
 * Ai(1 ≤ Ai ≤ N, 1 ≤ i ≤ N)
 * 희현이는 1번, 주경이는 N번
 *
 * 문제 해결 전략 :
 * 인접 연결리스트 유향그래프 DFS 탐색
 * 그래프 탐색을 위한 방문 배열
 */
public class Main {
    static class Node {
        int vertex;
        Node next;
        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", next=" + next +
                    '}';
        }
    }
    static int N=0, K=-1; // 주경이가 걸리기 위한 최소 숫자
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            boolean[] visited = new boolean[N+1];
            Node[] adjList = new Node[N+1];

            for(int i=1; i<=N; i++) {
                int to = Integer.parseInt(br.readLine());
                adjList[i]=new Node(to, adjList[i]);
            }

//            for(Node node : adjList) {
//                System.out.println(node);
//            }

            DFS(adjList[1].vertex, 1, visited, adjList);
            System.out.println(visited[N]?K:0); // 주경이가 불렸으면 K 아니면 0
        }
    }
    private static void DFS(int current, int cnt, boolean[] visited, Node[] adjList) {
        visited[current] = true;
        if(visited[N]) {
            K=cnt;
            return;
        }

//        System.out.println(Arrays.toString(visited));
//        System.out.print("current: "+current+" cnt: "+cnt);
//        System.out.println();

        for(Node temp = adjList[current]; temp!=null; temp=temp.next) {
            if(!visited[temp.vertex]) {
                DFS(temp.vertex, cnt+1, visited, adjList);
            }
        }
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int j = 0; j < testcase; j++) {

			int people = Integer.parseInt(br.readLine());

			int[] death = new int[people]; // 0부터 시작
			for (int k = 0; k < people; k++) {
				death[k] = Integer.parseInt(br.readLine());
			}
			
			int i = 0;
			int check = 1;
			if(death[i] == people){ // 지목과 일치하면 출력
				System.out.println(1);
			}
			while (death[i] != people) { // 주경이가 걸리지 않으면
				i = death[i] - 1; // 0부터 시작해서 배열의 원소에 맞게 -1
				check++; // 지목 횟수 증가
				if (death[i] == people) { // 지목과 일치하면(주경이가 걸리면) 출력
					System.out.println(check);
					break;
				}
				if (check >= people) { // 주경이가 걸리지 않을 경우
					System.out.println(0);
					break;
				}
			}
		}
	}
}
