
class Solution {

    private Map<Character, String> digitToLetters;
    private List<String> result;

    public List<String> letterCombinations(String digits) {
        result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // 建立數字到字母的對照表
        digitToLetters = new HashMap<>();
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");

        backtrack(digits, 0, new StringBuilder());

        return result;
    }

    private void backtrack(String digits, int index, StringBuilder currentCombination) {
        // 如果當前索引等於輸入字串的長度，表示一個組合完成
        if (index == digits.length()) {
            result.add(currentCombination.toString());
            return;
        }

        // 獲取當前數字對應的字母字串
        char digit = digits.charAt(index);
        String letters = digitToLetters.get(digit);

        for (char letter : letters.toCharArray()) {
            // 將字母添加到當前組合
            currentCombination.append(letter);

            // 處理下一個數字
            backtrack(digits, index + 1, currentCombination);

            // 移除最後一個添加的字母，以便探索其他路徑
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }
    }
}
