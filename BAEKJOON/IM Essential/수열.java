import java.io.*;
import java.util.*;

/**
 * 0~9까지의 숫자로 이루어진 N개의 숫자가 나열된 수열
 * 해당 수열에서 (같은 것 포함) 연속해서 커지거나 혹은 연속해서 작아지는 수열 중
 * 길이가 가장 긴 것을 찾아 해당 길이를 출력
 *
 * 문제 해결 전략 :
 * 투 포인터로 크기 비교하면서 upCnt와 downCnt 세기
 * 두 Cnt 중 큰 수 출력
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int upCnt=2, downCnt=2;
        int upSave=2, downSave=2; // n=3에서 동작 시작, 최소 2개
        int lt=0, rt=1; 
        while(lt<n-2 && rt<n-1) { // n=1, n=2에서 동작불가
            // int rt = lt+1;로 조건문에서 조건을 줄일 수 있음(조건이 길어질수록 복잡도 증가하기 때문)
            if(seq[lt]<=seq[rt]) {
                upCnt++;
                if(seq[lt+1]<=seq[rt+1]) {
                    upSave = Math.max(upSave, upCnt);
                    // upSave = upCnt; [X]
                    // 예외 TestCase : 10, 1 2 3 4 5 5 3 2 3 4 최댓값을 갱신하지 않고 바로 담아주었기 때문에 생긴 문제
                }
                else if(seq[lt+1]>seq[rt+1]) {
                    upCnt=2;
                }
            }
            if(seq[lt]>=seq[rt]) {
                downCnt++;
                if(seq[lt+1]>=seq[rt+1]) {
                    downSave = Math.max(downSave, downCnt);
                    // downSave = downCnt; [X]
                }
                else if(seq[lt+1]<seq[rt+1]) {
                    downCnt=2;
                }
            }
            lt++; rt++;
        }
        if(n==1) System.out.println(1);
        else if(n==2) System.out.println(2);
        else System.out.println(Math.max(upSave,downSave));
//        System.out.println(Arrays.toString(seq));
    }
}
