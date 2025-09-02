
class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // 處理邊界情況
        if (nums == null || nums.length < 3) {
            return result;
        }

        // 對陣列進行排序
        Arrays.sort(nums);

        // 遍歷範圍到 nums.length - 2，以確保 left 和 right 指針有足夠的空間
        for (int i = 0; i < nums.length - 2; i++) {
            // 處理重複的元素，避免產生重複的三元組
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 設置雙指針
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // 找到了總和為 0 的三元組
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 移動指針，並跳過重複的元素
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // 繼續尋找下一個可能的三元組
                    left++;
                    right--;
                } else if (sum < 0) {
                    // 總和太小，需要增加它，移動左指針向右
                    left++;
                } else {
                    // 總和太大，需要減小它，移動右指針向左
                    right--;
                }
            }
        }

        return result;
    }
}
