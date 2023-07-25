// 나의 풀이
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String[] subject = new String[20];
        double[] score = new double[20];
        String[] gradeChi = new String[20];
        double sum = 0;
        double avg = 0;

        for(int i=0; i<20; i++) {
            subject[i] = sc.next();
            score[i] = sc.nextDouble();
            gradeChi[i] = sc.next();
        }

        for(int i=0; i<20; i++) {
            if(gradeChi[i].contains("P")) continue;
            switch(gradeChi[i]) {
                case "A+":
                    sum += score[i];
                    avg += score[i]*4.5;
                    break;
                case "A0":
                    sum += score[i];
                    avg += score[i]*4.0;
                    break;
                case "B+":
                    sum += score[i];
                    avg += score[i]*3.5;
                    break;
                case "B0":
                    sum += score[i];
                    avg += score[i]*3.0;
                    break;
                case "C+":
                    sum += score[i];
                    avg += score[i]*2.5;
                    break;
                case "C0":
                    sum += score[i];
                    avg += score[i]*2.0;
                    break;
                case "D+":
                    sum += score[i];
                    avg += score[i]*1.5;
                    break;
                case "D0":
                    sum += score[i];
                    avg += score[i]*1.0;
                    break;
                case "F":
                    sum += score[i];
                    avg += score[i]*0.0;
                    break;
            }
        }
        System.out.printf("%-4f", avg/sum);
    }
}
====================================================================================================
// 배열 2개 사용하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    String[] credits = {"A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F"};
	    double[] grades = {4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0.0};
	    
	    double sum = 0.0;
	    double result = 0.0;
		
	    for (int i = 0; i < 20; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	String subject = st.nextToken();
	    	String credit = st.nextToken();
	    	String grade = st.nextToken();
	    	
	    	if (grade.equals("P")) continue;
	    	
	    	sum += Double.parseDouble(credit);
	    	
	    	for (int j = 0; j < 9; j++) {
	    		if (grade.equals(credits[j])) {
	    			result += Double.parseDouble(credit) * grades[j];
	    		}
	    	}
	    }
	    System.out.println(result / sum);
	}
}
====================================================================================================
// Map 사용하기
import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Double> map = new HashMap();
        map.put("A+", 4.5);
        map.put("A0", 4.0);
        map.put("B+", 3.5);
        map.put("B0", 3.0);
        map.put("C+", 2.5);
        map.put("C0", 2.0);
        map.put("D+", 1.5);
        map.put("D0", 1.0);
        map.put("F", 0.0);

        double sum=0;
        double count=0;
        for(int i=0; i<20; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            double credit = Double.parseDouble(st.nextToken());
            String grade = st.nextToken();
            if(grade.equals("P")) continue;
            sum+=(credit*map.get(grade));
            count+=credit;
        }
        System.out.printf("%.6f", sum/count);
    }
}
  
