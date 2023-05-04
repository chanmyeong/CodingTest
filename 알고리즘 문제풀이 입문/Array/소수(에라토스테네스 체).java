import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] sieve = new int[n+1];
        int cnt = 0;
        for(int i=2; i<=n; i++) {
            if(sieve[i]==0) cnt++;
            for(int j=i; j<=n; j=j+i) sieve[j]=1;
        }
        System.out.println(cnt);
    }
}
