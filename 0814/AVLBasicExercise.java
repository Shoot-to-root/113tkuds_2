public class AVLBasicExercise {

    class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1;
        }
    }

    private Node root;

    private int height(Node N) {
        if (N == null) return 0;
        return N.height;
    }

    private int getBalance(Node N) {
        if (N == null) return 0;
        return height(N.left) - height(N.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public void insert(int key) {
        System.out.println("插入 " + key);
        root = insertNode(root, key);
        printRootInfo();
    }

    private Node insertNode(Node node, int key) {
        if (node == null) return new Node(key);

        if (key < node.key)
            node.left = insertNode(node.left, key);
        else if (key > node.key)
            node.right = insertNode(node.right, key);
        else
            return node; // 重複鍵不插入

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // 左左
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // 右右
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // 左右
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 右左
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public boolean search(int key) {
        System.out.println("搜尋 " + key + ": " + (searchNode(root, key) != null));
        return searchNode(root, key) != null;
    }

    private Node searchNode(Node node, int key) {
        if (node == null || node.key == key)
            return node;
        if (key < node.key)
            return searchNode(node.left, key);
        return searchNode(node.right, key);
    }

    public int getHeight() {
        int h = height(root);
        System.out.println("樹的高度: " + h);
        return h;
    }

    public boolean isValidAVL() {
        boolean valid = isAVL(root);
        System.out.println("是否為有效 AVL 樹: " + valid);
        return valid;
    }

    private boolean isAVL(Node node) {
        if (node == null) return true;
        int balance = getBalance(node);
        return Math.abs(balance) <= 1 && isAVL(node.left) && isAVL(node.right);
    }

    private void printRootInfo() {
        System.out.println("根節點: " + (root != null ? root.key : "null") +
                ", 高度: " + height(root) +
                ", 平衡因子: " + getBalance(root));
        System.out.println();
    }

    public static void main(String[] args) {
        AVLBasicExercise avl = new AVLBasicExercise();

        System.out.println("==== 插入 ====");
        int[] keys = {10, 20, 30, 40, 50, 25};
        for (int k : keys) avl.insert(k);

        System.out.println("==== 搜尋 ====");
        avl.search(25);
        avl.search(60);

        System.out.println("==== 高度 ====");
        avl.getHeight();

        System.out.println("==== AVL 驗證 ====");
        avl.isValidAVL();
    }
}
