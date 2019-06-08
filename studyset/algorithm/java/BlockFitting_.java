package Algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlockFitting_ {
    static int MAX = 100;
    static int[][] Map;
    static int[][] Result;
    static int Column, Row, BlockTotal;
    static int[][] Blocks;
    static Node[][] PositionList = new Node[MAX+1][MAX+1];
    static Node[][] Header = new Node[MAX+1][MAX+1];
    static int[][] CounterBoard = new int[MAX+1][MAX+1];
    static int[][] Visited = new int[MAX+1][MAX+1];
    static int[][] Board = new int[MAX][MAX+1];
    static boolean FOUND = false;
    static int RC = 0;
    static int[][] Dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int Res_n;

    public static void main(String[] args) throws IOException {
        initialize();
        preprocess();
        findSolution();
        outResult();
    }

    static void initialize() throws IOException {
        String filePath = new File("").getAbsolutePath();
        BufferedReader input = new BufferedReader(new FileReader(filePath + "/src/Algorithm/input/input_block.txt"));
        String str = input.readLine().trim();
        int count = Integer.parseInt(str);
        List<String> stringArray = new ArrayList<>();
        String[] temp;
        BlockTotal = count * (count+1) / 2;
//        Blocks = new int[BlockTotal][2];

//        initBlock(count);

        while( (str = input.readLine()) != null){
            stringArray.add(str);
        }
        Column = stringArray.size();
//        System.out.println(Column);
        temp = stringArray.get(0).split(" ");
        Row = temp.length;

        Result = new int[Column][Row];
        Map = new int[MAX][MAX+1];

        for(int i=0; i<Column; ++i){
            temp = stringArray.get(i).split(" ");
            for(int j=0; j<Row; ++j){
                Board[i][j]=Integer.parseInt(temp[j]);
            }
        }

        for(int i=0; i<MAX+1; ++i){
            for(int j=0; j<MAX+1; ++j){
                PositionList[i][j]= new Node();
                Header[i][j] = new Node();
            }
        }

//        for(int i=0; i<Column; ++i){
//            for(int j=0; j<Row; ++j)
//                System.out.printf("%d",Map[i][j]);
//            System.out.printf("\n");
//        }

        input.close();
    }

    static void initBlock(int count){
        int k=0;

        for (int i = 0; i < count; ++i) {
            for (int j = i; j < count; ++j) {
                if(k==BlockTotal)
                    return;

                Blocks[k][0] = i + 1;
                Blocks[k][1] = j + 1;
                k++;
            }
        }

//        for(int i=0; i < Blocks.length; ++i){
//            System.out.printf("%d",Blocks[i][0]);
//            System.out.printf("%d",Blocks[i][1]);
//            System.out.printf("\n");
//        }
    }

    static boolean out(int y, int x){
        if((y < 0) || (x < 0) || (y > Column) || (x > Row)){
            return true;
        }else{
            return false;
        }

    }

    static void addPositionList(int p, int q, int y1, int x1, int y2, int x2){
        int temp;

        if(p > q){
            temp = p;
            p = q;
            q = temp;
        }

        CounterBoard[p][q]++;
//        System.out.println(PositionList[p][q].getX1());
        PositionList[p][q].next = new Node();
        PositionList[p][q] = PositionList[p][q].next;

        PositionList[p][q].setY1(y1);
        PositionList[p][q].setX1(x1);
        PositionList[p][q].setY2(y2);
        PositionList[p][q].setX2(x2);
        PositionList[p][q].next = null;
    }

    static void preprocess(){
        int p,q;

        for(int i=0; i<Column; ++i){
            for(int j=i; j<Column; ++j){
                PositionList[i][j] = new Node();
                PositionList[i][j].next = null;
                Header[i][j] = PositionList[i][j];
            }
        }

        for(int i=0; i<Column; ++i){
            p = Board[i][0];
            for(int j=1; j < Row; ++j){
                q = Board[i][j];
                addPositionList(p, q, i, j-1, i, j);
                p = q;
            }
        }

        for(int i=0; i<Row; ++i){
            p = Board[0][i];
            for(int j=1; j < Column; ++j){
                q = Board[j][i];
                addPositionList(p, q, j-1, i, j, i);
                p = q;
            }
        }

//        for(int i=0; i<MAX+1; ++i){
//            for(int j=i; j<Column; ++j){
//                System.out.println();
//            }
//        }

    }

    static void increase(int n1, int n2){
        if(n1 < n2) {
            CounterBoard[n1][n2]++;
        } else {
            CounterBoard[n2][n1]++;
        }
    }

    static void decrease(int n1, int n2){
        if(n1 < n2){
            CounterBoard[n1][n2]--;
        }else{
            CounterBoard[n2][n1]--;
        }
    }

    static void outResult(){
        if(!FOUND)
            System.out.println("Impossible");

        for(int i=0; i<Column; ++i){
            for(int j=0; j<Row; ++j){
                System.out.printf("%d", Result[i][j]);
            }
            System.out.printf("\n");
        }
    }

    static void findSolution(){
        int p, q, min;
        Node node;
        int x1, y1, x2, y2;
        int xx, yy;
        RC++;

        p = q = - 1;

        if(RC == MAX*(MAX+1) /2){
            FOUND = true;
            outResult();
            return;
        }

        min = MAX * MAX * 2 + 1;

        for(int i=0; i<Column; ++i){
            for(int j=i; j<Column; ++j){
                if((Visited[i][j] == 0) && (CounterBoard[i][j] < min) ){
                    min = CounterBoard[i][j];
                    p = i;
                    q = j;
                }
            }
        }

        if(min == 0)
            return;

        if(p<0 || q<0){
            FOUND = true;
            outResult();
            return;
        }

        node = Header[p][q];
        Visited[p][q] = 1;

        while(node.next != null){
            node = node.next;
            y1 = node.getY1();
            x1 = node.getX1();
            y2 = node.getY2();
            x2 = node.getX2();

            if(Result[y1][x1]==0 || Result[y2][x2]==0)
                continue;

            for(int i=0; i<4; i++){
                yy = y1 + Dir[i][0];
                xx = x1 + Dir[i][1];

                if((out(yy, xx)) || (yy == y2) && (xx == x2))
                    continue;

                if(Result[yy][xx] != 0)
                    decrease(Board[y1][x1], Board[yy][xx]);
            }

            for(int i=0; i<4; i++){
                yy = y2 + Dir[i][0];
                xx = x2 + Dir[i][1];

                if((out(yy, xx)) || (yy == y1) && (xx == x1))
                    continue;

                if(Result[yy][xx] != 0)
                    decrease(Board[y2][x2], Board[yy][xx]);
            }

            Res_n++;
            Result[y1][x1] = Res_n;
            Result[y2][x2] = Res_n;

            findSolution();

            Res_n--;
            Result[y1][x1] = 0;
            Result[y2][x2] = 0;

            for(int i=0; i<4; i++){
                yy = y1 + Dir[i][0];
                xx = x1 + Dir[i][1];

                if((out(yy, xx)) || (yy == y2) && (xx == x2))
                    continue;

                if(Result[yy][xx] != 0)
                    increase(Board[y1][x1], Board[yy][xx]);
            }

            for(int i=0; i<4; i++){
                yy = y2 + Dir[i][0];
                xx = x2 + Dir[i][1];

                if((out(yy, xx)) || (yy == y1) && (xx == x1))
                    continue;

                if(Result[yy][xx] != 0)
                    increase(Board[y2][x2], Board[yy][xx]);
            }
        }
        Visited[p][q] = 0;
    }
}

class Node{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    Node next;

    public Node() {
        this.x1 = 0;
        this.y1 = 0;
        this.x2 = 0;
        this.y2 = 0;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }
}
