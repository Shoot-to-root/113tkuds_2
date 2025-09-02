
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 創建一個虛擬頭節點，用來簡化新鏈結串列的創建
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, current = dummyHead;
        int carry = 0;

        // 迴圈直到兩個鏈結串列都遍歷完畢且沒有進位
        while (p != null || q != null || carry != 0) {
            // 獲取當前節點的值，如果節點為空則視為 0
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;

            // 計算總和，包含進位
            int sum = x + y + carry;

            // 計算新的進位
            carry = sum / 10;

            // 創建一個新節點，其值為總和的個位數
            current.next = new ListNode(sum % 10);

            // 移動到新鏈結串列的下一個節點
            current = current.next;
            // 移動 l1 和 l2 的指針
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        // 返回虛擬頭節點的下一個節點，即結果鏈結串列的頭部
        return dummyHead.next;
    }
}
