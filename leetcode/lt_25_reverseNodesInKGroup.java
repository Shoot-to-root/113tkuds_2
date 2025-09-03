
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

    public ListNode reverseKGroup(ListNode head, int k) {
        // 創建一個虛擬頭節點，簡化對鏈結串列頭部的操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            // 找到 k-group 的結束點
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }

            // 如果最後一組不足 k 個節點，不進行反轉
            if (end == null) {
                break;
            }

            ListNode start = prev.next;
            ListNode nextGroup = end.next;

            // 斷開當前 k-group 和下一組的連接
            end.next = null;
            // 反轉當前 k-group
            prev.next = reverseList(start);

            // 重新連接反轉後的區塊
            start.next = nextGroup;
            prev = start;
            end = start;
        }

        return dummy.next;
    }

    // 輔助函數：反轉一個鏈結串列
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
