package Algorithm;

import java.util.Scanner;

public class RandomMultiple {
    public static void main(String[] args) {
        System.out.println("1-1000 사이에서 선택한 수의 배수가 몇개고, 배수의 합은 얼마인가?");
        System.out.printf("1-1000 사이의 수 중에서 하나를 입력하세요 ==> ");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine().trim();
        int num = Integer.parseInt(number);

        if(num > 10000 && num < 0){
            return;
        }

        int count = 1000/num;
        int count2 = 0;

        int res = 0;
        int res2 = 0;

        for(int i = 1; i <= 1000; i++){
            if(i % num == 0){
                res2 += i;
                count2++;
            }
        }

        for(int i = 1; i <= count; i++){
            res += num * i;
        }

        System.out.printf("1부터 1000사이의 %d 의 배수의 개수 : %d, 배수의 합 : %d \n", num, count2, res2);

        System.out.printf("1부터 1000사이의 %d 의 배수의 개수 : %d, 배수의 합 : %d \n", num, count, res);


        scanner.close();
    }
}
