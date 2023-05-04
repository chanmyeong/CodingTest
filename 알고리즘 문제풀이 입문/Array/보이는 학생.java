// O[n^2]
// 시간초과
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
        int cnt = 1;
        boolean pass = false;
        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(num[i]>num[j]) pass=true;
                else {
                    pass=false;
                    break;
                }
            }
            if(pass) cnt++;
        }
        System.out.println(cnt);
    }
}

====================================================================================================
// O[n]
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
        int cnt = 1;
        int max = num[0];
        
        for(int i=1; i<n; i++) {
            if(max<num[i]) {
                max=num[i];
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
