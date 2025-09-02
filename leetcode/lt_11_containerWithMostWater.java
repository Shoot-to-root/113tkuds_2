class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        
        int maxArea = 0;
        
        while (left < right) {
            // 計算當前由兩個指針所代表的板子能盛多少水
            // 面積 = 較短的板子高度 * 兩個指針之間的寬度
            int currentHeight = Math.min(height[left], height[right]);
            int currentWidth = right - left;
            int currentArea = currentHeight * currentWidth;
            
            // 更新最大面積
            maxArea = Math.max(maxArea, currentArea);
            
            // 根據較短的板子來移動指針
            // 如果左邊的板子較短，移動左指針向右
            if (height[left] < height[right]) {
                left++;
            } 
            // 否則，移動右指針向左
            else {
                right--;
            }
        }
        
        return maxArea;
    }
}