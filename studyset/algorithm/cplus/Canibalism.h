#ifndef CPLUS_CANIBALISM_H

#define CPLUS_CANIBALISM_H
#define SIZE 256
typedef struct {
    int num_Researchers;
    int num_Cannibals;
    int boat;
    int counter;
} NODE;

int N, M, Solution;
NODE Queue[SIZE];
int flag[11][11][2];

int front, rear;

void initQueue();
int fullQueue();
int emptyQueue();
void enQueue(NODE node);
NODE deQueue();
int checkStatus(NODE n);

#endif //CPLUS_CANIBALISM_H
