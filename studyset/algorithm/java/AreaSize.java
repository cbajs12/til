package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class AreaSize {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        double[] result;
        int total;
        double[][] matrix;
        String[] data;

        if (number < 1 || number > 10)
            return;

        result = new double[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            total = Integer.parseInt(temp);

            if (total < 3 || total > 100)
                return;

            matrix = new double[total+1][2];
            for(int j = 1; j <= total; ++j){
                temp = scanner.nextLine().trim();
                data = temp.split(" ");
                matrix[j][0] = Double.parseDouble(data[0]);
                matrix[j][1] = Double.parseDouble(data[1]);
            }

            result[i] = findSolution(matrix, total);

        }

        for(double i : result)
            System.out.println(i);

        scanner.close();
    }

    static double findSolution(double[][] matrix, int total){
        double result = 0.0;
        int temp = 1;
        double tempX, tempY;

        for(int i = 2; i<=total; ++i){
            if(matrix[temp][1] - matrix[i][1] > 0 || (matrix[temp][1] - matrix[i][1] == 0 && matrix[temp][0] > matrix[i][0]))
                temp = i;
        }

        tempX = matrix[temp][0];
        tempY = matrix[temp][1];
        matrix[temp] = matrix[1];
        matrix[1][0] = tempX;
        matrix[1][1] = tempY;

        for(int i = 2; i < total; ++i){
            temp = i;
        }

        return result;
    }
}
