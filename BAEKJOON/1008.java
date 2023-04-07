[참고문헌]: https://st-lab.tistory.com/17

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 오차 범위를 고려하여 float가 아닌 double로 입력을 받아야 한다
        double A = sc.nextInt();
        double B = sc.nextInt();
        System.out.println(A/B);
    }
}

====================================================================================================
  
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // BufferedReader : Enter만 경계로 인식, String으로만 데이터 인식
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        // st.nextToken()은 문자열을 반환하기에 Double.parseDouble()로 double 형으로 변환시켜준다.
        double a = Double.parseDouble(st.nextToken());
        double b = Double.parseDouble(st.nextToken());

        System.out.println(a / b);
    }
}
