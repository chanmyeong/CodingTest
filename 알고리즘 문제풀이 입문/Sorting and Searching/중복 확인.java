// HashSet.size() 사용
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++) {
            int tmp = sc.nextInt();
            arr[i] = tmp;
            set.add(tmp);
        }

        boolean answer = true;
        if(set.size()!=n) answer=false;

        if(answer) System.out.println("U");
        else System.out.println("D");
    }
}

====================================================================================================
// HashMap의 key값으로 count시 count가 2 이상이면 바로 중복
====================================================================================================
// 정렬 사용
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        boolean answer = true;
        Arrays.sort(arr); // <-> Arrays.sort(arr, Collections.reverseOrder());
        for(int i=0; i<n-1; i++) {
            if(arr[i]==arr[i+1]) answer=false;
        }

        if(answer) System.out.println("U");
        else System.out.println("D");
    }
}
  
