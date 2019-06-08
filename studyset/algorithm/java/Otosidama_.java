package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class Otosidama_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        String[] data;
        int N, K;

        if (number < 1 || number > 20)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            data = temp.split(" ");
            N = Integer.parseInt(data[0]);
            K = Integer.parseInt(data[1]);

            if (N < 1 || N > 1000)
                return;

            if (K < 1 || K > 100)
                return;

            result[i] = findSolution(N, K);
        }

        for (int i : result)
            System.out.println(i);

        scanner.close();
    }

    static int findSolution(int N, int K){
        int MAX = 100000000;
        int[] result = new int[N+1];

        result[0] = 1;

        for(int i = 1; i <= K; ++i){
            for(int j = i; j <= N; ++j){
                result[j] = (result[j] + result[j-i]) % MAX;
            }
        }

        return result[N];
    }

}
