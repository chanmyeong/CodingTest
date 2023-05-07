import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] score1 = new int[n];
        int[] score2 = new int[n];
        int[] rank = new int[n];
        int[] answer = new int[n];
        int num = n;
        for(int i=0; i<n; i++) {
            score1[i] = sc.nextInt();
            score2[i] = score1[i];
        }

        Arrays.sort(score1);
        for(int i=0; i<n; i++) {
            rank[i] = num--;
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(score2[i]==score1[j]) answer[i]=rank[j];
            }
        }
        for(int i=0; i<n; i++) {
            System.out.print(answer[i]+" ");
        }
    }
}

===================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] score = new int[n];
        int[] answer = new int[n];
        int rank = 1;
        for(int i=0; i<n; i++) {
            score[i] = sc.nextInt();
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(score[i]<score[j]) rank++;
            }
            answer[i] = rank;
            rank=1;
        }
        for(int i=0; i<n; i++) {
            System.out.print(answer[i]+" ");
        }
    }
}
