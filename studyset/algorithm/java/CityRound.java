package Algorithm;


import java.io.IOException;
import java.util.Scanner;

import static java.lang.Math.abs;

public class CityRound {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        int total;
        int[] cities;

        if (number < 1 || number > 30)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            total = Integer.parseInt(temp);

            if (total < 1 || total > 50000)
                return;

            cities = new int[total];
            for(int j = 0; j < total; ++j){
                temp = scanner.nextLine().trim();
                cities[j] = Integer.parseInt(temp);
            }

            result[i] = findSolution2(cities);
        }

        for(int i : result)
            System.out.println(i);

        scanner.close();
    }

    static int findSolution(int[] cities){
        int now;
        int result = 0;

        for(int i =0; i < cities.length; ++i){
            now = cities[i];
            for(int j=0; j < cities.length; ++ j){
                result = result + abs(now-cities[j]);
            }
        }
        return result;
    }

    static int findSolution2(int[] cities){
        int[] location = new int[5000];
        for(int i=0; i<cities.length; ++i){
            location[cities[i]]++;
        }

        int number = 0, preLocation =0;
        int sum = 0, result = 0;

        for(int i=0; i<location.length; ++i){
            if(location[i] == 1){
                sum = sum + number * (i - preLocation);
                result = result + sum;
                preLocation = i;
                number++;

                if(number == cities.length)
                    break;
            }
        }
        return 2*result;
    }
}
