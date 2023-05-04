import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] pibo = new int[n];
        pibo[0] = 1;
        int sum = 1;
        for(int i=1; i<n; i++) {
            pibo[i] = sum;
            sum += pibo[i-1];
        }

        for(int i=0; i<n; i++) {
            System.out.print(pibo[i]+" ");
        }
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] pibo = new int[n];
        pibo[0] = 1;
        pibo[1] = 1;
        for(int i=2; i<n; i++) {
            pibo[i] = pibo[i-1]+pibo[i-2];
        }

        for(int i=0; i<n; i++) {
            System.out.print(pibo[i]+" ");
        }
    }
}

====================================================================================================
// 배열 사용X
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a=1,b=1,c;
        System.out.print(a+" "+b+" ");
        for(int i=2;i<n;i++) {
            c=a+b;
            System.out.print(c+" ");
            a=b;
            b=c;
        }
    }
}
