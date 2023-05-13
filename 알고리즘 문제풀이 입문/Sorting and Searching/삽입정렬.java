// 자료 배열의 모든 요소를 앞에서부터 차례대로 이미 정렬된 배열 부분과 비교하여, 자신의 위치를 찾아 삽입함으로써 정렬을 완성하는 알고리즘이다.
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i]=sc.nextInt();

        for(int i=1; i<n; i++) {
            int tmp = arr[i], j;
            for(j=i-1; j>=0; j--) {
                if(arr[j]>tmp) arr[j+1] = arr[j];
                else break;
            }
            arr[j+1]=tmp; // j for문이 멈추는 바로 뒤의 지점에 알맞은 원소 삽입
        }

        for(int i=0; i<n; i++)
            System.out.print(arr[i]+" ");
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i]=sc.nextInt();

        for(int i=1; i<n; i++) {
            int tmp = arr[i];
            int j = i-1;
            while(j>=0 && arr[j]>tmp) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1]=tmp; // 반복문이 멈추는 바로 뒤의 지점에 알맞은 원소 삽입
        }

        for(int i=0; i<n; i++)
            System.out.print(arr[i]+" ");
    }
}
