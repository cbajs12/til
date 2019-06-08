package Algorithm;


import java.io.IOException;
import java.util.Scanner;

import static sun.swing.MenuItemLayoutHelper.max;

public class PointSkiing_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        int total;
        String[][] tempString;
        int[][] pointTable;

        if (number < 1 || number > 30)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            total = Integer.parseInt(temp);

            if (total < 1 || total > 1000)
                return;

            tempString = new String[total][total];
            pointTable = new int[total+1][total+1];

            for(int j = 0; j < total; ++j){
                temp = scanner.nextLine().trim();
                tempString[j] = temp.split(" ");
            }

            for(int j = 0; j < total; ++j){
                for(int k = 0; k < tempString[j].length; ++k){
                    pointTable[j][k] = Integer.parseInt(tempString[j][k]);

                    if (pointTable[j][k] < 0 || pointTable[j][k] > 100)
                        return;
                }
            }

            result[i] = findSolution(pointTable, total);
        }

        for(int i : result)
            System.out.println(i);

        scanner.close();
    }

    static int findSolution(int[][] points, int total){
        for(int j = total -1; j >= 0; --j){
            for(int k = 0; k <= j; ++k){
                points[j][k] += max(points[j+1][k], points[j+1][k+1]);
            }
        }

        return points[0][0];
    }
}
