import java.util.*;

public class PersistentAVLExercise {

    // 節點類別設為不可變
    static class Node {
        final int key;
        final Node left, right;
        final int height, size;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(height(left), height(right));
            this.size = 1 + size(left) + size(right);
        }
    }

    private static int height(Node n) { return n == null ? 0 : n.height; }
    private static int size(Node n) { return n == null ? 0 : n.size; }
    private static int getBalance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }

    private static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        return new Node(x.key, x.left, new Node(y.key, T2, y.right));
    }

    private static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        return new Node(y.key, new Node(x.key, x.left, T2), y.right);
    }

    private static Node rebalance(Node node) {
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);
        if (balance > 1 && getBalance(node.left) < 0) 
            return rightRotate(new Node(node.key, leftRotate(node.left), node.right));
        if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);
        if (balance < -1 && getBalance(node.right) > 0) 
            return leftRotate(new Node(node.key, node.left, rightRotate(node.right)));
        return node;
    }

    private static Node insert(Node node, int key) {
        if (node == null) return new Node(key, null, null);
        if (key < node.key)
            node = new Node(node.key, insert(node.left, key), node.right);
        else if (key > node.key)
            node = new Node(node.key, node.left, insert(node.right, key));
        else
            return node; // 不允許重複
        return rebalance(node);
    }

    private static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.key + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        List<Node> versions = new ArrayList<>();
        Node root = null;

        // v0: 空樹
        versions.add(root);

        int[] insertValues = {10, 20, 5, 15};

        for (int val : insertValues) {
            System.out.println("插入 " + val + "：");
            root = insert(root, val);
            versions.add(root);
        }

        System.out.println("\n=== 各版本樹狀態 ===");
        for (int i = 0; i < versions.size(); i++) {
            System.out.print("版本 " + i + ": ");
            inorder(versions.get(i));
            System.out.println();
        }
    }
}
