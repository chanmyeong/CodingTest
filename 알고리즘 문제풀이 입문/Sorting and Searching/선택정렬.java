// 1. 주어진 리스트 중에 최소값을 찾는다.
// 2. 그 값을 맨 앞에 위치한 값과 교체한다(패스(pass)).
// 3. 맨 처음 위치를 뺀 나머지 리스트를 같은 방법으로 교체한다.

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
            int indexMin = i;
            for(int j=i+1; j<n; j++) {
                if (arr[j] < arr[indexMin]) indexMin=j;
            }
            int tmp = arr[indexMin];
            arr[indexMin]=arr[i];
            arr[i]=tmp;
        }
        for(int i=0; i<n; i++)
            System.out.print(arr[i]+" ");
    }
}
