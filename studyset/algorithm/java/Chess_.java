package Algorithm;


import java.io.IOException;
import java.util.Scanner;

public class Chess_ {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine().trim();
        int number = Integer.parseInt(temp);
        int[] result;
        String[] data;
        int column, row;
        int[][] map;
        int xMove, yMove, xStand, yStand;

        if (number < 1 || number > 20)
            return;

        result = new int[number];
        for (int i = 0; i < number; ++i) {
            temp = scanner.nextLine().trim();
            data = temp.split(" ");
            column = Integer.parseInt(data[0]);
            row = Integer.parseInt(data[1]);

            if (column < 1 || column > 50)
                return;

            if (row < 1 || row > 50)
                return;

            map = new int[column+1][row+1];
            temp = scanner.nextLine().trim();
            data = temp.split(" ");
            xMove = Integer.parseInt(data[0]);
            yMove = Integer.parseInt(data[1]);
            xStand = Integer.parseInt(data[2]);
            yStand = Integer.parseInt(data[3]);

            result[i] = findSolution(map, xMove, yMove, xStand, yStand, column, row);
        }

        for (int i : result)
            System.out.println(i);

        scanner.close();
    }

    static int findSolution(int[][] map, int x1, int y1, int x2, int y2, int column, int row){
        int[] move_X = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] move_Y = {-2, -1, 1, 2, 2, 1, -1, -2};
        int top = 0, bottom = 0;
        int[][] queue = new int[10005][3];
        int[] out;
        int[] in = new int[3];
        int x, y, temp;

        temp = top++;
        queue[temp][0] = x1;
        queue[temp][1] = y1;
        queue[temp][2] = 0;

        while(top != bottom){
            out = queue[bottom++];
//            System.out.printf("%d %d %d\n", out[0], out[1],out[2]);
            for(int i=0; i < move_X.length; ++i){
                x = out[0] + move_X[i];
                y = out[1] + move_Y[i];

                //add new position in queue
                if((x >= 1 && x <= column) && (y >= 1 && y <= row) && (map[x][y] == 0)){
                    in[0] = x;
                    in[1] = y;
                    in[2] = out[2] + 1;
                    queue[top++] = in;
                    map[x][y] = 1;
//                    System.out.printf("%d %d\n", x, y);
                }
            }
            if(map[x2][y2] == 1){
                break;
            }
        }

        if(map[x2][y2] == 1){
            return in[2];
        }
        return -1;
    }
}
