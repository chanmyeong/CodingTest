import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
/**
 * 
 * NxN 
 * 
 * 밥을 빨리 먹기 위해 아래층으로 내려가야 함
 * 
 * P : 사람
 * S : 계단 입구
 * 
 * 이동완료시간 : 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간
 * 
 * 1) 계단 입구까지 이동시간
 *  => 사람의 위치와 계단 입구의 열차이+행차이
 * 2) 계단을 내려가는 시간
 *   => 계단 입구 도착 후 1분 후에 1분 마다 아래칸으로 1칸
 *      => 계단에는 동시에 3명까자만 올라갈 수 있음
 *      => 계단마다 길이 K가 주어짐
 *      => 계단 입구 도착 후 K+1분 후 완전히 내려감
 *  => 같은 높이의 계단에 위치하는 것도 가능
 * 
 * => 모든 사람이 계단을 내려가 이동이 완료되는 최소시간을 출력하는 프로그램
 * 
 * 1: 사람
 * 2~10 : 계단
 * 
 * N 은 최대 10
 * 사람의 수 최대 1~10
 * 계단 입구는 2개
 * 
 * 1. 각 사람이 도착할 계단 정하기
 * 2. 사람의 위치와 계단과의 총 거리 구하기
 *  => 사람이 해당 계단까지 걸리는 시간과 계단 정보를 배열에 저장해놓기
 *  => 오름차순 정렬
 
 * 3. 계단입구에서 아래층까지 도달하는 시간 계산
 *      => 시간을 1초씩 증가하면서 계단에서 사람이 한 명 내려갈 때마다 계단을 내려가는 사람을 cnt
 *      => 현재 사람의 수가 3이라면 시간이 지나도 사람이 내려가지 못하고 기다림
 *      => K만큼 내려간 사람이 생길 때마다 cnt-1 
 *
 * 
 * @author minho
 *
 */
public class Solution_2383_점심식사시간 {
 
    static int minTime; //최소 소요 시간
     
    static int N ;  //한 변의 길이
     
     
    static int peopleCnt;   //사람의 숫자
    static Node[] peoples = new Node[10];   //사람의 좌표 정보
     
    static Node[] stairs = new Node[2];     //계단 좌표정보
     
    static class Node{
        int r,c;
         
        int height; //계단의 경우: 계단의 높이, 사람의 경우: 내려온 칸수
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public Node(int r, int c, int height) {
            this.r = r;
            this.c = c;
            this.height = height;
        }
         
        public int getDistance(Node n) {
            return Math.abs(r-n.r) + Math.abs(c-n.c);
        }
    }
     
    public static void main(String[] args) throws NumberFormatException, IOException {
    //  System.setIn(new FileInputStream("점심식사시간_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=T; tc++) {
            minTime = Integer.MAX_VALUE;    //초기화
            N = Integer.parseInt(br.readLine());
             
            peopleCnt = 0;
            int stairCnt = 0;
            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    int input = Integer.parseInt(st.nextToken());
                    //사람의 경우
                    if(input==1) {
                        //좌표 정보 저장하고 사람수 cnt
                        peoples[peopleCnt++] = new Node(i,j);
                    }
                    else if(input!=0) {
                        //계단 좌표와 높이 저장하고 계단 수 cnt
                        stairs[stairCnt++] = new Node(i,j, input);
                    }
                }
            }
             
            //사람이 계단까지 가는데 걸리는 시간과 계단 정보를 저장할 배열
            //targetInfo[2][0] : 2번 사람이 계단까지 걸리는 시간
            //targetInfo[2][1] : 2번 사람이 선택한 계단 번호
            int[][] targetInfo = new int[peopleCnt][2];
             
            //step 01. 각 사람마다 내려갈 계단 정하기 ( 부분집합 )
            for(int flag=0; flag<=1<<peopleCnt; flag++) {
                //flag 자체가 하나의 경우의 수를 의미
                //비트가 0인 경우 0번 좌표의 계단을 선택한 것으로 체크
                 
                 
                //step 02.사람이 계단까지 가는데 걸리는 시간과 계단 정보를 저장할 배열 채우기
                for(int i=0; i<peopleCnt; i++) {
                    //i번 사람이 0번 계단 선택
                    if((flag & 1<<i) == 0) {
                        targetInfo[i][0] = peoples[i].getDistance(stairs[0]);
                        targetInfo[i][1] = 0;
                    }
                    //i번 사람이 1번 계단 선택
                    else {
                        targetInfo[i][0]= peoples[i].getDistance(stairs[1]);
                        targetInfo[i][1] = 1;
                    }
                     
                }
                 
                //해당 정보를 시간 기준 오름차순 정렬
                Arrays.sort(targetInfo, (a,b)->a[0]-b[0]);
                 
                 
                 
                //3. 계단입구에서 아래층까지 도달하는 시간 계산
                int pointer = 0;    //target정보 에서 현재 계단을 내려가야할 사람을 가리키는 포인터
                int time = 1;   //현재 경우의 수에서 걸린 시간
                int[] stairInfo0 = new int[stairs[0].height+1];
                int[] stairInfo1 = new int[stairs[1].height+1];
                int stairCnt0 = 0; // 0번 계단의 사람 수
                int stairCnt1 = 0; // 1번 계단의 사람 수
                //1초씩 증가하며 계단 내려가기를 반복
                 
                /**
                 * 
                 * 1) 1~K까지의 계단 내려가기
                 *  => cnt 정보를 관리해야 함.
                 * 2) 0에 있던 사람을 1로 내려보내기
                 *  => 3-cnt 만큼의 사람만 내려보내기
                 *  => 내려보낸 사람을 cnt
                 * 3) targetInfo[pointer][0] == time 이라면 해당 계단의 stairInfo[0]에 누적
                 *  => 같은 시간이 걸리는 사람이 더 있을 수 있으므로 pointer만 증가후 continue
                 * 
                 * 
                 */
                 
                while(true) {
                    //1) 1~K까지의 계단 내려가기
                    //계단 0
                    stairCnt0 -= stairInfo0[stairInfo0.length-1];
                    for(int i=stairInfo0.length-1; i>=2; i--) {
                        stairInfo0[i] = stairInfo0[i-1];
                    }
                    //계단 1
                    stairCnt1 -= stairInfo1[stairInfo1.length-1];
                    for(int i=stairInfo1.length-1; i>=2; i--) {
                        stairInfo1[i] = stairInfo1[i-1];
                    }
                    //2) 0에 있던 사람을 1로 내려보내기
                    //계단 0
                    int possibleCnt0 = 3-stairCnt0;
                    //내려갈 수 있는 사람 < 입구에 사람이 많은 경우
                    if(possibleCnt0 < stairInfo0[0]) {
                        stairInfo0[0] -= possibleCnt0;
                        stairInfo0[1] = possibleCnt0;
                    }
                    //내려갈 수 있는 사람 >= 입구에 사람
                    else {
                        stairInfo0[1] = stairInfo0[0];
                        stairInfo0[0] = 0;
                    }
                    //계단에 있는 사람 cnt
                    stairCnt0 += stairInfo0[1];
                     
                    //계단1
                    int possibleCnt1 = 3-stairCnt1;
                    //내려갈 수 있는 사람 < 입구에 사람이 많은 경우
                    if(possibleCnt1 < stairInfo1[0]) {
                        stairInfo1[0] -= possibleCnt1;
                        stairInfo1[1] = possibleCnt1;
                    }
                    //내려갈 수 있는 사람 >= 입구에 사람
                    else {
                        stairInfo1[1] = stairInfo1[0];
                        stairInfo1[0] = 0;
                    }
                    //계단에 있는 사람 cnt
                    stairCnt1 += stairInfo1[1];
                     
                    //계단에 한 명도 없고 pointer가 사람수라면 모두 내려왔다는 의미이므로 끝내기
                    if(stairCnt0+stairCnt1==0 && pointer==peopleCnt) break;
                     
                     
                    // 3) targetInfo[pointer][0] == time 이라면 해당 계단의 stairInfo[0]에 누적
                    while(pointer < peopleCnt && targetInfo[pointer][0] == time) {
                        int stairNum = targetInfo[pointer][1];  //계단 번호
                        if(stairNum==0) stairInfo0[0]++;
                        else stairInfo1[0]++;
                        //같은 시간이 걸리는 사람이 더 있을 수 있으므로 pointer만 증가후 continue
                        pointer++;
                    }
                     
                    time++;
                }
                 
                //step 04. 현재 고른 계단 상태가 최소시간이 걸린다면 갱신
                minTime = Math.min(time, minTime);
            }
             
            sb.append('#').append(tc).append(' ').append(minTime).append('\n');
        }
        System.out.println(sb);
         
    }
 
}
