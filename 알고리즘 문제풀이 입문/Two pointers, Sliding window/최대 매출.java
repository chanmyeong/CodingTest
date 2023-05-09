// 시간 초과
// O[n*k]
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        int day=0; day=k;
        int sum = 0; int max = 0;
        for(int i=0; i<n; i++) {
            for(int j=i; j<i+day; j++) {
                if(i>=n-k+1) break;
                sum += arr[j];
            }
            if(max<sum) {
                max = sum;
            }
            sum=0;
        }
        System.out.println(max);
    }
}

====================================================================================================
// Sliding window
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        int sum = 0; int answer = 0;
        for(int i=0; i<k; i++)
            sum += arr[i];
        answer=sum;
        for(int i=k; i<n; i++) {
            sum = sum+arr[i]-arr[i-k];
            answer=Math.max(answer,sum);
        }
        System.out.println(answer);
    }
}
