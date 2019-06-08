package Algorithm;

import java.util.Scanner;

public class DecimalToHex {
    public static void main(String[] args) {
        System.out.println("10진수>16진수 변환 프로그램이다");
        System.out.println("10진수를 16진수를 바꾸려면 [A] 키를 누르고,");
        System.out.println("16진수를 10진수로 바꾸려면 [B] 키를 누르세요.");
        System.out.println("a나 b를 누르세요");
        Scanner scanner = new Scanner(System.in);
        String button = scanner.nextLine();
        button = button.toLowerCase();

        if(button.equals("a")){
            System.out.println("변환할 숫자를 입력하세요");
            Scanner scanner1 = new Scanner(System.in);
            String decimalStr = scanner1.nextLine();
            int decimalNum = Integer.parseInt(decimalStr.trim());
            String hexStr = Integer.toHexString(decimalNum);

            System.out.printf("10진수의 값 %s --> 16진수 값 : %s\n",decimalStr, hexStr);
            scanner1.close();

        }else if(button.equals("b")){
            System.out.println("변환할 숫자를 입력하세요");
            Scanner scanner2 = new Scanner(System.in);
            String hexStr = scanner2.nextLine();
            int decimalNum = Integer.parseInt(hexStr.trim(), 16);

            System.out.printf("16진수의 값 %s --> 10진수 값 : %d\n",hexStr, decimalNum);
            scanner2.close();

        }else{
            System.out.println("잘못된 입력");
            return;
        }
        scanner.close();
    }
}

