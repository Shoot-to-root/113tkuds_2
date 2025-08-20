
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class M09_AVLValidate {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private static String status = "Valid";

    /*
     * Time Complexity: O(n)
     * 說明：建樹需要 O(n)。驗證函式對每個節點只遍歷一次，單次遍歷的操作為 O(1)，因此總時間為 O(n)。
     */
    private static TreeNode buildTree(int[] values) {
        if (values == null || values.length == 0 || values[0] == -1) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(values[0]);
        queue.offer(root);

        int i = 1;
        while (i < values.length && !queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (i < values.length && values[i] != -1) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < values.length && values[i] != -1) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }
        return root;
    }

    private static int validateAndGetHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 後序遍歷，先處理子節點
        int leftHeight = validateAndGetHeight(node.left);
        int rightHeight = validateAndGetHeight(node.right);

        // 如果子樹已經不合法，直接向上返回 -1
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        // 檢查 AVL 平衡因子
        if (Math.abs(leftHeight - rightHeight) > 1) {
            status = "Invalid AVL";
            return -1; // 標記為不平衡
        }

        // 返回當前子樹的高度
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private static boolean validateBST(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            status = "Invalid BST";
            return false;
        }
        return validateBST(node.left, min, node.val) && validateBST(node.right, node.val, max);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        TreeNode root = buildTree(values);

        validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);

        if (status.equals("Valid")) {
            validateAndGetHeight(root);
        }

        System.out.println(status);

        scanner.close();
    }
}
