package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class IrreducibleFraction {
    static int CNT;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine().trim();
        int number = Integer.parseInt(num);
        String[] numArray = new String[number];
        int temp;
        for(temp=0; temp<number; ++temp){
            numArray[temp] = scanner.nextLine().trim();
        }

        for(String k : numArray){
            temp = Integer.parseInt(k);
            findSolution(temp);
        }

        scanner.close();
    }

    static void findSolution(int num){
        CNT = 0;
        int temp;
        for(int i=1; i<=num; ++i){
            for(int j=2; j<=num; ++j){
                temp = gcd(i,j);
                if(temp == 1 && i < j){
                    System.out.printf("찾은 기약분수 : %d/%d \n", i, j);
                    CNT++;
                }
            }
        }
        System.out.printf("총 %d개의 기약 분수가 존재합니다\n", CNT);
    }

    static int gcd(int num1, int num2){
        if(num2 == 0){
            return num1;
        }

        return gcd(num2, num1%num2);
    }
}
