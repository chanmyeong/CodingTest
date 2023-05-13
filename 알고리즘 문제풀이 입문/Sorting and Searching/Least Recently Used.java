import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int n  = sc.nextInt();
        int[] arr = new int[n];
        int[] cache = new int[s];
        for(int i=0; i<n; i++)
            arr[i]=sc.nextInt();

        for(int x : arr) {
            int pos=-1;
            for(int i=0; i<s; i++) if(x==cache[i]) pos=i;
            if(pos==-1) { // Cache Miss
                for(int i=s-1; i>=1; i--) {
                    cache[i]=cache[i-1];
                }
            }
            else { // Cache Hit
                for(int i=pos; i>=1; i--) {
                    cache[i]=cache[i-1];
                }
            }
            cache[0]=x;
        }

        for(int i=0; i<s; i++)
            System.out.print(cache[i]+" ");
    }
}
