#include "copy.h"

extern char line[MAXLINE];
extern char longest[MAXLINE];

void copy(char from[], char to[]){
	int i = 0;
	while((to[i] = from[i]) != '\0') ++i;
}
