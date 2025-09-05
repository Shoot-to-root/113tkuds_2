
import java.util.*;

public class LC33_SearchRotated_RentHot {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        int index = search(nums, target);

        System.out.println(index);
    }

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 命中
            if (nums[mid] == target) {
                return mid;
            }

            // 判斷左半是否有序
            if (nums[left] <= nums[mid]) {
                // target 是否落在左半區間
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // 收縮右邊
                } else {
                    left = mid + 1; // 收縮左邊
                }
            } // 否則右半必定有序
            else {
                // target 是否落在右半區間
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // 收縮左邊
                } else {
                    right = mid - 1; // 收縮右邊
                }
            }
        }

        return -1; // 找不到
    }
}
