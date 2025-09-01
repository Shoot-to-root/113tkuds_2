
class Solution {

    private int start = 0;
    private int maxLength = 0;

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        for (int i = 0; i < s.length(); i++) {
            expandFromCenter(s, i, i);

            expandFromCenter(s, i, i + 1);
        }

        return s.substring(start, start + maxLength);
    }

    private void expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        int currentLength = right - left - 1;

        if (currentLength > maxLength) {
            maxLength = currentLength;
            start = left + 1;
        }
    }
}
