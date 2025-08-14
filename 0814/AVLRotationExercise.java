public class AVLRotationExercise {

    class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1;
        }
    }

    Node root;

    int height(Node N) {
        return (N == null) ? 0 : N.height;
    }

    int getBalance(Node N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    void printNodeInfo(String label, Node node) {
        System.out.println(label + ": " + node.key + ", 高度: " + node.height);
        System.out.println("平衡因子: " + getBalance(node));
    }

    public static void main(String[] args) {
        AVLRotationExercise avl = new AVLRotationExercise();

        System.out.println("==== 測試右旋 ====");
        avl.root = avl.new Node(30);
        avl.root.left = avl.new Node(20);
        avl.root.left.left = avl.new Node(10);
        avl.root.height = 3;
        avl.root.left.height = 2;
        avl.root.left.left.height = 1;

        avl.printNodeInfo("右旋前\n根節點", avl.root);
        avl.root = avl.rightRotate(avl.root);
        avl.printNodeInfo("右旋後\n根節點", avl.root);

        System.out.println("\n==== 測試左旋 ====");
        avl.root = avl.new Node(10);
        avl.root.right = avl.new Node(20);
        avl.root.right.right = avl.new Node(30);
        avl.root.height = 3;
        avl.root.right.height = 2;
        avl.root.right.right.height = 1;

        avl.printNodeInfo("左旋前\n根節點", avl.root);
        avl.root = avl.leftRotate(avl.root);
        avl.printNodeInfo("左旋後\n根節點", avl.root);

        System.out.println("\n==== 測試左右旋 ====");
        avl.root = avl.new Node(30);
        avl.root.left = avl.new Node(10);
        avl.root.left.right = avl.new Node(20);
        avl.root.height = 3;
        avl.root.left.height = 2;
        avl.root.left.right.height = 1;

        avl.printNodeInfo("左右旋前\n根節點", avl.root);
        avl.root.left = avl.leftRotate(avl.root.left);
        avl.root = avl.rightRotate(avl.root);
        avl.printNodeInfo("左右旋後\n根節點", avl.root);

        System.out.println("\n==== 測試右左旋 ====");
        avl.root = avl.new Node(10);
        avl.root.right = avl.new Node(30);
        avl.root.right.left = avl.new Node(20);
        avl.root.height = 3;
        avl.root.right.height = 2;
        avl.root.right.left.height = 1;

        avl.printNodeInfo("右左旋前\n根節點", avl.root);
        avl.root.right = avl.rightRotate(avl.root.right);
        avl.root = avl.leftRotate(avl.root);
        avl.printNodeInfo("右左旋後\n根節點", avl.root);
    }
}
