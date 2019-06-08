package Algorithm;

import java.io.IOException;
import java.util.Scanner;

public class RanchFence_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int total;
        float[][] matrix;
        String[] coordinate;

        if (number < 1 || number > 10)
            return;

        for (int i = 0; i < number; ++i) {

            temp = scanner.nextLine().trim();
            total = Integer.parseInt(temp);
            if (total < 1 || total > 500)
                return;

            matrix = new float[total][2];
            for(int j = 0; j < total; ++j){
                temp = scanner.nextLine().trim();
                coordinate = temp.split(" ");
                matrix[j][0] = Float.parseFloat(coordinate[0]);
                matrix[j][1] = Float.parseFloat(coordinate[1]);
            }
            findSolution(total, matrix);
        }

        scanner.close();
    }

    static void findSolution(int total, float[][] matrix){
        int temp = 0;
        float maxRatio;
        float ratio;
        int i=1;

        System.out.printf("%d ", i);
        while(i < total){
            maxRatio = -10000;

            for(int j=i+1; j<=total; ++j){
                ratio = (matrix[j-1][1] - matrix[i-1][1]) / (matrix[j-1][0] - matrix[i-1][0]);

                if(ratio > maxRatio){
                    maxRatio = ratio;
                    temp = j;
                }
            }

            i = temp;
            System.out.printf("%d ", temp);

            if(i == total)
                break;
        }

        System.out.printf("\n");
    }
}
