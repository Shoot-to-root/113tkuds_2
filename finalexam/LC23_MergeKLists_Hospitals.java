
import java.util.*;

public class LC23_MergeKLists_Hospitals {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt(); // 串列數

        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            ListNode dummy = new ListNode(0);
            ListNode curr = dummy;
            while (true) {
                int val = sc.nextInt();
                if (val == -1) {
                    break; // 該串列結束

                                }curr.next = new ListNode(val);
                curr = curr.next;
            }
            lists[i] = dummy.next;
        }
        sc.close();

        ListNode merged = mergeKLists(lists);

        // 輸出結果
        ListNode curr = merged;
        boolean first = true;
        while (curr != null) {
            if (!first) {
                System.out.print(" ");
            }
            System.out.print(curr.val);
            first = false;
            curr = curr.next;
        }
        System.out.println();
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 最小堆，依節點值排序
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));

        // 初始將所有非空串列的頭放入堆
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode min = pq.poll();
            tail.next = min;
            tail = tail.next;
            if (min.next != null) {
                pq.offer(min.next);
            }
        }

        return dummy.next;
    }
}
