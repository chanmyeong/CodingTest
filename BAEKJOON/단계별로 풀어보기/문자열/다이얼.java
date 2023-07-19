// 나의 풀이
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		char[] word = sc.nextLine().toCharArray();
		
		int time = 0;
		for(int i=0; i<word.length; i++) {
			if(word[i]=='1') time+=2;
			else if(word[i]=='2' || word[i]=='A' || word[i]=='B' || word[i]=='C') time+=3;
			else if(word[i]=='3' || word[i]=='D' || word[i]=='E' || word[i]=='F') time+=4;
			else if(word[i]=='4' || word[i]=='G' || word[i]=='H' || word[i]=='I') time+=5;
			else if(word[i]=='5' || word[i]=='J' || word[i]=='K' || word[i]=='L') time+=6;
			else if(word[i]=='6' || word[i]=='M' || word[i]=='N' || word[i]=='O') time+=7;
			else if(word[i]=='7' || word[i]=='P' || word[i]=='Q' || word[i]=='R' || word[i]=='S') time+=8;
			else if(word[i]=='8' || word[i]=='T' || word[i]=='U' || word[i]=='V') time+=9;
			else if(word[i]=='9' || word[i]=='W' || word[i]=='X' || word[i]=='Y' || word[i]=='Z') time+=10;
			else if(word[i]=='0') time+=11;
		}
		System.out.println(time);
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		String inputArr[] = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
		int result = str.length();
		for(int i=0; i<str.length(); i++) {
			Character c = str.charAt(i);
			for(int j=0; j<inputArr.length; j++) {
				if(inputArr[j].contains(c.toString())) {
					result += j;
				}
			}
		}
		System.out.println(result);
	}
}
====================================================================================================
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String dial = sc.nextLine();

        String[] arr = {"ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
        int answer = 0;

        for(int i = 0; i < dial.length(); i++){
            for(int j = 0; j < arr.length; j++){
                for(int k = 0; k < arr[j].length(); k++){
                    if(dial.charAt(i) == arr[j].charAt(k)){
                        answer += j + 3;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
