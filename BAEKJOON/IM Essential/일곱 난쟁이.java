//O[C(9,7)]
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] h = new int[9];
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int[] ans = new int[7];
        for (int i1 = 0; i1 < 9; i1++) {
            for (int i2 = i1 + 1; i2 < 9; i2++) {
                for (int i3 = i2 + 1; i3 < 9; i3++) {
                    for (int i4 = i3 + 1; i4 < 9; i4++) {
                        for (int i5 = i4 + 1; i5 < 9; i5++) {
                            for (int i6 = i5 + 1; i6 < 9; i6++) {
                                for (int i7 = i6 + 1; i7 < 9; i7++) {
                                    sum = h[i1] + h[i2] + h[i3] + h[i4] + h[i5] + h[i6] + h[i7];
                                    if (sum == 100) {
                                        ans[0] = h[i1];
                                        ans[1] = h[i2];
                                        ans[2] = h[i3];
                                        ans[3] = h[i4];
                                        ans[4] = h[i5];
                                        ans[5] = h[i6];
                                        ans[6] = h[i7];
                                        break;
                                    }
                                }
                                if (sum == 100) break;
                            }
                            if (sum == 100) break;
                        }
                        if (sum == 100) break;
                    }
                    if (sum == 100) break;
                }
                if (sum == 100) break;
            }
            if (sum == 100) break;
        }

        int tmp = 0;
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++) {
                if (ans[i] < ans[j]) {
                    tmp = ans[i];
                    ans[i] = ans[j];
                    ans[j] = tmp;
                }
            }

        for (int i = 0; i < 7; i++)
            bw.write(ans[i]+"\n");
        bw.flush();
    }
}

====================================================================================================
//O[N^2]
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] sorted = new int[9];
        for(int i=0; i<9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sorted[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        boolean found = false;
        int[] ans = new int[7];
        for(int i=0; i<9; i++) {
            sum += sorted[i];
        }
        for(int i=0; i<9; i++) {
            for(int j=i+1; j<9; j++) {
                if(sum-sorted[i]-sorted[j] == 100) {
                    int ansIndex = 0;
                    for(int k=0; k<9; k++)
                        if(k!=i && k!=j) { // 난쟁이 2명을 제외하고 ans[]에 저장
                            ans[ansIndex++] = sorted[k];
                        }
                    found = true;
                    break;
                }
            }
            if(found) break;
        }

        int tmp = 0;
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++) {
                if (ans[i] < ans[j]) {
                    tmp = ans[i];
                    ans[i] = ans[j];
                    ans[j] = tmp;
                }
            }

        for(int i=0; i<7; i++)
            bw.write(ans[i]+"\n");
        bw.flush();
    }
}

====================================================================================================
//O[N^2]
import java.util.Arrays;
import java.util.Scanner;

class Main
{
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    int cur = arr[i];
                    for (int k = i; k > j; k--)
                        arr[k] = arr[k - 1];
                    arr[j] = cur;
                    break;
                }
            }
        }
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] h = new int[9];
        for (int i = 0; i < 9; i++)
            h[i] = sc.nextInt();

        // Arrays.sort(h);
        sort(h);

        int sum = Arrays.stream(h).sum();
        boolean find = false;
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++)
                if (sum - h[i] - h[j] == 100) {
                    h[i] = h[j] = -1;
                    find = true;
                    break;
                }
            if (find) break;
        }

        for (int i = 0; i < 9; i++)
            if (h[i] > 0)
                System.out.println(h[i]);
    }
}

====================================================================================================
//O[N^2]
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] a = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            a[i] = Integer.parseInt(br.readLine());
            sum += a[i];
        }
        for(int i=0; i<9; i++){
            for(int j=i+1; j<9; j++){
                if(sum-a[i]-a[j]==100){
                    a[i]=100;
                    a[j]=100;
                    Arrays.sort(a);
                    for(int k=0; k<7; k++){
                        System.out.println(a[k]);
                    }
                    return;
                }
            }
        }
    }
}
