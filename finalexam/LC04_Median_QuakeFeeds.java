
import java.util.Scanner;

public class LC04_Median_QuakeFeeds {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        double[] A = new double[n];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) {
            A[i] = sc.nextDouble();
        }
        for (int j = 0; j < m; j++) {
            B[j] = sc.nextDouble();
        }

        double median = findMedianSortedArrays(A, B);

        System.out.printf("%.1f\n", median);
    }

    // 在不合併兩個已排序數列的情況下，找出聯合集的中位數。
    public static double findMedianSortedArrays(double[] A, double[] B) {
        // 確保 A 是較短的數列，這樣二分範圍較小
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A);
        }

        int n = A.length;
        int m = B.length;
        int totalLeft = (n + m + 1) / 2; // 左半邊需要的總元素數

        int left = 0;
        int right = n;

        while (left <= right) {
            int i = (left + right) / 2;      // A 切割點
            int j = totalLeft - i;           // B 切割點

            // 處理邊界，若 i=0 表示 A 左半無元素, Aleft = -INF
            double Aleft = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];

            double Bleft = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            // 檢查是否符合條件：左半最大 <= 右半最小
            if (Aleft <= Bright && Bleft <= Aright) {
                // 總長度為奇數, 取左半最大
                if ((n + m) % 2 == 1) {
                    return Math.max(Aleft, Bleft);
                } // 總長度為偶數, 取左右中間平均
                else {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            } // 若 Aleft 太大，需往左縮
            else if (Aleft > Bright) {
                right = i - 1;
            } // 否則往右擴
            else {
                left = i + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not valid.");
    }
}
