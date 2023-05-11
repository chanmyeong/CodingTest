// TreeSet 사용
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i]=sc.nextInt();
        int answer = -1;

        // 중복제거(Set) + 내림차순 정렬(Collections.reverseOrder())
        TreeSet<Integer> Tset = new TreeSet<>(Collections.reverseOrder());
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                for(int l=j+1; l<n; l++) {
                    Tset.add(arr[i]+arr[j]+arr[l]);
                }
            }
        }
        int cnt=0;
      
        // Tset.remove(143); // TreeSet 원소 제거
        // System.out.println(Tset.size()); // 원소 개수
        // System.out.println(Tset.first()); // 정렬한 곳의 제일 앞 원소
        // System.out.println(Tset.pollFirst()); // 정렬한 곳의 제일 앞 원소 반환 후 해당 원소 제거
        // System.out.println(Tset.last()); // 정렬한 곳의 제일 마지막 원소
      
        for(int x : Tset) {
            cnt++;
            if(cnt==k) answer=x;
        }
        System.out.println(answer);
    }
}
