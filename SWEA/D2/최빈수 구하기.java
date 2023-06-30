import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0) {
            int num = sc.nextInt();
            int[] arr = new int[101];
            for(int i=0; i<1000; i++) {
                arr[sc.nextInt()]++;
            }
            int max = Integer.MIN_VALUE; int answer=0;
            for(int i=0; i<arr.length; i++) {
                if(max<=arr[i]) { // <= : 최빈수가 여러 개 일 때에는 가장 큰 점수를 출력
                    max=arr[i];
                    answer=i;
                }
            }
            System.out.println("#"+num+" "+answer);

        }
    }
}
