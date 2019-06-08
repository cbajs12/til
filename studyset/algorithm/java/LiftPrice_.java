package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class LiftPrice_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        int[] priceTable = new int[10];
        String[] prices;
        int days;

        if (number < 1 || number > 30)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            prices = temp.split(" ");
            int k=0;

            for(String j : prices){
                priceTable[k] = Integer.parseInt(j);

                if (priceTable[k] < 1 || priceTable[k] > 500000)
                    return;

                k++;
            }

            temp = scanner.nextLine().trim();
            days = Integer.parseInt(temp);

            if (days < 1 || days > 10000)
                return;

            result[i] = findSolution(priceTable, days);
        }

        for(int i : result)
            System.out.println(i);

        scanner.close();
    }

    static int findSolution(int[] prices, int days){
        int[] optimalDay = new int[10000];

        for(int i=0; i < days; ++i){
            optimalDay[i] = (i+1)*prices[0];
        }

        for(int i=1; i < 10; ++i){
            int fixedDayPay = prices[i];
            if(optimalDay[i] > fixedDayPay)
                optimalDay[i] = fixedDayPay;

            for(int j = i+1; j < days; ++j){
                if(optimalDay[j] > (fixedDayPay + optimalDay[j-i-1]))
                    optimalDay[j] = (fixedDayPay + optimalDay[j-i-1]);
            }
        }

        return optimalDay[days-1];
    }
}
