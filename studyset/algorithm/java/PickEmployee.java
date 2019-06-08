package Algorithm;

import java.io.IOException;
import java.util.Scanner;

public class PickEmployee {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int total;
        String[] middle;
        int[] scores;
        int mid;
        int[] table = new int[32001];
        int max = 0;

        if (number < 1 || number > 10)
            return;

        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            total = Integer.parseInt(temp);

            if (total < 10 || total > 30000)
                return;

            temp = scanner.nextLine().trim();
            middle = temp.split(" ");

            scores = new int[total];
            for(int j = 0; j<middle.length; ++j){
                mid = Integer.parseInt(middle[j]);
                if (mid < 1 || mid > 32000)
                    return;

                scores[j] = mid;
                table[mid]++;
                if(max < mid)
                    max = mid;
            }

            findSolution(scores, total);
            findSolution2(table, max, scores, total);
        }
        scanner.close();
    }

    static void findSolution(int[] scores, int total){
        int[] result = new int[total];

        for(int i = 0; i<result.length; ++i){
            result[i]++;
        }

        for(int i = 0; i<scores.length; ++i){
            for(int j = 0; j<scores.length; ++j){
                if(scores[i] < scores[j]){
                    result[i]++;
                }
            }
        }

        for(int i : result){
            System.out.println(i);
        }
    }

    static void findSolution2(int[] table, int max, int[] scores, int total){
        int rank = 1;
        int temp;
        for(int i = max; i >= 0; --i){
            if(table[i] != 0){
                temp = table[i];
                table[i] = rank;
                rank += temp;
            }
        }
        for(int i = 0; i < total; ++i){
            System.out.printf("%d ", table[scores[i]]);
        }
    }
}
