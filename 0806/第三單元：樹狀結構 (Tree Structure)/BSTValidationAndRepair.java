import java.util.ArrayList;
import java.util.List;

public class BSTValidationAndRepair {

    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    /**
     * 驗證一棵二元樹是否為有效的BST
     * 使用遞迴方法，檢查每個節點的值是否在允許的範圍內
     */
    public boolean isValidBST(Node root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(Node node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    // 儲存中序遍歷中前一個節點的參考
    private Node prev;
    private Node first;
    private Node second;

    /**
     * 找出BST中不符合規則的兩個節點
     * 使用中序遍歷，因為BST的中序遍歷應該是升序排列
     */
    private void findSwappedNodes(Node root) {
        if (root == null) {
            return;
        }

        findSwappedNodes(root.left);

        if (prev != null && prev.val >= root.val) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;

        findSwappedNodes(root.right);
    }

    /**
     * 修復一棵有兩個節點位置錯誤的BST
     */
    public void repairBST(Node root) {
        this.prev = null;
        this.first = null;
        this.second = null;

        findSwappedNodes(root);

        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    /**
     * 計算需要移除多少個節點才能讓樹變成有效的BST：找出最長遞增子序列的長度，即為有效BST的節點數
     * 總節點數 - 有效BST節點數 = 需移除的節點數
     */
    public int nodesToRemove(Node root) {
        if (root == null) {
            return 0;
        }

        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);

        int n = inorderList.size();
        if (n <= 1) {
            return 0;
        }

        int lisLength = 1;
        int maxLisLength = 1;
        for (int i = 1; i < n; i++) {
            if (inorderList.get(i) > inorderList.get(i - 1)) {
                lisLength++;
            } else {
                lisLength = 1;
            }
            maxLisLength = Math.max(maxLisLength, lisLength);
        }

        return n - maxLisLength;
    }

    private void inorderTraversal(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }


    public void printInorder(Node node) {
        if (node != null) {
            printInorder(node.left);
            System.out.print(node.val + " ");
            printInorder(node.right);
        }
    }

    public static void main(String[] args) {
        BSTValidationAndRepair validator = new BSTValidationAndRepair();

        // 範例 1: 有效的BST
        Node root1 = new Node(10);
        root1.left = new Node(5);
        root1.right = new Node(15);
        root1.left.left = new Node(2);
        root1.left.right = new Node(8);
        root1.right.left = new Node(12);
        root1.right.right = new Node(18);

        System.out.println("=== 範例 1: 有效的BST ===");
        System.out.println("中序遍歷: ");
        validator.printInorder(root1);
        System.out.println();
        System.out.println("是否為有效的BST: " + validator.isValidBST(root1));
        System.out.println("需移除的節點數: " + validator.nodesToRemove(root1));
        System.out.println("------------------------------------");

        // 範例 2: BST中兩個節點位置錯誤 (10 和 20 互換)
        Node root2 = new Node(20);
        root2.left = new Node(5);
        root2.right = new Node(15);
        root2.left.left = new Node(2);
        root2.left.right = new Node(8);
        root2.right.left = new Node(12);
        root2.right.right = new Node(10);

        System.out.println("=== 範例 2: 有兩個節點位置錯誤的BST ===");
        System.out.println("原始中序遍歷: ");
        validator.printInorder(root2);
        System.out.println();
        System.out.println("是否為有效的BST: " + validator.isValidBST(root2));

        validator.repairBST(root2);
        System.out.println("修復後的BST中序遍歷: ");
        validator.printInorder(root2);
        System.out.println();
        System.out.println("修復後是否為有效的BST: " + validator.isValidBST(root2));
        System.out.println("------------------------------------");

        // 範例 3: 完全不符合BST規則的樹 (需移除節點)
        Node root3 = new Node(10);
        root3.left = new Node(5);
        root3.right = new Node(1); // 錯誤
        root3.left.left = new Node(8); // 錯誤
        root3.left.right = new Node(12); // 錯誤

        System.out.println("=== 範例 3: 需移除節點的樹 ===");
        System.out.println("原始中序遍歷: ");
        validator.printInorder(root3);
        System.out.println();
        System.out.println("是否為有效的BST: " + validator.isValidBST(root3));
        System.out.println("需移除的節點數: " + validator.nodesToRemove(root3));
    }
}