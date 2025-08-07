public class GradeStatisticsSystem {
    public static void main(String[] args) {
        // input data
        int[] grades = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        // 1. 計算學生成績的平均值、最高分、最低分
        int sum = 0;
        int maxGrade = grades[0];
        int minGrade = grades[0];

        for (int grade : grades) {
            sum += grade;
            if (grade > maxGrade) {
                maxGrade = grade;
            }
            if (grade < minGrade) {
                minGrade = grade;
            }
        }
        
        double average = (double) sum / grades.length;

        // 2. 統計各等第（A、B、C、D、F）的人數
        int countA = 0; // 90-100
        int countB = 0; // 80-89
        int countC = 0; // 70-79
        int countD = 0; // 60-69
        int countF = 0; // 0-59

        for (int grade : grades) {
            if (grade >= 90) {
                countA++;
            } else if (grade >= 80) {
                countB++;
            } else if (grade >= 70) {
                countC++;
            } else if (grade >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        // 3. 找出高於平均分的學生人數
        int aboveAverageCount = 0;
        for (int grade : grades) {
            if (grade > average) {
                aboveAverageCount++;
            }
        }

        // 4. 列印完整的成績報表
        System.out.println("\n========================");
        System.out.println("====== 成績報表 =======");
        System.out.println("========================");
        System.out.printf("  平均分: %.2f\n", average);
        System.out.println("  最高分: " + maxGrade);
        System.out.println("  最低分: " + minGrade);
        System.out.println("------------------------");
        System.out.println("  等第人數統計:");
        System.out.println("    A (90-100): " + countA + " 人");
        System.out.println("    B (80-89):  " + countB + " 人");
        System.out.println("    C (70-79):  " + countC + " 人");
        System.out.println("    D (60-69):  " + countD + " 人");
        System.out.println("    F (0-59):   " + countF + " 人");
        System.out.println("------------------------");
        System.out.println("  高於平均分的學生人數: " + aboveAverageCount + " 人");
        System.out.println("------------------------");
        System.out.println("  各學生成績:");
        for (int i = 0; i < grades.length; i++) {
            System.out.printf("    學生 %d: %d 分\n", i + 1, grades[i]);
        }
        System.out.println("========================");
    }
}