import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b= sc.next();
		String reverseA = "";
		String reverseB = "";
		
		for(int i=2; i>=0; i--) {
			reverseA = reverseA + a.charAt(i);
			reverseB = reverseB + b.charAt(i);
		}
		int one = Integer.parseInt(reverseA);
		int two = Integer.parseInt(reverseB);
		System.out.println(one>two?one:two);
		
	}
}
====================================================================================================

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        a = Integer.parseInt(new StringBuilder().append(a).reverse().toString());
        b = Integer.parseInt(new StringBuilder().append(b).reverse().toString());

        System.out.println(a > b ? a : b);
    }
}
