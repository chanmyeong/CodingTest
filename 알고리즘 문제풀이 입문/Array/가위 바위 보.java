import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for(int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        for(int i=0; i<n; i++) {
            b[i] = sc.nextInt();
        }
        for(int i=0; i<n; i++) {
            if(a[i]==b[i]) System.out.println("D");
            else if(a[i]==1 && b[i]==2) System.out.println("B");
            else if(a[i]==2 && b[i]==3) System.out.println("B");
            else if(a[i]==3 && b[i]==1) System.out.println("B");
            else System.out.println("A");
        }
    }
}
