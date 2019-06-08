package Algorithm;

import java.io.IOException;
import java.util.Scanner;

public class SymmetricMatrix_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int length;
        String[] data;

        if (number < 1 || number > 10)
            return;

        for(int i = 0; i < number; ++i){
            temp = scanner.nextLine().trim();
            length = Integer.parseInt(temp);

            if(length < 4 || length > 128)
                return;
            if(length%2 != 0)
                return;

            temp = scanner.nextLine().trim();
            data = temp.split(" ");

            if(length != data.length)
                return;

            findSolution(length, data);
        }

        scanner.close();
    }

    static void findSolution(int length, String[] data){
        String[] matrix = new String[length*length];
        String c;
        int i = 0;

        while(i < matrix.length){
            for(int j=0; j < data.length; ++j){
                matrix[i] = data[j];
                i++;
            }
        }

        for(i=0; i < data.length; ++i){
            for(int j=0; j < data.length; ++j){
                c = matrix[i^j];
                if(j == data.length - 1 ) {
                    System.out.printf("%s\n", c);
                }else{
                    System.out.printf("%s", c);
                }
            }
        }
    }
}
