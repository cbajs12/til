package Algorithm;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DisasterController {
    static int MAX = 10000;
    static int[][] Queue;
    static int Rear, Front;
    static int[][] Map;
    static int Column, Row, Movement;
    static int[][] Path;
    static int[][] path;
    static int Found;
    static int Res_n, Res_y, Res_x;
    static int Sty, Stx;
    static int X, Y;
    static String[] Res;
    static String[] Dir = new String[]{" ", "U", "L", "R", "D"};

    public static void main(String[] args) throws IOException {
        Initialize();
        SearchPath();
        Result();
    }

    static void Initialize() throws IOException {
        String filePath = new File("").getAbsolutePath();
        BufferedReader input = new BufferedReader(new FileReader(filePath + "/src/Algorithm/input/input_disaster.txt"));
        String str = input.readLine().trim();
        String[] temp = str.split(" ");

        Column = Integer.parseInt(temp[0]);
        Row = Integer.parseInt(temp[1]);
        Movement = Integer.parseInt(temp[2]);
        Map = new int[Column][Row];
        Path = new int[Column][Row];
        path = new int[Column][Row];
        Res = new String[MAX];
        Queue = new int[MAX][2];

        for(int k=0; k < Column; ++k){
            str = input.readLine().trim();
            temp = str.split("");

            InitArray(Map, temp, k);

//            for(int j = 0; j < Column ; ++j){
//                System.out.printf("%d",Map[k][j]);
//            }
//            System.out.printf("\n");
        }

        input.close();

    }

    static void InitArray(int[][] map, String[] temp, int column){
        for(int i=0; i < Row ; ++i){
            if(temp[i].equals("F")){
                map[column][i]= MAX;
            }else if(temp[i].equals("Y")){
                map[column][i]= 1;
                Sty = column;
                Stx = i;
            }else if(temp[i].equals("E")){
                map[column][i]= -1;
            }else{
                map[column][i]= 0;
            }
        }
    }

    static void SearchPath(){
        int bound;
        int count = 0;
        InsertData(Sty, Stx, 0, count+1);
        bound = Rear + 1;
        while(Rear >= Front){
            RemoveData();
            if(Front >= bound){
                count++;
                if((count > Movement) && ((count-1)% Movement== 0)){
                    System.out.println("in fire");
                    Fire();
                }
                System.out.println("out fire");
                bound = Rear + 1;
            }

            if(Map[Y][X] == MAX)
                continue;

            if((Y>0) && (Map[Y-1][X] <= 0))
                InsertData(Y-1, X, 4, count+2);

//            System.out.println(Found);

            if((Y<Column -1 ) && (Map[Y+1][X] <= 0))
                InsertData(Y+1, X, 1, count+2);

//            System.out.println(Found);

            if((X>0) && (Map[Y][X-1] <= 0))
                InsertData(Y, X-1, 3, count+2);

//            System.out.println(Found);

            if((X<Row-1) && (Map[Y][X+1] <= 0))
                InsertData(Y, X+1, 2, count+2);

//            System.out.println(Found);

            if(Found == -1){
                Res_n = count+1;
                break;
            }
        }
    }

    static void InsertData(int y, int x, int dir, int count){
        Queue[Rear][0] = y;
        Queue[Rear][1] = x;

        if(Map[y][x] == -1){
            Found = -1;
            Res_y = y;
            Res_x = x;
        }

        Map[y][x] = count;
        path[y][x] = dir;
        Rear++;

        views();
    }

    static void RemoveData(){
        Y = Queue[Front][0];
        X = Queue[Front][1];
        Front++;
    }

    static void Fire(){
        int i, j;
        for(i=0; i < Column; i++){
            for(j=0; j < Row; j++){
                if(Map[i][j] == MAX){
                    if(i > 0)
                        Path[i-1][j] = MAX;
                    if(j > 0)
                        Path[i][j-1] = MAX;
                    if(i < Column-1)
                        Path[i+1][j] = MAX;
                    if(j < Row-1)
                        Path[i][j+1] = MAX;
                }
            }
        }

        for(i=0; i < Column; i++) {
            for (j = 0; j < Row; j++) {
                if (Path[i][j] == MAX)
                    Map[i][j] = MAX;
            }
        }

//        int i, j;
//        for(i=0; i < Column; i++){
//            for(j=0; j < Row; j++){
//                if(Map[i][j] == MAX){
//                    if(i > 0)
//                        Map[i-1][j] = MAX;
//                    if(j > 0)
//                        Map[i][j-1] = MAX;
//                    if(i < Column-1)
//                        Map[i+1][j] = MAX;
//                    if(j < Row-1)
//                        Map[i][j+1] = MAX;
//                }
//            }
//        }

        for(int p = 0; p < Column ; ++p) {
            for (int k = 0; k < Row; ++k)
                System.out.printf("%d", Map[p][k]);

            System.out.printf("\n");
        }
    }

    static void Result(){
        System.out.println(Res_n);
        for(int i = Res_n ; i >= 0; i--){
            Res[i] = Dir[5 - path[Res_y][Res_x]];
            Next();
            if(path[Res_y][Res_x] == 0){
                break;
            }
        }
        for(String i : Res){
            if(i!=null)
                System.out.printf("%s",i);
        }

//        for(int j = 0; j < Column ; ++j){
//                System.out.printf("%d",Map[0][j]);
//        }
//        System.out.printf("\n");
    }

    static void Next(){
        int temp = path[Res_y][Res_x];
        switch (temp){
            case 1:
                Res_y--;
                break;
            case 2:
                Res_x--;
                break;
            case 3:
                Res_x++;
                break;
            case 4:
                Res_y++;

        }
    }


    static void views(){
//        for(int i =0; i < Queue.length ; ++i){
//            System.out.printf(Queue[i][0]);
//            System.out.printf(Queue[i][1]);
//        }

        for(int j = 0; j < Column ; ++j) {
            for (int k = 0; k < Row; ++k)
                System.out.printf("%d", Map[j][k]);

            System.out.printf("\n");
        }
    }
}
