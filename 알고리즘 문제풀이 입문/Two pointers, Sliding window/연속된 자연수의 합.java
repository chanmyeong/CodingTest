// Two pointers
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 0;
        int end = n/2+1;
        int[] arr = new int[end];

        for(int i=0; i<end; i++)
            arr[i]=i+1;

        int lt=0, sum=0;
        for(int rt=0; rt<end; rt++) {
            sum+=arr[rt];
            if(sum==n) answer++;
            while(sum>=n) {
                sum-=arr[lt++];
                if(sum==n) answer++;
            }
        }
        System.out.println(answer);
    }
}

====================================================================================================
// 수학적 풀이
// [참고문헌]: https://bubblebubble.tistory.com/22
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 0;
        int standard=1;
        int num=n;

        for(int i=2; num-standard>0; i++) {
            standard+=i;
            if((num-standard)%i==0) answer++;
        }
        System.out.println(answer);
    }
}
