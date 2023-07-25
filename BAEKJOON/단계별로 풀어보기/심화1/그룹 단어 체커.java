import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int cntGroup=-1; // nextInt와 nextLine 혼용 시 공백을 먼저 읽어서 cntGroup++하고 시작해서 -1
        for(int tc=0; tc<=T; tc++) { // '<'로 할 경우 공백을 먼저 읽어서 한 줄을 덜 읽게된다
            boolean isGroup=true;
            int maxIdx=0;
            String word = sc.nextLine();
            for(int i=0; i<word.length(); i++) {
                int wordIdx = word.indexOf(word.charAt(i));
                maxIdx = Math.max(maxIdx,wordIdx); // 최초의 최대인덱스 갱신
                if(wordIdx<maxIdx) { // 최대 인덱스보다 작은 값이 나중에 등장할 경우 
                    isGroup = false; // 그룹 단어가 아니다
                }
            }
            if(isGroup) cntGroup++;
        }
        System.out.println(cntGroup);
    }
}
