import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeBasicOperations {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        Node rootComplete = new Node(10);
        rootComplete.left = new Node(5);
        rootComplete.right = new Node(15);
        rootComplete.left.left = new Node(2);
        rootComplete.left.right = new Node(7);
        rootComplete.right.left = new Node(12);
        
        System.out.println("--- 測試一個完全二元樹 ---");
        testTree(rootComplete);

        // 建立一個非完全二元樹用於測試
        Node rootIncomplete = new Node(10);
        rootIncomplete.left = new Node(5);
        rootIncomplete.right = new Node(15);
        rootIncomplete.left.right = new Node(7); // 左子節點為 null
        
        System.out.println("\n--- 測試一個非完全二元樹 ---");
        testTree(rootIncomplete);
    }


    public static void testTree(Node root) {
        // 1. 總和與平均值
        System.out.println("節點總和: " + getSum(root));
        System.out.println("節點平均值: " + getAverage(root));

        // 2. 最大值與最小值
        try {
            System.out.println("最大值: " + findMax(root));
            System.out.println("最小值: " + findMin(root));
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // 3. 樹的寬度
        System.out.println("樹的寬度: " + getWidth(root));

        // 4. 判斷是否為完全二元樹
        System.out.println("是否為完全二元樹? " + isComplete(root));
    }


    // 1. 總和與平均值
    public static long getSum(Node root) {
        if (root == null) {
            return 0;
        }
        // 遞迴計算: 目前節點值 + 左子樹總和 + 右子樹總和
        return root.value + getSum(root.left) + getSum(root.right);
    }
    
    public static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    public static double getAverage(Node root) {
        if (root == null) {
            return 0.0;
        }
        long sum = getSum(root);
        int count = countNodes(root);
        return (double) sum / count;
    }


    // 2. 找出樹中的最大值和最小值節點
    public static int findMax(Node root) {
        if (root == null) {
            throw new IllegalStateException("樹為空，無法尋找最大值");
        }
        int leftMax = (root.left != null) ? findMax(root.left) : Integer.MIN_VALUE;
        int rightMax = (root.right != null) ? findMax(root.right) : Integer.MIN_VALUE;
        return Math.max(root.value, Math.max(leftMax, rightMax));
    }

    // 找出樹中的最小值
    public static int findMin(Node root) {
        if (root == null) {
            throw new IllegalStateException("樹為空，無法尋找最小值");
        }
        int leftMin = (root.left != null) ? findMin(root.left) : Integer.MAX_VALUE;
        int rightMin = (root.right != null) ? findMin(root.right) : Integer.MAX_VALUE;
        return Math.min(root.value, Math.min(leftMin, rightMin));
    }

    // 3. 計算樹的寬度
    public static int getWidth(Node root) {
        if (root == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // 目前層的節點數
            maxWidth = Math.max(maxWidth, levelSize);

            // 將下一層的所有節點加入佇列
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }
        return maxWidth;
    }

    // 4. 判斷一棵樹是否為完全二元樹
    public static boolean isComplete(Node root) {
        if (root == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean nullNodeFound = false; // 標記是否已遇到空節點

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode == null) {
                nullNodeFound = true;
            } else {
                // 如果在遇到空節點之後又遇到非空節點，則不是完全二元樹
                if (nullNodeFound) {
                    return false;
                }
                queue.add(currentNode.left);
                queue.add(currentNode.right);
            }
        }
        return true;
    }
}