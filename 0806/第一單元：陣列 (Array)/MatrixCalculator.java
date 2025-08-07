public class MatrixCalculator {

    public static void printMatrix(int[][] matrix) {
        if (matrix == null) {
            System.out.println("  矩陣為空 (null)。");
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // 矩陣加法：計算兩個相同維度矩陣的和
    public static int[][] addMatrices(int[][] matrixA, int[][] matrixB) {
        if (matrixA == null || matrixB == null || matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            System.out.println("錯誤：矩陣加法需要兩個相同維度的矩陣。");
            return null;
        }
        
        int rows = matrixA.length;
        int cols = matrixA[0].length;
        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return result;
    }

    // 矩陣乘法：計算兩個可相乘矩陣的乘積
    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        if (matrixA == null || matrixB == null || matrixA[0].length != matrixB.length) {
            System.out.println("錯誤：矩陣乘法的維度不相容 (Matrix A 的列數必須等於 Matrix B 的行數)。");
            return null;
        }
        
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;
        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }

    // 矩陣轉置：將行列互換
    public static int[][] transposeMatrix(int[][] matrix) {
        if (matrix == null) {
            System.out.println("錯誤：無法轉置一個 null 矩陣。");
            return null;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    // 尋找矩陣中的最大值和最小值
    public static int[] findMinMax(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            System.out.println("錯誤：無法在 null 或空矩陣中尋找最大/最小值。");
            return null;
        }
        int max = matrix[0][0];
        int min = matrix[0][0];

        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
                if (value < min) {
                    min = value;
                }
            }
        }
        return new int[]{max, min};
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}};
        int[][] matrix2 = {{7, 8, 9}, {10, 11, 12}};
        int[][] matrix3 = {{1, 2}, {3, 4}, {5, 6}};

        System.out.println("====== 初始矩陣 ======");
        System.out.println("Matrix 1 (2x3):");
        printMatrix(matrix1);
        System.out.println("\nMatrix 2 (2x3):");
        printMatrix(matrix2);
        System.out.println("\nMatrix 3 (3x2):");
        printMatrix(matrix3);
        System.out.println();

        System.out.println("====== 矩陣加法 (Matrix 1 + Matrix 2) ======");
        int[][] sumMatrix = addMatrices(matrix1, matrix2);
        printMatrix(sumMatrix);
        
        // Error case
        System.out.println("\n====== Error case: 矩陣加法 (Matrix 1 + Matrix 3) ======");
        int[][] invalidSum = addMatrices(matrix1, matrix3);
        printMatrix(invalidSum);

        System.out.println("\n====== 矩陣乘法 (Matrix 1 * Matrix 3) ======");
        int[][] productMatrix1 = multiplyMatrices(matrix1, matrix3);
        printMatrix(productMatrix1);

        System.out.println("\n====== 矩陣乘法 (Matrix 3 * Matrix 1) ======");
        int[][] productMatrix2 = multiplyMatrices(matrix3, matrix1);
        printMatrix(productMatrix2);
        
        // Error case
        System.out.println("\n====== Error case: 矩陣乘法 (Matrix 1 * Matrix 2) ======");
        int[][] invalidProduct = multiplyMatrices(matrix1, matrix2);
        printMatrix(invalidProduct);

        System.out.println("\n====== 矩陣轉置 (Matrix 1) ======");
        int[][] transposedMatrix1 = transposeMatrix(matrix1);
        printMatrix(transposedMatrix1);
        
        System.out.println("\n====== 矩陣轉置 (Matrix 2) ======");
        int[][] transposedMatrix2 = transposeMatrix(matrix2);
        printMatrix(transposedMatrix2);
        
        System.out.println("\n====== 矩陣轉置 (Matrix 3) ======");
        int[][] transposedMatrix3 = transposeMatrix(matrix3);
        printMatrix(transposedMatrix3);

        System.out.println("\n====== 尋找最大值和最小值 (Matrix 1) ======");
        int[] minMax1 = findMinMax(matrix1);
        System.out.println("  最大值: " + minMax1[0]);
        System.out.println("  最小值: " + minMax1[1]);
        
        System.out.println("\n====== 尋找最大值和最小值 (Matrix 2) ======");
        int[] minMax2 = findMinMax(matrix2);
        System.out.println("  最大值: " + minMax2[0]);
        System.out.println("  最小值: " + minMax2[1]);
        
        System.out.println("\n====== 尋找最大值和最小值 (Matrix 3) ======");
        int[] minMax3 = findMinMax(matrix3);
        System.out.println("  最大值: " + minMax3[0]);
        System.out.println("  最小值: " + minMax3[1]);
    }
}