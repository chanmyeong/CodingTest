import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 모든 문자열에 등장하는 문자가 {A, C, G, T}인 문자열을 DNA문자열이라 칭함
 * DNA문자열에서의 부분문자열에서 등장하는 문자의 개수가  특정 개수 이상이어야 통과
 * 문자열 길이, 사용할 부분 문자열길이, 각 문자에 필요한 최소 사용 개수를 고려해 만들 수 있는
 * 비밀번호 종류 구하기
 * @author cm
 * sliding window
 * 초기값을 넣어두고 이후 반복문 하나로 빼주면서 더해나간 결과를 비교
 * 틀렸던 사고 흐름 : 증감연산과 결과비교를 같이 해야 시간복잡도가 줄어드는데
 * 연산과 결과비교를 따로 하다보니 시간복잡도가 줄어들지 않았었다 
 */
public class Main {
    static int s,p;
    static int[] mustDna;
    static int cntA, cntT, cntG, cntC;
    static char[] acgt;
    static String dna;
    static int answer=0; // 사용가능한 비밀번호 종류 수
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // 총 문자열 길이
        p = Integer.parseInt(st.nextToken()); // 부분 문자열 길이
        dna = br.readLine(); // 문자열
        acgt = dna.toCharArray();
        mustDna = new int[4]; // 필수 조건 저장한 배열 {A,C,G,T}
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            mustDna[i]=Integer.parseInt(st.nextToken());
        }
        
        int lt=0, rt=p;
        for(int i=0; i<rt; i++) {
        	if(acgt[i]=='A') cntA++;
    			else if(acgt[i]=='C') cntC++;
    			else if(acgt[i]=='G') cntG++;
    			else if(acgt[i]=='T') cntT++;
        }
        if(cntA>=mustDna[0] && cntC>=mustDna[1] && cntG>=mustDna[2] && cntT>=mustDna[3]) answer++;
        
        while(rt<acgt.length) {

        	// 이전의 lt값을 빼줘야함
        	if(acgt[lt]=='A') cntA--;
        	else if(acgt[lt]=='C') cntC--;
        	else if(acgt[lt]=='G') cntG--;
        	else if(acgt[lt]=='T') cntT--;

        	if(acgt[rt]=='A') cntA++;
        	else if(acgt[rt]=='C') cntC++;
        	else if(acgt[rt]=='G') cntG++;
        	else if(acgt[rt]=='T') cntT++;


        	if(cntA>=mustDna[0] && cntC>=mustDna[1] && cntG>=mustDna[2] && cntT>=mustDna[3]) answer++;

        	lt++;
        	rt++;

        	// 한번 돌때마다 lt, rt ++
        	// 한번 돌때마다 A, C, T, G 개수 카운트
        	// 만약 조건을 만족하면 answer++
        }
        System.out.println(answer);
    }
}
====================================================================================================
// 틀렸던 풀이
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int s,p;
    static int[] mustDna, checkDna;
    static char[] acgt;
    static String dna;
    static int answer=0; // 사용가능한 비밀번호 종류 수
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // 총 문자열 길이
        p = Integer.parseInt(st.nextToken()); // 부분 문자열 길이
        dna = br.readLine(); // 문자열
        acgt = dna.toCharArray();
        mustDna = new int[4]; // 필수 조건 저장한 배열 {A,C,G,T}
        checkDna = new int[4]; // 필수 조건을 충족하는지 확인하는 배열
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            mustDna[i]=Integer.parseInt(st.nextToken());
        }
        
        int lt=0, rt=p-1; // two pointer를 사용했지만 sliding window는 될 수없다
        while(rt<=s-1) {
        	
        	if(acgt[lt]=='A') checkDna[0]++;
        	else if(acgt[lt]=='C') checkDna[1]++;
        	else if(acgt[lt]=='G') checkDna[2]++;
        	else if(acgt[lt]=='T') checkDna[3]++;
        
        	if(lt==rt) {
        		if(checkDna[0]>=mustDna[0]&&checkDna[1]>=mustDna[1]&&checkDna[2]>=mustDna[2]&&checkDna[3]>=mustDna[3]) {
        			answer++;
        		}
//        		System.out.println(Arrays.toString(checkDna));
        		Arrays.fill(checkDna, 0); // 확인배열 초기화
        		rt++;
        		lt=rt-p; // lt=rt-(p-1) 할 것을 p만 빼주고 다음 줄에서 1을 더한다
        	}
        	lt++;
        }
        System.out.println(answer);
    }
}
