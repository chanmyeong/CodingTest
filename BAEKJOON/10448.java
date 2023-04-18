// O[K*triangleNumberCount + triangleNumberCount^2] => O[K*triangleNumberCount]
import java.io.*;
import java.util.*;

public class Main {

    static boolean[] isEurekaNumber = new boolean[1001];

    public static void preprocess() {
        // 1. K보다 작은 삼각수를 모두 구한다.
        int [] triangleNumbers = new int[50]; // to 44
        int triangleNumberCount = 0;
        for(int i=1; ; i++) {
            int triangleNumber = i*(i+1)/2;
            if(triangleNumber>=1000) break;
            triangleNumbers[triangleNumberCount++] = triangleNumber;
        }

        // 2. 구해진 삼각수 세 개의 합으로 K를 나타낼 수 있는지 확인한다.
        boolean[] isSumOfTriangleNumber = new boolean[1000];
        // 두개의 합을 먼저 구하고
        for(int i=0; i<triangleNumberCount; i++)
            for(int j=0; j<triangleNumberCount; j++) {
                int sum = triangleNumbers[i] + triangleNumbers[j];
                if(sum<1000) isSumOfTriangleNumber[sum] = true;
            }
        // 나머지 하나의 합까지 더한 후 확인한다.
        for(int i=1; i<1000; i++) {
            if(!isSumOfTriangleNumber[i]) continue;
            for(int j=0; j<triangleNumberCount; j++) {
                int sum = i + triangleNumbers[j];
                if(sum<=1000) isEurekaNumber[sum] = true;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        // [3,1000]
        // isEurekaNumber[]
        preprocess();
        
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0) {
            int K  = sc.nextInt();
            System.out.println(isEurekaNumber[K] ? "1" : "0");
        }
    }
}

====================================================================================================
//O[45^3]
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] gus = new int[45];
        for (int i = 1; i < 45; i++) {
            gus[i] = (i * (i + 1))/2;
        }
        boolean[] sum = new boolean[5000];
        for (int i = 1; i < 45; i++) {
            for (int j = 1; j < 45; j++) {
                for (int k = 1; k < 45; k++) {
                    sum[gus[i] + gus[j] + gus[k]] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int in = Integer.parseInt(br.readLine());
            if (sum[in]) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
        br.close();
    }
}

====================================================================================================
import java.util.Scanner;

public class Main {

    public static int sum(int a) { return a*(a+1)/2; }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while(t-- > 0) {
            int k = sc.nextInt();
            int check = 0;
            for(int i = 1; sum(i) < k; i++) {
                int s = sum(i);
                for(int j = 1; sum(j) < k; j++) {
                    s += sum(j);
                    for(int l = 1; sum(l) < k; l++) {
                        s += sum(l);
                        if(s == k) {
                            check = 1;
                            break; }
                        s -= sum(l);
                    }
                    s -= sum(j);
                }
            }
            System.out.println(check);
        }
    }
}
====================================================================================================
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int tri[] = new int[45];
		for(int i=0;i<45;i++) tri[i] = (i+1)*(i+2)/2;
		while(T-->0) System.out.println(euraka(Integer.parseInt(br.readLine()), tri));
	}

	public static int euraka(int N, int tri[]) {
		for(int i=0;i<45;i++)
			for(int j=0;j<45;j++)
				for(int k=0;k<45;k++) if(tri[i]+tri[j]+tri[k]==N) return 1;
		return 0;
	}
}
