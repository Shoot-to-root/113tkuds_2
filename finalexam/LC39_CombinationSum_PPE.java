
import java.util.*;

public class LC39_CombinationSum_PPE {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        sc.close();

        // 排序方便剪枝
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
     * @param start 當前搜尋起點（允許重複 → 傳入 i）
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
            int val = candidates[i];
            if (val > remain) {
                break; // 剪枝：數字過大

                        }path.add(val);
            // 可重複使用當前數字 -> i
            backtrack(candidates, remain - val, i, path, results);
            path.remove(path.size() - 1);
        }
    }
}
