
import java.util.*;

public class LC27_RemoveElement_Recycle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        int newLen = removeElement(nums, val);

        System.out.println(newLen);

        // 輸出更新後的前段內容
        for (int i = 0; i < newLen; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(nums[i]);
        }
        System.out.println();
    }

    public static int removeElement(int[] nums, int val) {
        // 指向下個可寫入位置
        int write = 0;

        for (int x : nums) {
            // 只把不是 val 的元素寫入
            if (x != val) {
                nums[write] = x;
                write++;
            }
        }

        return write;
    }
}
