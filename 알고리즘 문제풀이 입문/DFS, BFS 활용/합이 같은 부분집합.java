import java.io.*;
import java.util.*;
public class Main {
    static String answer="NO";
    static int n, total=0;
    boolean flag =false;
    public void DFS(int L, int sum, int[] arr) {
        if(flag) return; // 스택프레임 break 시키는 효과
        if(sum>total/2) return; 
        if(L==n) {
            if((total-sum)==sum) { // 서로소인 두 부분집합이므로 전체 합에서 sum을 뺀 값이 sum이면 합이 같은 부분집합이 존재
                answer="YES";
                flag=true;
            }
        }
        else {
            DFS(L+1, sum+arr[L], arr);
            DFS(L+1, sum, arr);
        }
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
            total += arr[i];
        }
        T.DFS(0, 0, arr);
        System.out.println(answer);
    }
}
