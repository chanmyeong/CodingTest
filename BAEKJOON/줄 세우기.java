import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] line = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            int j = Integer.parseInt(st.nextToken());
            for(int k=0; k<j; k++) {
                line[i-k] = line[i-k-1];
            }
            line[i-j]=i;
        }

        for(int i : line) {
            if (i == 0) continue;
            System.out.print(i+" ");
        }
    }
}
====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        LinkedList<Integer> line = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        line.add(a, 1);
        for(int i=1;i<n;i++) {
            a = Integer.parseInt(st.nextToken());
            line.add(i-a, i+1);
        }
        for(int i=0;i<n;i++) {
            System.out.print(line.get(i)+" ");
        }
    }
}
