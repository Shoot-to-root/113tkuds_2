import java.util.*;

public class MeetingRoomScheduler {

    // 最少需要多少個會議室
    public static int minMeetingRooms(int[][] meetings) {
        // 先按開始時間排序
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // 用 Min Heap 存每個會議室的結束時間
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int[] meeting : meetings) {
            // 如果最早結束的會議已經結束，可以釋放會議室
            if (!heap.isEmpty() && heap.peek() <= meeting[0]) {
                heap.poll();
            }
            // 分配會議室（放入結束時間）
            heap.offer(meeting[1]);
        }
        // 4. Heap 大小就是需要的會議室數
        return heap.size();
    }

    // 如果只有 N 個會議室，求如何安排會議使總會議時間最大
    public static int maxTotalTimeNRooms(int[][] meetings, int N, List<int[]> chosen) {
        // 按結束時間排序（使用貪心演算法選擇最早結束的會議）
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);

        // Min Heap 存每個會議室的最後結束時間
        PriorityQueue<int[]> rooms = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int totalTime = 0;

        for (int[] meeting : meetings) {
            // 如果有會議室空出來（最早結束的會議結束時間 <= 當前會議開始時間）
            if (!rooms.isEmpty() && rooms.peek()[1] <= meeting[0]) {
                rooms.poll(); // 釋放會議室
                rooms.offer(meeting); // 分配新會議
                chosen.add(meeting); 
                totalTime += meeting[1] - meeting[0]; 
            }
            // 還有空會議室可以直接安排
            else if (rooms.size() < N) {
                rooms.offer(meeting);
                chosen.add(meeting);
                totalTime += meeting[1] - meeting[0];
            }
            // 否則，跳過（因為會議室滿了且會議時間重疊）
        }
        return totalTime;
    }

    public static void main(String[] args) {

        int[][] meetings1 = {{0,30},{5,10},{15,20}};
        System.out.println("會議：" + Arrays.deepToString(meetings1) +
                " → 答案：需要" + minMeetingRooms(meetings1) + "個會議室");

        int[][] meetings2 = {{9,10},{4,9},{4,17}};
        System.out.println("會議：" + Arrays.deepToString(meetings2) +
                " → 答案：需要" + minMeetingRooms(meetings2) + "個會議室");

        int[][] meetings3 = {{1,5},{8,9},{8,9}};
        System.out.println("會議：" + Arrays.deepToString(meetings3) +
                " → 答案：需要" + minMeetingRooms(meetings3) + "個會議室");

        int[][] meetings4 = {{1,4},{2,3},{4,6}};
        List<int[]> chosen1 = new ArrayList<>();
        int maxTime1 = maxTotalTimeNRooms(meetings4, 1, chosen1);
        System.out.print("如果只有1個會議室，會議：" + Arrays.deepToString(meetings4));
        System.out.print(" → 最佳安排：選擇");
        for (int[] m : chosen1) {
            System.out.print(Arrays.toString(m));
        }
        System.out.println("，總時間 = " + maxTime1);

        int[][] meetings5 = {{1,4},{2,3},{4,6},{5,7},{6,8}};
        List<int[]> chosen2 = new ArrayList<>();
        int maxTime2 = maxTotalTimeNRooms(meetings5, 2, chosen2);
        System.out.print("如果有2個會議室，會議：" + Arrays.deepToString(meetings5));
        System.out.print(" → 最佳安排：選擇");
        for (int[] m : chosen2) {
            System.out.print(Arrays.toString(m));
        }
        System.out.println("，總時間 = " + maxTime2);
    }
}
