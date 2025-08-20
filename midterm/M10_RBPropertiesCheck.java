
import java.util.Scanner;

public class M10_RBPropertiesCheck {

    static class Node {

        int val;
        char color; // 'R' for Red, 'B' for Black
        Node left;
        Node right;

        Node(int val, char color) {
            this.val = val;
            this.color = color;
            this.left = null;
            this.right = null;
        }
    }

    // 儲存第一個 Red-Red 違規的索引
    private static int redRedViolationIndex = -1;

    private static Node buildTree(String[] values) {
        if (values == null || values.length == 0 || values[0].equals("-1")) {
            return null;
        }

        Node[] nodes = new Node[values.length / 2];
        for (int i = 0; i < values.length / 2; i++) {
            int val = Integer.parseInt(values[2 * i]);
            char color = values[2 * i + 1].charAt(0);
            if (val != -1) {
                nodes[i] = new Node(val, color);
            }
        }

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                int leftIndex = 2 * i + 1;
                int rightIndex = 2 * i + 2;
                if (leftIndex < nodes.length) {
                    nodes[i].left = nodes[leftIndex];
                }
                if (rightIndex < nodes.length) {
                    nodes[i].right = nodes[rightIndex];
                }
            }
        }
        return nodes[0];
    }

    // 根節點為黑色
    private static boolean checkRootColor(Node root) {
        return root == null || root.color == 'B';
    }

    // 不得有相鄰紅節點
    private static boolean checkRedRed(Node node, Node parent, int index) {
        if (node == null) {
            return true;
        }

        if (parent != null && parent.color == 'R' && node.color == 'R') {
            redRedViolationIndex = index;
            return false;
        }

        // 遞迴檢查左子樹和右子樹
        return checkRedRed(node.left, node, 2 * index + 1)
                && checkRedRed(node.right, node, 2 * index + 2);
    }

    // 自任一節點至所有葉路徑黑高度一致
    private static int checkBlackHeight(Node node) {
        if (node == null) {
            // 空節點視為黑葉節點，黑高度為 1
            return 1;
        }

        int leftBH = checkBlackHeight(node.left);
        int rightBH = checkBlackHeight(node.right);

        if (leftBH == -1 || rightBH == -1 || leftBH != rightBH) {
            // 如果子樹黑高度不一致，或子樹本身已違規，則向上傳遞 -1
            return -1;
        }

        // 返回當前子樹的黑高度
        int blackCount = (node.color == 'B') ? 1 : 0;
        return leftBH + blackCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        String[] input = scanner.nextLine().split("\\s+");

        Node root = buildTree(input);

        if (!checkRootColor(root)) {
            System.out.println("RootNotBlack");
            scanner.close();
            return;
        }

        if (!checkRedRed(root, null, 0)) {
            System.out.println("RedRedViolation at index " + redRedViolationIndex);
            scanner.close();
            return;
        }

        int blackHeightResult = checkBlackHeight(root);
        if (blackHeightResult == -1) {
            System.out.println("BlackHeightMismatch");
        } else {
            System.out.println("RB Valid");
        }

        scanner.close();
    }
}
