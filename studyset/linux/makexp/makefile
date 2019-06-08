#this makefile is for compilation of copy application
CC=gcc
CFLAG=-c-Wall

main: main.o copy.o
	$(CC) -g -o main main.o copy.o
main.o: main.c copy.h
	$(CC) $(CFLAG) main.c
copy.o: copy.c copy.h
	$(CC) $(CFLAG) copy.c
clean:
	rm *o main
