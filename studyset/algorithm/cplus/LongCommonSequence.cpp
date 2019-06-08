#include "LongCommonSequence.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int lcs(char* x, char* y, int i, int j, LCSTable* table){
    int m = 0;
    int n = 0;

    for(m=0; m<=i; ++m)
        table->data[m][0] = 0;

    for(n=0; n<=j; ++n)
        table->data[0][n] = 0;

    for(m=1; m<=i; ++m){
        for(n=1; n<=j; ++n){
            if(x[m-1] == y[n-1])
                table->data[m][n] = table->data[m-1][n-1] + 1;
            else{
                if(table->data[m][n-1] >= table->data[m-1][n])
                    table->data[m][n] = table->data[m][n-1];
                else
                    table->data[m][n] = table->data[m-1][n];
            }
        }
    }
    return table->data[i][j];
}

void lcsTraceBack(char* x, char* y, int m, int n, LCSTable* table, char* lcs){
    if(m == 0 || n == 0)
        return;

    if(table->data[m][n] > table->data[m][n-1] && table->data[m][n] > table->data[m-1][n] && table->data[m][n] > table->data[m-1][n-1]){
        char tempLcs[100];
        strcpy(tempLcs, lcs);
        sprintf(lcs, "%c%s", x[m-1], tempLcs);
        lcsTraceBack(x, y, m-1, n-1, table, lcs);
    }else if(table->data[m][n] > table->data[m-1][n] && table->data[m][n] == table->data[m][n-1]){
        lcsTraceBack(x, y, m, n-1, table, lcs);
    }else{
        lcsTraceBack(x, y, m-1, n, table, lcs);
    }
}

void lcsPrintTable(LCSTable* table, char* x, char* y, int lenX, int lenY){
    int i = 0;
    int j = 0;

    printf("%4s", "");

    for(i = 0; i<lenY+1; ++i)
        printf("%c ", y[i]);
    printf("\n");

    for(i=0; i<lenX+1; ++i){
        if(i==0)
            printf("%2s", "");
        else
            printf("%-2c", x[i-1]);

        for(j=0; j<lenY+1; ++j){
            printf("%d ", table->data[i][j]);
        }
        printf("\n");
    }
}

int main(void){
    char* x = "Good Morning.";
    char* y = "Guten Morgen.";
    char* result;

    int lenX = strlen(x);
    int lenY = strlen(y);

    int i=0;
    int j=0;
    int length=0;

    LCSTable table;

    table.data = (int**)malloc(sizeof(int*) * (lenX+1));

    for(i=0; i<lenX+1; ++i){
        table.data[i] = (int*)malloc(sizeof(int) * (lenY+1));
        memset(table.data[i], 0 , sizeof(int) * (lenY+1));
    }

    length = lcs(x, y, lenX, lenY, &table);

    lcsPrintTable(&table, x, y, lenX, lenY);

    result = (char*)malloc(sizeof(table.data[lenX][lenY] + 1));
    sprintf(result, "");

    lcsTraceBack(x, y, lenX, lenY, &table, result);
    printf("\n");
    printf("Lcs : \"%s\" (Length:%d) \n", result, length);

    return 0;
}
