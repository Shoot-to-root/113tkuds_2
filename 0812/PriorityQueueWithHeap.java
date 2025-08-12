import java.util.PriorityQueue;
import java.util.Comparator;

class Task {
    String name;
    int priority;
    long timestamp; 

    public Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }
}

public class PriorityQueueWithHeap {
    private PriorityQueue<Task> pq;
    private long timeCounter;

    public PriorityQueueWithHeap() {
        // 優先級高的先出，若相同優先級則先插入的先出
        pq = new PriorityQueue<>(Comparator
                .comparingInt((Task t) -> t.priority).reversed()
                .thenComparingLong(t -> t.timestamp));
        timeCounter = 0;
    }

    public void addTask(String name, int priority) {
        pq.offer(new Task(name, priority, timeCounter++));
    }

    public Task executeNext() {
        if (pq.isEmpty()) throw new IllegalStateException("佇列為空");
        return pq.poll();
    }

    public Task peek() {
        if (pq.isEmpty()) throw new IllegalStateException("佇列為空");
        return pq.peek();
    }

    public void changePriority(String name, int newPriority) {
        Task target = null;
        for (Task t : pq) {
            if (t.name.equals(name)) {
                target = t;
                break;
            }
        }
        if (target != null) {
            pq.remove(target); // 因為 PriorityQueue 沒有直接支援改變優先級，所以 changePriority 要透過「移除並重新加入」來重建 Heap
            pq.offer(new Task(name, newPriority, target.timestamp));
        } else {
            System.out.println("找不到任務：" + name);
        }
    }

    public boolean isEmpty() {
        return pq.isEmpty();
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        String[][] tasks = {
            {"備份", "1"},
            {"緊急修復", "5"},
            {"更新", "3"}
        };

        System.out.print("添加：");
        for (int i = 0; i < tasks.length; i++) {
            String name = tasks[i][0];
            int priority = Integer.parseInt(tasks[i][1]);
            pq.addTask(name, priority);
            System.out.print("(\"" + name + "\", " + priority + ")");
            if (i < tasks.length - 1) System.out.print(", ");
        }
        System.out.println();

        System.out.print("執行順序：");
        while (!pq.isEmpty()) {
            System.out.print(pq.executeNext().name);
            if (!pq.isEmpty()) System.out.print(" → ");
        }
        System.out.println();
    }
}
