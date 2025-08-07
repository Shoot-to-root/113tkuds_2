public class RecursiveMathCalculator {

    public static void main(String[] args) {
        System.out.println("--- 計算組合數 C(n, k) = C(n-1, k-1) + C(n-1, k) ---");
        int n_comb = 5, k_comb = 2;
        System.out.println("組合數 C(" + n_comb + ", " + k_comb + ") = " + combinations(n_comb, k_comb)); 
        System.out.println();

        System.out.println("--- 計算卡塔蘭數 C(n) = Σ(C(i) × C(n-1-i))，其中 i 從 0 到 n-1 ---");
        int n_catalan = 4;
        System.out.println("卡塔蘭數 C(" + n_catalan + ") = " + catalan(n_catalan)); 
        System.out.println();

        System.out.println("--- 計算漢諾塔移動步數 hanoi(n) = 2 × hanoi(n-1) + 1 ---");
        int n_hanoi = 3;
        System.out.println("漢諾塔移動步數 hanoi(" + n_hanoi + ") = " + hanoi(n_hanoi)); 
        System.out.println();

        System.out.println("--- 判斷一個數字是否為回文數（如 12321） ---");
        int num1 = 12321;
        int num2 = 12345;
        System.out.println("數字 " + num1 + " 是回文數嗎？ " + isPalindrome(num1)); 
        System.out.println("數字 " + num2 + " 是回文數嗎？ " + isPalindrome(num2)); 
    }

    // 計算組合數 C(n, k) = C(n-1, k-1) + C(n-1, k)
    public static int combinations(int n, int k) {
        // 基礎情況 (Base cases)
        // 1. 如果 k > n 或 k < 0，組合不存在。
        // 2. 如果 k == 0 或 k == n，只有一種組合方式（選擇全部或不選）。
        if (k < 0 || k > n) {
            return 0;
        }
        if (k == 0 || k == n) {
            return 1;
        }
        
        // 遞迴步驟
        return combinations(n - 1, k - 1) + combinations(n - 1, k);
    }

    // 計算卡塔蘭數 C(n) = Σ(C(i) × C(n-1-i))，其中 i 從 0 到 n-1
    public static int catalan(int n) {
        // 基礎情況
        if (n <= 1) {
            return 1;
        }
        
        // 遞迴步驟
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += catalan(i) * catalan(n - 1 - i);
        }
        return res;
    }

    // 計算漢諾塔移動步數 hanoi(n) = 2 × hanoi(n-1) + 1
    public static int hanoi(int n) {
        // 基礎情況：如果只有一個盤子，只需要移動一次。
        if (n == 1) {
            return 1;
        }
        
        // 遞迴步驟
        return 2 * hanoi(n - 1) + 1;
    }

    // 判斷一個數字是否為回文數（如 12321）
    public static boolean isPalindrome(int num) {
        // 負數不視為回文數
        if (num < 0) {
            return false;
        }
        String str = Integer.toString(num);
        return isPalindromeHelper(str);
    }

    // 檢查字串是否為回文
    private static boolean isPalindromeHelper(String str) {
        // 基礎情況
        // 如果字串長度為 0 或 1，它就是回文。
        if (str.length() <= 1) {
            return true;
        }
        
        // 遞迴步驟
        // 比較第一個和最後一個字元
        if (str.charAt(0) == str.charAt(str.length() - 1)) {
            // 如果相同，則遞迴檢查去掉頭尾的子字串
            return isPalindromeHelper(str.substring(1, str.length() - 1));
        } else {
            return false;
        }
    }
}