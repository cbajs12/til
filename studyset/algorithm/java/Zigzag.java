package Algorithm;

public class Zigzag {
    public static void main(String[] args) {
        int count = 1;
        boolean isOdd = true;

        while(count <= 100){
            if(isOdd){
                System.out.printf("%d\t",count);
                count++;
                if(count % 5 == 0){
                    System.out.printf("%d\n",count);
                    isOdd=false;
                    count += 5;
                }
            }else {
                System.out.printf("%d\t", count);
                count--;
                if (count % 5 == 1) {
                    System.out.printf("%d\n", count);
                    isOdd = true;
                    count += 5;
                }
            }
        }
    }
}
