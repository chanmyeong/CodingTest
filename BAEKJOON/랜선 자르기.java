import java.io.*;
import java.util.*;
/**
 * 자체적으로 K개의 랜선을 가지고 있다. 그러나 K개의 랜선은 길이가 제각각
 * 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때문에 K개의 랜선을 잘라서 만들어야 한다
 * 랜선을 자르거나 만들 때 손실되는 길이는 없다고 가정하며,
 * 기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정
 * 그리고 자를 때는 항상 센티미터 단위로 정수길이만큼 자른다고 가정하자.
 * N개보다 많이 만드는 것도 N개를 만드는 것에 포함
 * 이때 만들 수 있는 최대 랜선의 길이를 구하는 프로그램
 * K는 1이상 10,000이하의 정수이고, N은 1이상 1,000,000이하의 정수, 항상 K ≦ N
 * 랜선의 길이는 2^31-1보다 작거나 같은 자연수이다
 *
 * 문제 해결 전략 :
 * 길이가 N개보다 모자를 경우 만들 수 있는 최대 랜선의 길이보다 길므로 rt=mid-1
 * N개보다 많이 만드는 것도 N개를 만드는 것에 포함하므로
 * N개 혹은 그 이상의 개수일 경우 랜선의 길이는 최대 랜선의 길이보다는 짧으므로 lt=mid+1
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int K = Integer.parseInt(line[0]);
        int N = Integer.parseInt(line[1]);

        int[] arr = new int[K]; // 가지고 있는 K개의 랜선들

        for(int i=0; i<K; i++) {
            arr[i]= Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long lt,rt=0;
        rt=arr[K-1];
        lt=1; // 최소 랜선의 길이는 1

        while(lt<=rt) {
            long mid = (lt+rt)/2;

            long cnt=0;
            for(int i=0; i<arr.length; i++) {
                cnt += arr[i]/mid; // 각 랜선에서 얻을 수 있는 최대 길이의 랜선 개수
            }

            if(cnt<N) {
                rt = mid-1;
            }
            else {
                lt = mid+1;
            }
        }
        System.out.println(rt);
    }
}
