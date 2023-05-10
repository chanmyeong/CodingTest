import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        boolean answer = true;

        HashMap<Character, Integer> map = new HashMap<>();

        for(char x : a.toCharArray()) {
            map.put(x, map.getOrDefault(x,0)+1);
        }
        // map을 2개 만들지 않고 하나의 map으로 두 개의 문자열을 증감으로 비교  
        for(char y : b.toCharArray()) {
            map.put(y, map.getOrDefault(y,0)-1);
        }

        for(int z : map.values()) {
            if(z!=0) answer=false;
        }

        if(answer) System.out.println("YES");
        else System.out.println("NO");
    }
}
====================================================================================================
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        boolean answer = true;

        HashMap<Character, Integer> map = new HashMap<>();

        for(char x : a.toCharArray()) {
            map.put(x, map.getOrDefault(x,0)+1);
        }
        for(char y : b.toCharArray()) {
            // 해당 key의 값이 없거나 key 값(value)을 감소시키기 전에 그 값이 0일 경우(감소하면 -1) 거짓
            if(!map.containsKey(y) || map.get(y)==0) answer=false;
            map.put(y, map.getOrDefault(y,0)-1);
        }

        if(answer) System.out.println("YES");
        else System.out.println("NO");
    }
}
