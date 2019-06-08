package Algorithm;

import java.io.IOException;
import java.util.Scanner;

public class SquareNumber_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        int tempInt;

        if (number < 1 || number > 30)
            return;

        result = new int[number];

        for(int i = 0; i < number; ++i){
            temp = scanner.nextLine().trim();
            tempInt = Integer.parseInt(temp);

            if (tempInt < 2 || tempInt > 30)
                return;

            result[i] = findSolution(tempInt);
        }

        for(int i : result){
            System.out.println(i);
        }

        scanner.close();
    }

    static int findSolution(int base){
        int[] result = new int[300];
        int count = 0;

        for(int i=1; i<=300; ++i){
            result[i-1] = calculating(i*i, base);
        }

        for(int i=0; i<300; ++i){
            if(result[i] == 1){
                count++;
            }
        }

        return count;
    }

    static int calculating(int original, int base){
        int temp, reverse;
        temp = original;
        reverse = 0;

        while (temp != 0){
            reverse = (reverse * base + (temp%base));
            temp = temp/base;
//            System.out.printf("%d : %d \n",reverse, temp);
        }

        if(reverse == original){
//            System.out.printf("original : %d , reverse : %d \n",original, reverse);
            return 1;
        }
        return 0;
    }

//    static int calculating(int n, int base){
//        int count = 0;
//        int[] number = new int[20];
//        boolean flag = true;
//        int[] reverse;
//        int[] original;
//        int k, result;
//
//        while(flag){
//            if(n/base == 0){
//                number[count] = n%base;
//                flag=false;
//            }else{
//                number[count] = n%base;
//                n = n/10;
//            }
//            count++;
//        }
//
//        reverse = new int[count];
//        original = new int[count];
//        k = count-1;
//        result = 0;
//
//        for(int i=0; i<count; ++i){
//            reverse[i] = number[i];
//            original[i] = number[k];
//            k--;
//        }
//
//        for(int i=0; i<count; ++i){
//            if(reverse[i] != original[i]){
//                result=0;
//                break;
//            }
//            result++;
//        }
//
//        if(result!=0){
//            result=1;
//        }
//
//        return result;
//
//    }
}
