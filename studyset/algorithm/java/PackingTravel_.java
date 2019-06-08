package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class PackingTravel_ {
    static int[][] cost;
    static int[] visited;
    static int fare, finalFare, traverseCounter, total;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        String[][] tempCost;

        if (number < 1 || number > 30)
            return;

        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            total = Integer.parseInt(temp);

            if (total < 1 || total > 12)
                return;

            tempCost = new String[total][total];
            for(int j = 0; j < total; ++j){
                temp = scanner.nextLine().trim();
                tempCost[j] = temp.split(" ");
            }

            cost = new int[total][total];
            for(int j = 0; j < total; ++j){
                for(int k = 0; k < total; ++k){
                    cost[j][k] = Integer.parseInt(tempCost[j][k]);
                }
            }

            visited = new int[total];
            fare = 0;
            finalFare = 99999;
            traverseCounter = 0;
            findSolution(1);
            System.out.println(finalFare);
        }

        scanner.close();
    }

    static void findSolution(int src){
        visited[src] = 1;
        traverseCounter++;

        if(traverseCounter == total && cost[src][1] != 0){
            if(finalFare > fare + cost[src][1])
                finalFare = fare + cost[src][1];

            traverseCounter--;
            visited[src] = 0;

            return;
        }

        for(int i=0; i < total; ++i){
            if(cost[src][i] != 0 && visited[i] == 0){
                if(finalFare < fare + cost[src][i])
                    continue;

                fare = fare + cost[src][i];
                findSolution(i);
                fare = fare - cost[src][i];
            }
        }

        visited[src] = 0;
        traverseCounter--;
        return;
    }
}
