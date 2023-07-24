import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        int cntCroatia = 0; // ljlj
        for(int i=0; i<croatia.length; i++) {
            if(word.contains(croatia[i])) {
                word = word.replace(croatia[i], "*");
                //System.out.println(word);
            }
        }
        char[] oneword = word.toCharArray();
        for(int i=0; i<oneword.length; i++) {
            if(Character.isAlphabetic(oneword[i])) cntCroatia++;
            else if(oneword[i]=='*') cntCroatia++;
        }
        System.out.println(cntCroatia);
    }
}
====================================================================================================
import java.util.Scanner;
public class Main 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String [] alphabetList = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    
		for (String alphabet : alphabetList)
			s = s.replaceAll(alphabet, "!");	
		System.out.println(s.length());
	}
}
