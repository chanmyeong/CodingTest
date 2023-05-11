// HashSet 사용
// 시간초과 O[k^(n-k+1)]
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int tmp = n-k+1;
        int[] kind = new int[tmp];
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i]= sc.nextInt();

        HashSet<Integer> set = new HashSet<>();

        int p=0, q=0;
        while(tmp-- > 0) {
            for(int i=p; i<p+k; i++) {
                set.add(arr[i]);
            }
            kind[q]= set.size();
            p++; q++;
            set.clear();
        }
        for(int i=0; i<n-k+1; i++)
            System.out.print(kind[i]+" ");
    }
}

====================================================================================================
// HashMap 사용
// O[n]
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] kind = new int[n-k+1];
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i]= sc.nextInt();

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<k-1; i++)
            map.put(arr[i], map.getOrDefault(arr[i],0)+1);

        int lt=0; int cnt=0;
        for(int rt=k-1; rt<n; rt++) {
            map.put(arr[rt], map.getOrDefault(arr[rt],0)+1);
            kind[cnt]= map.size(); cnt++;
            map.put(arr[lt], map.getOrDefault(arr[lt],0)-1);
            if(map.get(arr[lt])==0) map.remove(arr[lt]); // key 값이 0이면 해당 key 삭제
            lt++;
        }

        for(int i=0; i<n-k+1; i++)
            System.out.print(kind[i]+" ");
    }
}
