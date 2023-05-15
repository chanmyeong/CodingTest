import java.io.*;
import java.util.*;

public class Main {
    public void DFS(int n) {
        if(n==1) { // 호출스택이 DFS(1)까지
            System.out.print(n);
            return;
        }
        else {
            DFS(n/2);
            System.out.print(n%2);
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main T = new Main();
        T.DFS(n);
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public void DFS(int n) {
        if(n==0) return; // 호출스택이 DFS(0)까지
        else {
            DFS(n/2);
            System.out.print(n%2);
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main T = new Main();
        T.DFS(n);
    }
}
