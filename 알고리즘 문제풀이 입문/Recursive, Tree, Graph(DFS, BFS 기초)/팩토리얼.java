import java.io.*;
import java.util.*;

public class Main {
    public int DFS(int n) {
        if(n==1) return 1;
        else return n*DFS(n-1);
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main T = new Main();
        System.out.println(T.DFS(n));
    }
}
