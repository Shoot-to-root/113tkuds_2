
class Solution {

    public int lengthOfLongestSubstring(String s) {
        // 使用 HashSet 來追蹤視窗內的不重複字元
        Set<Character> charSet = new HashSet<>();
        int left = 0;
        int maxLength = 0;

        // 右指針遍歷字串
        for (int right = 0; right < s.length(); right++) {
            // 如果當前字元（right）已存在於集合中，說明有重複
            while (charSet.contains(s.charAt(right))) {
                // 移除左邊的字元並移動左指針，直到沒有重複
                charSet.remove(s.charAt(left));
                left++;
            }
            // 將當前字元添加到集合中
            charSet.add(s.charAt(right));
            // 更新最長長度
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
