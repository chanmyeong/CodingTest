// O[N^2]
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int P = sc.nextInt();
        int countStepBack = 0;

        while(P-- > 0) {
            int T = sc.nextInt();
            int[] h = new int[20];
            for(int i=0; i<20; i++)
                h[i] = sc.nextInt();

            for(int i=0; i<20; i++) {
                for(int j=0; j<i; j++) {
                    // h[i]보다 앞에 있는 h[i]보다 큰 수의 개수
                    if(h[j]>h[i]){
                        countStepBack++;
                    }
                }
            }
            System.out.println(T+" "+countStepBack);
            countStepBack = 0;
        }

    }
}

====================================================================================================
// O[P*N^2]
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int P = sc.nextInt();

        while(P-- > 0) {
            int T = sc.nextInt();
            int[] h = new int[20];
            for(int i=0; i<20; i++)
                h[i] = sc.nextInt();

            int cnt=0;
            int[] sorted = new int[20];
            for(int i=0; i<20; i++) {
                // 1. 줄 서있는 학생 중에 자신보다 큰 학생을 찾는다.
                // 1-1. 찾지 못했다면, 줄의 가장 뒤에 선다.
                boolean find = false;
                for(int j=0; j<i; j++)
                    if(sorted[j]>h[i]){
                        // 2. 찾았다면, 그 학생의 앞에 선다.
                        // 3. 그 학생과 그 뒤의 학생이 모두 한칸씩 물러난다.
                        find = true;
                        for(int k = i-1; k>=j; k--) {
                            sorted[k+1]= sorted[k];
                            cnt++;
                        }
                        sorted[j]=h[i];
                        break;
                    }
                if(!find) sorted[i]=h[i];
            }
            System.out.println(T+" "+cnt);
        }
    }
}
