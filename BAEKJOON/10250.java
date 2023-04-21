// O[N]
import java.io.*;
import java.util.*;

public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int T = Integer.parseInt(br.readLine());

            while(T-- > 0){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int H = Integer.parseInt(st.nextToken());
                int W = Integer.parseInt(st.nextToken());
                int N = Integer.parseInt(st.nextToken());

                int floor = N;
                int walk = 0;
                while(floor>0) {
                    floor -= H;
                    walk++;
                    if(floor<0) break;
                }
                floor += H;
                if(walk>9) bw.write(floor+""+walk+"\n");
                else bw.write(floor+"0"+walk+"\n");
                bw.flush();
            }
    }
}

====================================================================================================
// O[N]
import java.io.*;
import java.util.*;

public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int T = Integer.parseInt(br.readLine());

            while(T-- > 0){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int H = Integer.parseInt(st.nextToken());
                int W = Integer.parseInt(st.nextToken());
                int N = Integer.parseInt(st.nextToken());

                int floor = 1;
                int distance = 1;
                while(--N>0) {
                    floor++;
                    if(floor>H) {
                        floor=1;
                        distance++;
                    }
                }
                if(distance>9) bw.write(floor+""+distance+"\n");
                else bw.write(floor+"0"+distance+"\n");
                bw.flush();
            }
    }
}

====================================================================================================
// O[1]
import java.io.*;
import java.util.*;

public class Main {
        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);

            int T = sc.nextInt();

            while(T-- > 0){
                int H = sc.nextInt();
                int W = sc.nextInt();
                int N = sc.nextInt();

                int distance = (N-1) / H + 1;
                int floor = (N-1) % H + 1;
                System.out.printf("%d%02d\n", floor, distance);
            }
    }
}  
  
====================================================================================================  
// O[1]
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            int n = sc.nextInt();

            if (n % h == 0) {
                System.out.println((h * 100) + (n / h));

            } else {
                System.out.println(((n % h) * 100) + ((n / h) + 1));

            }
        }
        sc.close();
    }
}
