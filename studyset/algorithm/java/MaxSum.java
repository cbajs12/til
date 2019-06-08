package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class MaxSum {
    static int MIN = -99999;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        int tempInt;
        String[] strArray;
        int[] intArray;

        if (number < 1 || number > 10)
            return;

        result = new int[number];

        for(int i = 0; i < number; ++i){
            temp = scanner.nextLine().trim();
            tempInt = Integer.parseInt(temp);

            if (tempInt < 1 || tempInt > 10000)
                return;

            temp = scanner.nextLine().trim();
            strArray = temp.split(" ");
            intArray = new int[strArray.length];
            for(int j=0; j < strArray.length; ++j){
                intArray[j] = Integer.parseInt(strArray[j]);
            }

            result[i] = findSolution(intArray);
        }

        for(int i : result){
            System.out.println(i);
        }

        scanner.close();
    }

    static int findSolution(int[] array){
        int result, sum=0;

        result = MIN;

        for(int i=0; i<array.length; ++i){
            sum = sum + array[i];

            if(result < sum){
                result = sum;
            }

            if(sum < 0){
                sum = 0;
            }
        }
        return result;
    }
}
