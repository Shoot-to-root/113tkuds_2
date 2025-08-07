import java.util.ArrayList;
import java.util.List;


public class BSTRangeQuerySystem {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;


    public void insert(int value) {
        this.root = insertRecursive(this.root, value);
    }

    private Node insertRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = insertRecursive(current.left, value);
        } else {
            current.right = insertRecursive(current.right, value);
        }
        return current;
    }
    
    // 1. 範圍查詢：找出在 [min, max] 範圍內的所有節點
    public List<Integer> findInRange(int min, int max) {
        List<Integer> result = new ArrayList<>();
        findInRangeRecursive(root, min, max, result);
        return result;
    }

    private void findInRangeRecursive(Node node, int min, int max, List<Integer> result) {
        if (node == null) {
            return;
        }
        // 如果目前節點的值大於 min，則左子樹中可能存在符合條件的節點
        if (node.value > min) {
            findInRangeRecursive(node.left, min, max, result);
        }
        // 檢查目前節點是否在範圍內
        if (node.value >= min && node.value <= max) {
            result.add(node.value);
        }
        // 如果目前節點的值小於 max，則右子樹中可能存在符合條件的節點
        if (node.value < max) {
            findInRangeRecursive(node.right, min, max, result);
        }
    }

    // 2. 範圍計數：計算在指定範圍內的節點數量
    public int countInRange(int min, int max) {
        return countInRangeRecursive(root, min, max);
    }

    private int countInRangeRecursive(Node node, int min, int max) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.value >= min && node.value <= max) {
            count = 1;
        }
        // 剪枝：只在必要時搜尋子樹
        if (node.value > min) {
            count += countInRangeRecursive(node.left, min, max);
        }
        if (node.value < max) {
            count += countInRangeRecursive(node.right, min, max);
        }
        return count;
    }
    
    // 3. 範圍總和：計算在指定範圍內所有節點值的總和
    public long sumInRange(int min, int max) {
        return sumInRangeRecursive(root, min, max);
    }
    
    private long sumInRangeRecursive(Node node, int min, int max) {
        if (node == null) {
            return 0;
        }
        long sum = 0;
        if (node.value >= min && node.value <= max) {
            sum = node.value;
        }
        // 剪枝：只在必要時搜尋子樹
        if (node.value > min) {
            sum += sumInRangeRecursive(node.left, min, max);
        }
        if (node.value < max) {
            sum += sumInRangeRecursive(node.right, min, max);
        }
        return sum;
    }

    // 4. 最接近查詢：找出最接近給定值的節點
    public int findClosest(int target) {
        if (root == null) {
            throw new IllegalStateException("樹為空，無法執行查詢");
        }
        
        Node current = root;
        int closest = root.value;

        while (current != null) {
            // 更新最接近的值
            if (Math.abs(target - current.value) < Math.abs(target - closest)) {
                closest = current.value;
            }
            // 如果找到完全相等的值，直接返回
            if (target == current.value) {
                return target;
            }
            // 根據 BST 的性質移動
            if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return closest;
    }


    public static void main(String[] args) {
        BSTRangeQuerySystem bst = new BSTRangeQuerySystem();
        int[] values = {15, 10, 20, 8, 12, 18, 25, 5, 9, 11, 13, 16, 19};
        for (int val : values) {
            bst.insert(val);
        }
        /*
         * 樹結構：
         * 15
         * /    \
         * 10      20
         * /  \    /  \
         * 8   12  18   25
         * / \  / \ / \
         * 5  9 11 13 16 19
         */


        // 測試範圍查詢
        int minRange = 9, maxRange = 18;
        System.out.println("\n1. 找出範圍 [" + minRange + ", " + maxRange + "] 內的所有節點:");
        System.out.println("結果: " + bst.findInRange(minRange, maxRange));

        // 測試範圍計數
        System.out.println("\n2. 計算範圍 [" + minRange + ", " + maxRange + "] 內的節點數量:");
        System.out.println("結果: " + bst.countInRange(minRange, maxRange));
        
        // 測試範圍總和
        System.out.println("\n3. 計算範圍 [" + minRange + ", " + maxRange + "] 內所有節點值的總和:");
        System.out.println("結果: " + bst.sumInRange(minRange, maxRange));

        // 測試最接近查詢
        int target = 14;
        System.out.println("\n4. 找出最接近 " + target + " 的節點值:");
        System.out.println("結果: " + bst.findClosest(target));
        
        target = 6;
        System.out.println("\n找出最接近 " + target + " 的節點值:");
        System.out.println("結果: " + bst.findClosest(target));
    }
}