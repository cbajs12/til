#ifndef CPLUS_LONGCOMMONSEQUENCE_H
#define CPLUS_LONGCOMMONSEQUENCE_H

typedef struct structLCSTable{
    int** data;
} LCSTable;

int lcs(char* x, char* y, int i, int j, LCSTable* table);
void lcsTraceBack(char* x, char* y, int m, int n, LCSTable* table, char* lcs);
void lcsPrintTable(LCSTable* table, char* x, char* y, int lenX, int lenY);

#endif //CPLUS_LONGCOMMONSEQUENCE_H
