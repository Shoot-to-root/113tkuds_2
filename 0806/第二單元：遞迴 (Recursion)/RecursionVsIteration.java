import java.util.Stack;
import java.util.Arrays;

public class RecursionVsIteration {

    public static void main(String[] args) {
        System.out.println("--- 遞迴 vs. 迭代 效能比較 ---");


        System.out.println("\n1. 計算二項式係數 C(30, 15)");
        int n = 30, k = 15;
        
        long startTimeRec1 = System.nanoTime();
        long resultRec1 = binomialRecursive(n, k);
        long endTimeRec1 = System.nanoTime();
        System.out.println("遞迴結果: " + resultRec1 + ", 耗時: " + (endTimeRec1 - startTimeRec1) + " 奈秒");
        
        long startTimeIter1 = System.nanoTime();
        long resultIter1 = binomialIterative(n, k);
        long endTimeIter1 = System.nanoTime();
        System.out.println("迭代結果: " + resultIter1 + ", 耗時: " + (endTimeIter1 - startTimeIter1) + " 奈秒");

        System.out.println("\n2. 計算陣列元素的乘積");
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        
        System.out.println("陣列：" + Arrays.toString(arr));
        long startTimeRec2 = System.nanoTime();
        long resultRec2 = productRecursive(arr);
        long endTimeRec2 = System.nanoTime();
        System.out.println("遞迴結果: " + resultRec2 + ", 耗時: " + (endTimeRec2 - startTimeRec2) + " 奈秒");

        long startTimeIter2 = System.nanoTime();
        long resultIter2 = productIterative(arr);
        long endTimeIter2 = System.nanoTime();
        System.out.println("迭代結果: " + resultIter2 + ", 耗時: " + (endTimeIter2 - startTimeIter2) + " 奈秒");

        System.out.println("\n3. 計算字串中元音字母的數量");
        String longString = "averylongstringforvowelcountingdemonstrationpurposes";
  
        System.out.println("字串：" + longString);
        long startTimeRec3 = System.nanoTime();
        int resultRec3 = countVowelsRecursive(longString);
        long endTimeRec3 = System.nanoTime();
        System.out.println("遞迴結果: " + resultRec3 + ", 耗時: " + (endTimeRec3 - startTimeRec3) + " 奈秒");
        
        long startTimeIter3 = System.nanoTime();
        int resultIter3 = countVowelsIterative(longString);
        long endTimeIter3 = System.nanoTime();
        System.out.println("迭代結果: " + resultIter3 + ", 耗時: " + (endTimeIter3 - startTimeIter3) + " 奈秒");


        System.out.println("\n4. 檢查括號是否配對正確");
        String brackets = "{[()()][()]}";
        
        System.out.println("括號：" + brackets);
        long startTimeRec4 = System.nanoTime();
        boolean resultRec4 = areBracketsBalancedRecursive(brackets);
        long endTimeRec4 = System.nanoTime();
        System.out.println("遞迴結果: " + resultRec4 + ", 耗時: " + (endTimeRec4 - startTimeRec4) + " 奈秒");

        long startTimeIter4 = System.nanoTime();
        boolean resultIter4 = areBracketsBalancedIterative(brackets);
        long endTimeIter4 = System.nanoTime();
        System.out.println("迭代結果: " + resultIter4 + ", 耗時: " + (endTimeIter4 - startTimeIter4) + " 奈秒");
    }

    // 1. 計算二項式係數
    public static long binomialRecursive(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static long binomialIterative(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        if (k > n / 2) k = n - k; // C(n, k) == C(n, n-k)
        
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }

    // 2. 尋找陣列中所有元素的乘積
    public static long productRecursive(int[] arr) {
        if (arr == null || arr.length == 0) return 1;
        return productRecursiveHelper(arr, 0);
    }
    private static long productRecursiveHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        return arr[index] * productRecursiveHelper(arr, index + 1);
    }

    public static long productIterative(int[] arr) {
        if (arr == null || arr.length == 0) return 1;
        long product = 1;
        for (int num : arr) {
            product *= num;
        }
        return product;
    }

    // 3. 計算字串中元音字母的數量
    public static int countVowelsRecursive(String str) {
        if (str == null || str.isEmpty()) return 0;
        char first = Character.toLowerCase(str.charAt(0));
        int count = ("aeiou".indexOf(first) != -1) ? 1 : 0;
        return count + countVowelsRecursive(str.substring(1));
    }

    public static int countVowelsIterative(String str) {
        if (str == null) return 0;
        int count = 0;
        String vowels = "aeiou";
        for (char c : str.toLowerCase().toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    // 4. 檢查括號是否配對正確 
    public static boolean areBracketsBalancedRecursive(String str) {
        int originalLength = str.length();
        str = str.replace("()", "").replace("[]", "").replace("{}", "");
        // 如果長度不變，表示沒有可配對的括號
        if (str.length() == originalLength) {
            return str.isEmpty();
        }
        // 繼續遞迴
        return areBracketsBalancedRecursive(str);
    }

    public static boolean areBracketsBalancedIterative(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}