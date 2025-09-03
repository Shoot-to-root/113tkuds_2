
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0);

        // 創建一個指針來追蹤合併後鏈結串列的當前位置。
        ListNode current = dummyHead;

        // 當兩個列表都不為空時
        while (list1 != null && list2 != null) {
            // 比較兩個列表中當前節點的值
            if (list1.val <= list2.val) {
                // 如果 list1 的值較小，將 list1 的節點附加到合併後的列表
                current.next = list1;
                // 移動 list1 指針到下一個節點
                list1 = list1.next;
            } else {
                // 否則，將 list2 的節點附加到合併後的列表
                current.next = list2;
                // 移動 list2 指針到下一個節點
                list2 = list2.next;
            }
            // 移動 current 指針到剛附加的新節點
            current = current.next;
        }

        // 處理剩餘的節點
        // 如果 list1 還有剩餘，直接將其附加到末尾
        if (list1 != null) {
            current.next = list1;
        } // 如果 list2 還有剩餘，直接將其附加到末尾
        else if (list2 != null) {
            current.next = list2;
        }

        // 返回合併後鏈結串列的真正頭部
        return dummyHead.next;
    }
}
