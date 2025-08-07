import java.util.*;

public class TreeReconstruction {


    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }
    
    // 1. 根據前序和中序走訪結果重建二元樹

    public Node buildFromPreIn(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        // 預先建立一個 map 以便在中序陣列中快速找到根節點的索引
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildFromPreInRecursive(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private Node buildFromPreInRecursive(int[] preorder, int preStart, int preEnd,
                                         int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        Node root = new Node(preorder[preStart]);
        int inRootIndex = inMap.get(root.value);
        int leftSubtreeSize = inRootIndex - inStart;

        root.left = buildFromPreInRecursive(preorder, preStart + 1, preStart + leftSubtreeSize,
                                            inorder, inStart, inRootIndex - 1, inMap);
        root.right = buildFromPreInRecursive(preorder, preStart + leftSubtreeSize + 1, preEnd,
                                             inorder, inRootIndex + 1, inEnd, inMap);
        return root;
    }


    // 2. 根據後序和中序走訪結果重建二元樹

    public Node buildFromPostIn(int[] postorder, int[] inorder) {
        if (postorder == null || inorder == null || postorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildFromPostInRecursive(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private Node buildFromPostInRecursive(int[] postorder, int postStart, int postEnd,
                                          int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        Node root = new Node(postorder[postEnd]);
        int inRootIndex = inMap.get(root.value);
        int leftSubtreeSize = inRootIndex - inStart;

        root.left = buildFromPostInRecursive(postorder, postStart, postStart + leftSubtreeSize - 1,
                                             inorder, inStart, inRootIndex - 1, inMap);
        root.right = buildFromPostInRecursive(postorder, postStart + leftSubtreeSize, postEnd - 1,
                                              inorder, inRootIndex + 1, inEnd, inMap);
        return root;
    }


    // 3. 根據層序走訪結果重建完全二元樹

    public Node buildCompleteFromLevelOrder(Integer[] levelOrder) {
        if (levelOrder == null || levelOrder.length == 0 || levelOrder[0] == null) {
            return null;
        }

        Node root = new Node(levelOrder[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (i < levelOrder.length) {
            Node parent = queue.poll();

            // 處理左子節點
            if (levelOrder[i] != null) {
                parent.left = new Node(levelOrder[i]);
                queue.add(parent.left);
            }
            i++;

            // 處理右子節點
            if (i < levelOrder.length && levelOrder[i] != null) {
                parent.right = new Node(levelOrder[i]);
                queue.add(parent.right);
            }
            i++;
        }
        return root;
    }


    // 4. 驗證重建的樹是否正確 
    
    public List<Integer> getInorderTraversal(Node root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }
    private void inorderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.value);
        inorderHelper(node.right, result);
    }
    
    
    public static void main(String[] args) {
        TreeReconstruction reconstructor = new TreeReconstruction();
        
        System.out.println("--- 1. 從前序和中序重建 ---");
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        Node root1 = reconstructor.buildFromPreIn(preorder, inorder);
        System.out.println("原始中序: " + Arrays.toString(inorder));
        System.out.println("重建後中序: " + reconstructor.getInorderTraversal(root1));
        System.out.println("重建是否正確: " + reconstructor.getInorderTraversal(root1).equals(Arrays.asList(9, 3, 15, 20, 7)));

        System.out.println("\n--- 2. 從後序和中序重建 ---");
        int[] postorder = {9, 15, 7, 20, 3};
        // inorder 陣列與上面相同
        Node root2 = reconstructor.buildFromPostIn(postorder, inorder);
        System.out.println("原始中序: " + Arrays.toString(inorder));
        System.out.println("重建後中序: " + reconstructor.getInorderTraversal(root2));
        System.out.println("重建是否正確: " + reconstructor.getInorderTraversal(root2).equals(Arrays.asList(9, 3, 15, 20, 7)));

        System.out.println("\n--- 3. 從層序重建完全二元樹 ---");
        Integer[] levelOrder = {1, 2, 3, 4, 5, 6, null};
        Node root3 = reconstructor.buildCompleteFromLevelOrder(levelOrder);
        // 驗證: 再次進行層序走訪並比較
        List<Integer> reconstructedLevelOrder = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root3);
        while(!q.isEmpty()){
            Node n = q.poll();
            reconstructedLevelOrder.add(n.value);
            if(n.left != null) q.add(n.left);
            if(n.right != null) q.add(n.right);
        }
        System.out.println("原始層序: " + Arrays.asList(1, 2, 3, 4, 5, 6)); // 忽略 null
        System.out.println("重建後層序: " + reconstructedLevelOrder);
        System.out.println("重建是否正確: " + reconstructedLevelOrder.equals(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }
}