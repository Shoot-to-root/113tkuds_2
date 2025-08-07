import java.util.*;

public class LevelOrderTraversalVariations {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }
    
    static class QueueNode {
        Node node;
        int column;

        QueueNode(Node node, int column) {
            this.node = node;
            this.column = column;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.right.right = new Node(8);
        root.right.right.right = new Node(9);
        /*
         * 1
         * /   \
         * 2     3
         * / \   / \
         * 4   5 6   7
         * \     \
         * 8     9
         */


        // 1. 將每一層的節點分別儲存
        System.out.println("\n1. 分層儲存節點:");
        System.out.println(levelOrderGrouped(root));

        // 2. 之字形層序走訪
        System.out.println("\n2. 之字形層序走訪:");
        System.out.println(zigzagLevelOrder(root));

        // 3. 只列印每一層的最後一個節點 
        System.out.println("\n3. 每一層的最後一個節點:");
        System.out.println(rightSideView(root));
        
        // 4. 垂直層序走訪
        System.out.println("\n4. 垂直層序走訪:");
        System.out.println(verticalOrder(root));
    }

    // --- 1. 將每一層的節點分別儲存在不同的List中 ---
    public static List<List<Integer>> levelOrderGrouped(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                currentLevel.add(node.value);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(currentLevel);
        }
        return result;
    }

    // --- 2. 實作之字形層序走訪 ---
    public static List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            // 使用 LinkedList 以便在頭部高效插入
            LinkedList<Integer> currentLevel = new LinkedList<>();
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                if (leftToRight) {
                    currentLevel.addLast(node.value);
                } else {
                    currentLevel.addFirst(node.value);
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(currentLevel);
            leftToRight = !leftToRight;
        }
        return result;
    }

    // --- 3. 只列印每一層的最後一個節點 ---
    public static List<Integer> rightSideView(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                // 如果是該層的最後一個節點，則加入結果列表
                if (i == levelSize - 1) {
                    result.add(node.value);
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }

    // --- 4. 實作垂直層序走訪 ---
    public static List<List<Integer>> verticalOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // 使用 TreeMap 可以保證 column 索引自動排序
        Map<Integer, List<Integer>> columnTable = new TreeMap<>();
        Queue<QueueNode> queue = new LinkedList<>();
        queue.add(new QueueNode(root, 0)); // 根節點在第 0 欄

        while (!queue.isEmpty()) {
            QueueNode qNode = queue.poll();
            Node node = qNode.node;
            int column = qNode.column;

            // 將節點值加入對應的欄位列表
            columnTable.computeIfAbsent(column, k -> new ArrayList<>()).add(node.value);

            if (node.left != null) {
                queue.add(new QueueNode(node.left, column - 1));
            }
            if (node.right != null) {
                queue.add(new QueueNode(node.right, column + 1));
            }
        }
        
        // 從 TreeMap 中取出所有值，它們已經按 column 排序好了
        result.addAll(columnTable.values());
        return result;
    }
}