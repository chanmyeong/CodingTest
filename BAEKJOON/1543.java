// 틀린 풀이
import java.util.*;

class Main
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String find = sc.nextLine();
        int countword = 0;
        int countfind = 0;
        int findindex = 0;

        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == find.charAt(findindex)) {
                countword++;   
            }
	findindex++;
            if(findindex==find.length()) findindex=0;
            if(countword==find.length()) {
            // 문제 예제에는 없는 반례가 존재 
            // 문서:aaaaaa 단어:a a 에서 결과가 0이어야 하지만 1이 나온다
            // 단어 한 묶음을 올바로 찾기 위해서는 find관련 반복문이 하나 더 필요하다
                countword=0;
                countfind++;
            }
        }
        System.out.println(countfind);
    }
}

====================================================================================================
  
import java.util.*;

class Main
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String doc = sc.nextLine();
        String word = sc.nextLine();
        int count=0;

        for(int i=0; i<doc.length(); i++) {
            boolean isMatch = true;
            for (int j = 0; j < word.length(); j++) {
                if (i + j >= doc.length() || doc.charAt(i + j) != word.charAt(j)) {
                    isMatch = false;
                    break;
                }
            }
            if(isMatch) {
                count++;
                i += word.length()-1;
            }
        }
        System.out.println(count);
    }
}

====================================================================================================
  
import java.util.*;

class Main
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String doc = sc.nextLine();
        String word = sc.nextLine();
        int startIndex = 0;
        int count=0;

        while(true) {
            int findIndex = doc.indexOf(word, startIndex);
            if(findIndex < 0) break;
            startIndex = findIndex + word.length();
            count++;
        }
        System.out.println(count);
    }
}

====================================================================================================
  
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String doc = sc.nextLine();
        String word = sc.nextLine();

        String replaced = doc.replace(word, "");
        int length = doc.length() - replaced.length();
        int count = length / word.length();
        
        System.out.println(count);

    }
}
  
