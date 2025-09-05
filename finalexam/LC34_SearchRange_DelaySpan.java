
import java.util.*;

public class LC34_SearchRange_DelaySpan {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        int[] range = searchRange(nums, target);

        System.out.println(range[0] + " " + range[1]);
    }

    // 找到 target 的首尾位置
    public static int[] searchRange(int[] nums, int target) {
        int first = lowerBound(nums, target);        // >= target 的第一個位置
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1}; // target 不存在
        }
        int last = lowerBound(nums, target + 1) - 1; // < target+1 的最後一個位置
        return new int[]{first, last};
    }

    /**
     * lowerBound: 找到陣列中第一個 >= target 的索引 若都小於 target，回傳 nums.length
     */
    private static int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length; // 開區間 [left, right)
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1; // 繼續往右
            } else {
                right = mid;    // 收縮到左邊含 mid
            }
        }
        return left;
    }
}
