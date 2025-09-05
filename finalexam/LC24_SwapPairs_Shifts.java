
import java.util.*;

public class LC24_SwapPairs_Shifts {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();
        sc.close();

        // 構建鏈結串列
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        if (!line.isEmpty()) {
            String[] parts = line.split("\\s+");
            for (String p : parts) {
                curr.next = new ListNode(Integer.parseInt(p));
                curr = curr.next;
            }
        }

        ListNode head = swapPairs(dummy.next);

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

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換 a 和 b1 2 
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // 移動 prev
            prev = a;
        }
        return dummy.next;
    }
}
