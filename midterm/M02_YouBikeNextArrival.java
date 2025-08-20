import java.util.Scanner;

public class M02_YouBikeNextArrival {

    // 轉成自 00:00 起的分鐘數
    private static int timeToMinutes(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }

    // 換回 HH:mm 格式
    private static String minutesToTime(int minutes) {
        int hour = minutes / 60;
        int minute = minutes % 60;
        return String.format("%02d:%02d", hour, minute);
    }

    private static int findNextGreater(int[] sortedArr, int target) {
        int left = 0;
        int right = sortedArr.length - 1;
        int resultIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedArr[mid] > target) {
                // 如果中間值大於目標，這可能是我們要找的答案
                // 但為了找到第一個大於的，我們還需要往左邊繼續找
                resultIndex = mid;
                right = mid - 1;
            } else {
                // 如果中間值小於或等於目標，說明目標在右半部分
                left = mid + 1;
            }
        }
        return resultIndex;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        int[] arrivalTimes = new int[n];
        for (int i = 0; i < n; i++) {
            arrivalTimes[i] = timeToMinutes(scanner.nextLine());
        }

        String queryTimeStr = scanner.nextLine();
        int queryMinutes = timeToMinutes(queryTimeStr);

        int nextIndex = findNextGreater(arrivalTimes, queryMinutes);

        if (nextIndex != -1) {
            System.out.println(minutesToTime(arrivalTimes[nextIndex]));
        } else {
            System.out.println("No bike");
        }

        scanner.close();
    }
}