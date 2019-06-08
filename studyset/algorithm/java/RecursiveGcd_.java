package Algorithm;

import java.util.Scanner;

public class RecursiveGcd_ {
    static int count = 1;

    public static void main(String[] args) {
        System.out.println("최대공약수를 구하는 프로그램");
        System.out.println("2개의 수를 입력하세요");
        System.out.printf("입력 => ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        String[] inputs = input.split(" ");

        int num1 = Integer.parseInt(inputs[0]);
        int num2 = Integer.parseInt(inputs[1]);

        int result = Gcd(num1, num2);
        System.out.printf("%d와 %d의 최대공약수는 : %d\n", num1, num2, result);
    }

    public static int Gcd(int x, int y){
        System.out.printf("%d 회 실행한다.\n", count++);
        if(y != 0){
            System.out.printf("x : %d, y: %d\n", x, y);
            return Gcd(y, x%y);
        }else {
            return x;
        }
    }
}
