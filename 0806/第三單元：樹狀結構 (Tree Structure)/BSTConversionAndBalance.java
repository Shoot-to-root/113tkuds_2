import java.util.Arrays;
import java.util.LinkedList;

public class BSTConversionAndBalance {

    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
    
    private Node head = null;
    private Node prev = null;


    public Node bstToDoublyLinkedList(Node root) {
        if (root == null) {
            return null;
        }
        
        inOrderToDoublyLinkedList(root);
        
        // 將鏈結串列變成循環的
        head.left = prev;
        prev.right = head;

        return head;
    }
    
    private void inOrderToDoublyLinkedList(Node node) {
        if (node == null) {
            return;
        }
        
        inOrderToDoublyLinkedList(node.left);
        
        if (prev == null) {
            head = node;
        } else {
            node.left = prev;
            prev.right = node;
        }
        prev = node;
        
        inOrderToDoublyLinkedList(node.right);
    }
    

    public Node sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return convertArrayToBST(nums, 0, nums.length - 1);
    }
    
    private Node convertArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        
        int mid = start + (end - start) / 2;
        Node root = new Node(nums[mid]);
        
        root.left = convertArrayToBST(nums, start, mid - 1);
        root.right = convertArrayToBST(nums, mid + 1, end);
        
        return root;
    }
    

    public boolean isBalanced(Node root) {
        return checkHeight(root) != -1;
    }
    
    private int checkHeight(Node node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private int sum = 0;
    

    public Node convertBSTToGreaterSumTree(Node root) {
        this.sum = 0; // 重置總和
        reverseInOrder(root);
        return root;
    }
    
    private void reverseInOrder(Node node) {
        if (node == null) {
            return;
        }
        
        reverseInOrder(node.right);
        
        this.sum += node.val;
        node.val = this.sum;
        
        reverseInOrder(node.left);
    }

    public void printInorder(Node node, LinkedList<Integer> list) {
        if (node != null) {
            printInorder(node.left, list);
            list.add(node.val);
            printInorder(node.right, list);
        }
    }

    public static void main(String[] args) {
        BSTConversionAndBalance solution = new BSTConversionAndBalance();


        // 範例 1: 將BST轉換為排序的雙向鏈結串列
        Node bstRoot = new Node(4);
        bstRoot.left = new Node(2);
        bstRoot.right = new Node(5);
        bstRoot.left.left = new Node(1);
        bstRoot.left.right = new Node(3);
        System.out.println("--- 轉換為雙向鏈結串列 ---");
        Node dllHead = solution.bstToDoublyLinkedList(bstRoot);
        Node current = dllHead;
        System.out.print("鏈結串列內容: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(current.val + " ");
            current = current.right;
        }
        System.out.println("\n------------------------------------");

        // 範例 2: 將排序陣列轉換為平衡的BST
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("--- 從排序陣列建立平衡BST ---");
        Node balancedRoot = solution.sortedArrayToBST(sortedArray);
        LinkedList<Integer> inorderList = new LinkedList<>();
        solution.printInorder(balancedRoot, inorderList);
        System.out.println("重建後BST的中序遍歷: " + inorderList);
        System.out.println("------------------------------------");
        
        // 範例 3: 檢查BST是否平衡
        Node balancedTest = solution.sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7});
        Node unbalancedTest = new Node(10);
        unbalancedTest.left = new Node(8);
        unbalancedTest.left.left = new Node(7); // 不平衡
        System.out.println("--- 檢查BST是否平衡 ---");
        System.out.println("平衡樹是否平衡: " + solution.isBalanced(balancedTest));
        System.out.println("不平衡樹是否平衡: " + solution.isBalanced(unbalancedTest));
        System.out.println("------------------------------------");

        // 範例 4: 將BST轉換為總和樹
        Node sumTreeRoot = new Node(4);
        sumTreeRoot.left = new Node(1);
        sumTreeRoot.right = new Node(6);
        sumTreeRoot.left.left = new Node(0);
        sumTreeRoot.left.right = new Node(2);
        sumTreeRoot.right.left = new Node(5);
        sumTreeRoot.right.right = new Node(7);
        System.out.println("--- 轉換為大於等於總和樹 ---");
        LinkedList<Integer> originalValues = new LinkedList<>();
        solution.printInorder(sumTreeRoot, originalValues);
        System.out.println("原始中序遍歷值: " + originalValues);
        
        solution.convertBSTToGreaterSumTree(sumTreeRoot);
        LinkedList<Integer> newValues = new LinkedList<>();
        solution.printInorder(sumTreeRoot, newValues);
        System.out.println("轉換後中序遍歷值: " + newValues);
        System.out.println("------------------------------------");
    }
}