
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 創建一個虛擬頭節點來簡化邊界情況處理
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = dummy;
        ListNode second = dummy;

        // 將 first 指針向前移動 n+1 步, 這樣 first 和 second 之間就會有 n 個節點的間隔
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // 同時移動兩個指針，直到 first 到達鏈結串列的末尾
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 當 first 達到末尾時，second 的下一個節點就是我們要刪除的目標
        // 執行刪除操作
        second.next = second.next.next;

        // 返回新的鏈結串列頭部
        return dummy.next;
    }
}
