import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String positions[] = br.readLine().split(" ");
        int S = Integer.parseInt(positions[0]);
        int E = Integer.parseInt(positions[1]);

        Queue<Integer> queue = new LinkedList<>();

        int levels[] = new int[10001];
        boolean[] visited = new boolean[10001];

        queue.offer(S);

        levels[S] = 0;
        visited[S] = true;
        int[] dis = {1, -1, 5};

        while(!queue.isEmpty()) { 
            int pos = queue.poll(); // queue에서 좌표를 하나 꺼낸다

            if(pos == E) { // 송아지의 위치와 같은 좌표가 나올 때까지 반복
                System.out.println(levels[pos]);
                break;
            }
            else {
                for(int i = 0; i < dis.length; i++) {
                    int newPos = pos + dis[i]; // queue에서 꺼낸 좌표로부터 이동할 수 있는 방법들을 거쳐 나온 새로운 좌표
                    if(newPos >=1 && newPos <= 10000 && !visited[newPos]) { // 새로운 좌표가 좌표 위의 점이면서 지났던 점이 아니라면
                        queue.add(newPos); // queue에 넣는다
                        levels[newPos] = (levels[pos] + 1);
                        visited[newPos] = true;
                    }
                }
            }
        }

    }
}

====================================================================================================
// 나의 풀이 (이동방법이 +1, -1, 5 라는 점에 주목)
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int e = sc.nextInt();
        int cnt1=0, cnt5=0, cntn1=0, cnt4=0;
        int n=e-s;
        while(n!=0) {
            if (n >= 5) {
                while (n >= 5) {
                    n -= 5;
                    cnt5++;
                }
            }
            else if (n < 4 && 0 < n) {
                while (n > 0) {
                    n -= 1;
                    cnt1++;
                }
            }
            else if (n==4) { // +4일 때 이동횟수가 4가 아닌 2로 단축이 가능
                cnt4+=2;
                n=0;
            }
            else if (n < 0) {
                cntn1=Math.abs(n);
                n=0;
            }
        }
        int sum = cnt5+cnt4+cnt1+cntn1;
        System.out.println(sum);
    }
}

====================================================================================================
// BFS: 상태트리탐색
// DFS: 어떤 경우의 수   BFS: 최단거리, 또는 최소횟수
import java.io.*;
import java.util.*;

public class Main {
    int answer = 0;
    int dis[] = {1, -1 , 5};
    int[] ch;
    Queue<Integer> Q = new LinkedList<>();
    public int BFS(int s, int e) {
        ch=new int[10001];
        ch[s]=1;
        Q.offer(s);
        int L=0;
        while(!Q.isEmpty()) {
            int len = Q.size();
            for(int i=0; i<len; i++) {
                int x = Q.poll();
                for(int j=0; j<3; j++) {
                    int nx=x+dis[j];
                    if(nx==e) return L+1; // queue에 넣기 전에 찾았다면 tree level+1 값을 반환
                    if(1<=nx && nx<=10000 && ch[nx]==0) {
                        ch[nx]=1;
                        Q.offer(nx);
                    }
                }
            }
            L++;
        }
        return 0;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int e = sc.nextInt();
        Main T = new Main();
        System.out.println(T.BFS(s, e));
    }
}
