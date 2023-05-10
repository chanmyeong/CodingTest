import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        int sum=0; int answer=0;
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        int p=0, q=0;
        while(p<n && q<n) {
            sum+=arr[p++];
            if(sum==m) {
                answer++;
                q++;
                p=q;
                sum=0;
            }
            else if(sum>m) {
                q++;
                p=q;
                sum=0;
            }
        }
        System.out.println(answer);
    }
}
====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        int sum=0; int answer=0;
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        int lt=0;
        for(int rt=0; rt<n; rt++) {
            sum+=arr[rt];
            if(sum==m) answer++;
            while(sum>=m) {
                sum-=arr[lt++];
                if(sum==m) answer++;
            }
        }
        System.out.println(answer);
    }
}  
