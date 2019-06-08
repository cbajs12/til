package Algorithm;

public class PrimeNumber_ {
    public static void main(String[] args) {
        System.out.println("1-1000 사이의 수 중에서 소수를 구하는 프로그램");
        int count = 1;
        int j;

        System.out.printf("%d\t", 1);

        for(int i=1; i <= 1000; i++){
            for (j = 2; j < i; j++) {
                if (i % j == 0)
                    break;
            }

            if(i == j){
                System.out.printf("%d\t", i);
                count++;

                if(count%8 == 0)
                    System.out.printf("\n");
            }
        }

        System.out.printf("\n");
        System.out.printf("1부터 1000 사이의 소수는 %d 개이다. \n", count);
    }
}
