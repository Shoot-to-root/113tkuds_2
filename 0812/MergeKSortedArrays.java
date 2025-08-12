import java.util.*;

class Element {
    int value;        // 當前值
    int arrayIndex;   // 來自第幾個陣列
    int elementIndex; // 在該陣列中的索引

    public Element(int value, int arrayIndex, int elementIndex) {
        this.value = value;
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }
}

public class MergeKSortedArrays {

    public static int[] mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));

        // 每個陣列的第一個元素放進 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        List<Integer> resultList = new ArrayList<>();

        // 每次取出最小值，然後將該陣列的下一個元素加入 heap
        while (!minHeap.isEmpty()) {
            Element e = minHeap.poll();
            resultList.add(e.value);

            int nextIndex = e.elementIndex + 1;
            if (nextIndex < arrays[e.arrayIndex].length) {
                minHeap.offer(new Element(arrays[e.arrayIndex][nextIndex], e.arrayIndex, nextIndex));
            }
        }

        // 轉成 int[]
        return resultList.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[][][] testCases = {
            { {1,4,5}, {1,3,4}, {2,6} },
            { {1,2,3}, {4,5,6}, {7,8,9} },
            { {1}, {0} }
        };

        for (int[][] arrays : testCases) {
            System.out.print("輸入：[");
            for (int i = 0; i < arrays.length; i++) {
                System.out.print(Arrays.toString(arrays[i]));
                if (i < arrays.length - 1) System.out.print(", ");
            }
            System.out.println("]");

            int[] merged = mergeKSortedArrays(arrays);
            System.out.println("輸出：" + Arrays.toString(merged));
            System.out.println();
        }
    }
}
