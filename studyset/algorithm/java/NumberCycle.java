package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class NumberCycle {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        String[] tempArray;
        int n, p;
        int[] result;

        if (number < 1 && number > 30)
            return;

        result = new int[number];

        for(int i = 0; i < number; ++i){
            temp = scanner.nextLine().trim();
            tempArray = temp.split(" ");

            n = Integer.parseInt(tempArray[0]);
            if (n < 2 || n > 9999)
                return;

            p = Integer.parseInt(tempArray[1]);
            if (p < 2 || p > 5)
                return;

            result[i] = findSolution(n,p);
        }


        for(int i : result){
            System.out.println(i);
        }

        scanner.close();
    }

    static int findSolution(int n, int p){
        int[] tempArray = new int[10000];
        int[] resultArray = new int[300000];
        int length = 0;

        tempArray[0] = n;
        resultArray[tempArray[0]]++;

        for(int i=1; i<tempArray.length; ++i){
            tempArray[i] = calculating(tempArray[i-1],p);
            resultArray[tempArray[i]]++;
        }

        for(int i=0; i<tempArray.length; ++i){
            if(resultArray[tempArray[i]] >= 2){
                for(int j : resultArray){
                    if(j == 1){
                        length++;
                    }
                }
                return length;
            }
        }

        return length;
    }

    static int calculating(int n, int p){
        int[] number = new int[4];
        int i =0;
        boolean flag = true;
        int[] resultArray = new int[4];
        int result = 0;
        while(flag){
            if(n/10 == 0){
                number[i] = n%10;
                flag=false;
            }else{
                number[i] = n%10;
                n = n/10;
            }
            i++;
        }

        for(int j = 0; j < number.length; ++j){
            resultArray[j] = number[j];
        }

        while(p > 1){
            for(int j = 0; j <number.length; ++j){
                resultArray[j] = resultArray[j] * number[j];
            }
            p--;
        }

        for(int j=0; j < resultArray.length; ++j){
            result = result + resultArray[j];
        }

        return result;
    }
}
