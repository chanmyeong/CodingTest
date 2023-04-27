import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().replaceAll("[a-zA-Z]", "");

        System.out.println(Integer.parseInt(str));
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int answer = 0;

        for(char x : str.toCharArray()) { // 아스키코드 '0'-'9'는 48~57에 해당
            if(x>=48 && x<=57) answer = answer*10+(x-48);
        }
        System.out.println(answer);
    }
}

====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String answer = "";

        for(char x : str.toCharArray()) {
            if(Character.isDigit(x)) answer += x;
        }
        System.out.println(Integer.parseInt(answer));
    }
}
