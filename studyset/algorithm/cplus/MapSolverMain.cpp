#include <stdio.h>
#include "MapSolver.h"

int main(int argc, char* argv[]){
    int i=0;
    int j=0;

    MazeInfo maze;

    if(argc<2){
        printf("Usage: MapSolver <MazeFile>\n");
        return 0;
    }

    if(GetMaze(argv[1], &maze) == FAIL)
        return 0;

    if(Solve(&maze) == FAIL)
        return 0;

    for(i=0; i<maze.RowSize; ++i){
        for(j=0; j<maze.ColumnSize; ++j){
            printf("%c", maze.Data[i][j]);
        }
        printf("\n");
    }

    return 0;
}
