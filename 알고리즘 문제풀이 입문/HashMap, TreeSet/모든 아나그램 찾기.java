import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String t = sc.next();
        char[] arrayS = s.toCharArray();
        char[] arrayT = t.toCharArray();
        int answer = 0;
        int l = t.length();
        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapT = new HashMap<>();

        for(int i=0; i<l; i++)
            mapT.put(arrayT[i], mapT.getOrDefault(arrayT[i],0)+1);

        for(int i=0; i<l-1; i++)
            mapS.put(arrayS[i], mapS.getOrDefault(arrayS[i],0)+1);
        int lt=0;
        for(int rt=l-1; rt<arrayS.length; rt++) {
            mapS.put(arrayS[rt], mapS.getOrDefault(arrayS[rt],0)+1);
            if(mapS.equals(mapT)) answer++;
            mapS.put(arrayS[lt], mapS.getOrDefault(arrayS[lt],0)-1);
            if(mapS.get(arrayS[lt])==0) mapS.remove(arrayS[lt]);
            lt++;
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
        String s = sc.next();
        String t = sc.next();
        int answer = 0;
        int l = t.length();
        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapT = new HashMap<>();

        for(char x : t.toCharArray())
            mapT.put(x, mapT.getOrDefault(x,0)+1);

        for(int i=0; i<l-1; i++)
            mapS.put(s.charAt(i), mapS.getOrDefault(s.charAt(i),0)+1);
        int lt=0;
        for(int rt=l-1; rt<s.length(); rt++) {
            mapS.put(s.charAt(rt), mapS.getOrDefault(s.charAt(rt),0)+1);
            if(mapS.equals(mapT)) answer++;
            mapS.put(s.charAt(lt), mapS.getOrDefault(s.charAt(lt),0)-1);
            if(mapS.get(s.charAt(lt))==0) mapS.remove(s.charAt(lt));
            lt++;
        }
        System.out.println(answer);
    }
}
