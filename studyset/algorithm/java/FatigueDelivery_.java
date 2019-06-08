package Algorithm;

import java.io.IOException;
import java.util.Scanner;

public class FatigueDelivery_ {
    static int UNCONNECTED = 9999;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        String[] data;
        int road, total;
        int[][] map;
        String[][] mold;
        int temp1, temp2, temp3;

        if (number < 1 || number > 20)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            data = temp.split(" ");
            road = Integer.parseInt(data[0]);
            if (road < 1 || road > 100000)
                return;

            total = Integer.parseInt(data[1]);
            if (total < 1 || total > 1000)
                return;

            mold = new String[road][3];
            for(int j = 0; j < road; ++j){
                temp = scanner.nextLine().trim();
                mold[j] = temp.split(" ");
            }

            map = new int[total+1][total+1];
            for(int j = 0; j < total; ++j){
                for(int k = 0; k < total; ++k){
                    map[j][k] = UNCONNECTED;
                }
            }

            for(int j = 0; j < road; ++j){
                temp1 = Integer.parseInt(mold[j][0]);
                temp2 = Integer.parseInt(mold[j][1]);
                temp3 = Integer.parseInt(mold[j][2]);
                if(temp3 < map[temp1-1][temp2-1]){
                    map[temp1-1][temp2-1] = temp3;
                    map[temp2-1][temp1-1] = temp3;
                }
            }

            for(int j = 0; j < total; ++j){
                for(int k = 0; k < total; ++k){
                    System.out.printf("%d ", map[j][k]);
                }
                System.out.printf("\n");
            }

            result[i] = findSolution(map, total);
        }

        for(int i : result)
            System.out.println(i);

        scanner.close();
    }

    static int findSolution(int[][] map, int total){
        int[] result = new int[total+1];
        int[] visited = new int[total+1];
        int min, u=0;

        for(int i = 0; i < total; ++i) {
            if (map[0][i] != UNCONNECTED && i != 0) {
                result[i] = map[0][i];
            } else {
                result[i] = UNCONNECTED;
            }
        }
        visited[0] = 1;

        for(int i = 0; i < total; ++i){
            min = UNCONNECTED;

            for(int j = 0; j < total; ++j){
                if(visited[j] == 0 && result[j] < min){
                    min = result[j];
                    u = j;
                }
            }

            visited[u] = 1;

            for(int k = 0; k < total; ++k){
                if(result[k] == UNCONNECTED){
                    if(map[u][k] != UNCONNECTED && visited[k] == 0){
                        result[k] = result[u] + map[u][k];
                    }
                }else{
                    if(map[u][k] != UNCONNECTED && visited[k] == 0 && (map[u][k] + result[u] < result[k])){
                        result[k] = result[u] + map[u][k];
                    }
                }
            }
        }

        return result[total-1];
    }
}
