
import java.util.*;

public class LC11_MaxArea_FuelHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }

        int result = maxArea(heights);

        System.out.println(result);
    }

    public static int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(heights[left], heights[right]); // 取左右最小高度
            int width = right - left;
            maxArea = Math.max(maxArea, h * width);          // 更新最大值

            // 移動較短邊, 才可能獲得更高的 min 高度
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
