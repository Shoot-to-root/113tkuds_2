public class AVLDeleteExercise {
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
        else return node;

        updateHeight(node);
        return rebalance(node, key);
    }

    public void delete(int key) {
        String caseType = getDeleteCase(root, key);
        System.out.println("刪除 " + key + " (" + caseType + ")");
        root = deleteNode(root, key);
        printRootInfo();
    }

    private String getDeleteCase(Node node, int key) {
        Node target = searchNode(node, key);
        if (target == null) return "節點不存在";
        if (target.left == null && target.right == null) return "葉子節點";
        if (target.left == null || target.right == null) return "只有一個子節點";
        return "有兩個子節點";
    }

    private Node searchNode(Node node, int key) {
        if (node == null) return null;
        if (key == node.key) return node;
        return key < node.key ? searchNode(node.left, key) : searchNode(node.right, key);
    }

    private Node deleteNode(Node root, int key) {
        if (root == null) return root;

        if (key < root.key) root.left = deleteNode(root.left, key);
        else if (key > root.key) root.right = deleteNode(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) temp = root.right;
                else temp = root.left;
                if (temp == null) root = null;
                else root = temp;
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null) return root;
        updateHeight(root);
        return rebalanceAfterDelete(root);
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
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

    private Node rebalanceAfterDelete(Node node) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private void printRootInfo() {
        if (root != null) {
            System.out.println("根節點: " + root.key + ", 高度: " + root.height +
                               ", 平衡因子: " + getBalance(root));
        } else {
            System.out.println("樹已空");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();

        int[] keys = {9, 5, 10, 0, 6, 11, -1, 1, 2};
        for (int k : keys) tree.insert(k);

        tree.delete(10); // 一個子節點
        tree.delete(9);  // 兩個子節點
        tree.delete(-1); // 葉子節點
    }
}
