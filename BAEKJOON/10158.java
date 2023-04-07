// 시간 초과 O(t)
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        int p = sc.nextInt();
        int q = sc.nextInt();
        int t = sc.nextInt();
        int deltaX = 1;
        int deltaY = 1;


        // t++ -> p+1,q+1
        // boundary -> toggle

        for (int i = 0; i < t; i++) {
            //오른쪽면
            if (p == w) deltaX = -1;
            //왼쪽면
            else if (p == 0) deltaX = 1;
            //윗면
            if (q == h) deltaY = -1;
            //아랫면
            else if (q == 0) deltaY = 1;
            p += deltaX;
            q += deltaY;
        }
        System.out.println(p+" "+q);
    }
}

====================================================================================================

// Java8 OK & Java11 시간 초과 O(max(W,H))
import java.util.*;

public class Main {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int W = sc.nextInt();
        int H = sc.nextInt();
        int P = sc.nextInt();
        int Q = sc.nextInt();
        int T = sc.nextInt();

        int timeX = T % (2 * W);
        int currentX = P;
        int deltaX = 1;
        while(timeX-- > 0) {
            if(currentX == W) deltaX = -1;
            else if (currentX == 0) deltaX = 1;
            currentX += deltaX;
        }

        int timeY = T % (2 * H);
        int currentY = Q;
        int deltaY = 1;
        while(timeY-- > 0) {
            if(currentY == H) deltaY = -1;
            else if (currentY == 0) deltaY = 1;
            currentY += deltaY;
        }

        System.out.println(currentX+" "+currentY);
    }
}

====================================================================================================

// O(1)  
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int W = sc.nextInt();
        int H = sc.nextInt();
        int P = sc.nextInt();
        int Q = sc.nextInt();
        int T = sc.nextInt();

        int currentX = (T+P) % (2*W);
        int currentY = (T+Q) % (2*H);
        if (currentX > W) currentX = 2*W-currentX;
        if (currentY > H) currentY = 2*H-currentY;

        System.out.println(currentX+" "+currentY);
    }
}

  
