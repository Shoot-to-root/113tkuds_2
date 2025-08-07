public class TreeMirrorAndSymmetry {


    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {

        // 1. 判斷樹是否對稱
        Node symmetricTree = new Node(1);
        symmetricTree.left = new Node(2);
        symmetricTree.right = new Node(2);
        symmetricTree.left.left = new Node(3);
        symmetricTree.left.right = new Node(4);
        symmetricTree.right.left = new Node(4);
        symmetricTree.right.right = new Node(3);

        Node nonSymmetricTree = new Node(1);
        nonSymmetricTree.left = new Node(2);
        nonSymmetricTree.right = new Node(2);
        nonSymmetricTree.left.right = new Node(3);
        nonSymmetricTree.right.right = new Node(3);
        
        System.out.println("\n1. 判斷樹是否對稱:");
        System.out.println("樹 1 是對稱的嗎? " + isSymmetric(symmetricTree));       
        System.out.println("樹 2 是對稱的嗎? " + isSymmetric(nonSymmetricTree)); 

        // 2. 將樹轉換為其鏡像
        System.out.println("\n2. 將樹轉換為其鏡像:");
        Node treeToMirror = new Node(4);
        treeToMirror.left = new Node(2);
        treeToMirror.right = new Node(7);
        treeToMirror.left.left = new Node(1);
        treeToMirror.left.right = new Node(3);
        
        System.out.println("原始樹:");
        printTree(treeToMirror);
        mirror(treeToMirror);
        System.out.println("鏡像後的樹:");
        printTree(treeToMirror);

        // 3. 比較兩棵樹是否互為鏡像
        Node treeA = new Node(1);
        treeA.left = new Node(2);
        treeA.right = new Node(3);
        
        Node treeB = new Node(1); // treeB 是 treeA 的鏡像
        treeB.left = new Node(3);
        treeB.right = new Node(2);

        Node treeC = new Node(1); // treeC 不是 treeA 的鏡像
        treeC.left = new Node(2);
        treeC.right = new Node(3);
        treeC.right.left = new Node(4);
        
        System.out.println("\n3. 比較兩棵樹是否互為鏡像:");
        System.out.println("treeA 和 treeB 互為鏡像嗎? " + areMirrors(treeA, treeB)); 
        System.out.println("treeA 和 treeC 互為鏡像嗎? " + areMirrors(treeA, treeC)); 

        // 4. 檢查一棵樹是否為另一棵樹的子樹
        Node mainTree = new Node(3);
        mainTree.left = new Node(4);
        mainTree.right = new Node(5);
        mainTree.left.left = new Node(1);
        mainTree.left.right = new Node(2);

        Node subTree1 = new Node(4); // subTree1 是子樹
        subTree1.left = new Node(1);
        subTree1.right = new Node(2);

        Node subTree2 = new Node(4); // subTree2 不是子樹 (結構不同)
        subTree2.left = new Node(1);
        subTree2.right = new Node(3);

        System.out.println("\n4. 檢查是否為子樹:");
        System.out.println("subTree1 是 mainTree 的子樹嗎? " + isSubtree(mainTree, subTree1)); 
        System.out.println("subTree2 是 mainTree 的子樹嗎? " + isSubtree(mainTree, subTree2)); 
    }

    // 1. 判斷一棵二元樹是否為對稱樹
    public static boolean isSymmetric(Node root) {
        if (root == null) {
            return true;
        }
        // 比較根節點的左右子樹是否互為鏡像
        return areMirrors(root.left, root.right);
    }

    // 2. 將一棵二元樹轉換為其鏡像樹
    public static Node mirror(Node node) {
        if (node == null) {
            return null;
        }
        // 遞迴地對左右子樹進行鏡像轉換
        mirror(node.left);
        mirror(node.right);

        // 交換左右子節點
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
        
        return node;
    }

    // 3. 比較兩棵二元樹是否互為鏡像
    public static boolean areMirrors(Node node1, Node node2) {
        // 基礎情況1: 如果兩個節點都是 null，它們是鏡像
        if (node1 == null && node2 == null) {
            return true;
        }
        // 基礎情況2: 如果只有一個是 null，或值不同，則不是鏡像
        if (node1 == null || node2 == null || node1.value != node2.value) {
            return false;
        }
        // 遞迴步驟：比較 node1 的左子樹與 node2 的右子樹，以及 node1 的右子樹與 node2 的左子樹
        return areMirrors(node1.left, node2.right) && areMirrors(node1.right, node2.left);
    }

    // -4. 檢查一棵樹是否為另一棵樹的子樹
    public static boolean isSubtree(Node mainTree, Node subTree) {
        // 基礎情況1: 子樹為 null，永遠是 true
        if (subTree == null) {
            return true;
        }
        // 基礎情況2: 主樹已空，但子樹不為空，永遠是 false
        if (mainTree == null) {
            return false;
        }
        // 如果以 mainTree 為根的樹和 subTree 完全相同，則返回 true
        if (areIdentical(mainTree, subTree)) {
            return true;
        }
        // 否則，遞迴地在 mainTree 的左子樹或右子樹中尋找 subTree
        return isSubtree(mainTree.left, subTree) || isSubtree(mainTree.right, subTree);
    }

    // 檢查兩棵樹的結構和值是否完全相同
    public static boolean areIdentical(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null || node1.value != node2.value) {
            return false;
        }
        return areIdentical(node1.left, node2.left) && areIdentical(node1.right, node2.right);
    }
    
    public static void printTree(Node node) {
        printTreeRecursive(node, "");
    }
    private static void printTreeRecursive(Node node, String indent) {
        if (node == null) return;
        printTreeRecursive(node.right, indent + "    ");
        System.out.println(indent + node.value);
        printTreeRecursive(node.left, indent + "    ");
    }
}