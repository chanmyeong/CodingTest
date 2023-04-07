import java.util.*;

class Main
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        char[] word = a.toCharArray();
        int max = 0;
        int index = 0;

        int[] countA = new int[26];
        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            if ('A' <= ch && ch <= 'Z') word[i] = (char) ('a' + ch - 'A');
            countA[word[i] - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (countA[i] > max) {
                max = countA[i];
                index = i;
            }
        }

        int maxCount=0;
        for(int i=0; i<26; i++) {
            if(max==countA[i]) maxCount++;
        }
        
        if(maxCount>1) System.out.println("?");
        else System.out.println((char) ('A' + index));

    }
}

====================================================================================================

import java.util.*;

class Main
{
    public static int[] getAlphabetCount(String str) {
        int[] count = new int[26];
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if('A' <= ch && ch <= 'Z') count[ch - 'A']++;
            else count[ch - 'a']++;
            // count[str.charAt(i)-'A']++;
        }
        return count;
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        // String str = sc.next().toUpperCase();
        int[] count = getAlphabetCount(str);

        int maxCount = -1;
        char maxAlphabet = '?';
        for(int i=0; i<26; i++) {
            if(count[i]>maxCount) {
                maxCount = count[i];
                maxAlphabet = (char)('A'+i);
            }
            else if(count[i]==maxCount) {
                maxAlphabet = '?';
            }
        }
        System.out.println(maxAlphabet);
    }
}

====================================================================================================

import java.util.*;

class Main
{
    public static int getAlphabetCount(String str, char alp) {
        int count = 0;
        for(int i=0; i < str.length(); i++) {
            if(alp == str.charAt(i)) count++;
        }
        return count;
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next().toUpperCase();

        int maxCount = -1;
        char maxAlphabet = '?';
        for(char alp = 'A'; alp <= 'Z'; alp++) {
            int count = getAlphabetCount(str, alp);
            if(count>maxCount) {
                maxCount = count;
                maxAlphabet = alp;
            }
            else if(count==maxCount) {
                maxAlphabet = '?';
            }
        }
        System.out.println(maxAlphabet);
    }
}
