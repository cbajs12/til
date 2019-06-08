package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class FixingFence_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        String[] data;
        int board, total, left;
        int[] list;

        if (number < 1 || number > 10)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            data = temp.split(" ");

            board = Integer.parseInt(data[0]);
            if (board < 1 || board > 50)
                return;

            total = Integer.parseInt(data[1]);
            if (total < 1 || total > 200)
                return;
            left = Integer.parseInt(data[2]);
            if (left < 1 || left > 200)
                return;

            if(total < left)
                return;

            list = new int[left];
            for(int j = 0; j < left; ++j){
                temp = scanner.nextLine().trim();
                list[j] = Integer.parseInt(temp);
            }

            result[i] = findSolution(list, board, left);
        }

        for(int i : result)
            System.out.println(i);

        scanner.close();
    }

    static int findSolution(int[] list, int board, int left){
        list = bubbleSort(list, left);
        int[] diff = new int[left];
        int sum=0, max;

        for(int i = 0; i < left; ++i){
            if(i+1 == left)
                continue;

            diff[i] = (list[i+1] - list[i] - 1);
        }

        diff = bubbleSort(diff, left - 1);

//        for(int i = 0; i < diff.length; ++i){
//            System.out.println(diff[i]);
//        }

        for(int i = 0; i < board; ++i){
            if(i >= left)
                break;

            sum += diff[left - i - 2];
        }

        max = list[left-1] - list[0] + 1;

        return max-sum;
    }

    static int[] bubbleSort(int[] data, int n){
        int temp;

        for(int i=0; i < n; ++i){
            for(int j=0; j < n - i - 1; ++j){
                if(data[j] > data[j+1]){
                    temp = data[j];
                    data[j] = data[j+i];
                    data[j+1] = temp;
                }
            }
        }

        return data;
    }
}
