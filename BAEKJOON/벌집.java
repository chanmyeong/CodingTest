import java.io.*;
import java.util.*;

public class Main {
    static int answer=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int init=1; // 자신
        int left=1, right=1;
        int m=0; int dist=0;
        while(true) {
            if(n==1) break;
            m+=6;
            left=right+1;
            right+=m;
            dist++;
            if(left<=n && n<=right) break;
        }
        answer=init+dist;
        System.out.println(answer);
    }
}
====================================================================================================
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 1;
        int range = 2;
        
        if(n==1){
            System.out.println("1");
        }
        else{
            while(range<=n){
                range = range + (6*count);
                count++;
            }
           System.out.println(count);
        }
    }
}
====================================================================================================
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println(getLayer(scan.nextInt())+1);
		
		scan.close();
	}
	public static int getLayer(int number) {
		for(int i=0; i<100000000; i++) {
			if(number>=getStartNum(i) && number<getStartNum(i+1)) {
				return i;
			}
		}
		return -1;
	}
	public static int getStartNum(int index) {
		if(index==0 || index==1) return index+1;
		else return 2+3*index*(index-1);
	}
}
