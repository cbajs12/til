package Algorithm;

import java.io.IOException;
import java.util.Scanner;

public class Apartment_ {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        int size;
        String[][] mold;
        int count;

        if (number < 1 || number > 20)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            size = Integer.parseInt(temp);

            if (size < 5 || size > 25)
                return;

            mold = new String[size][size];
            for(int j = 0; j < size; ++j){
                temp = scanner.nextLine().trim();
                mold[j] = temp.split(" ");
            }

            map = new int[size+2][size+2];
            for(int j = 1; j <= size; ++j){
                for(int k = 1; k <= size; ++k){
                    map[j][k] = Integer.parseInt(mold[j-1][k-1]);
                }
            }

            count = 0;
            for(int j = 1; j <= size; ++j){
                for(int k = 1; k <= size; ++k){
                    if(map[j][k] == 1){
                        count++;
                        findSolution(j,k);
                    }
                }
            }

            result[i] = count;
        }

        for (int i : result)
            System.out.println(i);

        scanner.close();
    }

    static void findSolution(int x, int y){
        map[x][y] = 0;

        if(map[x-1][y] == 1)
            findSolution(x-1, y);

        if(map[x][y-1] == 1)
            findSolution(x, y-1);

        if(map[x+1][y] == 1)
            findSolution(x+1, y);

        if(map[x][y+1] == 1)
            findSolution(x, y+1);
    }
}
