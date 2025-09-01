
class Solution {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int currentRow = 0;
        boolean isGoingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(currentRow).append(c);

            if (currentRow == 0 || currentRow == numRows - 1) {
                isGoingDown = !isGoingDown;
            }

            if (isGoingDown) {
                currentRow++;
            } else {
                currentRow--;
            }
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}
