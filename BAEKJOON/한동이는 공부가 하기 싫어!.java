import java.util.*;
import java.io.*;
/**
 * 한동이가 한 사람에게만 시험문제를 물어볼 수 있다고 할 때,
 * 최대한 많은 선배들을 만날 수 있게 하기 위해서 누구에게 시험문제를 물어 볼 것인지 정해주기
 * N은 2이상 1000 이하의 자연수
 * 선배들은 1부터 N까지 번호, N번째 선배의 대답
 * 하나 이상의 정답이 있다면 번호가 작은 선배를 출력한다.
 *
 * 문제 해결 전략 :
 * 가장 긴 DFS 탐색의 시작 지점 알아내기
 * 방문 배열 필요 -> 물어본 사람 겹치면 종료하고 depth 비교
 * depth 최댓값 갱신, 번호(index) 최소값 출력
 */
public class Main {
    static int N, depthLength=-1, answer=1000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[N+1];
        int[] adjList = new int[N+1]; // 1부터 시작

        for(int p=1; p<=N; p++) {
            int to = Integer.parseInt(br.readLine());
            adjList[p] = to;
        }

        int max=-1;
        for(int i=1; i<=N; i++) {
            visited[i]=true;
            DFS(adjList, adjList[i], 1, visited);
            if(max<depthLength) { // 번호가 커도 물어볼 수 있는 최대 길이가 같다면
                max=depthLength;
                answer=i; // 가장 작은 번호만 정답에 저장 되어있음
            }
//            System.out.println(i+": "+depthLength);
//            System.out.println("max: "+max);
            Arrays.fill(visited, false); // 시작점(i)이 바뀔 때마다 visited 초기화
        }
        System.out.println(answer);
    }
    private static void DFS(int[] adjList, int current, int depth, boolean[] visited) { // depth : 물어볼 수 있는 최대 길이
        if(visited[current]) {
            depthLength = Math.max(depthLength, depth);
            return;
        }
        visited[current] = true;
        DFS(adjList, adjList[current], depth+1, visited);
    }
}
====================================================================================================
// 매개변수에 무엇을 넘기는가에 따라서 약간의 조정만 있을뿐 DFS는 동일하다
// Arrays.fill(visited,false) 대신 visited[current]=false;가 코드 의미상 더 적절
  
import java.util.*;
import java.io.*;

public class Main {
    static int N, depthLength=-1, answer=1000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[N+1];
        int[] adjList = new int[N+1]; // 1부터 시작

        for(int p=1; p<=N; p++) {
            int to = Integer.parseInt(br.readLine());
            adjList[p] = to;
        }

        for(int i=N; i>=1; i--) { // 작은 번호를 출력하기 위해 adjList의 N부터 시작
            DFS(i, i, 0, adjList, visited);
        }
        System.out.println(answer);
    }
    private static void DFS(int index, int current, int depth, int[] adjList, boolean[] visited) { // depth : 물어볼 수 있는 최대 길이
        if(visited[current]) {
            depthLength = Math.max(depthLength, depth);
            if(depth==depthLength) { // 물어본 길이가 최대 길이라면
                answer=index; // 그 때의 index를 정답에 저장
            }
            return;
        }
        visited[current] = true;
        DFS(index, adjList[current], depth+1, adjList, visited);
        visited[current] = false; // visited 초기화(되돌리기)
    }
}
