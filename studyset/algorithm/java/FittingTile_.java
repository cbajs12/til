package Algorithm;

import java.io.IOException;
import java.util.Scanner;

public class FittingTile_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int row, rest;
        int[] result;

        if (number < 1 || number > 10)
            return;

        result = new int[number];

        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            row = Integer.parseInt(temp);
            if (row < 1 || row > 100000)
                return;

            temp = scanner.nextLine().trim();
            rest = Integer.parseInt(temp);
            if (rest < 1 || rest > 40000)
                return;

            result[i] = findSolution(row, rest);
        }

        for (int i : result) {
            System.out.println(i);
        }

        scanner.close();
    }

    static int findSolution(int row, int rest){
        int partOne, partTwo, partThree = 0;
        int answer;

        if(row == 1) {
            answer = 2 % rest;
        }else if(row == 2){
            answer = 3 % rest;
        }else{
            partOne = 1;
            partTwo = 3;

            for(int i=2; i<row; ++i){
                partThree = (partTwo + 2*partOne) % rest;
                partOne = partTwo;
                partTwo = partThree;
            }
            answer = partThree;
        }

        return answer;

    }
}
