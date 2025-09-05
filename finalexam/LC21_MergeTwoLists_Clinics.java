
import java.util.*;

public class LC21_MergeTwoLists_Clinics {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        // 建立第一條鏈結串列
        ListNode dummy1 = new ListNode(0);
        ListNode curr = dummy1;
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }

        // 建立第二條鏈結串列
        ListNode dummy2 = new ListNode(0);
        curr = dummy2;
        for (int i = 0; i < m; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        sc.close();

        ListNode head1 = dummy1.next;
        ListNode head2 = dummy2.next;

        // 合併兩條串列
        ListNode merged = mergeTwoLists(head1, head2);

        // 輸出結果
        curr = merged;
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

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // 接上剩餘節點
        if (l1 != null) {
            tail.next = l1;
        } else if (l2 != null) {
            tail.next = l2;
        }

        return dummy.next;
    }
}
