import java.util.*;
import java.io.*;
import java.math.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\ssafy\\workspace\\03-Algorithm\\src\\recursive\\input.txt"); // java.io.File;
		   // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		      BufferedReader br = new BufferedReader(new FileReader(file));
        String str = "Testcase";
        BufferedReader br = new BufferedReader(new StringReader(str));
        
====================================================================================================
     
        System.setIn(new FileInputStream("input.txt")); 
        // 이것을 사용하면 br.readLine()을 콘솔에서 받는것이 아닌 input.txt에서 받게 된다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.setOut(new PrintStream(new FileOutputStream("output.txt"))); 
        // 이것을 사용하면 System.out.println()으로 출력시 콘솔에서 받는게 아닌 output.txt에서 받게 된다.
        // 경로는 프로젝트의 src부터 적어주기 (ex. "src/Package명/.txt명") 
        
        int a = Integer.parseInt(br.readLine()); // 콘솔이 아닌 input.txt에서 한줄 긁어온다.
        
        for(int i = 1 ; i <= 300 ; i++) {
            for(int j = 1 ; j <= 300 ; j++)
                System.out.print((i+j) + " "); // 콘솔이 아닌 output.txt에 출력해버린다.
                System.out.println();
        }
     
    }
}
