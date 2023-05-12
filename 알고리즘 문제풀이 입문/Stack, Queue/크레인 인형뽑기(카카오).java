import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] board = new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                board[i][j]=sc.nextInt();
        int m = sc.nextInt();
        int[] moves = new int[m];
        for(int i=0; i<m; i++)
            moves[i]= sc.nextInt();

        Stack<Integer> st = new Stack<>();
        int answer=0; st.push(0);
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++){
                if(board[j][moves[i]-1]!=0) {
                    int num = board[j][moves[i]-1];
                    if(num==st.peek()) {
                        answer+=2;
                        st.pop();
                    }
                    else st.push(board[j][moves[i]-1]);
                    board[j][moves[i]-1] = 0;
                    break;
                }
            }
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
        int n = sc.nextInt();
        int[][] board = new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                board[i][j]=sc.nextInt();
        int m = sc.nextInt();
        int[] moves = new int[m];
        for(int i=0; i<m; i++)
            moves[i]= sc.nextInt();

        Stack<Integer> st = new Stack<>();
        int answer=0;
        for(int i : moves) {
            // board.length 열 길이 // board[a].length 행a 길이
            for(int j=0; j<board.length; j++){
                if(board[j][i-1]!=0) {
                    int num = board[j][i-1];
                    if(!st.isEmpty() && num==st.peek()) { // 위의 코드의 Stack에 0을 넣어둔 것과 동일한 조치
                        answer+=2;
                        st.pop();
                    }
                    else st.push(num);
                    board[j][i-1] = 0;
                    break; // 하나의 인형만을 뽑기 위해
                }
            }
        }
        System.out.println(answer);
    }
}
