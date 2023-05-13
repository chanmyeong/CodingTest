import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        int p=0, q=0;
        for(int i=0; i<n-1; i++) {
            if(arr[i]>arr[i+1]) {
                p=i;
                break;
            }
        }
        for(int i=p+1; i<n-1; i++) {
            if(arr[i]>arr[i+1]) {
                q=i+1;
                break;
            }
        }
        if(p>q) {
            q=p+1;
            for(int i=p; i>=0; i--) {
                if(arr[i]<arr[p]) {
                    p=i+1;
                    break;
                }
            }
        }
        System.out.println((p+1)+" "+(q+1));
    }
}
====================================================================================================
// 정렬을 이용하면 단순해지는 문제
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] sort = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
            sort[i]=arr[i]; // int[] sort = arr.clone();
        }
        Arrays.sort(sort);

        for(int i=0; i<n; i++) {
            if(arr[i]!=sort[i]) {
                System.out.print((i+1)+" ");
            }
        }
    }
}
