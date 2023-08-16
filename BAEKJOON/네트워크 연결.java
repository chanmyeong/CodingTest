// [참고문헌]: https://m.blog.naver.com/ndb796/221230994142 
// [참고문헌]: https://www.weeklyps.com/entry/%ED%94%84%EB%A6%BC-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Prims-algorithm
// [참고문헌]: https://bangu4.tistory.com/314
// [참고문헌]: https://velog.io/@jwkim/java-sorting

// 최소 신장 트리(Minimum Spanning Tree)
// 그래프에서 그래프의 모든 정점을 연결하되, 사이클이 존재하지 않도록 모든 정점을 간선으로 연결하는 것을 의미
// 가장 적은 비용으로 모든 노드를 연결한 트리
// 최소 신장 트리를 구하기 위해 사용되는 알고리즘
// 1. 크루스칼(Kruskal Algorithm) -> Union-Find 알고리즘(합집합 찾기)(서로소 집합 알고리즘(Disjoint-Set)) 이용
// 그래프 상에서 가중치를 가지는 간선의 연결 비용(먼저 간선 가중치 순서로 정렬 필요)을 최소로 가지는 해를 구할 때 알고리즘 이용
// Union-Find : 여러 개의 노드가 존재할 때 두 개의 노드를 선택해서, 현재 이 두 노드가 서로 같은 그래프에 속하는지 판별하는 알고리즘
// a. 모든 값이 자기 자신을 가리키도록 자료구조 생성(노드 번호 - 부모 노드 번호)
// b. 부모를 합칠 때는 일반적으로 더 작은 값 쪽으로 합침(Union) > 재귀가 사용됨
// c. 두 개의 노드의 부모 노드를 확인하여 현재 같은 집합에 속하는지 확인하는 알고리즘(Find)

// 2. 프림 알고리즘(Prim Algorithm) -> 일반적으로 Priority Queue 이용
// a. 임의의 정점을 선택하여 비어있는 T에 포함시킨다. (이제 T는 노드가 한 개인 트리)
// b. T 에 있는 노드와 T 에 없는 노드 사이의 간선 중 가중치가 최소인 간선을 찾는다.
// c. 찾은 간선이 연결하는 두 노드 중, T에 없던 노드를 T에 포함시킨다. (b.에서 찾은 간선도 같이 T에 포함)
// d. 모든 노드가 T에 포함될 때 까지, b.,c.를 반복한다.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		arr = new int[n+1]; // 노드수만큼을 가지는 arr 생성 (0은 편의상 미사용)
		
		// 연결정보 초기화
        for (int i = 0; i < arr.length; i++)
            arr[i] =i;
        // input 초기화
        int networks[][] = new int[m][3];
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            networks[i][0] = Integer.parseInt(st.nextToken()); // 노드 a
            networks[i][1] = Integer.parseInt(st.nextToken()); // 노드 b
            networks[i][2] = Integer.parseInt(st.nextToken()); // 가중치 cost
        }
        Arrays.sort(networks, Comparator.comparingInt(o->o[2])); // cost 낮은순 정렬, 2차원 배열을 특정 index 값을 기준으로 정렬
        
        int res=0; // 정답 result
        for(int[] network : networks) {
        	if(union(network[0],network[1])) { // 연결했으면
        		res+=network[2]; // 비용값 더함
        	}
        }
        bw.write(res+"\n");
        bw.flush();
        br.close();
        bw.close();
	}
	private static int find(int v) { // 연결 끝지점 탐색
		if(v==arr[v]) return v;
		return arr[v] = find(arr[v]); // 최적화 (값 최신화를 하지 않아도 무방함)
	}
	private static boolean union(int from, int to) {
		int a = find(from);
		int b = find(to);
		if(a!=b) { // 연결이 없으면 
			arr[a]=b; // 연결정보가 없는 끝 쪽에 갱신시켜야 연결이 끊어지지 않는다(중요)
			return true;
		}
		return false;
	}
	
}
