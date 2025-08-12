
import java.util.ArrayList;
import java.util.Scanner;

public class BasicMinHeapPractice {

    private ArrayList<Integer> heap;

    public BasicMinHeapPractice() {
        heap = new ArrayList<>();
    }

    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }

    public int extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    public int getMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) < heap.get(parent)) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public ArrayList<Integer> getHeapList() {
        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BasicMinHeapPractice minHeap = new BasicMinHeapPractice();
        ArrayList<Integer> insertOrder = new ArrayList<>();

        System.out.println("輸入 'i' 進入插入模式，輸入 'end' 結束插入並顯示結果");

        while (true) {
            String cmd = sc.nextLine().trim().toLowerCase();

            if (cmd.equals("i")) {
                System.out.println("進入插入模式（輸入 end 結束）：");
                while (true) {
                    String input = sc.nextLine().trim().toLowerCase();
                    if (input.equals("end")) {
                        break;
                    }
                    try {
                        int val = Integer.parseInt(input);
                        minHeap.insert(val);
                        insertOrder.add(val);
                        System.out.println(val + " 已插入");
                    } catch (NumberFormatException e) {
                        System.out.println("請輸入數字或 end");
                    }
                }

                System.out.println("\n插入順序：" + insertOrder);

                System.out.println("Heap 狀態 (ArrayList)：" + minHeap.getHeapList());

                BasicMinHeapPractice tempHeap = new BasicMinHeapPractice();
                for (int num : insertOrder) {
                    tempHeap.insert(num);
                }
                ArrayList<Integer> sorted = new ArrayList<>();
                while (!tempHeap.isEmpty()) {
                    sorted.add(tempHeap.extractMin());
                }
                System.out.println("排序後結果：" + sorted);

                System.out.println("\n=== 功能選單 ===");
                System.out.println("m: 查看最小值");
                System.out.println("e: 取出最小值");
                System.out.println("s: 查看大小");
                System.out.println("p: 判斷是否為空");
                System.out.println("q: 離開程式");

            } else if (cmd.equals("m")) {
                try {
                    System.out.println("最小值為：" + minHeap.getMin());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (cmd.equals("e")) {
                try {
                    System.out.println("取出最小值：" + minHeap.extractMin());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (cmd.equals("s")) {
                System.out.println("Heap 大小：" + minHeap.size());
            } else if (cmd.equals("p")) {
                System.out.println(minHeap.isEmpty() ? "Heap 為空" : "Heap 不為空");
            } else if (cmd.equals("q")) {
                System.out.println("程式結束");
                break;
            } else {
                System.out.println("未知指令，請重新輸入");
            }
        }

        sc.close();
    }
}
