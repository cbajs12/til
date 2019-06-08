package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class TruckAllocation {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int local, q;
        String[] strArray;
        int[] locals;
        String[] qs;

        if (number < 1 || number > 10)
            return;

        for(int i = 0; i < number; ++i){
            temp = scanner.nextLine().trim();
            strArray = temp.split(" ");
            local = Integer.parseInt(strArray[0]);
            if (local < 1 || local > 25000)
                return;

            q = Integer.parseInt(strArray[1]);
            if (q < 1 || q > 25000)
                return;

            locals = new int[local];
            for(int j=0; j<local; ++j){
                temp = scanner.nextLine().trim();
                locals[j] = Integer.parseInt(temp);
                if (locals[j] < 1 || locals[j] > 10000000)
                    return;
            }

            qs = new String[q];
            for(int j=0; j<q; ++j){
                temp = scanner.nextLine().trim();
                qs[j] = temp;
            }


            findSolution(locals, qs);
        }

//        for(int i : result){
//            System.out.println(i);
//        }

        scanner.close();
    }

    static void findSolution(int[] locals, String[] qs){
        String[] temp;
        int[] tempArray = new int[2];
        int low;

        for(int i=0; i<qs.length; ++i){
            low = 1000000000;
            temp = qs[i].split(" ");
            for(int j=0; j<temp.length; ++j){
                tempArray[j] = Integer.parseInt(temp[j]);
            }

            for(int j=tempArray[0]-1; j<tempArray[1]; ++j ){
                if(low > locals[j]){
                    low = locals[j];
                }
            }

            System.out.println(low);
        }
    }
}
