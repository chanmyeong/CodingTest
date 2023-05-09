// Two pointers 알기 전
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] total1 = new int[n];

        for(int i=0; i<n; i++)
            total1[i] = sc.nextInt();

        int m = sc.nextInt();
        int[] total2 = new int[n+m];
        for(int i=0; i<n; i++)
            total2[i] = total1[i];

        for(int i=n; i<n+m; i++)
            total2[i] = sc.nextInt();

        Arrays.sort(total2);

        for(int i=0; i<n+m; i++)
            System.out.print(total2[i]+" ");

    }
}

====================================================================================================
// Two pointers 안 후
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i=0; i<n; i++)
            a[i] = sc.nextInt();

        int m = sc.nextInt();
        int[] b = new int[m];
        for(int i=0; i<m; i++)
            b[i] = sc.nextInt();

        ArrayList<Integer> answer = new ArrayList<>();
        int p1=0, p2=0;
        while(p1<n && p2<m) {
            if(a[p1]<b[p2]) answer.add(a[p1++]);
            else answer.add(b[p2++]);
        }
        while(p1<n) answer.add(a[p1++]);
        while(p2<m) answer.add(b[p2++]);

        for(int x : answer)
            System.out.print(x+" ");
    }
}
