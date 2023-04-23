// O[max(N,K)]
// 입력된 순서로 값 찾기
import java.io.*;
import java.util.*;

public class Main {
        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            int K = sc.nextInt();

            char[] wheel = new char[N];
            Arrays.fill(wheel, '?');
            int curIndex = 0;
            boolean[] isExist = new boolean[26];

            while(K-- > 0) {
                int step = sc.nextInt();
                char nextAlphabet = sc.next().charAt(0);
                // Java의 음수 모듈러는 음의 나머지를 반환함으로 N을 한번 더 더한다.
                int nextIndex = ((curIndex-step) % N + N) % N;
//                int nextIndex = curIndex;
//                for(int i=0; i<step; i++) {
//                    nextIndex -= 1; // 화살표 고정, 시계방향 이동
//                    if(nextIndex<0) nextIndex = N-1; // 환형의 상태
//                }
                if(wheel[nextIndex] == '?') {
                    if(isExist[nextAlphabet-'A']) {
                        System.out.println("!");
                        return ;
                    }
                    wheel[nextIndex] = nextAlphabet;
                    isExist[nextAlphabet-'A']=true;
                }
                else if(wheel[nextIndex] != nextAlphabet) {
                    System.out.println("!");
                    return ;
                }

                curIndex = nextIndex;
            }

                for(int i=0; i<N; i++)
                    System.out.print(wheel[(curIndex+i)%N]);
                System.out.println();

    }
}

====================================================================================================  
// 마지막 입력부터 값 찾기
import java.io.*;
import java.util.*;

public class Main {
        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            int K = sc.nextInt();

            char[] wheel = new char[N];
            Arrays.fill(wheel, '?');
            int curIndex = 0;
            boolean[] isExist = new boolean[26];
            int[] inputStep = new int[K];
            char[] inputAlphabet = new char[K];
            for(int i=0; i<K; i++) {
                inputStep[i] = sc.nextInt();
                inputAlphabet[i] = sc.next().charAt(0);
            }

            wheel[curIndex] = inputAlphabet[K-1];
            for(int i=K-2; i>=0; i--) {
                int step = inputStep[i+1];
                char beforeAlphabet = inputAlphabet[i];
                int beforeIndex = (curIndex+step) % N;
                if(wheel[beforeIndex] == '?') {
                    if(isExist[beforeAlphabet-'A']) {
                        System.out.println("!");
                        return ;
                    }
                    wheel[beforeIndex] = beforeAlphabet;
                    isExist[beforeAlphabet-'A']=true;
                }
                else if(wheel[beforeIndex] != beforeAlphabet) {
                    System.out.println("!");
                    return ;
                }

                curIndex = beforeIndex;
            }

                for(int i=0; i<N; i++)
                    System.out.print(wheel[i]);
                System.out.println();

    }
}
  
  
