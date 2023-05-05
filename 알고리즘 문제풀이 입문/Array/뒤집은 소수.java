import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] num = new String[n];

        for(int i=0; i<n; i++) {
            num[i] = sc.next();
            String tmp = new StringBuilder(num[i]).reverse().toString();
            int h = Integer.parseInt(tmp);
            boolean yes = false;

            int[] sieve = new int[h+1];
            for(int j=2; j<=h; j++) {
                if(sieve[j]==0) yes=true;
                else yes=false;
                for(int k=j; k<=h; k=k+j) sieve[k]=1;
            }
            if(yes) System.out.print(h+" ");
        }
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static boolean isPrime(int num) {
        if(num==1) return false;
        for(int i=2; i<num; i++) {
            if(num%i==0) return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++)
            arr[i] = sc.nextInt();

        ArrayList<Integer> answer = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int tmp = arr[i];
            int res = 0;
            while(tmp>0) {
                int t = tmp%10;
                res = res*10+t;
                tmp = tmp/10;
            }
            if(isPrime(res)) answer.add(res);
        }
        for(int x : answer)
            System.out.print(x+" ");
    }
}
