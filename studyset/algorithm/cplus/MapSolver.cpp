#include "MapSolver.h"

int Solve(MazeInfo* Maze){
    int i=0;
    int j=0;
    int startFound = FAIL;
    int result = FAIL;

    Position start;

    for(i=0; i<Maze->RowSize; ++i){
        for(j=0; j<Maze->ColumnSize; ++j){
            if(Maze->Data[i][j] == START){
                start.X = i;
                start.Y = j;
                startFound = SUCCEED;
                break;
            }
        }
    }

    if(startFound == FAIL)
        return FAIL;

    if(MoveTo(Maze, &start, NORTH))
        result = SUCCEED;
    else if(MoveTo(Maze, &start, SOUTH))
        result = SUCCEED;
    else if(MoveTo(Maze, &start, EAST))
        result = SUCCEED;
    else if(MoveTo(Maze, &start, WEST))
        result = SUCCEED;

    Maze->Data[start.X][start.Y] = START;

    return result;
}

int MoveTo(MazeInfo* Maze, Position* Current, int Direction){
    int i=0;
    int dirs[] = {NORTH, SOUTH, WEST, EAST};

    Position next;

    if(Maze->Data[Current->X][Current->Y] == GOAL)
        return SUCCEED;

    Maze->Data[Current->X][Current->Y] = MARKED;

    for(i=0; i<4; ++i){
        if(GetNextStep(Maze, Current, dirs[i], &next) == FAIL)
            continue;

        if(MoveTo(Maze, &next, NORTH) == SUCCEED)
            return SUCCEED;
    }

    Maze->Data[Current->X][Current->Y] = WAY;

    return FAIL;
}

int GetNextStep(MazeInfo* Maze, Position* Current, int Direction, Position* Next){
    switch (Direction){
        case NORTH:
            Next->X = Current->X;
            Next->Y = Current->Y - 1;

            if(Next->Y == -1) return FAIL;

            break;
        case SOUTH:
            Next->X = Current->X;
            Next->Y = Current->Y + 1;

            if(Next->Y == Maze->RowSize) return FAIL;

            break;
        case WEST:
            Next->X = Current->X - 1;
            Next->Y = Current->Y;

            if(Next->X == -1) return FAIL;

            break;
        case EAST:
            Next->X = Current->X + 1;
            Next->Y = Current->Y;

            if(Next->X == Maze->ColumnSize) return FAIL;

            break;
    }

    if(Maze->Data[Next->X][Next->Y] == WALL) return FAIL;
    if(Maze->Data[Next->X][Next->Y] == MARKED) return FAIL;

    return SUCCEED;
}

int GetMaze(char* FilePath, MazeInfo* Maze) {
    int i = 0;
    int j = 0;
    int rowSize = 0;
    int columnSize = INIT_VALUE;

    FILE *fp;
    char buffer[MAX_BUFFER];

    if ((fp == fopen(FilePath, "r")) == NULL) {
        printf("Cannot open file:%s\n", FilePath);
        return FAIL;
    }

    while (fgets(buffer, MAX_BUFFER, fp) != NULL) {
        rowSize++;

        if (columnSize == INIT_VALUE) {
            columnSize = strlen(buffer) - 1;
        } else if (columnSize != strlen(buffer) - 1) {
            printf("Maze data in file:%s is not valid %d\n", FilePath, strlen(buffer));
            fclose(fp);
            return FAIL;
        }
    }

    Maze->RowSize = rowSize;
    Maze->ColumnSize = columnSize;
    Maze->Data = (char **) malloc(sizeof(char *) * rowSize);

    for (i = 0; i < rowSize; ++i)
        Maze->Data[i] = (char *) malloc(sizeof(char) * columnSize);

    rewind(fp);

    for (i = 0; i < rowSize; ++i) {
        fgets(buffer, MAX_BUFFER, fp);
        for (j = 0; j < columnSize; ++j) {
            Maze->Data[i][j] = buffer[j];
        }
    }

    fclose(fp);
    return SUCCEED;
}