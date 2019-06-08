package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class HackerChallenge_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        String[] result;

        if (number < 1 || number > 10)
            return;

        result = new String[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim().toUpperCase();
            result[i] = findSolution(temp);
        }

        for(String i : result){
            System.out.println(i);
        }

        scanner.close();
    }

    static String findSolution(String test){
        String[] buffer = test.split("");
        boolean flag = false;
        int temp;
        for(int i=1; i < buffer.length; ++i){
            for(int j=0; i+j < buffer.length; ++j){
                temp = i;
                while(temp-- != 0){
                    if(buffer[j].equals(buffer[i+j])){
                        flag = true;
                    }else{
                        flag = false;
                        break;
                    }
                    ++j;
                }
                if(flag)
                    break;
            }
            if(flag)
                break;
        }

        if(flag){
            return "Rejected";
        }else{
            return "Accepted";
        }
    }

    static int countString(char str[]){
        int counter = 0;

        for(int i =0; i < 10001; ++i){
            if((str[i] >= 'a' && str[i] <='z') || (str[i] >= 'A' && str[i] <= 'Z')){
                counter++;
            }else {
                break;
            }
        }
        return counter;
    }
}
