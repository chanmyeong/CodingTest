// [참고문헌]: https://st-lab.tistory.com/211

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
 
public class Main {
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		StringBuilder sb = new StringBuilder();
 
		int N = Integer.parseInt(br.readLine());
 
		for (int i = 0; i < N; i++) {
 
			String[] s = br.readLine().split(" ");
 
			// 첫 번째 단어에 대한 switch-case 
			switch (s[0]) {
 
				case "push_front": {
					dq.addFirst(Integer.parseInt(s[1]));
					break;
				}
				
				case "push_back": {
					dq.addLast(Integer.parseInt(s[1]));
					break;
				}
 
				case "pop_front": {
					if (dq.isEmpty()) {
						sb.append(-1).append('\n');
					} 
					else {
						sb.append(dq.pollFirst()).append('\n');
					}
					break;
				}
 
				case "pop_back": {
					if (dq.isEmpty()) {
						sb.append(-1).append('\n');
					} 
					else {
						sb.append(dq.pollLast()).append('\n');
					}
					break;
				}
 
				case "size": {
					sb.append(dq.size()).append('\n');
					break;
				}
 
				case "empty": {
					if (dq.isEmpty()) {
						sb.append(1).append('\n');
					} 
					else {
						sb.append(0).append('\n');
					}
					break;
				}
 
				case "front": {
					if (dq.isEmpty()) {
						sb.append(-1).append('\n');
					} 
					else {
						sb.append(dq.peekFirst()).append('\n');
					}
					break;
				}
 
				case "back": {
					if (dq.isEmpty()) {
						sb.append(-1).append('\n');
					} 
					else {
						sb.append(dq.peekLast()).append('\n');
					}
					break;
				}
			}
		}
		System.out.println(sb);
	}
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	
	static int front = 10000;
	static int back = 10000;
	static int size = 0;
	static int[] deque = new int[20001];
	
	/*
	 * Deque 배열에서 front가 최종적으로 가리키는 위치는 항상 비워둔다.
	 * 즉, 가장 앞에있는 원소는 front + 1이 된다.
	 * 
	 * 이유는 만약 front의 최종 위치에 원소를 넣게 되면 다음과 같
	 * 예외가 발생한다.
	 * 
	 * 예로들어  push_front(3) 을 실행하려 하는데 아무 원소도 없을 때  
	 * front--; 감소시킨 뒤 deque[front] = 3; 이 되면
	 * deque[9999] = 3; 이 된다. 이때 front = 9999; back = 10000 이 된다.
	 * 
	 * 하지만, 원소가 한 개만 있을 경우 해당 원소는 front 이자 back 원소가 된다.
	 * 이러한 경우를 방지하기 위해
	 * deque[front] 에 원소를 넣은 뒤, front--; 을 해준다.
	 * 
	 * 결과적으로 front 요소 자체는 deque[front + 1] 위치에 자리한다.
	 */
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
 
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			
			switch(s[0]) {
			
				case "push_front" : {
					push_front(Integer.parseInt(s[1]));
					break;
				}
				
				case "push_back" : {
					push_back(Integer.parseInt(s[1]));
					break;
				}
				
				case "pop_front" : {
					sb.append(pop_front()).append('\n');
					break;
				}
				
				case "pop_back" : {
					sb.append(pop_back()).append('\n');
					break;
				}
				
				case "size" : {
					sb.append(size()).append('\n');
					break;
				}
				
				case "empty" : {
					sb.append(empty()).append('\n');
					break;
				}
				
				case "front" : {
					sb.append(front()).append('\n');
					break;
				}
				
				case "back" : {
					sb.append(back()).append('\n');
					break;
				}
			}
			
		}
		System.out.println(sb);
	}
		
	
	static void push_front(int val) {
		// 원소를 먼저 넣은 뒤 front을 감소시킨다.
		deque[front] = val;
		front--;
		size++;
	}
	
	static void push_back(int val) {
		
		back++;
		size++;
		deque[back] = val;
	}
	
	static int pop_front() {
		if (size == 0) {
			return -1;
		} 
		/*
		 *  front + 1이 front 원소이므로 해당 원소를 임시 변수에 둔 뒤 
		 *  front 을 +1 증가시킨다.
		 */
		int ret = deque[front + 1];
		front++;
		size--;	
		return ret;
	}
	
	static int pop_back() {
		if (size == 0) {
			return -1;
		} 
		int ret = deque[back];
		back--;
		size--;
		return ret;
	}
	
	static int size() {
		return size;
	}
	
	static int empty() {
		if(size == 0) {
			return 1;
		}
		return 0;
	}
	
	static int front() {
		if(size == 0) {
			return -1;
		}
		return deque[front + 1];
	}
	
	static int back() {
		if(size == 0) {
			return -1;
		}
		return deque[back];
	}
	
}
====================================================================================================
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	
	static int front = 0;
	static int back = 0;
	static int size = 0;
	static int[] deque = new int[10000];
	
	/*
	 * Deque 배열에서 front가 최종적으로 가리키는 위치는 항상 비워둔다.
	 * 즉, 가장 앞에있는 원소는 front + 1이 된다.
	 * 
	 * 이유는 만약 front의 최종 위치에 원소를 넣게 되면 다음과 같
	 * 예외가 발생한다.
	 * 
	 * 예로들어  push_front(3) 을 실행하려 하는데 아무 원소도 없을 때  
	 * front--; 감소시킨 뒤 deque[front] = 3; 이 되면
	 * deque[9999] = 3; 이 된다. 이때 front = 9999; back = 0 이 된다.
	 * 
	 * 하지만, 원소가 한 개만 있을 경우 해당 원소는 front 이자 back 원소가 된다.
	 * 이러한 경우를 방지하기 위해 deque[front] 에 원소를 넣은 뒤, 
	 * front = (front - 1 + array.length) % array.length; 을 해준다.
	 * 
	 * 결과적으로 front 요소 자체는 deque[(front + 1) % array.length] 위치에 자리한다.
	 * ((front + 1) % array.length == (front + 1) % 10000)
	 */
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
 
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			
			switch(s[0]) {
			
				case "push_front" : {
					push_front(Integer.parseInt(s[1]));
					break;
				}
				
				case "push_back" : {
					push_back(Integer.parseInt(s[1]));
					break;
				}
				
				case "pop_front" : {
					sb.append(pop_front()).append('\n');
					break;
				}
				
				case "pop_back" : {
					sb.append(pop_back()).append('\n');
					break;
				}
				
				case "size" : {
					sb.append(size()).append('\n');
					break;
				}
				
				case "empty" : {
					sb.append(empty()).append('\n');
					break;
				}
				
				case "front" : {
					sb.append(front()).append('\n');
					break;
				}
				
				case "back" : {
					sb.append(back()).append('\n');
					break;
				}
			}
			
		}
		System.out.println(sb);
	}
		
	
	static void push_front(int val) {
		// 원소를 먼저 넣은 뒤 front을 감소시킨다.
		deque[front] = val;
		// 음수가 되지 않도록 배열 크기만큼 더해준다.
		front = (front - 1 + 10000) % 10000;
		size++;
	}
	
	static void push_back(int val) {
		/*
		 *  deque[9999] 까지 꽉 찼을 경우 0으로 가리키기 위해
		 *  배열 크기만큼 나눠 나머지를 구한다.
		 */
		back = (back + 1) % 10000;
		size++;
		deque[back] = val;
	}
	
	static int pop_front() {
		if (size == 0) {
			return -1;
		} 
		/*
		 *  front + 1이 front 원소이므로 해당 원소를 임시 변수에 둔 뒤 
		 *  front 을 +1 증가시킨다.
		 */
		int ret = deque[(front + 1) % 10000];
		front = (front + 1) % 10000;
		size--;	
		return ret;
	}
	
	static int pop_back() {
		if (size == 0) {
			return -1;
		} 
		int ret = deque[back];
		back = (back - 1 + 10000) % 10000;
		size--;
		return ret;
	}
	
	static int size() {
		return size;
	}
	
	static int empty() {
		if(size == 0) {
			return 1;
		}
		return 0;
	}
	
	static int front() {
		if(size == 0) {
			return -1;
		}
		return deque[(front + 1) % 10000];
	}
	
	static int back() {
		if(size == 0) {
			return -1;
		}
		return deque[back];
	}
	
}
====================================================================================================
import java.io.*;
import java.util.*;

/**
 * push_front X: 정수 X를 덱의 앞에 넣는다.
 * push_back X: 정수 X를 덱의 뒤에 넣는다.
 * pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * size: 덱에 들어있는 정수의 개수를 출력한다.
 * empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
 * front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 *
 * 명령의 수 N (1 ≤ N ≤ 10,000)
 * 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다
 *
 */
public class Main {
    static int[] deque;
    static int size, N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        deque = new int[N+1];
        size=0;
        for(int n=0; n<N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            String order = st.nextToken();
            switch(order) {
                case("push_back"):
                    push_back(Integer.parseInt(st.nextToken()));
                    break;
                case("push_front"):
                    push_front(Integer.parseInt(st.nextToken()));
                    break;
                case("pop_front"):
                    sb.append(pop_front()).append("\n");
                    break;
                case("pop_back"):
                    sb.append(pop_back()).append("\n");
                    break;
                case("size"):
                    sb.append(size).append("\n");
                    break;
                case("empty"):
                    sb.append(empty()).append("\n");
                    break;
                case("front"):
                    sb.append(front()).append("\n");
                    break;
                case("back"):
                    sb.append(back()).append("\n");
                    break;
            }

        }
//        System.out.println(Arrays.toString(deque));
        System.out.println(sb);
    }

    private static int back() {
        if(size==0) {
            return -1;
        } else {
            return deque[size-1];
        }
    }

    private static int front() {
        if(size==0) {
            return -1;
        } else {
            return deque[0];
        }
    }

    private static int empty() {
        if(size==0) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int pop_back() {
        if(size==0) {
            return -1;
        } else {
            int result = deque[size-1];
            deque[size-1]=0;
            size--;
            return result;
        }
    }

    private static int pop_front() {
        if(size==0) {
            return -1;
        } else {
            int result = deque[0];
            for(int i=0; i<size; i++) {
                deque[i]=deque[i+1];
            }
            size--;
            return result;
        }
    }

    private static void push_front(int x) {
        for(int i=size; i>=1; i--) {
            deque[i]=deque[i-1];
        }
        deque[0] = x;
        size++;
    }
    private static void push_back(int x) {
        deque[size] = x;
        size++;
    }
}  
