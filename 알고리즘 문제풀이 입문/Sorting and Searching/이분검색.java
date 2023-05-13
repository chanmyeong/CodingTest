// Binary Search
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i]=sc.nextInt();

        Arrays.sort(arr); // 정렬된 상태에서 이분검색
        int answer=0;
        int lt=0, rt=n-1;
        for(int i=0; i<n; i++) { // while(lt<=rt)
            int mid=(lt+rt)/2;
            if(arr[mid]==m) {
              answer=mid+1;
              break;
            }
            else if(arr[mid]>m) rt = mid-1;
            else if(arr[mid]<m) lt = mid+1;
            
//            if(lt>rt) {
//                System.out.println("can't find target.");
//                break;
//            }
        }
        System.out.println(answer);
    }
}
