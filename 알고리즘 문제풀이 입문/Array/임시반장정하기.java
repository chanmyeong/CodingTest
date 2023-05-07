// 틀린 풀이
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n][5];
        int[] std = new int[n];
        int[] clas = new int[10];

        for(int i=0;i<n;i++)
            for(int j=0;j<5;j++)
                map[i][j] = sc.nextInt();

        for(int i=0; i<5; i++) {
            for(int j=0; j<n; j++) {
                clas[map[j][i]]++;
            }
            for(int k=1; k<10; k++) {
                if(clas[k]>1) { // <== 잘못된 조건 : 학년이 겹치는 조건식이 올바르지 않다.
                // 인원 수(n)가 많아져 clas[k]가 1보다 클 확률이 다반사이다.
                    for(int j=0; j<n; j++) {
                        // 해당 반 학생 카운트 증가
                        if(map[j][i]==k) std[j]++;
                    }
                }
                clas[k]=0;
            }
        }
        int max = std[0];
        for(int i=0; i<n; i++) {
            if(max<std[i]) {
                max = std[i];
            }
//            System.out.print(std[i]+" ");
        }
        for(int i=0; i<n; i++)
            if(max==std[i]) {
                System.out.println(i+1);
                break;
            }
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n + 1][6];
        int max = Integer.MIN_VALUE;
        int answer = 0;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= 5; j++)
                map[i][j] = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            int cnt = 0 ;
            for (int j = 1; j <= n; j++) {
                for(int k=1; k<=5; k++) {
                    if(map[i][k]==map[j][k]) { // 학년이 겹치는 올바른 조건식
                        cnt++;
                        break;
                    }
                }
            }
            if(cnt>max) {
                max=cnt;
                answer=i;
            }
        }
        System.out.println(answer);
    }
}
