
class Solution {

    public int romanToInt(String s) {
        // 建立羅馬數字到整數的對照表
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        // 初始化總和，並取得最後一個羅馬字元的值
        int result = romanMap.get(s.charAt(s.length() - 1));

        // 從右到左遍歷字串
        for (int i = s.length() - 2; i >= 0; i--) {
            // 取得當前字元和前一個字元的值
            int currentVal = romanMap.get(s.charAt(i));
            int nextVal = romanMap.get(s.charAt(i + 1));

            // 如果當前值小於前一個值，表示是減法，例如 IV 或 IX
            if (currentVal < nextVal) {
                result -= currentVal;
            } else {
                // 否則，直接相加
                result += currentVal;
            }
        }

        return result;
    }
}
