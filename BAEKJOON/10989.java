// O[max(N, 10000)]
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        int[] cnt = new int[10001];
        for(int i=0; i<num; i++)
            cnt[Integer.parseInt(br.readLine())]++ ;

        for(int i=1; i<=10000; i++)
            while(cnt[i]-- > 0) {
            // BufferedWriter는 int형을 인자로 받아서 정수형으로 출력할 수 없다. String형으로 변환해서 넘겨줘야 한다.
                bw.write(i+"\n");
            }

        bw.flush();
        bw.close();
    }
}
