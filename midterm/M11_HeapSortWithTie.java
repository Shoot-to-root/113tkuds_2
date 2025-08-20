
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class M11_HeapSortWithTie {

    static class Student {

        int score;
        int index;

        public Student(int score, int index) {
            this.score = score;
            this.index = index;
        }
    }

    /**
     * Time Complexity: O(n log n) 說明：建堆操作為 O(n)。從堆中取出 n 個元素，每次操作為 O(log n)。
     * 因此，總時間複雜度為 O(n + n * log n) = O(n log n)。
     */
    public static Student[] heapSort(Student[] students) {
        Comparator<Student> comparator = (s1, s2) -> {
            if (s1.score != s2.score) {
                // 分數不同時，分數高的優先
                return Integer.compare(s2.score, s1.score);
            } else {
                // 分數相同時，索引小的優先
                return Integer.compare(s1.index, s2.index);
            }
        };

        PriorityQueue<Student> maxHeap = new PriorityQueue<>(comparator);

        // 建堆 (O(n))
        for (Student student : students) {
            maxHeap.offer(student);
        }

        // 依次從堆中取出元素並放入新陣列 (O(n log n))
        Student[] sortedStudents = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            sortedStudents[i] = maxHeap.poll();
        }

        // 因為從最大堆中取出的是遞減序列，需要反轉得到遞增序列
        Student[] result = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            result[i] = sortedStudents[students.length - 1 - i];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            students[i] = new Student(scanner.nextInt(), i);
        }

        Student[] sortedStudents = heapSort(students);

        for (Student student : sortedStudents) {
            System.out.print(student.score + " ");
        }
        System.out.println();

        scanner.close();
    }
}
