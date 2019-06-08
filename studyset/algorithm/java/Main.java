import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayList<String> arrayList = new ArrayList<>();
        StringTokenizer stringTokenizer= new StringTokenizer(input, " ");
        while(stringTokenizer.hasMoreTokens()){
            arrayList.add(stringTokenizer.nextToken());
        }
        for(String i : arrayList){
            System.out.println(i);
        }
        BigDecimal b1 = new BigDecimal(arrayList.get(0));
        BigDecimal b2 = new BigDecimal(arrayList.get(1));
        BigDecimal b3 = b1.divide(b2, 9, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(b3.toString());

    }

}

