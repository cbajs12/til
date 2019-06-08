package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class InvestStartup_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        String[] data;
        int money, total;
        String[][] list;
        int[][] database;

        if (number < 1 || number > 20)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            data = temp.split(" ");
            money = Integer.parseInt(data[0]);
            total = Integer.parseInt(data[1]);

            list = new String[money][total+1];
            for(int j = 0; j < money; ++j){
                temp = scanner.nextLine().trim();
                list[j] = temp.split(" ");
            }

            database = new int[money][total+1];
            for(int j = 0; j < money; ++j){
                for(int k = 0; k <= total; ++k){
                    database[j][k] = Integer.parseInt(list[j][k]);
//                    System.out.printf("%d ", database[j][k]);
                }
//                System.out.printf("\n");
            }

            result[i] = findSolution(database, money, total);
        }

        for(int i : result)
            System.out.println(i);

        scanner.close();
    }

    static int findSolution(int[][] data, int money, int total){
        int[] result = new int[money+1];

        for(int i = 0; i < total; ++i){
            for(int j = money-1; j >= 0; --j){
                int max = -1;

                for(int k = 0; k < money; ++k){
                    if(j - k >= 0){
                        max = getMax(max, (result[j-k] + data[k][i+1]));
                    }

                    result[j] = getMax(result[j], max);
                }
            }
        }
        return result[money-1];
    }

    static int getMax(int p, int q){
        if(p > q){
            return p;
        }else{
            return q;
        }
    }
}
