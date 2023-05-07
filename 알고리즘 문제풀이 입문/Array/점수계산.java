import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] score = new int[n];
        int cnt = 0, sum=0;
        for(int i=0; i<n; i++)
            score[i] = sc.nextInt();

        for(int i=0; i<n; i++) {
            if(score[i]==1) {
                cnt++;
                sum += cnt;
            }
            else cnt=0;
        }
        
        System.out.println(sum);
    }
}
