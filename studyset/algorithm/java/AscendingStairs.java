package Algorithm;

import java.io.IOException;
import java.util.Scanner;

public class AscendingStairs {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int local;
        int[] locals;
        int[] result;

        if (number < 1 || number > 20)
            return;

        result = new int[number];

        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            local = Integer.parseInt(temp);

            if (local < 1 || local > 300)
                return;

            locals = new int[local];
            for (int j = 0; j < local; ++j) {
                temp = scanner.nextLine().trim();
                locals[j] = Integer.parseInt(temp);
                if (locals[j] < 1 || locals[j] > 100000)
                    return;
            }

            result[i] = findSolution(locals);
        }

        for (int i : result) {
            System.out.println(i);
        }

        scanner.close();
    }


    static int findSolution(int[] locals){
        int[] temp = new int[locals.length];
        int one, two;

        temp[0] = 0;
        temp[1] = locals[0];
        temp[2] = locals[0] + locals[1];

        for(int i=3; i<locals.length; ++i){
            one = locals[i] + locals[i-1] + temp[i-2];
            two = locals[i] + temp[i-1];

            if(one > two){
                temp[i] = one;
            }else{
                temp[i] = two;
            }
            System.out.println(temp[i]);
        }
        return temp[locals.length-1];
    }
}
