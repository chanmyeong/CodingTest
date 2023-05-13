// 버블 정렬은 기본적으로 배열의 두 수를 선택한 뒤, 만약 그 두 수가 정렬되었다면 놔두고 아니라면 두 수를 바꾸는 방식으로 진행된다.
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i]=sc.nextInt();

        for(int i=0; i<n-1; i++) {
            for(int j=0; j<n-i-1; j++) {
                if(arr[j]>arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        for(int i=0; i<n; i++)
            System.out.print(arr[i]+" ");
    }
}
====================================================================================================
// for문을 조금이라도 덜 도는 위의 방식이 더 괜찮다.
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i]=sc.nextInt();

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(arr[i]<arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        for(int i=0; i<n; i++)
            System.out.print(arr[i]+" ");
    }
}
