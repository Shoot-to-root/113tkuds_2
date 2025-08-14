import java.util.*;

public class AVLLeaderboardSystem {
    class Node {
        String player;
        int score, height, size;
        Node left, right;
        Node(String p, int s) {
            player = p;
            score = s;
            height = 1;
            size = 1;
        }
    }

    private Node root;
    private Map<String, Integer> playerScores = new HashMap<>();

    private void update(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = 1 + size(node.left) + size(node.right);
        }
    }

    private int height(Node node) { return node == null ? 0 : node.height; }
    private int size(Node node) { return node == null ? 0 : node.size; }
    private int getBalance(Node node) { return node == null ? 0 : height(node.left) - height(node.right); }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        update(x);
        update(y);
        return y;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        update(y);
        update(x);
        return x;
    }

    private int compare(String player, int score, Node node) {
        if (score != node.score) return score > node.score ? -1 : 1;
        return player.compareTo(node.player);
    }

    private Node insert(Node node, String player, int score) {
        if (node == null) return new Node(player, score);
        if (compare(player, score, node) < 0)
            node.left = insert(node.left, player, score);
        else if (compare(player, score, node) > 0)
            node.right = insert(node.right, player, score);
        else
            return node;
        update(node);
        return rebalance(node, player, score);
    }

    private Node delete(Node node, String player, int score) {
        if (node == null) return null;
        if (compare(player, score, node) < 0)
            node.left = delete(node.left, player, score);
        else if (compare(player, score, node) > 0)
            node.right = delete(node.right, player, score);
        else {
            if (node.left == null || node.right == null)
                node = (node.left != null) ? node.left : node.right;
            else {
                Node successor = minValueNode(node.right);
                node.player = successor.player;
                node.score = successor.score;
                node.right = delete(node.right, successor.player, successor.score);
            }
        }
        if (node != null) {
            update(node);
            return rebalance(node, player, score);
        }
        return null;
    }

    private Node minValueNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node rebalance(Node node, String player, int score) {
        int balance = getBalance(node);
        if (balance > 1 && compare(player, score, node.left) < 0)
            return rightRotate(node);
        if (balance < -1 && compare(player, score, node.right) > 0)
            return leftRotate(node);
        if (balance > 1 && compare(player, score, node.left) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && compare(player, score, node.right) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public void addOrUpdateScore(String player, int score) {
        if (playerScores.containsKey(player)) {
            int oldScore = playerScores.get(player);
            root = delete(root, player, oldScore);
        }
        playerScores.put(player, score);
        root = insert(root, player, score);

        printLeaderboard();
    }

    public int getRank(String player) {
        if (!playerScores.containsKey(player)) return -1;
        int score = playerScores.get(player);
        return getRankRec(root, player, score) + 1;
    }

    private int getRankRec(Node node, String player, int score) {
        if (node == null) return 0;
        if (compare(player, score, node) < 0)
            return getRankRec(node.left, player, score);
        else if (compare(player, score, node) > 0)
            return size(node.left) + 1 + getRankRec(node.right, player, score);
        else
            return size(node.left);
    }

    public List<String> getTopK(int k) {
        List<String> res = new ArrayList<>();
        getTopKRec(root, res, k);
        return res;
    }

    private void getTopKRec(Node node, List<String> res, int k) {
        if (node == null || res.size() >= k) return;
        getTopKRec(node.left, res, k);
        if (res.size() < k) res.add(node.player + "(" + node.score + ")");
        getTopKRec(node.right, res, k);
    }

    private void printLeaderboard() {
        List<String> allPlayers = getTopK(playerScores.size());
        System.out.println("=== 最新排行榜 ===");
        int rank = 1;
        for (String entry : allPlayers) {
            System.out.println(rank + ". " + entry);
            rank++;
        }
        System.out.println();
    }

    private static void printHelp() {
        System.out.println("\n=== 可用指令 ===");
        System.out.println("add <name> <score>     - 新增玩家");
        System.out.println("update <name> <score>  - 更新玩家分數");
        System.out.println("rank <name>            - 查詢玩家排名");
        System.out.println("topk <k>               - 查詢前 K 名玩家");
        System.out.println("help                   - 顯示指令列表");
        System.out.println("exit                   - 離開系統\n");
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== 遊戲排行榜系統 ===");
        printHelp();

        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String cmd = parts[0].toLowerCase();

            try {
                switch (cmd) {
                    case "exit":
                        System.out.println("系統結束");
                        sc.close();
                        return;
                    case "help":
                        printHelp();
                        break;
                    case "add":
                    case "update":
                        if (parts.length != 3) {
                            System.out.println("格式錯誤！用法: " + cmd + " <name> <score>");
                            break;
                        }
                        String name = parts[1];
                        int score = Integer.parseInt(parts[2]);
                        lb.addOrUpdateScore(name, score);
                        System.out.println(name + " 分數已更新為 " + score);
                        break;
                    case "rank":
                        if (parts.length != 2) {
                            System.out.println("格式錯誤！用法: rank <name>");
                            break;
                        }
                        name = parts[1];
                        int rank = lb.getRank(name);
                        if (rank == -1) System.out.println("找不到該玩家");
                        else System.out.println(name + " 的排名是: " + rank);
                        break;
                    case "topk":
                        if (parts.length != 2) {
                            System.out.println("格式錯誤！用法: topk <k>");
                            break;
                        }
                        int k = Integer.parseInt(parts[1]);
                        System.out.println("前 " + k + " 名: " + lb.getTopK(k));
                        break;
                    default:
                        System.out.println("未知指令: " + cmd + " (輸入 help 查看指令)");
                }
            } catch (NumberFormatException e) {
                System.out.println("分數或 K 必須是數字！");
            }
        }
    }
}
