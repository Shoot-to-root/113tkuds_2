
import java.util.HashMap;
import java.util.Scanner;

public class LC01_TwoSum_THSRHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextInt();
        }

        int[] result = twoSum(seats, target);

        System.out.println(result[0] + " " + result[1]);
    }

    // 尋找兩個索引 i, j 使得 seats[i] + seats[j] == target
    public static int[] twoSum(int[] seats, int target) {
        // HashMap 用來記錄 "還需要的座位數" -> "對應的索引"
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < seats.length; i++) {
            int current = seats[i];

            // 如果當前數字剛好是之前需要的數字 -> 找到解答
            if (map.containsKey(current)) {
                return new int[]{map.get(current), i};
            }

            // 否則，記錄「還需要 target - current」
            int need = target - current;
            map.put(need, i);
        }

        // 若完全沒找到，回傳 -1 -1
        return new int[]{-1, -1};
    }
}
