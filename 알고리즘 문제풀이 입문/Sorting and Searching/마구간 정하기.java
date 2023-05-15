import java.io.*;
import java.util.*;

public class Main {
    public int count(int[] arr, int dist) {
        int ep=arr[0]; int cnt=1;
        for(int i=1; i<arr.length; i++) {
            if (arr[i]-ep >= dist) { // 배치가 가능하다면
                ep=arr[i];
                cnt++; // 배치
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i]=sc.nextInt();

        Arrays.sort(arr);
        Main T = new Main();
        int answer = 0;
        int lt=1; int rt=arr[n-1];
        while (lt<=rt) {
            int mid=(lt+rt)/2;
            if(T.count(arr,mid)>=c) { // 배치 가능 수 >= 배치 해야 할 수
                answer = mid; // mid는 정답이 가능
                lt=mid+1;
            }
            else rt=mid-1;
        }
        System.out.println(answer);
    }
}
