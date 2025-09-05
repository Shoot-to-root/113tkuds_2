
import java.util.*;

public class LC40_CombinationSum2_Procurement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        sc.close();

        // 排序方便去重
        Arrays.sort(candidates);

        // 執行回溯
        List<List<Integer>> results = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), results);

        // 輸出結果
        for (List<Integer> combo : results) {
            for (int i = 0; i < combo.size(); i++) {
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(combo.get(i));
            }
            System.out.println();
        }
    }

    /**
     * @param candidates 可用的物資價格
     * @param remain 剩餘預算
     * @param start 當前搜尋起點（只能用一次 → 傳入 i+1）
     * @param path 當前組合
     * @param results 收集所有合法組合
     */
    private static void backtrack(int[] candidates, int remain, int start,
            List<Integer> path, List<List<Integer>> results) {
        if (remain == 0) {
            results.add(new ArrayList<>(path)); // 找到一組解
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 跳過同層重複數字
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            int val = candidates[i];
            if (val > remain) {
                break; // 剪枝
            }
            path.add(val);
            // 每個數字只能用一次 -> i+1
            backtrack(candidates, remain - val, i + 1, path, results);
            path.remove(path.size() - 1);
        }
    }
}
