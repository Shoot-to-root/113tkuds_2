public class ValidMaxHeapChecker {

    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        if (n == 0 || n == 1) return true; // 空陣列或單元素一定是有效的

        int lastParentIndex = (n - 2) / 2;

        for (int i = 0; i <= lastParentIndex; i++) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            if (leftChild < n && arr[i] < arr[leftChild]) {
                System.out.println("違反規則：索引 " + leftChild + " 的值 " + arr[leftChild] +
                                   " 大於父節點索引 " + i + " 的值 " + arr[i]);
                return false;
            }

            if (rightChild < n && arr[i] < arr[rightChild]) {
                System.out.println("違反規則：索引 " + rightChild + " 的值 " + arr[rightChild] +
                                   " 大於父節點索引 " + i + " 的值 " + arr[i]);
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };

        for (int[] testCase : testCases) {
            boolean result = isValidMaxHeap(testCase);
            System.out.println(java.util.Arrays.toString(testCase) + " → " + result);
        }
    }
}
