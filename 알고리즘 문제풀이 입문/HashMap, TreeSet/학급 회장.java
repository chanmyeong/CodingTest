import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // nextLine() Enter 전까지 쓴 문자열 모두 반환 -> 뒤에 공백+문자열 없을 시 입력에서 인식불가
        String str = sc.next();
        int[] rank = new int[26];

        for(char x : str.toCharArray()){
            rank[(int)x-65]++;
        }
        int one=0; int answer=0;
        for(int i=0; i<26; i++) {
            if(one<rank[i]) {
                one=rank[i];
                answer=i;
            }
        }
        System.out.println((char)(answer+65));
    }
}

====================================================================================================
// HashMap 사용
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        char answer = ' ';
        HashMap<Character, Integer> map = new HashMap<>();

        for(char x : str.toCharArray()){
            // map.getOrDefault : x의 key 값(즉 value)을 가져오되 없다면 0을 리턴
            // map.getOrDefault -> map count 시 많이 사용
            map.put(x,map.getOrDefault(x,0)+1);
        }

        // System.out.println(map.containsKey('A')); // key 값을 포함하는 boolean 리턴
        // System.out.println(map.size()); // key 종류개수 리턴
        // System.out.println(map.remove('A')); // 해당 key 값을 리턴 후 해당 key 삭제

        int max = Integer.MIN_VALUE;
        for(char key : map.keySet()) {
            // System.out.println(key+" "+map.get(x));
            if(map.get(key)>max) {
                max=map.get(key);
                answer=key;
            }
        }
        System.out.println(answer);
    }
}

