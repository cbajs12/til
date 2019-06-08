package Algorithm;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SelfReplicating {
    public static void main(String[] args) throws IOException {
        String filePath = new File("").getAbsolutePath();
        BufferedReader input = new BufferedReader(new FileReader(filePath + "/src/Algorithm/input/input_replicate.txt"));
        String str = input.readLine().trim();
        int count = Integer.parseInt(str);
        int dnaTotal;
        int[][] dna;
        String[] temp;
        int flag, i, j, total;

        while(count > 0){
            str = input.readLine().trim();
            dnaTotal = Integer.parseInt(str);

            str = input.readLine().trim();
            temp = str.split(" ");
            dna = new int[2][dnaTotal];
            changeArray(temp, dna);
            flag = 0;
            total = 0;

            while(flag == 0){
                dna[flag+1][dnaTotal-2] = dna[flag][dnaTotal-1];

                for(i = dnaTotal-4 ; i >= 0 ; i-=2){
                    dna[flag+1][i] = dna[flag][i+1] - dna[flag+1][i+2];
                }

                j = i + 2;
                j = 1 - j;

                dna[flag+1][j] = dna[flag][j] - dna[flag+1][j+1];

                for(i = j + 2  ; i < dnaTotal ; i+=2){
                    dna[flag+1][i] = dna[flag][i-1] - dna[flag+1][i-2];
                }

                System.out.printf("result : ");
                for(int k = 0; k < dna[1].length; k++){
                    System.out.printf("%d", dna[1][k]);
                    dna[0][k] = dna[1][k];
                    if(dna[1][k] < 0){
                        flag++;
                    }
                }

                if(flag == 0){
                    total++;
                }

                if(total == 0){
                    total = -1;
                }

                System.out.printf("\n");
            }
            System.out.printf("total count : %d\n",total);
            System.out.println("=============================================");
            count--;
        }
        input.close();

    }

    static void changeArray(String[] arrayString, int[][] arrayInt){
        for(int i = 0; i < arrayString.length; ++i){
            arrayInt[0][i] = Integer.parseInt(arrayString[i]);
            arrayInt[1][i] = 0;
        }
    }
}
