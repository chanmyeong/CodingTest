import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String current = sc.next();
        String drop = sc.next();

        String[] currentUnit = current.split(":");
        int currentHour = Integer.parseInt(currentUnit[0]);
        int currentMinute = Integer.parseInt(currentUnit[1]);
        int currentSecond = Integer.parseInt(currentUnit[2]);
        int currentSecondAmount = currentHour * 3600 + currentMinute * 60 + currentSecond;

        String[] dropUnit = drop.split(":");
        int dropHour = Integer.parseInt(dropUnit[0]);
        int dropMinute = Integer.parseInt(dropUnit[1]);
        int dropSecond = Integer.parseInt(dropUnit[2]);
        int dropSecondAmount = dropHour * 3600 + dropMinute * 60 + dropSecond;

        int needSecondAmount = dropSecondAmount - currentSecondAmount;
        // 두 시간이 같을 때 00:00:00 이 아닌 24:00:00으로 출력하려면 <0 -> <=0
        if (needSecondAmount <= 0) needSecondAmount += 24 * 3600;

        int needHour = needSecondAmount / 3600;
        int needMinute = (needSecondAmount % 3600) / 60;
        int needSecond = needSecondAmount % 60;

        System.out.printf("%02d:%02d:%02d", needHour, needMinute, needSecond);
    }
}

====================================================================================================
  
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String current = sc.next();
        String drop = sc.next();

        int currentHour = Integer.parseInt(current.substring(0,2));
        int currentMinute = Integer.parseInt(current.substring(3,5));
        int currentSecond = Integer.parseInt(current.substring(6,8));

        int dropHour = Integer.parseInt(drop.substring(0,2));
        int dropMinute = Integer.parseInt(drop.substring(3,5));
        int dropSecond = Integer.parseInt(drop.substring(6,8));

        int needHour = dropHour - currentHour;
        int needMinute = dropMinute - currentMinute;
        int needSecond = dropSecond - currentSecond;

        // s->m->h 순서로 해야 올바른 내림계산이 가능하다
        if(needSecond < 0) {
            needSecond += 60; needMinute--;
        }
        if(needMinute < 0) {
            needMinute += 60; needHour--;
        }
        if(needHour <= 0) needHour += 24;

        String ans = "";
        if(needHour<10) ans += "0" + needHour + ":";
        else ans += needHour + ":";
        if(needMinute<10) ans += "0" + needMinute + ":";
        else ans += needMinute + ":";
        if(needSecond<10) ans += "0" + needSecond;
        else ans += needSecond;
        System.out.println(ans);
    }
}
