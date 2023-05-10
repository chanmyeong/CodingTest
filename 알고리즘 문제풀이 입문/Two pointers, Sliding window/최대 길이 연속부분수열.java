import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int answer=0;
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i]=sc.nextInt();

        int cnt=0, lt=0;
        for(int rt=0; rt<n; rt++) {
            if(arr[rt]==0) cnt++;
            while(cnt>k) { // if(cnt==k)에 너무 얽매여 있었다.
                if(arr[lt]==0) cnt--;
                lt++;
            }
            answer=Math.max(answer,rt-lt+1); // 답을 계속 갱신해야 할 때를 생각하자
        }
        System.out.println(answer);
    }
}
