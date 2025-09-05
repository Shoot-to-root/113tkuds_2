
import java.util.*;

public class LC18_4Sum_Procurement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long target = sc.nextLong();
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextLong();
        }
        sc.close();

        Arrays.sort(nums); // 排序以便去重和雙指針

        // 儲存所有解答
        List<List<Long>> result = new ArrayList<>();

        for (int i = 0; i < n - 3; i++) {
            // 去重：跳過與前一個相同的 i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                // 去重：跳過與前一個相同的 j
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int L = j + 1;
                int R = n - 1;

                while (L < R) {
                    long sum = nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum == target) {
                        // 找到一組解答
                        result.add(Arrays.asList(nums[i], nums[j], nums[L], nums[R]));

                        // 移動 L，跳過重複
                        long leftVal = nums[L];
                        while (L < R && nums[L] == leftVal) {
                            L++;
                        }

                        // 移動 R，跳過重複
                        long rightVal = nums[R];
                        while (L < R && nums[R] == rightVal) {
                            R--;
                        }
                    } else if (sum < target) {
                        L++; // 總和太小，左指針右移
                    } else {
                        R--; // 總和太大，右指針左移
                    }
                }
            }
        }

        // 輸出答案
        for (List<Long> quad : result) {
            for (int k = 0; k < quad.size(); k++) {
                if (k > 0) {
                    System.out.print(" ");
                }
                System.out.print(quad.get(k));
            }
            System.out.println();
        }
    }
}
