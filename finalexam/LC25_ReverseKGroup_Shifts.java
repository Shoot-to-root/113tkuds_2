
import java.util.*;

public class LC25_ReverseKGroup_Shifts {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = Integer.parseInt(sc.nextLine().trim()); // 第一行 k
        String line = sc.hasNextLine() ? sc.nextLine().trim() : "";
        sc.close();

        // 建立鏈結串列
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        if (!line.isEmpty()) {
            String[] parts = line.split("\\s+");
            for (String p : parts) {
                curr.next = new ListNode(Integer.parseInt(p));
                curr = curr.next;
            }
        }

        ListNode head = reverseKGroup(dummy.next, k);

        // 輸出結果
        curr = head;
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

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            // 找到這一組的第 k 個節點
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) {
                break; // 不足 k 結束
            }
            ListNode groupNext = kth.next;

            // 反轉這組節點
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // 調整連結
            ListNode tmp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = tmp;
        }
        return dummy.next;
    }

    private static ListNode getKthNode(ListNode start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }
        return start;
    }
}
