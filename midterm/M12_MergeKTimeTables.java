
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class M12_MergeKTimeTables {

    static class TimeEntry {

        int time;
        int listIndex;
        int timeIndex;

        public TimeEntry(int time, int listIndex, int timeIndex) {
            this.time = time;
            this.listIndex = listIndex;
            this.timeIndex = timeIndex;
        }
    }

    public static List<Integer> mergeKLists(List<List<Integer>> lists) {
        PriorityQueue<TimeEntry> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.time)
        );

        // 將每個列表的第一個元素放入堆中
        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                minHeap.offer(new TimeEntry(lists.get(i).get(0), i, 0));
            }
        }

        List<Integer> result = new ArrayList<>();

        // 只要堆不為空，就重複取最小元素並將其下一個元素推入
        while (!minHeap.isEmpty()) {
            TimeEntry current = minHeap.poll();
            result.add(current.time);

            // 檢查同一個列表中是否還有下一個元素
            int nextTimeIndex = current.timeIndex + 1;
            if (nextTimeIndex < lists.get(current.listIndex).size()) {
                minHeap.offer(new TimeEntry(
                        lists.get(current.listIndex).get(nextTimeIndex),
                        current.listIndex,
                        nextTimeIndex
                ));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            int len = scanner.nextInt();
            List<Integer> currentList = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                currentList.add(scanner.nextInt());
            }
            lists.add(currentList);
        }

        List<Integer> mergedList = mergeKLists(lists);

        for (int time : mergedList) {
            System.out.print(time + " ");
        }
        System.out.println();

        scanner.close();
    }
}
