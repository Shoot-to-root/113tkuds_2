
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode swapPairs(ListNode head) {
        // 創建虛擬頭節點，簡化對頭部節點的操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy; // 用來追蹤當前要交換的一對節點的前一個節點

        // 遍歷鏈結串列，每次處理一對節點
        // 迴圈條件確保至少還有兩個節點可以交換
        while (prev.next != null && prev.next.next != null) {
            ListNode firstNode = prev.next;
            ListNode secondNode = prev.next.next;

            // 重新連接指針來完成交換
            // 將前一個節點連接到第二個節點
            prev.next = secondNode;
            // 將第一個節點連接到下一個區塊的開頭
            firstNode.next = secondNode.next;
            // 將第二個節點連接到第一個節點
            secondNode.next = firstNode;

            // 移動 prev 指針到新的「前一個」位置，為下一次迭代做準備
            prev = firstNode;
        }

        // 返回新鏈結串列的頭部
        return dummy.next;
    }
}
