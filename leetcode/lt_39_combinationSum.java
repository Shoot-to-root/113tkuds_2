
class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 對陣列進行排序，這有助於剪枝優化
        Arrays.sort(candidates);
        // 開始回溯
        backtrack(candidates, target, result, new ArrayList<>(), 0);
        return result;
    }

    /**
     * @param candidates 候選數字陣列
     * @param target 目標總和
     * @param result 儲存所有有效組合的列表
     * @param currentCombination 當前的組合
     * @param start 遍歷的起始索引
     */
    private void backtrack(int[] candidates, int target, List<List<Integer>> result, List<Integer> currentCombination, int start) {
        // 如果總和等於目標
        if (target == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        // 如果總和超過目標
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 選擇: 將當前數字添加到組合中
            currentCombination.add(candidates[i]);

            // 遞迴: 呼叫自身，並將目標減去當前數字
            // 這裡傳入的索引仍然是 i，因為同一個數字可以重複使用
            backtrack(candidates, target - candidates[i], result, currentCombination, i);

            // 移除最後一個數字，以便嘗試其他路徑
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
