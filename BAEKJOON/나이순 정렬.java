import java.util.*;
import java.io.*;

/*
 * 나이와 이름이 가입한 순서대로 주어진다.
 * 원들을 나이가 증가하는 순으로, 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬하는 프로그램
 *
 * 문제 해결 전략 :
 * 객체 만들어서 Comparable 정렬
 */
public class Main {
    static class User implements Comparable<User>{
        int age, order; String name;

        public User(int age, int order, String name) {
            this.age = age;
            this.order = order;
            this.name = name;
        }

        @Override
        public int compareTo(User o) {
            if(this.age==o.age) {
                return this.order-o.order;
            }
            return this.age-o.age;
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        User[] user = new User[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            user[i] = new User(age, i, name);
        }

        Arrays.sort(user);
        for(User u : user)
            System.out.println(u.age+" "+u.name);
    }
}
====================================================================================================
// for문 출력보다 StringBuilder 출력이 절반 가량 빠르다
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static class person implements Comparable<person>{
		int age;
		String name;
		public person(int age, String name) {
			this.age = age;
			this.name = name;
		}
		@Override
		public int compareTo(person o) {
			return this.age - o.age;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		int N = Integer.parseInt(br.readLine());
		person[] people = new person[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			people[i] = new person(Integer.parseInt(st.nextToken()), st.nextToken());
		}
		Arrays.sort(people);
		for(int i = 0; i < N; i++) {
			sb.append(people[i].age).append(" ").append(people[i].name).append("\n");
		}
		System.out.println(sb);

	}
}
====================================================================================================
// StringBuilder타입 배열 만들어서 저장 후 인덱스 순서대로 출력
// StringBuilder로 인해 자동으로 늦게 가입한 사람이 늦게 append 된다
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder[] p = new StringBuilder[201];
		
		for(int i = 0; i < p.length; i++) {
			p[i] = new StringBuilder();
		}
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			p[age].append(age).append(' ').append(name).append('\n');
		}
		
		StringBuilder sb = new StringBuilder();
		for(StringBuilder val : p) {
			sb.append(val);
		}
		System.out.println(sb);
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 10814 문제 
	 * 문제 URL : https://www.acmicpc.net/problem/10814
	 */	
	static int MAX_OLD = 201;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 나이가 1부터 200까지이므로 해당 나이만큼 배열로 만들고, 나이 정보에 맞는 회원을 해당 배열에 덧붙여 저장하는 방법
		StringBuffer[] sb = new StringBuffer[MAX_OLD];
		
		for (int i=0;i<N;i++) {
			StringTokenizer memInfo = new StringTokenizer(br.readLine(), " ");
			String old = memInfo.nextToken();
			String name = memInfo.nextToken();
			if (sb[Integer.parseInt(old)] == null) {
				sb[Integer.parseInt(old)] = new StringBuffer();
			} else {
				sb[Integer.parseInt(old)].append(System.lineSeparator());
			}
			sb[Integer.parseInt(old)].append(Integer.parseInt(old)).append(' ').append(name);
		}
		
		for (StringBuffer n : sb) {
			if(n != null) System.out.println(n.toString());
		}
	}
}

====================================================================================================
// 2차원 배열 Comparator
  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * [문제] 가입순으로 사람들의 나이, 이름  주어짐. 나이 오름차순(나이 같으면 가입 먼저 한 순으로) 정렬
 * [조건] 1<=N<=100000. 1<=나이<=200, 이름은 알파벳 대소문자, 길이가 100보다 작거나 같은 문자열
 * [입력] 첫 줄 사람 수 N, 다음부터 사람의 나이와 이름
 * 
 * */

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //BufferedReader 사용
		int N = Integer.parseInt(br.readLine()); //사람 수 N
		
		String [][] ageName = new String [N][2]; //아예 나이도 문자열처럼 저장한 후 비교할 때 Integer.parseInt로 형변환하자
		
		StringTokenizer st; //StringTokenizer 사용
		
		for (int i=0; i < N; i ++) {
			st = new StringTokenizer(br.readLine()," ") ; //입력이 나이 (한 칸 띄고) 이름
			ageName[i][0] = st.nextToken(); //나이는 [i][0]번 인덱스에
			ageName[i][1] = st.nextToken(); //이름은 [i][1]번 인덱스에 저장
		}
    
		Arrays.sort(ageName, new Comparator<String[]>() { //ageName 이라는 배열에 대해 String[]라는 형을 비교할꺼임
			public int compare(String[] str1, String[] str2) { //String[]형인 str1과 str2를 비교하는데
				return Integer.parseInt(str1[0]) - Integer.parseInt(str2[0]); 
       			 //형변환한 나이를 비교하겠다. 같으면 어짜피 가입(입력받은) 순 그래도 유지될 것
			}
		});
		
		StringBuilder sb = new StringBuilder(); //StringBuilder 사용
		
		for (int i = 0 ; i < N ; i++) {
			sb.append(ageName[i][0]).append(' ').append(ageName[i][1]).append('\n');
		} //정렬된 좌표를 append 사용하여 쭉 이어진 문자열로 만들고
		
		System.out.println(sb); //출력
	}
}

//추가내용들
/* Comparator로 2차원배열 정렬 구현하는 방법
import java.util.Arrays;
import java.util.Comparator;

int[][] arrays = { { 0, 3 }, { 2, 6 }, { 1, 9 }, { 1, 8 } };

Arrays.sort(arrays, new Comparator<int[]>() {
  @Override
  public int compare(int[] o1, int[] o2) {
    if (o1[0] == o2[0])
      return o1[1] - o2[1];
    else
      return o1[0] - o2[0];
  }
});
*/
