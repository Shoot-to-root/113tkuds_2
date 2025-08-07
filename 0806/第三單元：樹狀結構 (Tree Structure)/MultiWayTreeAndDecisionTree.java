import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MultiWayTreeAndDecisionTree {


    static class MultiWayNode {
        String val;
        List<MultiWayNode> children;

        MultiWayNode(String val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }


    public MultiWayNode buildMultiWayTree() {
        MultiWayNode root = new MultiWayNode("A");
        
        MultiWayNode nodeB = new MultiWayNode("B");
        MultiWayNode nodeC = new MultiWayNode("C");
        MultiWayNode nodeD = new MultiWayNode("D");
        
        root.children.add(nodeB);
        root.children.add(nodeC);
        root.children.add(nodeD);

        nodeB.children.add(new MultiWayNode("E"));
        nodeB.children.add(new MultiWayNode("F"));
        
        nodeC.children.add(new MultiWayNode("G"));
        
        nodeD.children.add(new MultiWayNode("H"));
        nodeD.children.add(new MultiWayNode("I"));
        nodeD.children.add(new MultiWayNode("J"));
        
        return root;
    }


    public void depthFirstSearch(MultiWayNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        for (MultiWayNode child : node.children) {
            depthFirstSearch(child);
        }
    }


    public void breadthFirstSearch(MultiWayNode root) {
        if (root == null) {
            return;
        }
        Queue<MultiWayNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            MultiWayNode node = queue.poll();
            System.out.print(node.val + " ");
            queue.addAll(node.children); // 將所有子節點加入佇列
        }
    }


    public int calculateHeight(MultiWayNode root) {
        if (root == null) {
            return -1; // 空樹的高度定義為 -1
        }
        if (root.children.isEmpty()) {
            return 0; // 葉子節點的高度定義為 0
        }
        
        int maxHeight = 0;
        for (MultiWayNode child : root.children) {
            maxHeight = Math.max(maxHeight, calculateHeight(child));
        }
        
        return 1 + maxHeight;
    }


    public int calculateDegree(MultiWayNode node) {
        if (node == null) {
            return 0;
        }
        return node.children.size();
    }
    
    // 簡單決策樹範例 (猜動物)
    public void decisionTreeDemo() {
        System.out.println("--- 簡單決策樹範例 (猜動物) ---");
        
        Map<String, String[]> tree = new HashMap<>();
        tree.put("根", new String[]{"有羽毛嗎?", "有羽毛嗎?否", "有羽毛嗎?是"});
        tree.put("有羽毛嗎?是", new String[]{"會飛嗎?", "會飛嗎?否", "會飛嗎?是"});
        tree.put("會飛嗎?是", new String[]{"答案是:老鷹"});
        tree.put("會飛嗎?否", new String[]{"答案是:企鵝"});
        tree.put("有羽毛嗎?否", new String[]{"有四條腿嗎?", "有四條腿嗎?否", "有四條腿嗎?是"});
        tree.put("有四條腿嗎?是", new String[]{"答案是:貓"});
        tree.put("有四條腿嗎?否", new String[]{"答案是:蛇"});
        
        String currentNodeKey = "根";
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String[] nodeData = tree.get(currentNodeKey);
            String questionOrAnswer = nodeData[0];
            
            // 檢查是否為最終答案節點
            if (questionOrAnswer.startsWith("答案是:")) {
                System.out.println(questionOrAnswer);
                break;
            }
            
            // 如果是問題節點，則向使用者提問
            System.out.println(questionOrAnswer + " (請輸入 'y' 或 'n')");
            String answer = scanner.nextLine();
            
            // 根據使用者的回答導航到下一個節點
            if (answer.equalsIgnoreCase("y")) {
                currentNodeKey = nodeData[2]; // 對應 "是" 分支
            } else {
                currentNodeKey = nodeData[1]; // 對應 "否" 分支
            }
        }
        scanner.close();
    }
    
    public void printAllDegrees(MultiWayNode node) {
        if (node == null) {
            return;
        }

        
        System.out.printf("節點 '%s' 度數: %d\n", node.val, calculateDegree(node));

        
        for (MultiWayNode child : node.children) {
            printAllDegrees(child);
        }
    }

    public static void main(String[] args) {
        MultiWayTreeAndDecisionTree processor = new MultiWayTreeAndDecisionTree();
        MultiWayNode root = processor.buildMultiWayTree();
        
        
        System.out.println("--- 深度優先走訪 (DFS) ---");
        processor.depthFirstSearch(root);
        System.out.println();
        
        System.out.println("\n--- 廣度優先走訪 (BFS) ---");
        processor.breadthFirstSearch(root);
        System.out.println();
        
        System.out.println("\n--- 樹的高度與節點度數 ---");
        System.out.printf("樹的高度: %d\n", processor.calculateHeight(root));
        processor.printAllDegrees(root);
        

        try {
          System.out.println();
            processor.decisionTreeDemo();
        } catch (Exception e) {
            System.out.println("\n決策樹範例因非互動環境而跳過。");
        }
    }
}