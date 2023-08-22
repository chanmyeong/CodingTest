import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int n,m;
	static int[] parents;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int ts=1; ts<=T; ts++) {
			String[] line = br.readLine().split(" ");
			n = Integer.parseInt(line[0]);
			m = Integer.parseInt(line[1]);
			parents = new int[n+1]; // 1부터 시작
			
			sb.append("#"+ts+" ");

			for(int i=1; i<=n; i++) {
				parents[i] = i; // 각 집합에 대해 자기 자신을 가리키도록 한다
			}
			
			int uf=-1, a=-1, b=-1;
			for(int i=0; i<m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				uf = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
			
				if(uf==0) {
					union(a,b);
				}
				else if(uf==1) {
					if(find(a)==find(b)) sb.append(1);
					else sb.append(0);
				}			
			}
			
			System.out.println(sb);
			sb.setLength(0); // StringBuilder 초기화, sb.delete(from, to)
		}
	}
	private static int find(int a) {
		if(a==parents[a]) {
			return a;
		}
//		return find(parents[a]);
		return parents[a] = find(parents[a]); // 최적화, 찾음과 동시에 트리배열 갱신
	}
	private static boolean union(int a, int b) {
		int aRoot = find(a); // 단순한 p[b]=a;가 아니라 p[find(b)]=find(a);
		int bRoot = find(b);
		
		if(aRoot==bRoot) return false; // 서로의 대표자가 같음, 같은 집합이라 union하지 않음
		parents[bRoot] = aRoot; // union, 대표자 찾아서 연결
		return true;
	}
}
