// StringTokenizer는 null을 다룰 수 없지만 String은 null을 다룰 수 있다

import java.io.*;
import java.util.*;

public class Main {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String cur = br.readLine();
            if (cur == null) break;
            StringTokenizer st = new StringTokenizer(cur);
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            sb.append(s / (n+1)).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        // String의 빈 값 or null을 다루는 방법
        // while((input = br.readLine()) != null && !input.equals("")) { // equals() 내부적으로 가지는 여러 로직에 의해 기능적으로 낭비일 수 있다
        // while((input = br.readLine()) != null && input.trim().length()!=0) { // 자바 6보다 낮은 버전이라면 length()를 활용
        // while((input = br.readLine()) != null && !input.isBlank()) { // 자바 11이상, isBlank()는 문자열이 빈 값이거나 whitespace인 경우에는 true를 반환
        while((input = br.readLine()) != null && !input.trim().isEmpty()) { // 자바 6이상
            String[] line = input.split(" ");
            int N = Integer.parseInt(line[0]);
            int S = Integer.parseInt(line[1]);
            System.out.println(S / (N + 1));
        }
    }
}
