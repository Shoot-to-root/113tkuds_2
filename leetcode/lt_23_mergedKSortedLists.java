
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

    public ListNode mergeKLists(ListNode[] lists) {
        // 處理邊界情況：如果列表為空或沒有鏈結串列，直接返回 null
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 創建一個最小堆來存放節點，排序依據是節點的值
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 初始化堆，將每個鏈結串列的第一個節點放入堆中
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        // 創建一個虛擬頭節點和一個指針來構建合併後的鏈結串列
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            // 從堆中取出最小的節點
            ListNode smallest = minHeap.poll();

            // 將其附加到合併後的鏈結串列
            current.next = smallest;
            current = current.next;

            // 如果取出的節點還有下一個節點，將其加入堆中
            if (smallest.next != null) {
                minHeap.add(smallest.next);
            }
        }

        // 返回合併後鏈結串列的頭部
        return dummy.next;
    }
}
