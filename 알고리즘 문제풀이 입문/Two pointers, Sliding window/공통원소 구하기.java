import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i=0; i<n; i++)
            a[i] = sc.nextInt();

        int m = sc.nextInt();
        int[] b = new int[m];
        for(int i=0; i<m; i++)
            b[i] = sc.nextInt();

        // Two pointers 사용 시 반드시 정렬을 한 뒤에 사용
        Arrays.sort(a);
        Arrays.sort(b);

        ArrayList<Integer> answer = new ArrayList<>();
        int p1=0, p2=0;
        while(p1<n && p2<m) {
            if(a[p1]==b[p2]) {
                answer.add(a[p1++]);
                p2++;
            }
            else if(a[p1]<b[p2]) p1++;
            else p2++;
        }

        // ArrayList의 sort는 Arrays.이 아니라 Collections. or 해당ArrayList이름.
        Collections.sort(answer);
        for(int x : answer)
            System.out.print(x+" ");
    }
}
