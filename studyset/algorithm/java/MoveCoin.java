package Algorithm;

import java.io.IOException;
import java.util.Scanner;

public class MoveCoin {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine().trim();
        int number = Integer.parseInt(num);
        String[] temp;
        int[] tempArray;
        int tempNumber;
        int[] result;

        if(number < 1 && number > 30)
            return;

        result = new int[number];


        for(int i=0; i < number; ++i){
            num = scanner.nextLine().trim();
            tempNumber = Integer.parseInt(num);

            if(tempNumber < 2 && tempNumber > 320000)
                return;

            temp = new String[tempNumber];
            tempArray = new int[tempNumber];

            for(int j=0; j < tempNumber; ++j){
                temp[j] = scanner.nextLine().trim();
                tempArray[j] = Integer.parseInt(temp[j]);
            }

            result[i] = findSolution(tempArray);
        }

        for(int i=0; i<number; ++i)
            System.out.println(result[i]);

        scanner.close();
    }

    static int findSolution(int[] number){
        int count = 0;
        int total = 0;
        int average;

        for(int i=0; i< number.length; ++i){
            total = total + number[i];
        }

        average = total/number.length;
//        System.out.println(average);

        for(int i=0; i< number.length; ++i){
            if(average > number[i]){
                number[i] = average - number[i];
            }else{
                number[i] = number[i] - average;
            }
        }

        for(int i=0; i<number.length; ++i) {
//            System.out.println(number[i]);
            count = count + number[i];
        }

//        System.out.println(count);

        if(count%2 !=0){
            count = -1;
        }else{
            count = count/2;
        }

        return count;
    }
}
