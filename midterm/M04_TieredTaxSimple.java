
import java.util.*;

public class M04_TieredTaxSimple {

    private static int calculateTax(int income) {
        int tax = 0;

        /*
       * Time Complexity: O(n)
       * 說明：每筆收入的稅額計算僅需常數次判斷與運算 O(1)。
       * 共處理 n 筆輸入資料 O(n)，故整體時間複雜度為 O(n)。
         */
        if (income <= 120000) {
            tax = (int) (income * 0.05);
        } else if (income <= 500000) {
            tax = (int) (120000 * 0.05 + (income - 120000) * 0.12);
        } else if (income <= 1000000) {
            tax = (int) (120000 * 0.05 + (500000 - 120000) * 0.12 + (income - 500000) * 0.20);
        } else {
            tax = (int) (120000 * 0.05 + (500000 - 120000) * 0.12
                    + (1000000 - 500000) * 0.20 + (income - 1000000) * 0.30);
        }
        return tax;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int total = 0;
        for (int i = 0; i < n; i++) {
            int income = sc.nextInt();
            int tax = calculateTax(income);
            System.out.println("Tax: " + tax);
            total += tax;
        }

        int avg = total / n;

        System.out.println("Average: " + avg);
    }
}
