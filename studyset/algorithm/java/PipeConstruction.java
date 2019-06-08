package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class PipeConstruction {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        int total;
        int[][] pipes;
        String[][] map;

        if (number < 1 || number > 10)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            total = Integer.parseInt(temp);

            if (total < 3 || total > 100)
                return;

            map = new String[total][total];
            for(int j = 0; j < total; ++j){
                temp = scanner.nextLine().trim();
                map[j] = temp.split(" ");
            }

            pipes = new int[total][total];
            for(int j = 0; j < total; ++j){
                for(int k = 0; k < total; ++k){
                    pipes[j][k] = Integer.parseInt(map[j][k]);
                }
            }

            result[i] = findSolution(pipes, total);
        }

        for(int i : result)
            System.out.println(i);

        scanner.close();
    }

    static int findSolution(int[][] pipies, int total){
        int[] length = new int[total];
        int result = 0;

        for(int i = 0; i < pipies.length; ++i){
            for(int j = i; j < pipies.length; ++j){
                if(j == i)
                    continue;

                if(length[j] == 0){
                    length[j] = pipies[i][j];
                }

                if(length[j] > pipies[i][j]){
                    length[j] = pipies[i][j];
                }
            }
        }
        for(int i : length){
            result = result + i;
        }

        return result;
    }
}
