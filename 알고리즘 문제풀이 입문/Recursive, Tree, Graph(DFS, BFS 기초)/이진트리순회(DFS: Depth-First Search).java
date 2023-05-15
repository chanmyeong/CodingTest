// 전위순회(prefix)
// 중위순회(infix, inorder traverse)
// 후위순회(postfix) ex)병합정렬

import java.io.*;
import java.util.*;

class Node {
    int data;
    Node lt, rt;
    public Node(int val) {
        data=val;
        lt=rt=null;
    }
}
public class Main {
    Node root;
    public void DFS(Node root) {
        if(root==null) return;
        else {
            System.out.print(root.data+" "); // prefix
            DFS(root.lt);
            // System.out.print(root.data+" "); // infix
            DFS(root.rt);
            // System.out.print(root.data+" "); // postfix
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main tree = new Main();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        tree.root.rt.lt = new Node(6);
        tree.root.rt.rt = new Node(7);
        tree.DFS(tree.root);
    }
}
