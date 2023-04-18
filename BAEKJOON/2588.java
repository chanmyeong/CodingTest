import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int B3 = B/100;
        int B2 = (B-B3*100)/10;
        int B1 = (B-B3*100-B2*10);

        bw.write(A*B1+"\n");
        bw.write(A*B2+"\n");
        bw.write(A*B3+"\n");
        bw.write(A*B+"\n");

        bw.flush();
    }
}

====================================================================================================
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		int b = scan.nextInt();
		System.out.println(a*(b%10));
		System.out.println(a*((b/10)%10));
		System.out.println(a*(b/100));
		System.out.print(a*b);
	}
}
