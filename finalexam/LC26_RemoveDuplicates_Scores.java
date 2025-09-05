
import java.util.*;

public class LC26_RemoveDuplicates_Scores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        int newLen = removeDuplicates(nums);

        System.out.println(newLen);
        // 輸出前段結果
        for (int i = 0; i < newLen; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(nums[i]);
        }
        System.out.println();
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int write = 1; // 下個可寫位置
        // 從第二個元素開始遍歷
        for (int i = 1; i < nums.length; i++) {
            // 如果當前值與上一次保留的不同，就寫入
            if (nums[i] != nums[write - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }
}
