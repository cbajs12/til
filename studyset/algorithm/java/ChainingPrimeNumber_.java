package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class ChainingPrimeNumber_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] input;
        int tempInt;

        if (number < 1 || number > 10)
            return;

        input = new int[2];

        for(int i = 0; i < number; ++i){
            temp = scanner.nextLine().trim();
            tempInt = Integer.parseInt(temp);
            input[i] = tempInt;
        }

        for(int i = 0; i < number; ++i){
            if (input[i] < 1 || input[i] > 8)
                return;

            findSolution(input[i]);
        }

        scanner.close();
    }

    static void findSolution(int digit){
        int num;
        int x;
        int[][] prime = new int[8][1000];
        for(int i=0; i< digit; ++i) {
            if (i == 0) {
                if (prime[0][0] == 0) {
                    prime[0][0] = 2;
                    prime[0][1] = 3;
                    prime[0][2] = 5;
                    prime[0][3] = 7;
                }
            } else {
                x = 0;
                for (int j = 0; prime[i - 1][j] > 0; ++j) {
                    for (int k = 0; k < 5; ++k) {
                        num = prime[i - 1][j] * 10 + 2 * k + 1;
                        //System.out.println(num);

                        if ((prime[i][x] == 0) && (checkPrime(num))) {
                            prime[i][x] = num;
                            x++;
                        }
                    }
                }
            }
        }

        for(int i=0; prime[digit-1][i] > 0; ++i){
            System.out.println(prime[digit-1][i]);
        }
    }

    static boolean checkPrime(int num){
        for(int i = 2; i*i <= num; ++i){
            if(num % i == 0){
                return false;
            }
        }

        return true;
    }
}
