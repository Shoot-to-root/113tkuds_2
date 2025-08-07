import java.util.ArrayList;
import java.util.List;


public class BSTKthElement {

    static class Node {
        int value;
        int size = 1; // 初始化時，每個節點自身大小為 1
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else {
            node.right = insertRecursive(node.right, value);
        }
        // 回溯時更新 size
        node.size = 1 + getNodeSize(node.left) + getNodeSize(node.right);
        return node;
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node node, int value) {
        if (node == null) return null;

        if (value < node.value) {
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // 找到要刪除的節點
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // 處理有兩個子節點的情況
            Node successor = findMin(node.right);
            node.value = successor.value;
            node.right = deleteRecursive(node.right, successor.value);
        }
        // 回溯時更新 size
        node.size = 1 + getNodeSize(node.left) + getNodeSize(node.right);
        return node;
    }

    private Node findMin(Node node) {
        return node.left == null ? node : findMin(node.left);
    }

    private int getNodeSize(Node node) {
        return node == null ? 0 : node.size;
    }

    // 1. 找出BST中第k小的元素
    public Integer findKthSmallest(int k) {
        Node resultNode = findKthSmallestRecursive(root, k);
        return resultNode == null ? null : resultNode.value;
    }
    
    private Node findKthSmallestRecursive(Node node, int k) {
        if (node == null || k <= 0 || k > node.size) {
            return null;
        }
        int leftSize = getNodeSize(node.left);
        if (k == leftSize + 1) {
            return node;
        }
        if (k <= leftSize) {
            return findKthSmallestRecursive(node.left, k);
        }
        return findKthSmallestRecursive(node.right, k - leftSize - 1);
    }
    
    // 2. 找出BST中第k大的元素
    public Integer findKthLargest(int k) {
        if (root == null || k <= 0 || k > root.size) {
            return null;
        }
        // 第 k 大的元素 = 第 (N - k + 1) 小的元素
        return findKthSmallest(root.size - k + 1);
    }

    // 3. 找出BST中第k小到第j小之間的所有元素
    public List<Integer> findRange(int k, int j) {
        List<Integer> result = new ArrayList<>();
        if (root == null || k > j || k <= 0 || j > root.size) {
            return result;
        }
        findRangeRecursive(root, k, j, result, new int[]{0});
        return result;
    }
    
    private void findRangeRecursive(Node node, int k, int j, List<Integer> result, int[] counter) {
        if (node == null) return;

        // 中序遍歷
        findRangeRecursive(node.left, k, j, result, counter);
        
        // 處理目前節點
        counter[0]++; // 訪問到的節點排名+1
        if (counter[0] > j) return; // 優化：如果已超過範圍，停止遍歷
        if (counter[0] >= k) {
            result.add(node.value);
        }

        findRangeRecursive(node.right, k, j, result, counter);
    }
    

    public static void main(String[] args) {
        BSTKthElement bst = new BSTKthElement();
        int[] values = {20, 8, 22, 4, 12, 10, 14};
        for (int val : values) {
            bst.insert(val);
        }


        // 1. 測試找第 k 小
        int kSmall = 3;
        System.out.println("\n1. 尋找第 " + kSmall + " 小的元素:");
        System.out.println("結果: " + bst.findKthSmallest(kSmall)); 

        // 2. 測試找第 k 大
        int kLarge = 2;
        System.out.println("\n2. 尋找第 " + kLarge + " 大的元素:");
        System.out.println("結果: " + bst.findKthLargest(kLarge)); 

        // 3. 測試範圍查詢
        int k_range = 2, j_range = 5;
        System.out.println("\n3. 尋找第 " + k_range + " 小到第 " + j_range + " 小的元素:");
        System.out.println("結果: " + bst.findRange(k_range, j_range)); 

        // 4. 測試動態插入刪除
        System.out.println("\n4. 測試動態插入與刪除:");
        System.out.println("目前第 3 小的元素是: " + bst.findKthSmallest(3)); 
        
        System.out.println("刪除 4...");
        bst.delete(4); // 刪除最小的
        System.out.println("現在第 3 小的元素是: " + bst.findKthSmallest(3)); 
        
        System.out.println("插入 11...");
        bst.insert(11);
        System.out.println("現在第 3 小的元素是: " + bst.findKthSmallest(3)); 
    }
}