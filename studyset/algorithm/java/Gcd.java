package Algorithm;

import java.util.Scanner;

public class Gcd {
    public static void main(String[] args) {
        System.out.println("약수를 구할 두 정숫값을 입력하세요");
        Scanner scanner = new Scanner(System.in);
        System.out.printf("입력1 : ");
        String firstNum = scanner.nextLine().trim();
        System.out.printf("입력2 : ");
        String secondNum = scanner.nextLine().trim();

        int first = Integer.parseInt(firstNum);
        int second = Integer.parseInt(secondNum);
        int temp;

        if(first > second){
            int dec = first;
            int div = second;
            while(div != 0){
                temp = div;
                div = dec%div;
                dec = temp;
            }
            System.out.println("1");
            System.out.printf("%d 과 %d 의 최대공약수는 %d이다", first, second, dec);

        }else if(first < second){
            int dec = second;
            int div = first;
            while(div != 0){
                temp = div;
                div = dec%div;
                dec = temp;
            }
            System.out.println("2");
            System.out.printf("%d 과 %d 의 최대공약수는 %d이다", first, second, dec);

        }else{
            System.out.println("3");
            System.out.printf("%d 과 %d 의 최대공약수는 %d이다", first, second, first);
        }
        scanner.close();
    }
}
