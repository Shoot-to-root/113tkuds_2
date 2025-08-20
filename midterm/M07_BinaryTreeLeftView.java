import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class M07_BinaryTreeLeftView {

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

            // 處理左子節點
            if (i < values.length && values[i] != -1) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;

            // 處理右子節點
            if (i < values.length && values[i] != -1) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }
        return root;
    }

    private static void leftView(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        System.out.print("LeftView:");

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // 當前層的節點數
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // 只有第一個節點是該層最左邊的
                if (i == 0) {
                    System.out.print(" " + current.val);
                }

                // 將子節點（從左到右）加入佇列，準備下一層遍歷
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }
        
        TreeNode root = buildTree(values);
        
        leftView(root);
        
        scanner.close();
    }
}