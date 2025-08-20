import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class M08_BSTRangedSum {

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

    private static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }

        int sum = 0;
        
        // 節點值在區間內，加到總和中，並繼續檢查左右子樹
        if (root.val >= L && root.val <= R) {
            sum += root.val;
            sum += rangeSumBST(root.left, L, R);
            sum += rangeSumBST(root.right, L, R);
        }
        // 節點值 < L，只需走右子樹
        else if (root.val < L) {
            sum += rangeSumBST(root.right, L, R);
        }
        // > R 只走左子樹
        else if (root.val > R) {
            sum += rangeSumBST(root.left, L, R);
        }
        
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }
        
        int L = scanner.nextInt();
        int R = scanner.nextInt();
        
        TreeNode root = buildTree(values);
        
        int sum = rangeSumBST(root, L, R);
        
        System.out.println("Sum: " + sum);
        
        scanner.close();
    }
}