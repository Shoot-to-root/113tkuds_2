import java.util.ArrayList;
import java.util.List;

public class AdvancedStringRecursion {

    public static void main(String[] args) {
        

        System.out.println("--- 遞迴產生字串的所有排列組合 ---");
        String strForPermutation = "ABC";
        System.out.println("字串 '" + strForPermutation + "' 的所有排列組合:");
        List<String> permutations = generatePermutations(strForPermutation);
        System.out.println(permutations);
        System.out.println();

        System.out.println("--- 遞迴實作字串匹配演算法 ---");
        String text = "hello world";
        String pattern1 = "world";
        String pattern2 = "java";
        System.out.println("在 '" + text + "' 中尋找 '" + pattern1 + "': " + contains(text, pattern1)); 
        System.out.println("在 '" + text + "' 中尋找 '" + pattern2 + "': " + contains(text, pattern2)); 
        System.out.println();

       System.out.println("--- 遞迴移除字串中的重複字符 ---");
        String strWithDuplicates = "programming";
        System.out.println("原始字串: " + strWithDuplicates);
        String strWithoutDuplicates = removeDuplicates(strWithDuplicates);
        System.out.println("移除重複字元後: " + strWithoutDuplicates); 
        System.out.println();
        
        System.out.println("--- 遞迴計算字串的所有子字串組合 ---");
        String strForSubsequence = "abc";
        System.out.println("字串 '" + strForSubsequence + "' 的所有子序列:");
        List<String> subsequences = generateSubsequences(strForSubsequence);
        System.out.println(subsequences); 
    }

    // 1. 遞迴產生字串的所有排列組合
    public static List<String> generatePermutations(String str) {
        List<String> results = new ArrayList<>();
        permutationsRecursive("", str, results);
        return results;
    }

    private static void permutationsRecursive(String prefix, String suffix, List<String> results) {
        // 基礎情況：當後綴為空時，表示一個完整的排列已形成
        if (suffix.isEmpty()) {
            results.add(prefix);
            return;
        }
        // 遞迴步驟：
        for (int i = 0; i < suffix.length(); i++) {
            char currentChar = suffix.charAt(i);
            String newPrefix = prefix + currentChar;
            String newSuffix = suffix.substring(0, i) + suffix.substring(i + 1);
            permutationsRecursive(newPrefix, newSuffix, results);
        }
    }

    // 2. 遞迴實作字串匹配演算法
    public static boolean contains(String text, String pattern) {
        // 基礎情況 1: pattern 是空的，永遠為 true
        if (pattern.isEmpty()) {
            return true;
        }
        // 基礎情況 2: text 比 pattern 短，不可能包含
        if (text.length() < pattern.length()) {
            return false;
        }
        // 遞迴步驟:
        // 檢查 text 是否以 pattern 開頭
        if (text.startsWith(pattern)) {
            return true;
        }
        // 如果不是，則在 text 的其餘部分遞迴尋找
        return contains(text.substring(1), pattern);
    }

    // 3. 遞迴移除字串中的重複字符
    public static String removeDuplicates(String str) {
        return removeDuplicatesRecursive(str, "");
    }
    
    private static String removeDuplicatesRecursive(String remaining, String result) {
        // 基礎情況：沒有剩餘的字元需要處理
        if (remaining.isEmpty()) {
            return result;
        }
        // 遞迴步驟：
        char currentChar = remaining.charAt(0);
        String newResult = result;
        // 檢查目前字元是否已存在於結果中
        if (result.indexOf(currentChar) == -1) {
            newResult += currentChar; // 如果不存在，則加入
        }
        return removeDuplicatesRecursive(remaining.substring(1), newResult);
    }


    // 4. 遞迴計算字串的所有子序列
    public static List<String> generateSubsequences(String str) {
        List<String> results = new ArrayList<>();
        subsequencesRecursive("", str, results);
        return results;
    }

    private static void subsequencesRecursive(String processed, String unprocessed, List<String> results) {
        // 基礎情況：當沒有未處理的字元時，將已處理的結果加入列表
        if (unprocessed.isEmpty()) {
            results.add(processed);
            return;
        }

        char currentChar = unprocessed.charAt(0);
        String remaining = unprocessed.substring(1);

        // 遞迴步驟：
        // 選擇 1: 不包含目前字元
        subsequencesRecursive(processed, remaining, results);
        // 選擇 2: 包含目前字元
        subsequencesRecursive(processed + currentChar, remaining, results);
    }
}