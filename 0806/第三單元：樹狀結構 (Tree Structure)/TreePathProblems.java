import java.util.ArrayList;
import java.util.List;

public class TreePathProblems {

    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }


    public List<String> findAllPaths(Node root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        
        List<Integer> currentPath = new ArrayList<>();
        dfs(root, currentPath, paths);
        return paths;
    }

    private void dfs(Node node, List<Integer> currentPath, List<String> paths) {
        currentPath.add(node.val);

        if (node.left == null && node.right == null) {
            // 找到一個葉節點，將路徑轉換成字串後加入結果列表
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < currentPath.size(); i++) {
                sb.append(currentPath.get(i));
                if (i < currentPath.size() - 1) {
                    sb.append("->");
                }
            }
            paths.add(sb.toString());
        }

        if (node.left != null) {
            dfs(node.left, currentPath, paths);
        }
        if (node.right != null) {
            dfs(node.right, currentPath, paths);
        }

        // 回溯：移除當前節點，以便處理其他路徑
        currentPath.remove(currentPath.size() - 1);
    }

    /**
     * 判斷樹中是否存在和為目標值的根到葉路徑
     */
    public boolean hasPathSum(Node root, int targetSum) {
        if (root == null) {
            return false;
        }
        
        // 檢查是否為葉節點且路徑和等於目標值
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        // 遞迴檢查左右子樹
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 找出樹中和最大的根到葉路徑
     */
    public int maxPathSumFromRoot(Node root) {
        if (root == null) {
            return 0;
        }
        
        // 遞迴計算左右子樹的最大路徑和
        int leftSum = maxPathSumFromRoot(root.left);
        int rightSum = maxPathSumFromRoot(root.right);
        
        // 返回當前節點的值加上左右子樹中較大的路徑和
        return root.val + Math.max(leftSum, rightSum);
    }

    /**
     * 計算樹中任意兩節點間的最大路徑和（樹的直徑）
     * 透過一個輔助變數來追蹤全局最大值
     */
    private int diameter = 0;

    public int treeDiameter(Node root) {
        this.diameter = 0;
        treeHeight(root);
        return this.diameter;
    }

    private int treeHeight(Node node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = treeHeight(node.left);
        int rightHeight = treeHeight(node.right);
        
        // 更新全局最大直徑
        this.diameter = Math.max(this.diameter, leftHeight + rightHeight);
        
        // 返回當前節點的高度
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    public static void main(String[] args) {
        TreePathProblems solution = new TreePathProblems();
        //         10
        //        /  \
        //       5    15
        //      / \     \
        //     2   7     20
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(2);
        root.left.right = new Node(7);
        root.right.right = new Node(20);


        // 1. 找出所有根到葉的路徑
        System.out.println("--- 根節點到所有葉節點的路徑 ---");
        List<String> paths = solution.findAllPaths(root);
        for (String path : paths) {
            System.out.println(path);
        }

        // 2. 判斷是否存在目標路徑和
        System.out.println("\n--- 判斷是否存在目標路徑和 ---");
        int target1 = 22; // 10 -> 5 -> 7
        int target2 = 30; // 10 -> 15 -> 20
        int target3 = 100; // 不存在
        System.out.printf("是否存在路徑和為 %d: %b\n", target1, solution.hasPathSum(root, target1));
        System.out.printf("是否存在路徑和為 %d: %b\n", target2, solution.hasPathSum(root, target2));
        System.out.printf("是否存在路徑和為 %d: %b\n", target3, solution.hasPathSum(root, target3));

        // 3. 找出最大路徑和
        System.out.println("\n--- 找出和最大的根到葉路徑 ---");
        int maxRootToLeafSum = solution.maxPathSumFromRoot(root);
        System.out.printf("最大的根到葉路徑和為: %d\n", maxRootToLeafSum);

        // 4. 計算樹的直徑
        System.out.println("\n--- 計算樹中任意兩節點間的最大路徑和（樹的直徑）---");
        int diameter = solution.treeDiameter(root);
        System.out.printf("樹的直徑為: %d\n", diameter);
    }
}