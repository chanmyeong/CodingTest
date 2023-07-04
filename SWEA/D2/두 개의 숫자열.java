import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int cnt=1;
        while(t-->0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[m];
            for(int i=0; i<n; i++)
                a[i]= sc.nextInt();
            for(int j=0; j<m; j++)
                b[j]= sc.nextInt();

            int lt=0;  int answer=0; int num=0;
            int rt = Math.min(n,m);
            int tt = Math.max(n,m);
            while(rt++<=tt) {
                int sum=0;
                //System.out.print("rt:"+rt+" ");
                for(int k=lt++; k<rt-1; k++) { // while문의 rt++로 인해서 for문 rt의 시작이 4부터 시작이므로 rt-1
                    if(Math.min(n,m)==n) {
                        sum+=a[num++]*b[k];
                        if(num==n) num=0;
                    }
                    else {
                        sum+=a[k]*b[num++];
                        if(num==m) num=0;
                    }
                }
                //System.out.print(sum+" ");
                answer=Math.max(answer,sum);
            }

            System.out.println("#"+cnt+" "+answer);
            cnt++;
        }
    }
}
====================================================================================================
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int cnt=1;
        while(t-->0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[m];
            for(int i=0; i<n; i++)
                a[i]= sc.nextInt();
            for(int j=0; j<m; j++)
                b[j]= sc.nextInt();

            int answer=0;
            if(n<m) {
                answer = getAnswer(n, m, a, b, answer);
            }
            else if(n>m) {
                answer = getAnswer(m, n, b, a, answer);
            }
            else {
                int result=0;
                for(int i=0; i<m; i++)
                    result+=a[i]*b[i];
                answer=Math.max(result,answer);
            }

            System.out.println("#"+cnt+" "+answer);
            cnt++;
        }
    }

    private static int getAnswer(int n, int m, int[] a, int[] b, int answer) {
        for(int i=0; i<=m-n; i++) {
            int result=0;
            for(int j=0; j<n; j++)
                result+=a[j]*b[j+i];
            answer=Math.max(result,answer);
        }
        return answer;
    }
}
