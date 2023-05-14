// Parametric Search
// 주어진 범위 내에서 원하는 값 또는 원하는 조건에 가장 일치하는 값을 찾아내는 알고리즘
// 찾고자 하는 것이 lt~rt 사이에 있다고 확신할 때 사용 (이분검색 기반)

import java.io.*;
import java.util.*;

public class Main {
    public int count(int[] arr, int capacity) {
        int cnt=1; // DVD 장 수
        int sum=0; // DVD에 담아내는 곡들의 합 (저장 용량)
        for(int x : arr) {
            if(sum+x>capacity) {
                cnt++;
                sum=x; // 새로운 DVD로 초기화
            }
            else sum+=x;
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i]=sc.nextInt();
        
        int answer=0;
        int lt = Arrays.stream(arr).max().getAsInt();
        int rt = Arrays.stream(arr).sum();
        // 큰 데이터에서의 최대/최소, 합 등을 구해야 할 때 스트림(중간,최종연산) 사용 시 편리
        Main T = new Main(); // 인스턴스 메서드 사용을 위한 객체 선언
        while(lt<=rt) {
            int mid=(lt+rt)/2;
            if(T.count(arr, mid)<=m) { //T.count(a,b)
                answer=mid;
                rt=mid-1;
            }
            else lt=mid+1;
        }
        System.out.println(answer);
    }
}
