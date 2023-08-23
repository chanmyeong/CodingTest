import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static class Edge implements Comparable<Edge>{ // <>제네릭 타입 명시 
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
//			return this.weight-o.weight;
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static Edge[] edgeList; // <<
	static int[] parents;
	static int E,V;
	
	private static void make() {
		parents = new int[V+1]; // <<
		for(int i=1; i<=V; i++) {
			parents[i]=i;
		}
	}
	
	private static int find(int a) {
		if(a==parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot==bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int ts=1; ts<=T; ts++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edgeList = new Edge[E];

			for(int i=0; i<E; i++) { // 간선의 정보를 간선리스트에 담기 <<
				st = new StringTokenizer(br.readLine()," ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);
			}

			Arrays.sort(edgeList);// 모든 간선을 가중치에 따라 오름차순 정렬 필수
			
			make();

			// 정점의 개수 V(1≤V≤100,000)와 간선의 개수 E(1≤E≤200,000)
			// 가중치는 음수일 수도 있으며, 절대값이 1,000,000을 넘지 않는다.
			// (V-1)*W -> int 범위를 벗어남
			long result=0; // MST 비용, 가중치의 합이 최소
			int count=0; // 연결된 간선의 개수
			for(Edge edge : edgeList) {// 간선에 대한 정보를 담은 간선 리스트를 탐색 
				if(union(edge.from, edge.to)) { // <<
					result += edge.weight;
					if(++count==V-1) break;
				}
			}
			
			System.out.printf("#%d %d%n",ts,result);
		}
	}
}
