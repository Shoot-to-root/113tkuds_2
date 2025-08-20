import java.util.Scanner;

public class M05_GCD_LCM_Recursive {

    /**
     * Time Complexity: O(log(min(a, b)))
     * 說明：這是歐幾里得演算法的時間複雜度。每次遞迴呼叫，a 和 b 的值會迅速減小。
     * 每次迭代，b 變成 a % b。這使得數字的大小以對數速率減少。
     * 最壞情況下，當 a 和 b 是連續的費波那契數時，遞迴次數最多。
     */
    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private static long lcm(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return (a / gcd(a, b)) * b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long a = scanner.nextLong();
        long b = scanner.nextLong();

        if (a <= 0 || b <= 0) {
            System.out.println("輸入必須為正整數：");
            return;
        }

        long g = gcd(a, b);
        System.out.println("GCD: " + g);

        long l = lcm(a, b);
        System.out.println("LCM: " + l);

        scanner.close();
    }
}