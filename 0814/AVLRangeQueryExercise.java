import java.util.*;

public class AVLRangeQueryExercise {
    class Node {
        int key, height;
        Node left, right;
        Node(int d) { key = d; height = 1; }
    }

    private Node root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insertRec(node.left, key);
        else if (key > node.key) node.right = insertRec(node.right, key);
        else return node; // 不允許重複

        updateHeight(node);
        return rebalance(node, key);
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private Node rebalance(Node node, int key) {
        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) return rightRotate(node);
        if (balance < -1 && key > node.right.key) return leftRotate(node);
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryRec(root, min, max, result);
        return result;
    }

    private void rangeQueryRec(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;

        // 左子樹可能有範圍內的值
        if (node.key > min) rangeQueryRec(node.left, min, max, result);

        // 當前節點在範圍內
        if (node.key >= min && node.key <= max) result.add(node.key);

        // 右子樹可能有範圍內的值
        if (node.key < max) rangeQueryRec(node.right, min, max, result);
    }

    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();
        int[] keys = {20, 10, 30, 5, 15, 25, 35};
        for (int k : keys) tree.insert(k);

        System.out.println("==== 範圍查詢測試 ====");
        int min = 10, max = 25;
        List<Integer> result = tree.rangeQuery(min, max);
        System.out.println("範圍 [" + min + ", " + max + "] 的節點: " + result);

        min = 5; max = 15;
        result = tree.rangeQuery(min, max);
        System.out.println("範圍 [" + min + ", " + max + "] 的節點: " + result);
    }
}
