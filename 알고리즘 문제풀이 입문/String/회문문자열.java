import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.next().toUpperCase();
        String reverse = "";

        for(int i=str.length()-1; i>=0; i--)
            reverse += str.charAt(i);

        if(str.equals(reverse)) System.out.println("YES");
        else System.out.println("NO");
    }
}

====================================================================================================
  
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.next().toUpperCase();
        boolean fact = true;

        for(int i=0; i<str.length()/2; i++)
            if(str.charAt(i) != str.charAt(str.length()-1-i)) fact=false;

        if(fact) System.out.println("YES");
        else System.out.println("NO");
    }
}

====================================================================================================

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String tmp = new StringBuilder(str).reverse().toString();

        if(str.equalsIgnoreCase(tmp)) System.out.println("YES");
        else System.out.println("NO");

    }
}
