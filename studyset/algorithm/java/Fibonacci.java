package Algorithm;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println("피보나치 수열을 구해보자");
        int first = 0;
        int second = 1;
        int count = 0;
        int res;

        while(count < 12) {
            res = first + second;
            System.out.printf("%d\n", res);
            first = second;
            second = res;
            count += 1;
        }
    }
}
