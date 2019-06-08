package Algorithm;

public class Boggle {
    final char[][] board = new char[][]{
            {'U','R','L','P','M'},
            {'X','P','R','E','T'},
            {'G','I','A','E','T'},
            {'X','T','N','Z','Y'},
            {'X','O','Q','R','S'}
    };

    final int[] dx = new int[]{-1,-1,-1,1,1,1,0,0};
    final int[] dy = new int[]{-1,0,1,-1,0,1,-1,1};

    public boolean hasWord(int y, int x, String word){
        StringBuffer wordBuffer = new StringBuffer();
        wordBuffer.append(word);

        if(!inRange(y,x)) return false;

        if(board[y][x] != wordBuffer.charAt(0)) return false;

        if(wordBuffer.length() == 1) return true;

        for(int direction = 0; direction < 8; ++ direction){
            int nextY = y + dy[direction], nextX = x + dx[direction];

            if(hasWord(nextY, nextX, wordBuffer.substring(1))) return true;
        }

        return false;
    }

    public boolean inRange(int y, int x){
        if(y > 4 || y < 0){
            return false;
        }else if(x > 4 || x < 0){
            return false;
        }
        return true;
    }

    public void boggleMain(){
        Boggle boggle = new Boggle();
        boolean result = boggle.hasWord(2,0,"GIRL");
        System.out.println(result);
    }
}
