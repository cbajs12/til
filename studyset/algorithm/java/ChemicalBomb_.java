package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class ChemicalBomb_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        int row, column;
        String[] data;
        String[][] mold;
        String[] point;
        int[][] matrix;
        int mid, x, y;

        if (number < 1 || number > 30)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            data = temp.split(" ");
            row = Integer.parseInt(data[0]);
            column = Integer.parseInt(data[1]);

            if (row < 1 || row > 100)
                return;
            if (column < 1 || column > 100)
                return;

            mold = new String[column][row];
            for(int j = 0; j < column; ++j){
                temp = scanner.nextLine().trim();
                mold[j] = temp.split(" ");
            }

            matrix = new int[column+2][row+2];
            for(int j = 1; j <= column; ++j){
                for(int k = 1; k <= row; ++k){
                    matrix[j][k] = Integer.parseInt(mold[j-1][k-1]);
                }
            }

            temp = scanner.nextLine().trim();
            point = temp.split(" ");
            x = Integer.parseInt(point[1]);
            y = Integer.parseInt(point[0]);
            matrix[x][y] = 2;
            mid = 2;

            result[i] = findSolution(matrix, mid, column, row);
        }
        for (int i : result)
            System.out.println(i);

        scanner.close();
    }

    static int findSolution(int[][] matrix, int result, int column, int row){
        boolean flag;
        while(true){
            flag = true;
            for(int i = 1; i<=column; ++i){
                for(int j = 1; j<=row; ++j){
                    if(matrix[i][j] == result){
                        if((matrix[i-1][j] == 1) || (matrix[i][j-1] == 1) || (matrix[i+1][j] == 1) || (matrix[i][j+1] == 1))
                            flag = false;

                        if(matrix[i-1][j] == 1)
                            matrix[i-1][j] = result + 1;

                        if(matrix[i][j-1] == 1)
                            matrix[i][j-1] = result + 1;

                        if(matrix[i+1][j] == 1)
                            matrix[i+1][j] = result + 1;

                        if(matrix[i][j+1] == 1)
                            matrix[i][j+1] = result + 1;


//                        for(int t = 1; t <= column; ++t) {
//                            for (int k = 1; k <= row; ++k) {
//                                System.out.printf("%d ", matrix[t][k]);
//                            }
//                            System.out.printf("\n");
//                        }
//                        System.out.printf("\n");
                    }
                }
            }

            if(flag){
                break;
            }else{
                result++;
            }

        }
        return result-1;
    }
}
