import java.io.*;
import java.util.*;
public class Main {
    static Integer[] arr;
    static int n,m,answer=Integer.MAX_VALUE;
    public void DFS(int L, int sum) {
        if(sum>m) return;
        if(L>=answer) return; // 더 깊이 탐색할 이유가 없어서 cut
        if(sum==m) {
            answer=Math.min(answer, L);
        }
        else {
            for(int i=0; i<n; i++) {
                DFS(L+1, sum+arr[i]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new Integer[n];
        for(int i=0; i<n; i++)
            arr[i] = sc.nextInt();
        // Collections.reverseOrder()를 사용하기 위해서 배열이 객체형이어야 한다. int->Integer
        Arrays.sort(arr, Collections.reverseOrder()); // 시간복잡도를 줄이기 위해 내림차순 정렬
        m = sc.nextInt();
        T.DFS(0, 0);
        System.out.println(answer);
    }
}
