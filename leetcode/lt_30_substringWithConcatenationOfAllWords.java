
class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        // 處理邊界情況
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        // 計算單詞參數
        int wordLen = words[0].length();
        int wordCount = words.length;
        int subLen = wordLen * wordCount;

        // 創建單詞頻率表
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // 滑動視窗遍歷主字串
        for (int i = 0; i <= s.length() - subLen; i++) {
            // 創建一個新的頻率表，用於當前視窗內的單詞
            Map<String, Integer> seenWords = new HashMap<>();

            // 內部迴圈，以 wordLen 為步長遍歷當前視窗
            for (int j = 0; j < wordCount; j++) {
                int wordStartIndex = i + j * wordLen;
                String currentWord = s.substring(wordStartIndex, wordStartIndex + wordLen);

                // 如果當前單詞在 wordFreq 中，繼續檢查
                if (wordFreq.containsKey(currentWord)) {
                    seenWords.put(currentWord, seenWords.getOrDefault(currentWord, 0) + 1);

                    // 檢查頻率是否超過期望值
                    if (seenWords.get(currentWord) > wordFreq.get(currentWord)) {
                        break; // 頻率超過，當前視窗無效
                    }
                } else {
                    break; // 遇到不在單詞列表中的單詞，當前視窗無效
                }
            }

            // 如果內部迴圈成功完成，表示找到有效子字串
            if (seenWords.equals(wordFreq)) {
                result.add(i);
            }
        }

        return result;
    }
}
