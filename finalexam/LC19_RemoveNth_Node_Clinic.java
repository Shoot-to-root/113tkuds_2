
import java.util.*;

public class LC19_RemoveNth_Node_Clinic {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 節點數
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // 建立鏈結串列
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        int k = sc.nextInt(); // 要刪除的倒數第 k 個
        sc.close();

        ListNode head = dummy.next;
        head = removeNthFromEnd(head, k);

        // 輸出刪除後的串列
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

    // 刪除倒數第 k 個節點
    public static ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 k+1 步，讓 slow 停在要刪除節點的前一個
        for (int i = 0; i < k + 1; i++) {
            fast = fast.next;
        }

        // fast 和 slow 同步移動
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow.next
        slow.next = slow.next.next;
        return dummy.next;
    }
}
