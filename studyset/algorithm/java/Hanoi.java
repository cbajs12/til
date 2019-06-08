package Algorithm;

import java.io.IOException;
import java.util.Scanner;

public class Hanoi {
    static int Cnt;

    public static void main(String[] args) throws IOException {
        System.out.printf("이동할 원판 수를 입력하세요 : ");
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine().trim();
        int number = Integer.parseInt(num);

        hanoi(number, 1, 2);
        System.out.printf("총 이동횟수 : %d\n",Cnt);

        scanner.close();
    }

    static void hanoi(int n, int a, int b){
        int temp;

        if(n==1){
            System.out.printf("원판 %d을(를) %d에서 %d로 이동한다\n",n,a,b);
        }else{
            temp = 6 - a - b;
            hanoi(n-1, a, temp);
            System.out.printf("원판 %d을(를) %d에서 %d로 이동한다\n",n,a,b);
            hanoi(n-1, temp, b);
        }
        Cnt++;
    }
}
