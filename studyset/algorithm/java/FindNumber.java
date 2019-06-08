package Algorithm;

import java.util.Random;
import java.util.Scanner;

public class FindNumber {
    public static void main(String[] args) {
        System.out.println("0 ~ 9의 숫자를 입력하세요");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine().trim();
        int num = Integer.parseInt(number);

        if(num < 10 && num >= 0){
            Random random = new Random();
            int randomNum = random.nextInt(10);
            int count = 1;
            Scanner scanner1 = new Scanner(System.in);

            while(true){
                System.out.printf("[%d 번째 도전] : ", count);
                System.out.printf("%d\n", num);

                if(num == randomNum){
                    System.out.printf("정확하다 %d 번만에 맞히셨군요!\n", count);
                    scanner1.close();
                    return;
                }else if(num > randomNum){
                    System.out.printf("%d 보다 작습니다.\n", num);
                }else{
                    System.out.printf("%d 보다 큽니다.\n", num);
                }

                String number1 = scanner1.nextLine().trim();
                num = Integer.parseInt(number1);
                count += 1;
            }

        }else{
            System.out.println("잘못된 입력");
        }

        scanner.close();

    }
}
