package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class FastFood_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        String[] clues;
        int ticket, member, count;
        String[][] lines;
        int[][] line;

        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            clues = temp.split(" ");
            ticket = Integer.parseInt(clues[0]);
            member = Integer.parseInt(clues[1]);
            count = Integer.parseInt(clues[2]);

            lines = new String[count][ticket];
            for(int j = 0; j < count; ++j){
                temp = scanner.nextLine().trim();
                lines[j] = temp.split(" ");
            }

            line = new int[count][ticket];
            for(int j = 0; j < count; ++j){
                for(int k = 0; k < count; ++k){
                    line[j][k] = Integer.parseInt(lines[j][k]);
                }
            }

            findSolution(ticket, member, count, line);
        }
        scanner.close();
    }

    static void findSolution(int ticket, int member, int count, int[][] lines){
        int[] result = new int[ticket];
        int k;

        for(int j = 0; j < count; ++j){
            result[j] = 1;
            k = member;
            for(int i = 0; i < ticket; ++i){
                if((i < k) && (lines[j][i] > k)){
                    result[j] = 0;
                    i = ticket + 1;
                }
                k++;
            }

        }
        for(int i=0; i<count; ++i){
            System.out.printf("%d",result[i]);
        }

    }
}
