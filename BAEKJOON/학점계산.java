import java.io.*;
import java.util.*;

// 나의 풀이
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String C = br.readLine();
        String[] grade = {"none", "A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D+", "D0", "D-"};
        double score = 4.3;

        for(int i=1; i<grade.length; i++) {
            if(i==4) score=3.3;
            if(i==7) score=2.3;
            if(i==10) score=1.3;
            if(C.equals(grade[i])) {
                System.out.printf("%.1f%n",score);
            }
            if(C.equals("F")) {
                System.out.println("0.0");
                break;
            }
            score=score-0.3;
        }
    }
}
====================================================================================================
import java.io.*;
class Main{   
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	    String s=br.readLine();
        int count=0;
        if(s.charAt(0)=='F'){
            System.out.print("0.0");
        }else{
            count=69-(int)(s.charAt(0)); // 'E'==69
            if(s.charAt(1)=='-'){
                count-=1;
                System.out.print(count+".7");
            }else if(s.charAt(1)=='+'){
                System.out.print(count+".3");
            }else{
                System.out.print(count+".0");
            }
        }
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, String> grades = new HashMap<>();
        grades.put("A+", "4.3");
        grades.put("A0", "4.0");
        grades.put("A-", "3.7");
        grades.put("B+", "3.3");
        grades.put("B0", "3.0");
        grades.put("B-", "2.7");
        grades.put("C+", "2.3");
        grades.put("C0", "2.0");
        grades.put("C-", "1.7");
        grades.put("D+", "1.3");
        grades.put("D0", "1.0");
        grades.put("D-", "0.7");
        grades.put("F", "0.0");
        System.out.println(grades.get(new BufferedReader(new InputStreamReader(System.in)).readLine()));
    }
}
====================================================================================================
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		StringTokenizer token = new StringTokenizer( br.readLine() );
		double[][] score = { { 4.3, 4.0, 3.7 }, { 3.3, 3.0, 2.7 }, { 2.3, 2.0, 1.7 }, { 1.3, 1.0, 0.7 }, };

		String grade = token.nextToken();
		int num = ( int ) (grade.charAt( 0 ) - 'A');
		if ( num == 5 )
			System.out.println( "0.0" );
		else {
			int num2 = 0;
			if ( grade.charAt( 1 ) == '0' )
				num2++;
			else if ( grade.charAt( 1 ) == '-' )
				num2 += 2;
			System.out.println( score[num][num2] );
		}
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    private static final double[][] SCORE = {{4.3, 4.0, 3.7}, {3.3, 3.0, 2.7},
                                             {2.3, 2.0, 1.7}, {1.3, 1.0, 0.7}, {0.0}};    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        
        int leng = line.length();
        
        System.out.println(leng == 1 ? SCORE[4][0] : SCORE[line.charAt(0) - 'A'][line.charAt(1) == '0' ? 1 : line.charAt(1) == '+' ? 0 : 2]);
    }
}
