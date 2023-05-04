import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] num = new int[n+1];
        for(int i=0; i<n; i++) {
            num[i] = sc.nextInt();
        }
        System.out.print(num[0]+" ");
        for(int i=0; i<n; i++) {
            if(num[i]<num[i+1]) System.out.print(num[i+1]+" ");
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
        int[] num = new int[n];
        for(int i=0; i<n; i++) {
            num[i] = sc.nextInt();
        }

        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(num[0]);
        for(int i=1; i<n; i++) {
            if(num[i]>num[i-1]) answer.add(num[i]);
        }

        for(int x : answer)
            System.out.print(x+" ");
    }
}
